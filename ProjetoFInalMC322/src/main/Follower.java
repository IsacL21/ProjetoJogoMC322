package main;

import java.util.Random;

public class Follower extends Personagem {

	//Propriedades
	private int followedX;
	private int followedY;
	
	//Construtor
	public Follower (GamePanel gamePanel, int xInicial, int yInicial, int speed, int followedX, int followedY) {
		super(xInicial, yInicial, gamePanel, 0, false, speed);
		this.followedX = followedX;
		this.followedY = followedY;
	}
	
	public void setFollowedPoint(int x, int y) {
		followedX = x;
		followedY = y;
	}
	
	public int followInX() {
		if (followedX - this.getVelocidade() > this.getX() ) {
			setDirecao("right");
			moveRight();
			return 1;
		}
		else if ((followedX + this.getVelocidade() < this.getX())){
			setDirecao("left");
			moveLeft();
			return -1;
		}
		else return 0;
	}
	
	public int followInY() {
		if (followedY - this.getVelocidade() > this.getY()) {
			setDirecao("down");
			moveDown();
			return 1;
		}
		else if ((followedY + this.getVelocidade() < this.getY())){
			setDirecao("up");
			moveUp();
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