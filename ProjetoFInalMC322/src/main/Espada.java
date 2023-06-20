package main;

public class Espada {
	
	private double ataque;
	private int alcance;
	private double velocidadeAtaque;
	
	public Espada(double ataque, int alcance, double velocidadeAtaque) {
		this.ataque = ataque;
		this.alcance = alcance;
		this.velocidadeAtaque = velocidadeAtaque;
	}
	
	public double getAtaque() {
		return ataque;
	}
	public void setAtaque(double ataque) {
		this.ataque = ataque;
	}
	public int getAlcance() {
		return alcance;
	}
	public void setAlcance(int alcance) {
		this.alcance = alcance;
	}
	public double getVelocidadeAtaque() {
		return velocidadeAtaque;
	}
	public void setVelocidadeAtaque(double velocidadeAtaque) {
		this.velocidadeAtaque = velocidadeAtaque;
	}
}