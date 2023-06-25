package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import arquivos.Arquivos;

public class Player extends Personagem{

	//Propriedades
	private KeyboardInput keyInput;
	private int contadorFrames = 0;
	private int framesAnimacaoAndar = 30; 
	private int framesAnimacaoAtaque = 30; 
	private boolean atacando = false;
	private boolean andando = false;
	private Engine engine;
	
	//Construtor
	public Player(double vida, boolean invencivel, int velocidade, GamePanel gamePanel,
			KeyboardInput keyInput, Engine engine) {
		super(100, 100, gamePanel, vida, invencivel, velocidade, new Rectangle(50, 86, 28, 25));
		this.keyInput = keyInput;
		this.engine = engine;
	}

	//Getters e Setters
	public KeyboardInput getKeyInput() {
		return keyInput;
	}

	public void setKeyInput(KeyboardInput keyInput) {
		this.keyInput = keyInput;
	}

	//Métodos
	public void update() {
		if (keyInput.isXPressed() && !atacando) {
				atacando = true;
				andando = false;
				contadorFrames = 0;
		}
		else if (atacando && (contadorFrames % framesAnimacaoAtaque == framesAnimacaoAtaque - 1)) {
			atacando = false;
		}
		
		else if (!atacando && (keyInput.isUpPressed() || keyInput.isDownPressed() || keyInput.isLeftPressed() || keyInput.isRightPressed())) {
			andando = true;
			if(keyInput.isUpPressed()) {
				setDirecao("cima");
			}
			else if(keyInput.isDownPressed()) {
				setDirecao("baixo");
			}
			else if(keyInput.isLeftPressed()) {
				setDirecao("esquerda");
			}
			else if(keyInput.isRightPressed()) {
				setDirecao("direita");
			}

			setColidindo(false);
			engine.getColisaoChecker().checkColisao(this);
			if (getColidindo() == false) {
				switch(getDirecao()) {
					case "cima": moveCima(); break;
					case "baixo": moveBaixo(); break;
					case "esquerda": moveEsquerda(); break;
					case "direita": moveDireita(); break;
				}
			}
		}
		else andando = false;
		contadorFrames = contadorFrames + 1 % 60; 
	}
	
	BufferedImage getImagemAtaque() {
		switch (getDirecao()) {
		case "baixo":
			return Arquivos.getPlayerDownattack().get((contadorFrames % framesAnimacaoAtaque)/(framesAnimacaoAtaque/(Arquivos.getPlayerDownattack().size()-1)));
		case "cima":
			return Arquivos.getPlayerUpattack().get((contadorFrames % framesAnimacaoAtaque)/(framesAnimacaoAtaque/(Arquivos.getPlayerUpattack().size()-1)));
		case "direita":
			return Arquivos.getPlayerRightattack().get((contadorFrames % framesAnimacaoAtaque)/(framesAnimacaoAtaque/(Arquivos.getPlayerRightattack().size()-1)));
		case "esquerda":
			return Arquivos.getPlayerLeftattack().get((contadorFrames % framesAnimacaoAtaque)/(framesAnimacaoAtaque/(Arquivos.getPlayerLeftattack().size()-1)));
		}
		return Arquivos.getPlayerDownattack().get((contadorFrames % framesAnimacaoAtaque)/(framesAnimacaoAtaque/(Arquivos.getPlayerDownattack().size()-1)));
	}
	
	BufferedImage getImagemAndar() {
		switch (getDirecao()) {
		case "baixo":
			return Arquivos.getPlayerDownwalk().get((contadorFrames % framesAnimacaoAndar)/(framesAnimacaoAndar/(Arquivos.getPlayerDownwalk().size()-1)));
		case "cima":
			return Arquivos.getPlayerUpwalk().get((contadorFrames % framesAnimacaoAndar)/(framesAnimacaoAndar/(Arquivos.getPlayerUpwalk().size()-1)));
		case "direita":
			return Arquivos.getPlayerRightwalk().get((contadorFrames % framesAnimacaoAndar)/(framesAnimacaoAndar/(Arquivos.getPlayerRightwalk().size()-1)));
		case "esquerda":
			return Arquivos.getPlayerLeftwalk().get((contadorFrames % framesAnimacaoAndar)/(framesAnimacaoAndar/(Arquivos.getPlayerLeftwalk().size()-1)));
		}
		return Arquivos.getPlayerDownwalk().get((contadorFrames % framesAnimacaoAndar)/(framesAnimacaoAndar/(Arquivos.getPlayerDownwalk().size()-1)));
	}
	
	BufferedImage getImagemParado() {
		switch (getDirecao()) {
		case "baixo":
			return Arquivos.getPlayerDownwalk().get(Arquivos.getPlayerDownwalk().size() - 1);
		case "cima":
			return Arquivos.getPlayerUpwalk().get(Arquivos.getPlayerUpwalk().size() - 1);
		case "direita":
			return Arquivos.getPlayerRightwalk().get(Arquivos.getPlayerRightwalk().size() - 1);
		case "esquerda":
			return Arquivos.getPlayerLeftwalk().get(Arquivos.getPlayerLeftwalk().size() - 1);
		}
		return Arquivos.getPlayerDownwalk().get(Arquivos.getPlayerDownwalk().size() - 1);
	}
	
	public void draw(Graphics2D tela) {
		BufferedImage image = Arquivos.getPlayerDownwalk().get(Arquivos.getPlayerDownwalk().size()-1);
		int alturaImagem= 43*3;
		int larguraImagem = 43*3;
		
		if (atacando)
			image = getImagemAtaque();
		else if (andando)
			image = getImagemAndar();
		else image = getImagemParado();
		tela.drawImage(image, getX(), getY(), larguraImagem, alturaImagem, null);
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
