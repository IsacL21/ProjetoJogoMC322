package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import arquivos.Arquivos;

public class Item extends Entity{

	private boolean consumivel;
	private String nome;
	
	public Item(int x, int y, boolean colisivel, String nome) {
		super(x, y, colisivel);
		this.nome = nome;
	}

	public boolean isConsumivel() {
		return consumivel;
	}
	
	public void draw(Graphics2D tela) {
		BufferedImage image = Arquivos.getItemimages().get(0);
		tela.drawImage(image, this.getX(), this.getY(), 25, 25, null);
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