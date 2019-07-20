package components;

public class Action {
	private Board chessBoard;
	player player1,player2;

	public Action(Board board,player play1,player play2) {
		chessBoard=board;
		player1=play1;
		player2=play2;
	}
	public Board getChessBoard() {
		return chessBoard;
	}

	public void setChessBoard(Board chessBoard) {
		this.chessBoard = chessBoard;
	}
	
	public player getPlayer1() {
		return player1;
	}

	public void setPlayer1(player player1) {
		this.player1 = player1;
	}

	public player getPlayer2() {
		return player2;
	}

	public void setPlayer2(player player2) {
		this.player2 = player2;
	}

	//��ҽ�δ���������ϵ����ӷ��������ϣ����Χ��
	public void putPiece(player play,piece p,Position pos)throws Exception{
		if(!chessBoard.check(pos.getX(), pos.getY())){
			throw new Exception("position�������̷�Χ��");
		}
		else if(chessBoard.getBoardPiece(pos.getX(), pos.getY())!=null) {
			throw new Exception("��λ���������ӣ�");
		}
		else if(p.getPieceState()==1) {
			throw new Exception("�������������ϣ�");
		}
		else {
			//����������ӵ�����
			p.setPieceX(pos.getX());
			p.setPieceY(pos.getY());
			p.setPieceState(1);
			if(p==null)
				System.out.println("p is null");
			play.add_piece(p);
			chessBoard.setBoardPiece(pos.getX(), pos.getY(), p);//������p����ָ��λ��
			play.addHistory(String.format("%s put a piece %s in (%d,%d)\n",
					play.getPlayerName(),p.getPieceName(),pos.getX(),pos.getY()));
		}
	}
	
	//����ƶ����ӣ����������˵
	public void movePiece(player play,Position old_pos,Position new_pos) throws Exception{
		piece p1=chessBoard.getBoardPiece(old_pos.getX(), old_pos.getY());
		if((!play.judgeOwnPiece(p1))) {
			throw new Exception("λ�ù�������ȷ");
		}
		if(p1==null) {
			throw new Exception("��λ��û������");
		}
		piece pNew=new piece(p1.getPieceName(),1,new_pos.getX(),new_pos.getY());
		//p1.setPieceState(0);
		chessBoard.setBoardPiece(old_pos.getX(),old_pos.getY(),null);
		chessBoard.setBoardPiece(new_pos.getX(), new_pos.getY(),pNew);
		play.getPlayerPieces().remove(p1);
		play.getPlayerPieces().add(pNew);
		play.addHistory(String.format("%s move piece %s from (%d,%d) to (%d,%d)!", 
		play.getPlayerName(),pNew.getPieceName(),old_pos.getX(), old_pos.getY(),old_pos.getX(), old_pos.getY()));
	}
	
	//���player�Ƴ��Է�����
	public void removePiece(player player,Position pos)throws Exception{
		piece p=chessBoard.getBoardPiece(pos.getX(), pos.getY());
		if(player.judgeOwnPiece(p))
		{
			throw new Exception("piece���ǶԷ����ӣ�");
		}
		else if(!chessBoard.check(pos.getX(), pos.getY())){
			throw new Exception("position�������̷�Χ��");
		}
		else if(p==null) {
			throw new Exception("��λ��û�����ӣ�");
		}
		else {
			p.remove();
			chessBoard.setBoardPiece(pos.getX(), pos.getY(),null);
			player.addHistory(String.format("���%s�Ƴ��˶Է�������%s", player.getPlayerName(),p.getPieceName()));
		}
	}
	
	//
	public void eatPiece(player play,Position pos1,player play2,Position pos2)throws Exception{
		piece p1=chessBoard.getBoardPiece(pos1.getX(), pos1.getY());
		piece p2=chessBoard.getBoardPiece(pos2.getX(), pos2.getY());
		
		if(!chessBoard.check(pos1.getX(), pos1.getY())||
			!chessBoard.check(pos2.getX(), pos2.getY())) {
			throw new Exception("λ�ó������̷�Χ");
		}
		if(!play.judgeOwnPiece(p1)||play.judgeOwnPiece(p2)) {
			throw new Exception("���ӹ�������ȷ");
		}
		if(p1==null||p2==null) {
			throw new Exception("���Ӳ�����");
		}
		piece new_piece=new piece(p1.getPieceName(),1,pos2.getX(),pos2.getY());
		chessBoard.setBoardPiece(pos1.getX(), pos1.getY(),null);
		chessBoard.setBoardPiece(pos2.getX(), pos2.getY(), new_piece);
		chessBoard.setBoardPieceState(pos2.getX(), pos2.getY(),2);
		
		play.getPlayerPieces().remove(p1);//�д�ȷ��
		new_piece.setPieceState(1);
		play.add_piece(new_piece);
		play2.getPlayerPieces().remove(p2);
		
		play.addHistory(String.format("%s use piece %s in (%d,%d) "
				+ "eat piece %s in (%d,%d)", 
				play.getPlayerName(),p1.getPieceName(),p1.getPieceX(),p1.getPieceY(),
				p2.getPieceName(),p2.getPieceX(),p2.getPieceY()));
		
	}
}
