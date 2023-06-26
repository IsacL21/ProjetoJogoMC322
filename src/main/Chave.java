package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import arquivos.Arquivos;

public class Chave extends Item{

	public Chave(int x, int y, GamePanel gamePanel) {
		super(x, y, true, gamePanel, true, "Chave");
	}

	public void draw(Graphics2D tela) {
		BufferedImage image = Arquivos.getItemimages().get(0);
		tela.drawImage(image, this.getX(), this.getY(), 25, 25, null);
	}
}
