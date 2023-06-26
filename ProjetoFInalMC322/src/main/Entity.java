package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Entity implements Desenhavel{

	//Propriedades
	private int x, y;
	private boolean colisivel;
	private Rectangle hitBox;
	private Engine engine;
	private GamePanel gamePanel;
	
	//Construtor
	public Entity(int x, int y, boolean colisivel, Engine engine, Rectangle hitBox) {
		this.x = x;
		this.y = y;
		this.colisivel = colisivel;
		this.engine = engine;
		this.gamePanel = engine.getGamePanel();
		this.hitBox = hitBox;
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

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
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

	//Métodos
	public void updateHitBox() {
		hitBox = new Rectangle(x + 8, y + 16, 32, 32);
	}
}
