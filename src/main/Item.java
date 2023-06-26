package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import arquivos.Arquivos;
public abstract class Item extends Entity{

	//Propriedades
	private boolean consumivel;
	private String nome;
	
	//Construtor
	public Item(int x, int y, boolean colisivel, GamePanel gamePanel, boolean consumivel, String nome) {
		super(x, y, colisivel, gamePanel);
		this.consumivel = colisivel;
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