package components;

public class Position {
	
    public int x;//获得位置的x坐标
    public int y;//获得该位置的y坐标
    
    //构造函数
    public Position(int posX,int posY) {
    	this.x=posX;
    	this.y=posY;
    }
    
    ////get方法，返回该位置的xy坐标
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
    
	//重写equals方法，用于判断两个位置是否相同
	@Override
    public boolean equals(Object that) {
    	Position p=(Position) that;
    	if(x==p.x && y==p.y)
    		return true;
    	return false;
    } 
}
