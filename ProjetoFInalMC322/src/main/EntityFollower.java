package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import arquivos.Arquivos;

public class EntityFollower extends Follower {
	Entity followed = null;
	private int spriteNum = 0;
	private BufferedImage image = Arquivos.getSlimeimages().get(0);
	private String direcaoOlhar = "direita"; //necessario pois o slime so olha para algum lado horizontal
	
	public EntityFollower (int x, int y, GamePanel gamePanel, double vida, boolean invencivel, int velocidade, ArrayList<Item> listaDrops, int followedX, int followedY, Entity followed) {
		super(x, y, gamePanel, vida, invencivel, velocidade, listaDrops, followedX, followedY);
		this.followed = followed;
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
		// tela.setColor(Color.GREEN);
		tela.fillRect(this.getX(), this.getY(), getGamePanel().getTamanhoBloco(), getGamePanel().getTamanhoBloco());
		

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
		}else{
			
			
		}
		
		 spriteNum = (spriteNum + 1) % 20;
		tela.drawImage(image, this.getX(), this.getY(), getGamePanel().getTamanhoBloco(), getGamePanel().getTamanhoBloco(), null);
	}
}