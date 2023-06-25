package main;

import java.awt.Rectangle;

public class Entity {

	//Propriedades
	private int x, y;
	private boolean colisivel;
	private Rectangle hitBox;
	private GamePanel gamePanel;
	
	//Construtor
	public Entity(int x, int y, boolean colisivel, GamePanel gamePanel) {
		this.x = x;
		this.y = y;
		this.colisivel = colisivel;
		this.gamePanel = gamePanel;
		hitBox = new Rectangle(8, 16, 32, 32);
	}
	
	//Getters e Setters
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

	public boolean getColisivel() {
		return colisivel;
	}

	public void setColisivel(boolean colisivel) {
		this.colisivel = colisivel;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public Rectangle getHitBox() {
		return hitBox;
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}
}
