package main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.util.Random;

public abstract class Follower extends Inimigo {
	int followedX;
	int followedY;
	String direction = "Right";

	//Construtor
	public Follower (int x, int y, GamePanel gamePanel, double vida, boolean invencivel, int velocidade, Rectangle hitBox, ArrayList<Item> listaDrops, int followedX, int followedY) {
		super(x, y, gamePanel, vida, invencivel, velocidade, hitBox, listaDrops);
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
			direction = "down";
			moveBaixo();
			return 1;
		}
		else if ((followedY + this.getVelocidade() < this.getY())){
			direction = "up";
			moveCima();
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

	@Override
	public void draw(Graphics2D tela) {

	}

	@Override
	public void update() {

	}
}