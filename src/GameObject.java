import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract  class GameObject{
	public static final int SIZE = 20;
	int x;
	int y;
	BufferedImage image;
	BlockState state;
	Board board;
	public boolean isAlive;
	public boolean removed;
	
	public GameObject(int x,int y, BufferedImage image,Board board,boolean isAlive){
		this.x=x;
		this.y=y;
		this.image = image;
		this.board=board;
		this.isAlive=isAlive;
	}
	
	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public abstract boolean burnable();
	public abstract boolean solid();
	public abstract void draw(Graphics graphics);
	
	public void changeState(BlockState state){
	}
	
	public void update() {	
	}	
}