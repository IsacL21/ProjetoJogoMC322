package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import arquivos.Arquivos;

public class Pocao extends Item{

	private int vida;
	
	public Pocao(int x, int y, boolean colisivel) {
		super(x, y, colisivel);
		// TODO Auto-generated constructor stub
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
