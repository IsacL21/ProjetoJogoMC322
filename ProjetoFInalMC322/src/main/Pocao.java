package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import arquivos.Arquivos;

public class Pocao extends Item{

	private int vida = 1;
	
	public Pocao(int x, int y, GamePanel gamePanel) {
		super(x, y, true, gamePanel, true, "pocao");
	}
	
	public void draw(Graphics2D tela) {
		BufferedImage image = Arquivos.getItemimages().get(1);
		tela.drawImage(image, this.getX(), this.getY(), getGamePanel().getTamanhoBloco(), getGamePanel().getTamanhoBloco(), null);
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

}
