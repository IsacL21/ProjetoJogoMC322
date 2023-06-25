package main;

import java.awt.Rectangle;

public class Item extends Entity{

	private boolean consumivel;
	private String nome;
	
	public Item(int x, int y, boolean colisivel, Rectangle hitBox) {
		super(x, y, colisivel, hitBox);
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