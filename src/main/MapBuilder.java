package main;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import arquivos.Arquivos;

public class MapBuilder implements Desenhavel{
	
	//Propriedades
	private Bloco[] blocos;
	private int[][] mapa;
	private GamePanel gamePanel;

	//Construtor
	public MapBuilder(int[][] mapa, GamePanel gamePanel) {
		blocos = new Bloco[10];
		this.mapa = mapa;
		this.gamePanel = gamePanel;
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

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	//Métodos
	public void carregaImagemBlocos() {
		blocos[0] = new Bloco(Arquivos.getTextureimages().get(0),true);
		blocos[1] = new Bloco(Arquivos.getTextureimages().get(1),false);
		blocos[2] = new Bloco(Arquivos.getTextureimages().get(2),true);
		blocos[3] = new Bloco(Arquivos.getTextureimages().get(1),true);
	}
	
	public void carregaBlocosMapa() {
		InputStream file = getClass().getResourceAsStream("/rooms/room1");
		BufferedReader entrada = new BufferedReader(new InputStreamReader(file));
		try {
			for (int i = 0; i < gamePanel.getNumeroBlocosVertical(); i++) {
				String[] numeroColuna = entrada.readLine().split(" ");
				for (int j = 0; j < gamePanel.getNumeroBlocosHorizontal(); j++) {
					mapa[i][j] = Integer.parseInt(numeroColuna[j]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D tela) {
		int tamanhoBloco = gamePanel.getTamanhoBloco();
		for (int i = 0; i < gamePanel.getNumeroBlocosVertical(); i++) {
			for (int j = 0; j < gamePanel.getNumeroBlocosHorizontal(); j++) {
				tela.drawImage(blocos[mapa[i][j]].getTextura(), j * tamanhoBloco, i * tamanhoBloco,tamanhoBloco ,tamanhoBloco,null);
			}
		}
	}
	
}