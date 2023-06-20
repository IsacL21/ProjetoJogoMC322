package main;

public class Pocao extends Item{

	private int vida;
	
	public Pocao(int x, int y, boolean colisivel) {
		super(x, y, colisivel);
		// TODO Auto-generated constructor stub
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

}
