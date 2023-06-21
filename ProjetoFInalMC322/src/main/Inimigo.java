package main;

import java.util.ArrayList;

public class Inimigo extends Personagem{

	private ArrayList<Item> listaDrops = new ArrayList<Item>();
	
	public Inimigo(int x, int y, double vida, boolean invencivel, int velocidade) {
		super(x, y, vida, invencivel, velocidade);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void causarDano() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void levarDano() {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Item> getListaDrops() {
		return listaDrops;
	}

	public void setListaDrops(ArrayList<Item> listaDrops) {
		this.listaDrops = listaDrops;
	}

}
