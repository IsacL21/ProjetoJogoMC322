package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import arquivos.Arquivos;

public class Pocao extends Item{

	//Propriedade
	private int vida;

	//Construtor
	public Pocao(int x, int y, Engine engine) {
		super(x, y, true, engine, "pocao");
		this.vida = 2;
	}

	//Getter e Setter
	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	//Método
	public void draw(Graphics2D tela) {
		BufferedImage image = Arquivos.getItemimages().get(1);
		tela.drawImage(image, this.getX(), this.getY(), getGamePanel().getTamanhoBloco(), getGamePanel().getTamanhoBloco(), null);
	}
}
