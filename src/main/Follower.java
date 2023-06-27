package main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.util.Random;

public abstract class Follower extends Inimigo {
	int followedX;
	int followedY;
	String direction = "direita";
	

	//Construtor
	public Follower (int x, int y, Engine engine, int vida, boolean invencivel, int velocidade, Rectangle hitBox, ArrayList<Item> listaDrops, int followedX, int followedY) {
		super(x, y, engine, vida, invencivel, velocidade, listaDrops, hitBox);
		this.followedX = followedX;
		this.followedY = followedY;
	}
	
	public void setFollowedPoint(int x, int y) {
		followedX = x;
		followedY = y;
	}

	public boolean followInX() {
		if (followedX - this.getVelocidade() > this.getX() ) {
			setDirecao("direita");
			this.moveDireita();
			return true;
		}
		else if ((followedX + this.getVelocidade() < this.getX())){
			setDirecao("esquerda");
			this.moveEsquerda();
			return true;
		}
		else return false;
	}
	
	public boolean followInY() {
		if (followedY - this.getVelocidade() > this.getY()) {
			setDirecao("baixo");;
			moveBaixo();
			return true;
		}
		else if ((followedY + this.getVelocidade() < this.getY())){
			setDirecao("cima");
			moveCima();
			return true;
		}
		else return false;
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