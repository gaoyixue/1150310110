package components;

public class Board {
    private int boardType;//0表示放在格子内(国际象棋)，1表示放在交叉点(围棋)
    private int boardSize;//棋盘尺寸,行或列的格子数目
    private piece boardPieces[][];//棋盘上的棋子
    
    public Board(int type,int size,piece[][] pieces) {
    	// TODO Auto-generated constructor stub
    	boardType=type;
    	boardSize=size;
    	boardPieces=pieces;
    }
	
	public int getBoardType() {
		return boardType;
	}
	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}
	public int getBoardSize() {
		return boardSize;
	}
	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}
	public piece[][] getBoardPieces() {
		return boardPieces;
	}
	public void setBoardPieces(piece[][] boardPieces) {
		this.boardPieces = boardPieces;
	}
    
	//判断(x,y)是否合法
	//可知，棋盘2能容纳的个数是size+type
    public boolean check(int x,int y) {
    	int num=boardSize+boardType;
    	if(x>=0&&x<num&&y>=0&&y<num) 
    		return true;
    	return false;
    }
    
    //获得指定位置的棋子，若参数超出范围，抛出异常
    public piece getBoardPiece(int x,int y) throws Exception{
    	if(!check(x, y))
    		throw new Exception("位置不合法！");
    	else if(boardPieces[x][y]==null){
    		return null;
    	}
    	else
    		return boardPieces[x][y];
    }
    
    //将棋子p放在(x,y)处，若位置不合法抛出异常
    public void setBoardPiece(int x,int y,piece p)throws Exception {
    	if((!check(x, y)))
    		throw new Exception("位置不合法！");
    	else {
    		boardPieces[x][y]=p;
    	}
    }
    
    //
    public void setBoardPieceState(int x,int y,int newState)throws Exception{
    	if(!check(x, y))
    		throw new Exception("位置不合法！");
    	else {
    		boardPieces[x][y].setPieceState(newState);
    	}
    }
}
