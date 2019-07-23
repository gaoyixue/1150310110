package components;
import components.piece;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class player {
	private String playerName;//玩家姓名
	private String history=new String("");//玩家操作历史
	private Set<piece> playerPieces;//玩家在棋盘上的棋子
	
	public player(String Name,Set<piece> playerpieces) {
		playerName=Name;
		playerPieces=playerpieces;
		history=new String("");
	}
	
	public player() {
		// TODO Auto-generated constructor stub
	}

	//切记，私有属性必须设置get方法，否则访问不了
	public String getPlayerName() {
		return playerName;
	}
	public String getHistory() {
		return history;
	}
	public Set<piece> getPlayerPieces() {
		return playerPieces;
	}
    //为玩家姓名设置
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public boolean add_piece(piece p) {
		if(playerPieces==null) {
			
			playerPieces.add(p);
			return true;
		}
		//若玩家已有该棋子，则添加失败
		if(playerPieces.contains(p)) {
			return false;
		}
		//若玩家没有该棋子，将该棋子添加至棋子列表中
		playerPieces.add(p);
		return true;
	}
	
	//针对go写的方法，根据传入的pieceName,
	//从player剩余的棋子中返回一个未放置的棋子，若不存在则返回null
	public piece getPiece(String piece_name) {
		for(piece p:playerPieces) {
			if(p.getPieceState()==0 && p.getPieceName()==piece_name) {
				return p;
			}
		}
		return null;
	}
	
	//传入的是每次操作后需要添加的玩家历史，若判断为非空则将其加入到history后面
	public void addHistory(String his) {
		history=history.concat(his);
	}
	
	//判断玩家剩余的棋子中是否有p，若有返回true,否则返回false
	public boolean judgeOwnPiece(piece p) {
		if(playerPieces.contains(p))
			return true;
		return false;
	}
	
	
	//判断棋盘上的棋子个数，即遍历玩家棋子统计状态是1的棋子个数
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
