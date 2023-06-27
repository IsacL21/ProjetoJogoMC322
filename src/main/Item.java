package main;

public abstract class Item extends Entity{

	//Propriedades
	private boolean consumivel;
	private String nome;
	
	//Construtor
	public Item(int x, int y, boolean colisivel, Engine engine, String nome) {
		super(x, y, colisivel, engine);
		this.consumivel = true;
		this.nome = nome;
	}

	//Getters e Setters
	public boolean getConsumivel() {
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