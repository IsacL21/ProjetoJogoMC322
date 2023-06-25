package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import arquivos.Arquivos;

public class InimigoSentinela extends Follower{
	private int xInicial;
	private int yInicial;
	private int xFinal;
	private int yFinal;
	private int spriteNum;
	private String followedPoint;
	
	public InimigoSentinela (GamePanel gamePanel, int xInicial, int yInicial, int speed, int xFinal, int yFinal) {
		super(gamePanel, xInicial, yInicial, speed, xFinal, yFinal);
		
		this.xInicial = xInicial;
		this.xFinal = xFinal;
		this.yInicial = yInicial;
		this.yFinal = yFinal;
		
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
        case "up":
            if (spriteNum < 10)
                image = Arquivos.getMorcegoimages().get(3);
            else if (spriteNum <=20 )
            	image = Arquivos.getMorcegoimages().get(2);
            break;
        case "down":
            if (spriteNum < 10)
            	image = Arquivos.getMorcegoimages().get(1);
            else if (spriteNum <=20)
            	image = Arquivos.getMorcegoimages().get(0);
            break;
        case "left":
            if (spriteNum < 10)
            	image = Arquivos.getMorcegoimages().get(7);
            else if (spriteNum <=20)
            	image = Arquivos.getMorcegoimages().get(6);
            break;
        case "right":
            if (spriteNum < 10)
            	image = Arquivos.getMorcegoimages().get(4);
            else if (spriteNum <=20)
            	image = Arquivos.getMorcegoimages().get(5);
            break;
        }
        spriteNum = (spriteNum + 1) % 20; 
		
		tela.drawImage(image, this.getX(), this.getY(), GamePanel.getTamanhoBloco(), GamePanel.getTamanhoBloco(), null);
		
		
	}
	
}
