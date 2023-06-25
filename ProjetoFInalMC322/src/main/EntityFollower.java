package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import arquivos.Arquivos;

public class EntityFollower extends Follower {
	int xInicial;
	int yInicial;
	Entity followed = null;
	private int spriteNum = 0;
	private BufferedImage image = Arquivos.getSlimeimages().get(0);
	
	public EntityFollower (GamePanel gamePanel, int xInicial, int yInicial, int speed, Entity followed) {
		super(gamePanel, xInicial, yInicial, speed, xInicial, yInicial);
		this.followed = followed;
		this.xInicial = xInicial;
		this.yInicial = yInicial;
	}
	
	private void updateFollowedPosition(){
		this.setFollowedPoint(followed.getX(), followed.getY());
	}
	
	public void update() {
		updateFollowedPosition();
		this.longestFollow();
	}
	
	public void draw(Graphics2D tela) {
		
		if(getDirection().equals("left")) {
			if(spriteNum < 10) {
				image = Arquivos.getSlimeimages().get(0);
			}else {
				image = Arquivos.getSlimeimages().get(1);
			}
		}else if(getDirection().equals("right")) {
			if(spriteNum < 10) {
				image = Arquivos.getSlimeimages().get(2);
			}else {
				image = Arquivos.getSlimeimages().get(3);
			}
		}else{
			
			
		}
		
		 spriteNum = (spriteNum + 1) % 20;
		tela.drawImage(image, this.getX(), this.getY(), GamePanel.getTamanhoBloco(), GamePanel.getTamanhoBloco(), null);
	}
}