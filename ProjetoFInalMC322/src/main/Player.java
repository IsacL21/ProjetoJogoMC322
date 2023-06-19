package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity{
	private GamePanel gamePanel;
	private KeyboardInput keyInput;
	
	private BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	
	private String direction = "down";
	private int spriteNum = 0;

	public Player(GamePanel gamePanel, KeyboardInput keyInput) {
		super(100,100,3);
		this.gamePanel = gamePanel;
		this.keyInput = keyInput;
		this.getPlayerImage();
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_right_2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		BufferedImage image = down1;
		
		switch (direction) {
        case "up":
            if (spriteNum < 10 && keyInput.isUpPressed())
                image = up2;
            else if (spriteNum <=20 )
            	image = up1;
            break;
        case "down":
            if (spriteNum < 10 && keyInput.isDownPressed())
            	image = down2;
            else if (spriteNum <=20)
            	image = down1;
            break;
        case "left":
            if (spriteNum < 10 && keyInput.isLeftPressed())
            	image = left2;
            else if (spriteNum <=20)
            	image = left1;
            break;
        case "right":
            if (spriteNum < 10 && keyInput.isRightPressed())
            	image = right2;
            else if (spriteNum <=20)
            	image = right1;
            break;
        }
        spriteNum = (spriteNum + 1) % 20; 
		
		tela.drawImage(image, this.getX(), this.getY(), GamePanel.getTamanhoBloco(), GamePanel.getTamanhoBloco(), null);
	}
}
