package main;

import java.awt.image.BufferedImage;

public class Bloco {

	//Propriedades
	private BufferedImage textura;
	private boolean colidivel;
	
	//Construtor
	public Bloco(BufferedImage textura,  boolean colidivel) {
		this.colidivel = colidivel;
		this.textura = textura;
	}

	//Getters e Setters
	public BufferedImage getTextura() {
		return textura;
	}

	public void setTextura(BufferedImage textura) {
		this.textura = textura;
	}

	public boolean getColidivel() {
		return colidivel;
	}

	public void setColidivel(boolean colidivel) {
		this.colidivel = colidivel;
	}
}