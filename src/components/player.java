package components;
import components.piece;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class player {
	private String playerName;//�������
	private String history=new String("");//��Ҳ�����ʷ
	private Set<piece> playerPieces;//����������ϵ�����
	
	public player(String Name,Set<piece> playerpieces) {
		playerName=Name;
		playerPieces=playerpieces;
		history=new String("");
	}
	
	public player() {
		// TODO Auto-generated constructor stub
	}

	//�мǣ�˽�����Ա�������get������������ʲ���
	public String getPlayerName() {
		return playerName;
	}
	public String getHistory() {
		return history;
	}
	public Set<piece> getPlayerPieces() {
		return playerPieces;
	}
    //Ϊ�����������
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public boolean add_piece(piece p) {
		if(playerPieces==null) {
			
			playerPieces.add(p);
			return true;
		}
		//��������и����ӣ������ʧ��
		if(playerPieces.contains(p)) {
			return false;
		}
		//�����û�и����ӣ�������������������б���
		playerPieces.add(p);
		return true;
	}
	
	//���goд�ķ��������ݴ����pieceName,
	//��playerʣ��������з���һ��δ���õ����ӣ����������򷵻�null
	public piece getPiece(String piece_name) {
		for(piece p:playerPieces) {
			if(p.getPieceState()==0 && p.getPieceName()==piece_name) {
				return p;
			}
		}
		return null;
	}
	
	//�������ÿ�β�������Ҫ��ӵ������ʷ�����ж�Ϊ�ǿ�������뵽history����
	public void addHistory(String his) {
		history=history.concat(his);
	}
	
	//�ж����ʣ����������Ƿ���p�����з���true,���򷵻�false
	public boolean judgeOwnPiece(piece p) {
		if(playerPieces.contains(p))
			return true;
		return false;
	}
	
	
	//�ж������ϵ����Ӹ������������������ͳ��״̬��1�����Ӹ���
	public int countQuantityOfPieceInBoard() {
		int count=0;
		for(piece p:playerPieces) {
			if(p.getPieceState()==1) {
				count=count+1;
			}
		}
		return count;
	}
}
