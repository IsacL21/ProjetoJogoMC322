package main;

import java.util.Random;

public abstract class Slime extends Inimigo{
	int followedX;
	int followedY;
	
	public Slime (GamePanel gamePanel, int xInicial, int yInicial, int speed, int followedX, int followedY) {
		super(xInicial, yInicial, 0, false, speed, gamePanel);
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
}