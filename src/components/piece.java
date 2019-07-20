package components;

//棋盘上的棋子
public class piece {
    private String pieceName="";//棋子名称
    private int pieceState;
    //棋子状态,0未放置，1已放置，2是放了之后被拿走且不可用
    private int pieceX;
    private int pieceY;//棋子的坐标
    
	public piece(String pieceName, int pieceState, int pieceX, int pieceY) {
		// TODO Auto-generated constructor stub
		this.pieceName = pieceName;
		this.pieceState = pieceState;
		this.pieceX = pieceX;
		this.pieceY = pieceY;
	}

	public String getPieceName() {
		return pieceName;
	}

	public void setPieceName(String pieceName) {
		this.pieceName = pieceName;
	}

	public int getPieceState() {
		return pieceState;
	}

	public void setPieceState(int pieceState) {
		this.pieceState = pieceState;
	}

	public int getPieceX() {
		return pieceX;
	}

	public void setPieceX(int pieceX) {
		this.pieceX = pieceX;
	}

	public int getPieceY() {
		return pieceY;
	}

	public void setPieceY(int pieceY) {
		this.pieceY = pieceY;
	}
    
	//将一个棋子从棋盘中去除,将其坐标设置成(-1，-1),状态设置成2
    public void remove() {
    	pieceX=-1;
    	pieceY=-1;
    	pieceState=2;
    }
    
}
