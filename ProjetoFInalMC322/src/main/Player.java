package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import arquivos.Arquivos;

public class Player extends Personagem{

	//Propriedades
	private KeyboardInput keyInput;
	private int contadorFrames = 0;
	private int framesAnimacaoAndar = 20; 
	private int framesAnimacaoAtaque = 30; 
	private Engine engine;
	private boolean atacando = false;
	private Inventario inventario;
	
	///////////////////Usado para testes para abrir, mas talvez vai ter que mudar quando tiver a colisao
	private ArrayList<Bau> bausMapa = new ArrayList<Bau>();
	private Porta porta;
	
	//Construtor
	public Player(double vida, boolean invencivel, int velocidade, int capacidadeInventario, GamePanel gamePanel,
			KeyboardInput keyInput, String direcao, Engine engine, ArrayList<Bau> bausMapa, Porta porta) {
		super(100, 100, vida, invencivel, velocidade, gamePanel);
		this.inventario = new Inventario(capacidadeInventario);
		this.porta = porta;
		this.bausMapa = bausMapa;
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
		if (keyInput.isZPressed() && !atacando) {
				atacando = true;
				contadorFrames = 0;
		}
		
		if(keyInput.isVPressed()) {
			mostrarVida();
		}
		
		if(keyInput.isCPressed()) {
			usaPocao();
		}
		
		if(keyInput.isXPressed()) {
			
			/*Informacoes para quando tiver a colisao
			 *Quando colidir com os seguintes objetos e apertar X usar os metodos abaixo*/
			
			////////Tem que ter um if aqui pra ver se teve colisao e com qual objeto bau ele colidiu. Feito isso, chama esse metodo
			abrirBau(bausMapa.get(0));
			
			///A mesma coisa aqui
			abrirPorta(porta);
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
	
	public void mostrarVida() {
		
		keyInput.resetaValores();
		JOptionPane.showMessageDialog(null, "Vida do personagem: "+getVida(), "Vida", JOptionPane.INFORMATION_MESSAGE); 		
		
	}
	
	public void usaPocao() {
		
		for(int i = 0; i<inventario.getListaItens().size(); i++) {
			
			if(inventario.getListaItens().get(i).getNome().equals("Pocao")) {

				Pocao pocao = (Pocao)inventario.getListaItens().get(i);
				setVida(getVida() + pocao.getVida());
				inventario.getListaItens().remove(i);
				break;
			}
		}
	}
	
	public boolean procuraChave(){
		
		for(int i = 0; i < inventario.getListaItens().size(); i++) {	
			if(inventario.getListaItens().get(i).getNome().equals("Chave")) {
				return true;
			}
		}
		return false;
	}
	
	public void coletaItem(Item item) {
		
		inventario.addItem(item);
		
	}
	
	public void abrirBau(Bau bau) {
		
		if(bau.isTrancado()) {
			bau.setTrancado(false);
			coletaItem(bau.getItem());
			keyInput.resetaValores();
			keyInput.SetisXPressed(false);
			JOptionPane.showMessageDialog(null, "Voce coletou: "+bau.getItem().getNome(), "Coleta de item", JOptionPane.INFORMATION_MESSAGE); 
		}
	}
	
	public void abrirPorta(Porta portaTeste) {
		
		if(portaTeste.isTrancado()) {
			if(procuraChave()) {
				keyInput.resetaValores();
				portaTeste.setTrancado(false);
		}}
	}
	
	@Override
	public void causarDano(Personagem inimigo) {
		// TODO Auto-generated method stub
		
		/////////////////////Vejam como vao calcular o que o player ir fazer nos mobs ja que ele tem espada e etc
		int dano = 0;
		inimigo.levarDano(dano);
		
	}

	@Override
	public boolean levarDano(int danoRecebido) {
		// TODO Auto-generated method stub
		
		////////////////////////A funcao levar dano retorna true quando o personagem morre
		///////////////////////Usem isso para apagar o personagem da lista de personagens
		
		///////////////////////O player fecha o jogo ao morrer
		setVida(getVida()-danoRecebido);
		
		if(getVida() <= 0) {
			return true;
		}
		
		return false;
		
	}

	@Override
	public void morrer() {
		// TODO Auto-generated method stub
		
		JOptionPane.showMessageDialog(null, "Derrotado!", "Perdeu", JOptionPane.INFORMATION_MESSAGE); 
        System.exit(0); // Encerra o processo atual
        
	}

}
