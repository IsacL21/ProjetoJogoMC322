package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import arquivos.Arquivos;

public class EntityFollower extends Slime {
	int xInicial;
	int yInicial;
	Entity followed = null;
	private int spriteNum = 0;
	private BufferedImage image = Arquivos.getSlimeimages().get(0);
	private String direcaoOlhar = "direita"; //necessario pois o slime so olha para algum lado horizontal
	
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
		if (getDirecao() == "direita" || getDirecao() == "esquerda")
			direcaoOlhar = getDirecao();
		
		updateFollowedPosition();
		this.longestFollow();
		
	}
	
	public void draw(Graphics2D tela) {
		

		if(direcaoOlhar.equals("esquerda")) {
			if(spriteNum < 10) {
				image = Arquivos.getSlimeimages().get(0);
			}else {
				image = Arquivos.getSlimeimages().get(1);
			}

		}else if(direcaoOlhar.equals("direita")) {
			if(spriteNum < 10) {
				image = Arquivos.getSlimeimages().get(2);
			}else {
				image = Arquivos.getSlimeimages().get(3);
			}
		}
		
		 spriteNum = (spriteNum + 1) % 20;
		tela.drawImage(image, this.getX(), this.getY(), GamePanel.getTamanhoBloco(), GamePanel.getTamanhoBloco(), null);
	}

	@Override
	public void morrer() {
		// TODO Auto-generated method stub
		
	}
}