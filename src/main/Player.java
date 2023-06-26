package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
<<<<<<< HEAD:src/main/Player.java
=======
import java.io.IOException;
import java.util.ArrayList;
>>>>>>> 015ed5f712515c86374fd1123b688e30847bdcb9:ProjetoFInalMC322/src/main/Player.java

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
	private Engine engine;
	

	private Inventario inventario;

	
	///////////////////Usado para testes para abrir, mas talvez vai ter que mudar quando tiver a colisao
	private ArrayList<Bau> bausAlcance = new ArrayList<Bau>();
	private Porta porta;

	
	//Construtor
	public Player(double vida, boolean invencivel, int velocidade, int capacidadeInventario, GamePanel gamePanel,
			KeyboardInput keyInput, Engine engine) {
		super(0, 0, gamePanel, vida, invencivel, velocidade, new Rectangle(50, 86, 28, 25));
		this.inventario = new Inventario(capacidadeInventario);
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
				andando = false;
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
			abrirBau(bausAlcance.get(0));
			
			///A mesma coisa aqui
			abrirPorta(porta);
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
