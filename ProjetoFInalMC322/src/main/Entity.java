package main;

public class Entity {
	private int x, y;
	private int speed;
	
	public Entity(int x, int y, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public boolean colissionWallUP() {
		
		if(y <= 5) {
			return true;
		}
				
		return false;
	}
	
	public boolean colissionWallDown() {
		
		if(y>= GamePanel.getTelaAltura()-48) {
			return true;
		}
		
		return false;
	}
	
	public boolean colissionWallRight() {
		
		if(x >= GamePanel.getTelaLargura()-48) {
			return true;
		}
		
		return false;
	}
	
	public boolean colissionWallLeft() {
		
		if(x <= 0) {
			return true;
		}
		
		return false;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void moveUp() {
		y -= speed;
	}
	
	public void moveDown() {
		y += speed;
	}
	
	public void moveLeft() {
		x -= speed;
	}
	
	public void moveRight() {
		x += speed;
	}
}
