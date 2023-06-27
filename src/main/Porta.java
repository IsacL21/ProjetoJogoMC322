package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import arquivos.Arquivos;

public class Porta extends Entity{

	//Propriedade
	private boolean trancado;

	//Construtor
	public Porta(int x, int y, boolean colisivel, Engine engine, boolean trancado) {
		super(x, y, colisivel, engine, new Rectangle(0, 0, engine.getGamePanel().getTamanhoBloco(), engine.getGamePanel().getTamanhoBloco()));
		this.trancado = true;
	}

	//Getter e Setter
	public boolean isTrancado() {
		return trancado;
	}

	public void setTrancado(boolean trancado) {
		this.trancado = trancado;
	}
	
	//Método
	public void draw(Graphics2D tela) {
		BufferedImage image = Arquivos.getPortaimages().get(0);
		
		if(trancado == false) {
			image = Arquivos.getPortaimages().get(1);
		}

		tela.drawImage(image, this.getX(), this.getY(), getGamePanel().getTamanhoBloco(), getGamePanel().getTamanhoBloco(), null);
	}
}