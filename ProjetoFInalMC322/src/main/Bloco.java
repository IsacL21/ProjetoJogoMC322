package main;

import java.awt.image.BufferedImage;

public class Bloco {
	private BufferedImage textura;
	private boolean colidivel;
	
	public Bloco(BufferedImage textura,  boolean colidivel) {
		this.colidivel = colidivel;
		this.textura = textura;
	}

	public BufferedImage getTextura() {
		return textura;
	}

	public void setTextura(BufferedImage textura) {
		this.textura = textura;
	}

	public boolean isColidivel() {
		return colidivel;
	}

	public void setColidivel(boolean colidivel) {
		this.colidivel = colidivel;
	}
}