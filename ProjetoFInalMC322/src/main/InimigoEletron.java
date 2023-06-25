package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class InimigoEletron extends Inimigo{
	
	private int numero = 0;
	private int spriteNum = 0;
	private int xInicial;
	private int yInicial;

	public InimigoEletron(int xInicial, int yInicial, double vida, int velocidade, GamePanel gamePanel) {
		super(xInicial, yInicial, vida, false, velocidade, gamePanel);
		this.xInicial = xInicial;
		this.yInicial = yInicial;
	}
	
	public void update() {
		
		if(spriteNum == 59) {
			numero = 0;
			
		}else if(spriteNum >= 50) {
			
			if(numero == 0) {
				
				Random aleatorio = new Random();
				numero = 1 + aleatorio.nextInt((4 - 1) + 1);
				
			}else if(numero == 1 && (getY()-yInicial)<40) {
				moveDown();
			}else if(numero == 2 && (getY()-yInicial)>-40) {
				moveUp();
			}else if(numero == 3 && (getX()-xInicial)>-40) {
				moveLeft();
			}else if((getX()-xInicial)<40){
				moveRight();
			}
		}
		
		spriteNum = (spriteNum + 1) % 60; 

	}
	
	public void draw(Graphics2D tela) {
		tela.setColor(Color.black);
		tela.fillRect(getX(), getY(), 48, 48);
	}
	
	@Override
	public void causarDano() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void levarDano() {
		// TODO Auto-generated method stub
		
	}

}
