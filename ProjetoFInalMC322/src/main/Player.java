package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import arquivos.Arquivos;

public class Player extends Personagem{

	//Propriedades
	private GamePanel gamePanel;
	private KeyboardInput keyInput;
	private int spriteNum = 0;
	private Engine engine;
	private boolean atacando = false;
	
	//Construtor
	public Player(double vida, boolean invencivel, int velocidade, GamePanel gamePanel,
			KeyboardInput keyInput, String direcao, int spriteNum, Engine engine) {
		super(100, 100, vida, invencivel, velocidade);
		this.gamePanel = gamePanel;
		this.keyInput = keyInput;
		this.spriteNum = spriteNum;
		this.engine = engine;
	}

	//Getters e Setters
	public KeyboardInput getKeyInput() {
		return keyInput;
	}

	public void setKeyInput(KeyboardInput keyInput) {
		this.keyInput = keyInput;
	}

	//Métodos
	public void update() {
		if (keyInput.isXPressed()) {
			if (atacando == false)
				spriteNum = 0
		}
		
		if (keyInput.isUpPressed() || keyInput.isDownPressed() || keyInput.isLeftPressed() || keyInput.isRightPressed()) {
			
			if(keyInput.isUpPressed()) {
				setDirecao("up");
			}
			else if(keyInput.isDownPressed()) {
				setDirecao("down");
			}
			else if(keyInput.isLeftPressed()) {
				setDirecao("left");
			}
			else if(keyInput.isRightPressed()) {
				setDirecao("right");
			}

			setColisao(false);
			engine.getColisaoChecker().checkColisao(this);
			if (getColisao() == false) {
				switch(getDirecao()) {
					case "up": moveUp(); break;
					case "down": moveDown(); break;
					case "left": moveLeft(); break;
					case "right": moveRight(); break;
				}
			}
		}
	}
	
	public void draw(Graphics2D tela) {
		BufferedImage image = Arquivos.getPlayerimages().get(2);
		
		switch (getDirecao()) {
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
