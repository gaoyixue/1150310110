package components;

import java.util.HashSet;
import java.util.Set;

public class Game {
    private String gameName;
    private Board gameBoard;
    private Action gameAction;
    private player player1;
    private player player2;
    
    public Game(String gameType,String player1Name,String player2Name) {
		// TODO Auto-generated constructor stub
		System.out.println(gameType);
		if(gameType.equals("chess")) {//国际象棋
			gameName=gameType;
			Set<piece> player1Pieces=new HashSet<piece>();
			Set<piece> player2Pieces=new HashSet<piece>();
			//初始化gameBoard
			piece[][] boardPieces=new piece[8][8];
			
			boardPieces[0][0]=new piece("rook",1,0,0);
			
			boardPieces[1][0]=new piece("knight",1,1,0);
			boardPieces[2][0]=new piece("bishop",1,2,0);
			boardPieces[3][0]=new piece("king",1,3,0);
			boardPieces[4][0]=new piece("queen",1,4,0);
			boardPieces[5][0]=new piece("bishop",1,5,0);
			boardPieces[6][0]=new piece("knight",1,6,0);
			boardPieces[7][0]=new piece("rook",1,7,0);
			
			boardPieces[0][7]=new piece("rook",1,0,7);
			boardPieces[1][7]=new piece("knight",1,1,7);
			boardPieces[2][7]=new piece("bishop",1,2,7);
			boardPieces[3][7]=new piece("king",1,3,7);
			boardPieces[4][7]=new piece("queen",1,4,7);
			boardPieces[5][7]=new piece("bishop",1,5,7);
			boardPieces[6][7]=new piece("knight",1,6,7);
			boardPieces[7][7]=new piece("rook",1,7,7);
			for(int i=0;i<8;i++) {
				boardPieces[i][1]=new piece("pawn",1,i,1);
				boardPieces[i][6]=new piece("pawn",1,i,6);
			}
			
			gameBoard=new Board(0,8,boardPieces);
			for(int i=0;i<8;i++) {
				for(int j=0;j<2;j++) {
					player1Pieces.add(boardPieces[i][j]);
					player2Pieces.add(boardPieces[i][7-j]);
				}
			}
			
			player1=new player(player1Name,player1Pieces);
			player2=new player(player2Name,player2Pieces);
			gameAction=new Action(gameBoard,player1,player2);
		}
		else {//围棋
			gameName=gameType;
			Set<piece> player1Pieces=new HashSet<piece>();
			Set<piece> player2Pieces=new HashSet<piece>();
			//初始化gameBoard
			piece[][] boardPieces = new piece[19][19];
			
			//for(int i=0;i<=18;i++) {
				//for(int j=0;j<=18;j++) {
					//boardPieces[i][j]=new piece("",0,i,j);
				//}
			//}
			gameBoard=new Board(0,18,boardPieces);
		
			player1=new player(player1Name,player1Pieces);
			player2=new player(player2Name,player2Pieces);
			gameAction=new Action(gameBoard,player1,player2);
		}
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
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
	public Board getGameBoard() {
		return gameBoard;
	}
	public Action getGameAction() {
		return gameAction;
	}
    
	//以下四个方法调用Action里面的即可
	public void putPiece(player play,piece p,Position pos)throws Exception{
		gameAction.putPiece(play, p, pos);
	}
	
	public void movePiece(player play,Position pos1,Position pos2)throws Exception{
		gameAction.movePiece(play, pos1, pos2);
	}
	
	public void removePiece(player play,Position pos)throws Exception{
		gameAction.removePiece(play, pos);
	}
	
	public void eatPiece(player play1,Position pos1,player play2,Position pos2)throws Exception{
		gameAction.eatPiece(play1, pos1,play2, pos2);
	}
	
	//在指定的位置获得一个棋子
	public piece getOccupationOfPos(Position pos)throws Exception{
		return gameBoard.getBoardPiece(pos.getX(), pos.getY());
	}
	
	public player getOwnerAtCord(Position pos) throws Exception{
		piece piece=getOccupationOfPos(pos);
		if(player1.judgeOwnPiece(piece))
			return player1;
		if(player2.judgeOwnPiece(piece))
			return player2;
		return null;
	}
	
	public int getNumOfPiecesInBoard(player player) {
		return player.getPlayerPieces().size();
	}
	
	//构造函数，根据游戏类型[chess,go]初始化游戏
	
	public void initGameByPlayerName(String player1,String player2) {
	}
	
	public void printBoard() {
		
	}
}
