package main;

import java.awt.Rectangle;

public class Porta extends Entity{

	private boolean trancado;
	
	public Porta(int x, int y, boolean colisivel) {
		super(x, y, colisivel,  new Rectangle(0, 0, GamePanel.getTamanhoBloco(), GamePanel.getTamanhoBloco()));
	}

	public boolean isTrancado() {
		return trancado;
	}

	public void setTrancado(boolean trancado) {
		this.trancado = trancado;
	}
}
