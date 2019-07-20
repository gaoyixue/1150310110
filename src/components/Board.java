package components;

public class Board {
    private int boardType;//0��ʾ���ڸ�����(��������)��1��ʾ���ڽ����(Χ��)
    private int boardSize;//���̳ߴ�,�л��еĸ�����Ŀ
    private piece boardPieces[][];//�����ϵ�����
    
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
    
	//�ж�(x,y)�Ƿ�Ϸ�
	//��֪������2�����ɵĸ�����size+type
    public boolean check(int x,int y) {
    	int num=boardSize+boardType;
    	if(x>=0&&x<num&&y>=0&&y<num) 
    		return true;
    	return false;
    }
    
    //���ָ��λ�õ����ӣ�������������Χ���׳��쳣
    public piece getBoardPiece(int x,int y) throws Exception{
    	if(!check(x, y))
    		throw new Exception("λ�ò��Ϸ���");
    	else if(boardPieces[x][y]==null){
    		return null;
    	}
    	else
    		return boardPieces[x][y];
    }
    
    //������p����(x,y)������λ�ò��Ϸ��׳��쳣
    public void setBoardPiece(int x,int y,piece p)throws Exception {
    	if((!check(x, y)))
    		throw new Exception("λ�ò��Ϸ���");
    	else {
    		boardPieces[x][y]=p;
    	}
    }
    
    //
    public void setBoardPieceState(int x,int y,int newState)throws Exception{
    	if(!check(x, y))
    		throw new Exception("λ�ò��Ϸ���");
    	else {
    		boardPieces[x][y].setPieceState(newState);
    	}
    }
}
