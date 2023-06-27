package main;

import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Inimigo extends Personagem{

	//Propriedades
	private ArrayList<Item> listaDrops = new ArrayList<Item>();
	
	//Construtor
	public Inimigo(int x, int y, Engine engine, double vida, boolean invencivel, int velocidade, ArrayList<Item> listaDrops, Rectangle hitBox) {
		super(x, y, engine, vida, invencivel, velocidade, hitBox);
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
	public void causarDano(Personagem personagem) {
		personagem.levarDano(1);
	}
}
