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
	public Follower (int x, int y,Engine engine, double vida, boolean invencivel, int velocidade, ArrayList<Item> listaDrops, int followedX, int followedY) {
		super(x, y, engine, vida, invencivel, velocidade, listaDrops);
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
			setDirecao("baixo");;
			moveBaixo();
			return 1;
		}
		else if ((followedY + this.getVelocidade() < this.getY())){
			setDirecao("cima");
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
	public void causarDano(Personagem player) {
		// TODO Auto-generated method stub
		
		/////////////////////Vejam como vao calcular o dano do personagem ja que ele tem armadura e etc
		int dano = 0;
		player.levarDano(dano);

	}


	@Override
	public void draw(Graphics2D tela) {

	}

	@Override
	public void update() {

	}
}