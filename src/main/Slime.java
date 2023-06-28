package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import arquivos.Arquivos;


public class Slime extends Follower {

	//Propriedades
	Personagem followed = null;
	private int spriteNum = 0;
	private BufferedImage image = Arquivos.getSlimeimages().get(0);
	private String direcaoOlhar = "direita"; //necessario pois o slime so olha para algum lado horizontal
	
	//Construtor
	public Slime (int x, int y, Engine engine, boolean invencivel, int velocidade, ArrayList<Item> listaDrops, Personagem followed) {
		super(x, y, engine, 3, invencivel, velocidade, new Rectangle(8, 8, 32, 32), listaDrops, x, y);
		this.followed = followed;
	}

	//Getters e Setters
	public Personagem getFollowed() {
		return followed;
	}

	public void setFollowed(Personagem followed) {
		this.followed = followed;
	}

	public int getSpriteNum() {
		return spriteNum;
	}

	public void setSpriteNum(int spriteNum) {
		this.spriteNum = spriteNum;
	}

	public String getDirecaoOlhar() {
		return direcaoOlhar;
	}

	public void setDirecaoOlhar(String direcaoOlhar) {
		this.direcaoOlhar = direcaoOlhar;
	}
	
	//Métodos
	private void updateFollowedPosition(){
		this.setFollowedPoint(followed.getX() + followed.getHitBox().x, followed.getY() + followed.getHitBox().y);
	}
	
	public void update() {
		super.update();
		if (!isSofrendoKnockback()) {
			if (getDirecao() == "direita" || getDirecao() == "esquerda")
				direcaoOlhar = getDirecao();
			updateFollowedPosition();
			this.longestFollow();
		}
	}
	
	public void draw(Graphics2D tela) {	
		if(direcaoOlhar.equals("esquerda")) {
			if(spriteNum < 10) {
				image = Arquivos.getSlimeimages().get(0);
			}
			else {
				image = Arquivos.getSlimeimages().get(1);
			}
		}
		else if(direcaoOlhar.equals("direita")) {
			if(spriteNum < 10) {
				image = Arquivos.getSlimeimages().get(2);
			}
			else {
				image = Arquivos.getSlimeimages().get(3);
			}
		}
		spriteNum = (spriteNum + 1) % 20;
		tela.drawImage(image, this.getX(), this.getY(), getGamePanel().getTamanhoBloco(), getGamePanel().getTamanhoBloco(), null);
	}
}