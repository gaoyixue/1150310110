package components;

public class Position {
	
    public int x;//���λ�õ�x����
    public int y;//��ø�λ�õ�y����
    
    //���캯��
    public Position(int posX,int posY) {
    	this.x=posX;
    	this.y=posY;
    }
    
    ////get���������ظ�λ�õ�xy����
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
    
	//��дequals�����������ж�����λ���Ƿ���ͬ
	@Override
    public boolean equals(Object that) {
    	Position p=(Position) that;
    	if(x==p.x && y==p.y)
    		return true;
    	return false;
    } 
}
