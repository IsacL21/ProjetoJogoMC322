package main;

import java.awt.Rectangle;

public class Pocao extends Item{

	private int vida;
	
	public Pocao(int x, int y, boolean colisivel) {
		super(x, y, colisivel,  new Rectangle(0, 0, GamePanel.getTamanhoBloco(), GamePanel.getTamanhoBloco()));
		// TODO Auto-generated constructor stub
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

}
