package main;

import java.awt.Rectangle;

public abstract class Item extends Entity{

	//Propriedades
	private boolean consumivel;
	private String nome;
	
	//Construtor
	public Item(int x, int y, boolean colisivel, Engine engine, String nome) {
		super(x, y, colisivel, engine, new Rectangle(0, 0, engine.getGamePanel().getTamanhoBloco(), engine.getGamePanel().getTamanhoBloco()));

		this.consumivel = true;
		this.nome = nome;
		// TODO Auto-generated constructor stub
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