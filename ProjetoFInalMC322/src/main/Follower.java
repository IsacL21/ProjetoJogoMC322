package main;

import java.awt.Rectangle;
import java.util.Random;

public abstract class Follower extends Inimigo{
	int followedX;
	int followedY;
	
	public Follower (GamePanel gamePanel, int xInicial, int yInicial, int speed, int followedX, int followedY, Rectangle hitBox) {
		super(xInicial, yInicial, 0, false, speed, gamePanel, hitBox);
		this.followedX = followedX;
		this.followedY = followedY;
	}
	
	public void setFollowedPoint(int x, int y) {
		followedX = x;
		followedY = y;
	}

	public int followInX() {
		if (followedX - this.getVelocidade() > this.getX() ) {
			setDirecao("direita");
			this.moveDireita();
			return 1;
		}
		else if ((followedX + this.getVelocidade() < this.getX())){
			setDirecao("esquerda");
			this.moveEsquerda();
			return -1;
		}
		else return 0;
	}
	
	public int followInY() {
		if (followedY - this.getVelocidade() > this.getY()) {
			setDirecao("baixo");
			this.moveBaixo();
			return 1;
		}
		else if ((followedY + this.getVelocidade() < this.getY())){
			setDirecao("cima");
			this.moveCima();
			return -1;
		}
		else return 0;
	}
	
	public void randomFollow() {
		Random random = new Random();
		int number = random.nextInt(2);
		
		if (number == 0)
			followInY();
		else followInX();
	}
	
	public void longestFollow() {
		if (Math.abs(followedY - this.getY()) > (Math.abs(followedX - this.getX())))
			followInY();
		else
		followInX();
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