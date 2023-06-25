package main;

import java.awt.Rectangle;

public class Entity {
	private int x, y;
	private boolean colisivel;
	private Rectangle hitBox;
	
	public Entity(int x, int y, boolean colisivel,Rectangle hitBox) {
		this.x = x;
		this.y = y;
		this.colisivel = colisivel;
		this.hitBox = hitBox;
	}
	
	/*ver se vai ficar assim ainda*/
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

	public boolean isColisivel() {
		return colisivel;
	}

	public void setColisivel(boolean colisivel) {
		this.colisivel = colisivel;
	}

	public Rectangle getHitBox() {
		return hitBox;
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}
	
}
