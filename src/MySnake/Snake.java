package MySnake;

import java.awt.Graphics;

//蛇身体的结点
public class Snake {
	private int x=0;
	private int y=0;//位置坐标
	public Snake() {
		
	}
	public Snake(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public void setX(int x) {
		this.x=x;
	}
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y=y;
	}
	public int getY() {
		return y;
	}
	public void draw(Graphics g) {
		g.fillRect(x, y, 20,20);
	}

	
}
