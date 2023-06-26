package main;

public class Bau extends Entity{

	//Propriedades
	private boolean trancado;
	private Item item;
	
	//Construtor
	public Bau(int x, int y, boolean colisivel, Engine engine, boolean trancado, Item item) {
		super(x, y, colisivel, engine);
		this.trancado = trancado;
		this.item = item;
	}

	//Getters e Setetrs
	public boolean getTrancado() {
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
