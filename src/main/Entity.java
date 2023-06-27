package main;

import java.awt.Rectangle;

public abstract class Entity implements Desenhavel{

	//Propriedades
	private int x, y;
	private boolean colisivel;

	private Rectangle hitBox;
	private Engine engine;
	
	//Construtor
	public Entity(int x, int y, boolean colisivel, Engine engine, Rectangle hitBox) {
		this.x = x;
		this.y = y;
		this.colisivel = colisivel;
		this.engine = engine;
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

	public GamePanel getGamePanel() {
		return engine.getGamePanel();
	}

	public Rectangle getHitBox() {
		return hitBox;
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}
}
