package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import arquivos.Arquivos;

public class MapBuilder {
	
	//Propriedades
	private Bloco[] blocos;
	private int[][] mapa;
	
	//Construtor
	public MapBuilder(int[][] mapa) {
		blocos = new Bloco[10];
		this.mapa = mapa;
		carregaImagemBlocos();
		
	}

	//Getters e Setters
	public Bloco[] getBlocos() {
		return blocos;
	}

	public void setBlocos(Bloco[] blocos) {
		this.blocos = blocos;
	}

	public int[][] getMapa() {
		return mapa;
	}

	public void setMapa(int[][] mapa) {
		this.mapa = mapa;
	}
	
	//Métodos
	public void carregaImagemBlocos() {
		
		blocos[0] = new Bloco(Arquivos.getTextureimages().get(0),false);
		blocos[1] = new Bloco(Arquivos.getTextureimages().get(1),false);
		blocos[2] = new Bloco(Arquivos.getTextureimages().get(2),true);
		
	}
	
	public void draw(Graphics2D tela) {
		int tamanhoBloco = GamePanel.getTamanhoBloco();
		for (int i=0; i < GamePanel.getNumeroBlocosVertical(); i++) {
			for (int j=0; j < GamePanel.getNumeroBlocosHorizontal(); j++) {
				tela.drawImage(blocos[mapa[i][j]].getTextura(), j * tamanhoBloco, i * tamanhoBloco,tamanhoBloco ,tamanhoBloco,null);
			}
		}
	}
	
}