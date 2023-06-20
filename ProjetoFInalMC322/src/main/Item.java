package main;

public class Item extends Entity{

	private boolean consumivel;
	private String nome;
	
	public Item(int x, int y, boolean colisivel) {
		super(x, y, colisivel);
		// TODO Auto-generated constructor stub
	}

	public boolean isConsumivel() {
		return consumivel;
	}

	public void setConsumivel(boolean consumivel) {
		this.consumivel = consumivel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}