package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Personagem extends Entity{
	
	//Propriedades
	private double vida;
	private boolean invencivel;
	private int velocidade;
	private boolean colidindo;
	private String direcao = "down";
	private Rectangle hitBox;
	
	//Construtor
	public Personagem(int x, int y, GamePanel gamePanel, double vida, boolean invencivel, int velocidade, Rectangle hitBox) {
		super(x, y, false, gamePanel);
		this.vida = vida;
		this.invencivel = invencivel;
		this.velocidade = velocidade;
		this.hitBox = hitBox;
	}

	//Getters e Setters
	public double getVida() {
		return vida;
	}
	
	public void setVida(double vida) {
		this.vida = vida;
	}
	public boolean getInvencivel() {
		return invencivel;
	}
	public void setInvencivel(boolean invencivel) {
		this.invencivel = invencivel;
	}
	public int getVelocidade() {
		return velocidade;
	}
	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}
	public boolean getColidindo() {
		return colidindo;
	}
	public void setColidindo(boolean colisao) {
		this.colidindo = colisao;
	}
	public String getDirecao() {
		return direcao;
	}
	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}

	//Métodos
	public abstract void causarDano();

	public abstract void levarDano();

	public abstract void morrer();
	
	public void moveCima() {
		setY(getY() - velocidade);
	}
	
	public void moveBaixo() {
		setY(getY() + velocidade);
	}
	
	public void moveEsquerda() {
		setX(getX() - velocidade);
	}
	
	public void moveDireita() {
		setX(getX() + velocidade);
	}
	
	public abstract void update();
	
	public abstract void draw(Graphics2D tela);

	public Rectangle getHitBox() {
		return hitBox;
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}

	
}
