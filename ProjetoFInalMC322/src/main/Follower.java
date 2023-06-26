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
	public boolean levarDano(int danoRecebido) {
		
		////////////////////////A funcao levar dano retorna true quando o personagem morre
		///////////////////////Usem isso para apagar o personagem da lista de personagens
		
		setVida(getVida()-danoRecebido);
		
		if(getVida() <= 0) {
			return true;
		}
		
		return false;
		
	}

	@Override
	public void draw(Graphics2D tela) {

	}

	@Override
	public void update() {

	}
}