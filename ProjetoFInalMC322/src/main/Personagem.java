package main;

public abstract class Personagem extends Entity{
	
	//Propriedades
	private double vida;
	private boolean invencivel;
	private int velocidade;
	private boolean colisao;
	private String direcao = "down";
	
	//Construtor
	public Personagem(int x, int y, double vida, boolean invencivel, int velocidade) {
		super(x, y, false);
		this.vida = vida;
		this.invencivel = invencivel;
		this.velocidade = velocidade;
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

	//Métodos
	public abstract void causarDano();

	public abstract void levarDano();

	public void morrer() {
		
	}
	
	public void mostrarVida() {
		
	}
	
	public void moveUp() {
		setY(getY() - velocidade);
	}
	
	public void moveDown() {
		setY(getY() + velocidade);
	}
	
	public void moveLeft() {
		setX(getX() - velocidade);
	}
	
	public void moveRight() {
		setX(getX() + velocidade);
	}

	
}
