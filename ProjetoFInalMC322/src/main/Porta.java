package main;

import java.awt.Rectangle;

public class Porta extends Entity{

	private boolean trancado;
	
	public Porta(int x, int y, boolean colisivel, GamePanel gamePanel, boolean trancado) {
		super(x, y, colisivel, gamePanel);
		this.trancado = trancado;
	}

	public boolean getTrancado() {
		return trancado;
	}

	public void setTrancado(boolean trancado) {
		this.trancado = trancado;
	}
}
