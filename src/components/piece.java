package components;

//�����ϵ�����
public class piece {
    private String pieceName="";//��������
    private int pieceState;
    //����״̬,0δ���ã�1�ѷ��ã�2�Ƿ���֮�������Ҳ�����
    private int pieceX;
    private int pieceY;//���ӵ�����
    
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
    
	//��һ�����Ӵ�������ȥ��,�����������ó�(-1��-1),״̬���ó�2
    public void remove() {
    	pieceX=-1;
    	pieceY=-1;
    	pieceState=2;
    }
    
}
