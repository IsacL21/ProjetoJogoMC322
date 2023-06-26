package main;

import java.awt.Graphics2D;

public abstract class Personagem extends Entity{
	
	//Propriedades
	private double vida;
	private boolean invencivel;
	private int velocidade;
	private boolean colisao;
	private String direcao = "down";
	private GamePanel gamePanel;
	
	//Construtor
	public Personagem(int x, int y, double vida, boolean invencivel, int velocidade, GamePanel gamePanel) {
		super(x, y, false);
		this.vida = vida;
		this.invencivel = invencivel;
		this.velocidade = velocidade;
		this.gamePanel = gamePanel;
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
	public boolean getColisao() {
		return colisao;
	}
	public void setColisao(boolean colisao) {
		this.colisao = colisao;
	}
	public String getDirecao() {
		return direcao;
	}
	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}
	

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	//Métodos
	public abstract void causarDano(Personagem personagem);

	public abstract boolean levarDano(int danoRecebido);

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

	
}
