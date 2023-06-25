package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import arquivos.Arquivos;

public class Player extends Personagem{

	//Propriedades
	private KeyboardInput keyInput;
	private int contadorFrames = 0;
	private int framesAnimacaoAndar = 20; 
	private int framesAnimacaoAtaque = 30; 
	private Engine engine;
	private boolean atacando = false;
	
	//Construtor
	public Player(double vida, boolean invencivel, int velocidade, GamePanel gamePanel,
			KeyboardInput keyInput, String direcao, Engine engine) {
		super(100, 100, vida, invencivel, velocidade, gamePanel);
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
				contadorFrames = 0;
		}
		else if (atacando && (contadorFrames % framesAnimacaoAtaque == framesAnimacaoAtaque - 1)) {
			atacando = false;
		}
		
		else if (!atacando && (keyInput.isUpPressed() || keyInput.isDownPressed() || keyInput.isLeftPressed() || keyInput.isRightPressed())) {
			
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

			setColisao(false);
			engine.getColisaoChecker().checkColisao(this);
			if (getColisao() == false) {
				switch(getDirecao()) {
					case "cima": moveCima(); break;
					case "baixo": moveBaixo(); break;
					case "esquerda": moveEsquerda(); break;
					case "direita": moveDireita(); break;
				}
			}
		}
		contadorFrames = contadorFrames + 1 % 60; 
	}
	
	public void draw(Graphics2D tela) {
		BufferedImage image = Arquivos.getPlayerimages().get(0);
		int alturaImagem= 42;
		int larguraImagem = 26;
		int imageX = getX();
		int imageY = getY();
		
		switch (getDirecao()) {
        case "cima":
            if (atacando) {
            	alturaImagem *= 2;
            	imageY -= getGamePanel().getTamanhoBloco();
            	if (contadorFrames % framesAnimacaoAndar < framesAnimacaoAndar/2)
            		image = Arquivos.getPlayerimages().get(10);
            	else image = Arquivos.getPlayerimages().get(11);
            }
            else if ((contadorFrames % framesAnimacaoAndar < framesAnimacaoAndar/2) && keyInput.isUpPressed())
                image = Arquivos.getPlayerimages().get(3);
            else
            	image = Arquivos.getPlayerimages().get(2);
            break;
        case "baixo":
        	if (atacando) {
        		alturaImagem *= 2;
            	if (contadorFrames % framesAnimacaoAtaque < framesAnimacaoAtaque/2)
            		image = Arquivos.getPlayerimages().get(8);
            	else image = Arquivos.getPlayerimages().get(9);
            }
        	else if ((contadorFrames % framesAnimacaoAndar < framesAnimacaoAndar/2) && keyInput.isDownPressed())
            	image = Arquivos.getPlayerimages().get(1);
            else
            	image = Arquivos.getPlayerimages().get(0);
            break;
        case "esquerda":
        	if (atacando) {
            	if (contadorFrames % framesAnimacaoAtaque < framesAnimacaoAtaque/2)
            		image = Arquivos.getPlayerimages().get(14);
            	else {
            		imageX -= larguraImagem;
            		larguraImagem *= 2;
            		image = Arquivos.getPlayerimages().get(15);
            	}
            }
        	else if ((contadorFrames % framesAnimacaoAndar < framesAnimacaoAndar/2) && keyInput.isLeftPressed())
            	image = Arquivos.getPlayerimages().get(7);
            else
            	image = Arquivos.getPlayerimages().get(6);
            break;
        case "direita":
        	if (atacando) {
            	if (contadorFrames % framesAnimacaoAtaque < framesAnimacaoAtaque/2)
            		image = Arquivos.getPlayerimages().get(12);
            	else {
            		larguraImagem *= 2;
            		image = Arquivos.getPlayerimages().get(13);
            	}
            }
        	else if ((contadorFrames % framesAnimacaoAndar < framesAnimacaoAndar/2) && keyInput.isRightPressed())
            	image = Arquivos.getPlayerimages().get(5);
            else
            	image = Arquivos.getPlayerimages().get(4);
            break;
        }
		
		tela.drawImage(image, imageX, imageY, larguraImagem, alturaImagem, null);
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
