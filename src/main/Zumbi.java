package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.ArrayList;


import arquivos.Arquivos;


public class Zumbi extends Inimigo{
	
	private int numero = 0;
	private int xInicial;
	private int yInicial;
	private int contMov1 = 0;
	private static int counter = 0;
	

	public Zumbi(int x, int y, boolean invencivel, int velocidade, Engine engine, ArrayList<Item> listaDrops) {
		super(x, y, engine, 3, invencivel, velocidade, listaDrops, new Rectangle(8, 8, 32, 32));
		this.xInicial = x;
		this.yInicial = y;
	}
	
	public void update() {
		super.update();
		if (!isSofrendoKnockback()) {
			if(contMov1 == 59) {
				numero = 0;
				
			}else if(contMov1 >= 20) {
				
				if(numero == 0) {
					Random aleatorio = new Random();
					numero = 1 + aleatorio.nextInt((4 - 1) + 1);
				}else {
					
					if(numero == 1 && (getY()-yInicial)<120) {
							moveBaixo();
							setDirecao("baixo");
					
					}else if(numero == 2 && (getY()-yInicial)>-120) {
						moveCima();
						setDirecao("cima");
					
					}else if(numero == 3 && (getX()-xInicial)>-120) {
						moveEsquerda();
						setDirecao("esquerda");
					
					}else if((getX()-xInicial)<120){
						moveDireita();
						setDirecao("direita");
					}
				}
			}
			
			contMov1 = (contMov1 + 1) % 60; 
		}
	}
	
	public void draw(Graphics2D tela) {
		BufferedImage image = Arquivos.getZumbiImages().get(0);
		
		switch (getDirecao()) {
        case "cima":
            if (contMov1 < 50)
                image = Arquivos.getZumbiImages().get(2);
            else if (contMov1 >=50)
            	image = Arquivos.getZumbiImages().get(3);
            break;
        case "baixo":
            if (contMov1  < 50)
            	image = Arquivos.getZumbiImages().get(0);
            else if (contMov1 >=50)
            	image = Arquivos.getZumbiImages().get(1);
            break;
        case "esquerda":
            if (contMov1 < 50)
            	image = Arquivos.getZumbiImages().get(6);
            else if (contMov1 >=50)
            	image = Arquivos.getZumbiImages().get(7);
            break;
        case "direita":
            if (contMov1 < 50)
            	image = Arquivos.getZumbiImages().get(4);
            else if (contMov1 >=50)
            	image = Arquivos.getZumbiImages().get(5);
            break;
        }
		
		tela.drawImage(image, this.getX(), this.getY(), getGamePanel().getTamanhoBloco(), getGamePanel().getTamanhoBloco(), null);
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

	@Override
	public void morrer() {
		// TODO Auto-generated method stub
	}
}
