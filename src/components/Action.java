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

	//玩家将未处于棋盘上的棋子放在棋盘上，针对围棋
	public void putPiece(player play,piece p,Position pos)throws Exception{
		if(!chessBoard.check(pos.getX(), pos.getY())){
			throw new Exception("position超出棋盘范围！");
		}
		else if(chessBoard.getBoardPiece(pos.getX(), pos.getY())!=null) {
			throw new Exception("该位置已有棋子！");
		}
		else if(p.getPieceState()==1) {
			throw new Exception("棋子已在棋盘上！");
		}
		else {
			//设置玩家棋子的属性
			p.setPieceX(pos.getX());
			p.setPieceY(pos.getY());
			p.setPieceState(1);
			if(p==null)
				System.out.println("p is null");
			play.add_piece(p);
			chessBoard.setBoardPiece(pos.getX(), pos.getY(), p);//将棋子p放在指定位置
			play.addHistory(String.format("%s put a piece %s in (%d,%d)\n",
					play.getPlayerName(),p.getPieceName(),pos.getX(),pos.getY()));
		}
	}
	
	//玩家移动棋子，针对象棋来说
	public void movePiece(player play,Position old_pos,Position new_pos) throws Exception{
		piece p1=chessBoard.getBoardPiece(old_pos.getX(), old_pos.getY());
		if((!play.judgeOwnPiece(p1))) {
			throw new Exception("位置归属不正确");
		}
		if(p1==null) {
			throw new Exception("该位置没有棋子");
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
	
	//玩家player移除对方棋子
	public void removePiece(player player,Position pos)throws Exception{
		piece p=chessBoard.getBoardPiece(pos.getX(), pos.getY());
		if(player.judgeOwnPiece(p))
		{
			throw new Exception("piece不是对方棋子！");
		}
		else if(!chessBoard.check(pos.getX(), pos.getY())){
			throw new Exception("position超出棋盘范围！");
		}
		else if(p==null) {
			throw new Exception("该位置没有棋子！");
		}
		else {
			p.remove();
			chessBoard.setBoardPiece(pos.getX(), pos.getY(),null);
			player.addHistory(String.format("玩家%s移除了对方的棋子%s", player.getPlayerName(),p.getPieceName()));
		}
	}
	
	//
	public void eatPiece(player play,Position pos1,player play2,Position pos2)throws Exception{
		piece p1=chessBoard.getBoardPiece(pos1.getX(), pos1.getY());
		piece p2=chessBoard.getBoardPiece(pos2.getX(), pos2.getY());
		
		if(!chessBoard.check(pos1.getX(), pos1.getY())||
			!chessBoard.check(pos2.getX(), pos2.getY())) {
			throw new Exception("位置超出棋盘范围");
		}
		if(!play.judgeOwnPiece(p1)||play.judgeOwnPiece(p2)) {
			throw new Exception("棋子归属不正确");
		}
		if(p1==null||p2==null) {
			throw new Exception("棋子不存在");
		}
		piece new_piece=new piece(p1.getPieceName(),1,pos2.getX(),pos2.getY());
		chessBoard.setBoardPiece(pos1.getX(), pos1.getY(),null);
		chessBoard.setBoardPiece(pos2.getX(), pos2.getY(), new_piece);
		chessBoard.setBoardPieceState(pos2.getX(), pos2.getY(),2);
		
		play.getPlayerPieces().remove(p1);//有待确定
		new_piece.setPieceState(1);
		play.add_piece(new_piece);
		play2.getPlayerPieces().remove(p2);
		
		play.addHistory(String.format("%s use piece %s in (%d,%d) "
				+ "eat piece %s in (%d,%d)", 
				play.getPlayerName(),p1.getPieceName(),p1.getPieceX(),p1.getPieceY(),
				p2.getPieceName(),p2.getPieceX(),p2.getPieceY()));
		
	}
}
