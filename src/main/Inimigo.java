package main;

import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Inimigo extends Personagem{

	//Propriedades
	private ArrayList<Item> listaDrops = new ArrayList<Item>();
	
	//Construtor
	public Inimigo(int x, int y, GamePanel gamePanel, double vida, boolean invencivel, int velocidade, Rectangle hitBox , ArrayList<Item> listaDrops) {
		super(x, y, gamePanel, vida, invencivel, velocidade, hitBox);
		this.listaDrops = listaDrops;
	}
	
	//Getters e Setters
	public ArrayList<Item> getListaDrops() {
		return listaDrops;
	}

	public void setListaDrops(ArrayList<Item> listaDrops) {
		this.listaDrops = listaDrops;
	}

	//Métodos
	@Override
	public void causarDano() {
		
	}

	@Override
	public void levarDano() {
		
	}

	

}
