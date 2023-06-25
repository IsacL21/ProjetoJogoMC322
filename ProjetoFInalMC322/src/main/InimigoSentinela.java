package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class InimigoSentinela extends Follower{
	int xInicial;
	int yInicial;
	int xFinal;
	int yFinal;
	String followedPoint;
	
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
		tela.setColor(Color.BLUE);
		tela.fillRect(this.getX(), this.getY(), getGamePanel().getTamanhoBloco(), getGamePanel().getTamanhoBloco());
	}
	
}
