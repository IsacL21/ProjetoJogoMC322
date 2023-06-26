package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import arquivos.Arquivos;

public class Porta extends Entity{

	private boolean trancado;
	
	public Porta(int x, int y, boolean colisivel, GamePanel gamePanel, boolean trancado) {
		super(x, y, colisivel, gamePanel);
		this.trancado = trancado;
	}

	public void draw(Graphics2D tela) {
		BufferedImage image = Arquivos.getPortaimages().get(0);
		tela.drawImage(image, this.getX(), this.getY(), getGamePanel().getTamanhoBloco(), getGamePanel().getTamanhoBloco(), null);
	}

	public boolean getTrancado() {
		return trancado;
	}

	public void setTrancado(boolean trancado) {
		this.trancado = trancado;
	}
}

