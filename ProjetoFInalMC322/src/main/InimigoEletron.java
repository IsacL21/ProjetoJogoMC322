package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import arquivos.Arquivos;

public class InimigoEletron extends Personagem{
	
	private GamePanel gamePanel;
	private int numero = 0;
	private int posicaoX, posicaoY;
	private String direcao = "down";
	private int contMov1 = 0, contMov2 = 0;
	
	 static int counter = 0;
	
	public InimigoEletron(int posicaoX, int posicaoY, double vida, boolean invencivel, int velocidade, GamePanel gamePanel,
			String direction, int spriteNum) {
		super(posicaoX, posicaoY, vida, invencivel, velocidade);
		this.gamePanel = gamePanel;
		this.direcao = direction;
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
	}
	
	public void update() {
		
		if(contMov1 == 59) {
			numero = 0;
			
		}else if(contMov1 >= 50) {
			
			if(numero == 0) {
				Random aleatorio = new Random();
				numero = 1 + aleatorio.nextInt((4 - 1) + 1);
				
			}else if(numero == 1 && (getY()-posicaoY)<40) {
				moveDown();
				direcao = "baixo";
			}else if(numero == 2 && (getY()-posicaoY)>-40) {
				moveUp();
			}else if(numero == 3 && (getX()-posicaoX)>-40) {
				moveLeft();
				direcao = "esquerda";
			}else if((getX()-posicaoX)<40){
				moveRight();
				direcao = "direita";
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
        case "up":
            if (contMov2 < 10)
                image = Arquivos.getZumbiImages().get(3);
            else if (contMov2 <=15 )
            	image = Arquivos.getZumbiImages().get(2);
            break;
        case "down":
            if (contMov2  < 10)
            	image = Arquivos.getZumbiImages().get(1);
            else if (contMov2 <=15)
            	image = Arquivos.getZumbiImages().get(0);
            break;
        case "left":
            if (contMov2 < 10)
            	image = Arquivos.getZumbiImages().get(7);
            else if (contMov2 <=15)
            	image = Arquivos.getZumbiImages().get(6);
            break;
        case "right":
            if (contMov2 < 10)
            	image = Arquivos.getZumbiImages().get(4);
            else if (contMov2 <=15)
            	image = Arquivos.getZumbiImages().get(5);
            break;
        }
		contMov2 = (contMov2 + 1) % 15; 
		
		tela.drawImage(image, this.getX(), this.getY(), GamePanel.getTamanhoBloco(), GamePanel.getTamanhoBloco(), null);
	}
	
	public int getPosicaoX() {
		return posicaoX;
	}

	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}

	public int getPosicaoY() {
		return posicaoY;
	}

	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}

	public String getDirecao() {
		return direcao;
	}

	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}

}
