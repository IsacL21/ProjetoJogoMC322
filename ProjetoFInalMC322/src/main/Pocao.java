package main;

public class Pocao extends Item{

	private int vida;
	
	public Pocao(int x, int y, boolean colisivel, Engine engine, int vida) {
		super(x, y, colisivel, engine, true, "pocao");
		this.vida = vida;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

}
