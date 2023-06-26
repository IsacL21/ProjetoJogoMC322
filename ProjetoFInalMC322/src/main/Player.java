package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import arquivos.Arquivos;

public class Player extends Personagem{

	//Propriedades
	private KeyboardInput keyInput;
	private int contadorFrames = 0;
	private int framesAnimacaoAndar = 30; 
	private int framesAnimacaoAtaque = 30; 
	private boolean atacando = false;
	private boolean andando = false;
	
	private Inventario inventario;
	
	//Construtor
	public Player(double vida, boolean invencivel, int velocidade, int capacidadeInventario, Engine engine, KeyboardInput keyInput) {
		super(100, 100, engine, vida, invencivel, velocidade);
		this.inventario = new Inventario(capacidadeInventario);
		this.keyInput = keyInput;
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
				andando = false;
				contadorFrames = 0;
		}
		
		if(keyInput.isCPressed()) {
			keyInput.resetaValores();
			
			for(int i = 0; i < inventario.getListaItens().size(); i++) {
				
				if(inventario.getListaItens().get(i).getNome().equals("pocao")) {
					Pocao pocao = (Pocao)inventario.getListaItens().get(i);
					setVida(getVida() + pocao.getVida());
					inventario.removeItem(i);
					break;
				}
			}
		}
		
		
		if(keyInput.isXPressed()) {
			/*Informacoes para quando tiver a colisao
			 *Quando colidir com os seguintes objetos e apertar X usar os metodos abaixo*/
			
			keyInput.resetaValores();
			
			if(getObjetoColidido() != null) {
				if(getObjetoColidido().getClass().getName().equals("main.Bau")) {
						
					abrirBau((Bau)getObjetoColidido());
					
				}
			}
			
			if(getObjetoColidido() != null) {
				if(getObjetoColidido().getClass().getName().equals("main.Porta")) {
						
					abrirPorta((Porta)getObjetoColidido());
					
				}
			}
			
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
			// setColidindo(engine.getColisaoChecker().checkColisaoInimigos(this, engine.getListaInimigos()));
			// boolean colisao = checarColisaoMapa(this);
			// boolean colisaoObjeto = checarColisaoEntidades(this, ;
			// boolean colisao = getEngine().getColisaoChecker().checkColisao(this, getEngine().getListaInimigos()); //&& colisaoObjeto;

			// if (colisaoPersonagem) {
			// 	levarDano();
			// }
			// setColidindo(false);

			// boolean colisao = getEngine().getColisaoChecker().checkColisao(this, getEngine().getListaInimigos());
			
			Personagem colisaoPersonagem = checarColisaoPersonagens(this, getEngine().getListaInimigos());
			boolean colisaoMapa = checarColisaoMapa(this);
			Entity colisaoEntidade = checarColisaoEntidades(this, getEngine().getListaEntidades());

			if(colisaoPersonagem == null && colisaoEntidade == null && colisaoMapa == false) {
				setColidindo(false);
				setObjetoColidido(null);
			}else {
				setObjetoColidido(colisaoEntidade);
				if(colisaoEntidade != null) {
					if(colisaoEntidade.getClass().getName().equals("main.Chave") ||
							colisaoEntidade.getClass().getName().equals("main.Pocao")) {
							
						coletaItem((Item)colisaoEntidade);
					}else if(colisaoEntidade.getClass().getName().equals("main.Porta")) {
						Porta porta = (Porta)colisaoEntidade;
						
						if(!porta.getTrancado()) {
							System.out.println("Passou de fase");
						}
					}
				}
				
				setColidindo(true);
			}
			
			if (getColidindo() == false) {
				switch(getDirecao()) {
					case "cima": moveCima(); break;
					case "baixo": moveBaixo(); break;
					case "esquerda": moveEsquerda(); break;
					case "direita": moveDireita(); break;
				}
				// updateHitBox();
			}
		}
		else andando = false;
		contadorFrames = contadorFrames + 1 % 60; 
	}
	
	BufferedImage[] getImagemBarraVida() {
		BufferedImage[] listaCoracoes = new BufferedImage[3];
		int vidaTemp = (int) getVida();
		for (int i = 0; i < 3; i++) {
			if (vidaTemp >= 2) {
				vidaTemp -= 2;
				listaCoracoes[i] = Arquivos.getPlayerBarraVida().get(0);
			}
			else if (vidaTemp == 1) {
				vidaTemp--;
				listaCoracoes[i] = Arquivos.getPlayerBarraVida().get(1);
			}
			else listaCoracoes[i] = Arquivos.getPlayerBarraVida().get(2);
		}
		return listaCoracoes;
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
		
		int alturaCoracao = 32;
		int larguraCoracao = 32;
		int xTemp = 0;
		
		for(BufferedImage i:getImagemBarraVida()) {
			tela.drawImage(i, xTemp, 0, larguraCoracao, alturaCoracao, null);
			xTemp += larguraCoracao;
		}
	}
	
	public void coletaItem(Item item) {
		
		inventario.addItem(item);
		
		for(int i = 0; i < getEngine().getListaEntidades().size(); i++) {
			
			if(getEngine().getListaEntidades().get(i) == item) {
				getEngine().getListaEntidades().remove(i);
				
			}
			
		}
		
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
		
		if(portaTeste.getTrancado()) {
			if(procuraChave()) {
				keyInput.resetaValores();
				portaTeste.setTrancado(false);
		}}
	}
	
	public boolean procuraChave(){
		
		for(int i = 0; i < inventario.getListaItens().size(); i++) {	
			if(inventario.getListaItens().get(i).getNome().equals("Chave")) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void causarDano() {
		
	}

	@Override
	public void levarDano() {
		
	}

	@Override
	public void morrer() {
		
		JOptionPane.showMessageDialog(null, "Derrotado!", "Perdeu", JOptionPane.INFORMATION_MESSAGE); 
		
		
	}

}
