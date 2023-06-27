package main;

import java.util.ArrayList;

public class Inventario {

	private ArrayList<Item> listaItens = new ArrayList<Item>();
	private int capacidade;
	
	public Inventario(ArrayList<Item> listaItens, int capacidade) {
		this.listaItens = listaItens;
		this.capacidade = capacidade;
	}
	
	public Inventario(int capacidade) {
		this.capacidade = capacidade;
	}
	
	public boolean addItem(Item item) {
		
		listaItens.add(item);
		return true;
	}

	public boolean removeItem(int indice) {
		
		listaItens.remove(indice);
		return true;
	}
	
	public ArrayList<Item> getListaItens() {
		return listaItens;
	}
	public void setListaItens(ArrayList<Item> listaItens) {
		this.listaItens = listaItens;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
}