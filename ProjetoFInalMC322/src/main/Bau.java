package main;

import java.awt.Rectangle;

public class Bau extends Entity{

	private boolean trancado;
	private Item item;
	
	public Bau(int x, int y, boolean colidivel) {
		super(0, 0, colidivel, new Rectangle(x, y, GamePanel.getTamanhoBloco(), GamePanel.getTamanhoBloco()));
	}

	public boolean isTrancado() {
		return trancado;
	}

	public void setTrancado(boolean trancado) {
		this.trancado = trancado;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
