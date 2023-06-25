package main;

public class Bau extends Entity{

	private boolean trancado;
	private Item item;
	
	public Bau(int x, int y, boolean colidivel) {
		super(x, y, colidivel);
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
