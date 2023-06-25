package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class EntityFollower extends Follower {
	int xInicial;
	int yInicial;
	Entity followed = null;
	
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
		tela.setColor(Color.GREEN);
		tela.fillRect(this.getX(), this.getY(), getGamePanel().getTamanhoBloco(), getGamePanel().getTamanhoBloco());
	}
}
