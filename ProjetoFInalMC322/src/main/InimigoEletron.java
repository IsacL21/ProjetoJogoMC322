package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class InimigoEletron extends Inimigo{
	
	private GamePanel gamePanel;
	private int numero = 0;
	
	private String direction = "down";
	private int spriteNum = 0;
	
	 static int counter = 0;
	
	public InimigoEletron(int xInicial, int yInicial, double vida, boolean invencivel, int velocidade, GamePanel gamePanel) {
		super(xInicial, yInicial, vida, invencivel, velocidade);
		this.gamePanel = gamePanel;
	}
	
	public void update() {
		
		if(spriteNum == 59) {
			numero = 0;
			
		}else if(spriteNum >= 50) {
			
			if(numero == 0) {
				
				Random aleatorio = new Random();
				numero = 1 + aleatorio.nextInt((4 - 1) + 1);
				
			}else if(numero == 1) {
				moveDown();
			}else if(numero == 2) {
				moveUp();
			}else if(numero == 3) {
				moveLeft();
			}else {
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
