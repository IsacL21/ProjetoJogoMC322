package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import arquivos.Arquivos;

public class Player extends Personagem{
	private GamePanel gamePanel;
	private KeyboardInput keyInput;
	
	private String direction = "down";
	private int spriteNum = 0;
	
	public Player(double vida, boolean invencivel, int velocidade, GamePanel gamePanel,
			KeyboardInput keyInput, String direction, int spriteNum) {
		super(100, 100, 3, vida, invencivel, velocidade);
		this.gamePanel = gamePanel;
		this.keyInput = keyInput;
		this.direction = direction;
		this.spriteNum = spriteNum;
	}

	public void update() {
		if (keyInput.isUpPressed()) {
			
			if(!colissionWallUP()) {
				moveUp();
			}
			direction = "up";
		}
			
		else if (keyInput.isDownPressed()) {
			
			if(!colissionWallDown()) {
				moveDown();
			}
			direction = "down";
			
		}
		else if (keyInput.isLeftPressed()) {
			
			if(!colissionWallLeft()) {
				moveLeft();
			}
			direction = "left";
		}
			
		else if (keyInput.isRightPressed()) {
			
			if(!colissionWallRight()) {
				moveRight();
			}
			
			direction = "right";
		}	
	}
	
	public void draw(Graphics2D tela) {
		BufferedImage image = Arquivos.getPlayerimages().get(2);
		
		switch (direction) {
        case "up":
            if (spriteNum < 10 && keyInput.isUpPressed())
                image = Arquivos.getPlayerimages().get(1);
            else if (spriteNum <=20 )
            	image = Arquivos.getPlayerimages().get(0);
            break;
        case "down":
            if (spriteNum < 10 && keyInput.isDownPressed())
            	image = Arquivos.getPlayerimages().get(3);
            else if (spriteNum <=20)
            	image = Arquivos.getPlayerimages().get(2);;
            break;
        case "left":
            if (spriteNum < 10 && keyInput.isLeftPressed())
            	image = Arquivos.getPlayerimages().get(5);
            else if (spriteNum <=20)
            	image = Arquivos.getPlayerimages().get(4);
            break;
        case "right":
            if (spriteNum < 10 && keyInput.isRightPressed())
            	image = Arquivos.getPlayerimages().get(7);
            else if (spriteNum <=20)
            	image = Arquivos.getPlayerimages().get(6);
            break;
        }
        spriteNum = (spriteNum + 1) % 20; 
		
		tela.drawImage(image, this.getX(), this.getY(), GamePanel.getTamanhoBloco(), GamePanel.getTamanhoBloco(), null);
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
