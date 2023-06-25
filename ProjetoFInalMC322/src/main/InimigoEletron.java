package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;


import arquivos.Arquivos;

public class InimigoEletron extends Inimigo{
	
	private int numero = 0;
	private int spriteNum = 0;
	private int xInicial;
	private int yInicial;
	private int contMov1 = 0, contMov2 = 0;
	
	static int counter = 0;

	public InimigoEletron(int xInicial, int yInicial, double vida, int velocidade, GamePanel gamePanel) {
		super(xInicial, yInicial, vida, false, velocidade, gamePanel);
		this.xInicial = xInicial;
		this.yInicial = yInicial;
	}
	
	public void update() {
		
		if(contMov1 == 59) {
			numero = 0;
			
		}else if(contMov1 >= 50) {
			
			if(numero == 0) {
				Random aleatorio = new Random();
				numero = 1 + aleatorio.nextInt((4 - 1) + 1);
				
			}else if(numero == 1 && (getY()-yInicial)<40) {
				moveBaixo();
				setDirecao("baixo");

			}else if(numero == 2 && (getY()-yInicial)>-40) {
				moveCima();
				setDirecao("cima");
			}else if(numero == 3 && (getX()-xInicial)>-40) {
				moveEsquerda();
				setDirecao ("esquerda");
			}else if((getX()-xInicial)<40){
				moveDireita();
				setDirecao("direita");
			}
		}
		contMov1 = (contMov1 + 1) % 60; 
	}

	@Override
	public void causarDano() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void levarDano() {
		// TODO Auto-generated method stub
		
	}
	
	public void draw(Graphics2D tela) {
		BufferedImage image = Arquivos.getZumbiImages().get(0);
		
		
		switch (getDirecao()) {
        case "cima":
            if (contMov2 < 10)
                image = Arquivos.getZumbiImages().get(3);
            else if (contMov2 <=15 )
            	image = Arquivos.getZumbiImages().get(2);
            break;
        case "baixo":
            if (contMov2  < 10)
            	image = Arquivos.getZumbiImages().get(1);
            else if (contMov2 <=15)
            	image = Arquivos.getZumbiImages().get(0);
            break;
        case "esquerda":
            if (contMov2 < 10)
            	image = Arquivos.getZumbiImages().get(7);
            else if (contMov2 <=15)
            	image = Arquivos.getZumbiImages().get(6);
            break;
        case "direita":
            if (contMov2 < 10)
            	image = Arquivos.getZumbiImages().get(4);
            else if (contMov2 <=15)
            	image = Arquivos.getZumbiImages().get(5);
            break;
        }
		contMov2 = (contMov2 + 1) % 15; 
		
		tela.drawImage(image, this.getX(), this.getY(), GamePanel.getTamanhoBloco(), GamePanel.getTamanhoBloco(), null);
	}
	
	public int getXinicial() {
		return xInicial;
	}

	public void setPosicaoX(int xInicial) {
		this.xInicial = xInicial;
	}

	public int getYinicial() {
		return yInicial;
	}

	public void setPosicaoY(int yInicial) {
		this.yInicial = yInicial;
	}
}
