package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import arquivos.Arquivos;

public class Pocao extends Item{

	private int vida;
	
	public Pocao(int x, int y, boolean colisivel, String nome) {
		super(x, y, colisivel, nome);
		this.vida = 30;
	}
	
	public void draw(Graphics2D tela) {
		BufferedImage image = Arquivos.getItemimages().get(1);
		tela.drawImage(image, this.getX(), this.getY(), GamePanel.getTamanhoBloco(), GamePanel.getTamanhoBloco(), null);
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

}
