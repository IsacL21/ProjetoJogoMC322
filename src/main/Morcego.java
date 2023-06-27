package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import arquivos.Arquivos;

public class Morcego extends Follower{
	private int xInicial;
	private int yInicial;
	private int xFinal;
	private int yFinal;
	private int spriteNum;
	private String followedPoint;
	

	public Morcego(int x, int y, Engine engine, int vida, boolean invencivel, int velocidade, ArrayList<Item> listaDrops, int followedX, int followedY, Entity followed) {
		super(x, y, engine, vida, invencivel, velocidade, new Rectangle(20, 20, 5, 5), listaDrops, x, y);
		this.xInicial = x;
		this.xFinal = followedX;
		this.yInicial = y;
		this.yFinal = followedY;
		followedPoint = "inicial";
	}

	private void switchFollowedPoint() {
		if (followedPoint == "inicial") {
			this.setFollowedPoint(xFinal, yFinal);
			followedPoint = "final";
		}
		else if (followedPoint == "final") {
			this.setFollowedPoint(xInicial, yInicial);
			followedPoint = "inicial";
		}
	}
	
	public void update() {
		if (this.followInX() == 0)
			if (this.followInY() == 0)
				switchFollowedPoint();
	}
	
	public void draw(Graphics2D tela) {
		
		BufferedImage image = Arquivos.getMorcegoimages().get(0);
		

		switch (getDirecao()) {
		case "cima":
            if (spriteNum < 10) {
                image = Arquivos.getMorcegoimages().get(3);
            }else if (spriteNum <=20)
            	image = Arquivos.getMorcegoimages().get(2);
            break;
        case "baixo":
            if (spriteNum < 10)
            	image = Arquivos.getMorcegoimages().get(1);
            else if (spriteNum <=20)
            	image = Arquivos.getMorcegoimages().get(0);
            break;
        case "esquerda":
            if (spriteNum < 10)
            	image = Arquivos.getMorcegoimages().get(7);
            else if (spriteNum <=20)
            	image = Arquivos.getMorcegoimages().get(6);
            break;
        case "direita":
            if (spriteNum < 10)
            	image = Arquivos.getMorcegoimages().get(4);
            else if (spriteNum <=20)
            	image = Arquivos.getMorcegoimages().get(5);
            break;
        }
        spriteNum = (spriteNum + 1) % 20; 
		
		tela.drawImage(image, this.getX(), this.getY(), 40, 40, null);
		
	}

	@Override
	public void morrer() {
		// TODO Auto-generated method stub
		
	}
	
}
