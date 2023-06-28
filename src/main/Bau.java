package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import arquivos.Arquivos;

public class Bau extends Entity{

	//Propriedades
	private boolean trancado;
	private Item item;
	
	//Construtor
	public Bau(int x, int y, Engine engine, boolean trancado, Item item) {
		super(x, y, true, engine, new Rectangle(0, 0, engine.getGamePanel().getTamanhoBloco(), engine.getGamePanel().getTamanhoBloco()));
		this.trancado = true;
		this.item = item;
	}

	//Getters e Setetrs
	public boolean isTrancado() {
		return trancado;
	}

	public void setTrancado(boolean trancado) {
		this.trancado = trancado;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	//Método
	public void draw(Graphics2D tela) {
		BufferedImage image = Arquivos.getBauimages().get(0);
		
		if(trancado == false) {
			image = Arquivos.getBauimages().get(1);
		}
		
		tela.drawImage(image, this.getX(), this.getY(), 40, 40, null);
	}
}
