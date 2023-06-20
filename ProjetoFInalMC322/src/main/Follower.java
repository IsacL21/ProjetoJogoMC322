package main;

import java.util.Random;

public class Follower extends Entity{
	private GamePanel gamePanel;
	int followedX;
	int followedY;
	String direction = "Right";
	
	public Follower (GamePanel gamePanel, int xInicial, int yInicial, int speed, int followedX, int followedY) {
		super(xInicial, yInicial, speed);
		this.followedX = followedX;
		this.followedY = followedY;
		this.gamePanel = gamePanel;	
	}
	
	public void setFollowedPoint(int x, int y) {
		followedX = x;
		followedY = y;
	}
	
	
	
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int followInX() {
		if (followedX - this.getSpeed() > this.getX() ) {
			direction = "right";
			this.moveRight();
			return 1;
		}
		else if ((followedX + this.getSpeed() < this.getX())){
			direction = "left";
			this.moveLeft();
			return -1;
		}
		else return 0;
	}
	
	public int followInY() {
		if (followedY - this.getSpeed() > this.getY()) {
			direction = "down";
			this.moveDown();
			return 1;
		}
		else if ((followedY + this.getSpeed() < this.getY())){
			direction = "up";
			this.moveUp();
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
}