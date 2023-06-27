package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import arquivos.Arquivos;

public class Player extends Personagem{
	//Propriedades
	private final Rectangle hitBoxAtaqueCima = new Rectangle(30,54,60,30);
	private final Rectangle hitBoxAtaqueBaixo = new Rectangle(42,96,60,30);
	private final Rectangle hitBoxAtaqueEsquerda = new Rectangle(12,84,51,36);
	private final Rectangle hitBoxAtaqueDireita = new Rectangle(63,84,51,36);
	
	
	private KeyboardInput keyInput;
	private int framesAnimacaoAndar = 30; 
	private int framesAnimacaoAtaque = 16; 
	private boolean atacando = false;
	private boolean andando = false;
	private Inventario inventario;
	BufferedImage image = Arquivos.getPlayerDownwalk().get(Arquivos.getPlayerDownwalk().size()-1);
	private Entity objetoColidido = null;
	
	///////////////////Usado para testes para abrir, mas talvez vai ter que mudar quando tiver a colisao
	private ArrayList<Bau> bausAlcance = new ArrayList<Bau>();
	private Porta porta;

	
	//Construtor
	public Player(int vida, boolean invencivel, int velocidade, int capacidadeInventario, Engine engine, KeyboardInput keyInput) {
		super(100, 100, engine, vida, invencivel, velocidade,new Rectangle(50, 86, 28, 25));
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
		super.update();
		if (!isSofrendoKnockback()) {
			if (keyInput.isZPressed() && !atacando) {
					atacando = true;
					aplicaAtaque(getEngine().getListaInimigos());
					andando = false;
					setContadorFrames(0);
			}
			
			else if(keyInput.isCPressed()) {
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
			
			else if(keyInput.isXPressed()) {
				/*Informacoes para quando tiver a colisao
				 *Quando colidir com os seguintes objetos e apertar X usar os metodos abaixo*/
				
				
				////////Tem que ter um if aqui pra ver se teve colisao e com qual objeto bau ele colidiu. Feito isso, chama esse metodo
				abrirBau(bausAlcance.get(0));
				
				///A mesma coisa aqui
				abrirPorta(porta);
				
				keyInput.resetaValores();
				
	
				if(objetoColidido != null) {
					if(objetoColidido.getClass().getName().equals("main.Bau")) {
							
						abrirBau((Bau) objetoColidido);
						
					}
				}
				
				if(objetoColidido != null) {
					if(objetoColidido.getClass().getName().equals("main.Porta")) {
							
						abrirPorta((Porta) objetoColidido);
						
					}
				}
				
			}
			
			else if (atacando && (getContadorFrames() % framesAnimacaoAtaque == framesAnimacaoAtaque - 1)) {
				atacando = false;
			}
			
			else if (!atacando && (keyInput.isUpPressed() || keyInput.isDownPressed() || keyInput.isLeftPressed() || keyInput.isRightPressed())) {
				andando = true;
				if(keyInput.isUpPressed()) {
					setDirecao("cima");
					moveCima();
				}
				else if(keyInput.isDownPressed()) {
					setDirecao("baixo");
					moveBaixo();
				}
				else if(keyInput.isLeftPressed()) {
					setDirecao("esquerda");
					moveEsquerda();
				}
				else if(keyInput.isRightPressed()) {
					setDirecao("direita");
					moveDireita();
				}
				
			}
		else andando = false;
		}
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
			return Arquivos.getPlayerDownattack().get((getContadorFrames() % framesAnimacaoAtaque)/(framesAnimacaoAtaque/(Arquivos.getPlayerDownattack().size())));
		case "cima":
			return Arquivos.getPlayerUpattack().get((getContadorFrames() % framesAnimacaoAtaque)/(framesAnimacaoAtaque/(Arquivos.getPlayerUpattack().size())));
		case "direita":
			return Arquivos.getPlayerRightattack().get((getContadorFrames() % framesAnimacaoAtaque)/(framesAnimacaoAtaque/(Arquivos.getPlayerRightattack().size())));
		case "esquerda":
			return Arquivos.getPlayerLeftattack().get((getContadorFrames() % framesAnimacaoAtaque)/(framesAnimacaoAtaque/(Arquivos.getPlayerLeftattack().size())));
		}
		return Arquivos.getPlayerDownattack().get((getContadorFrames() % framesAnimacaoAtaque)/(framesAnimacaoAtaque/(Arquivos.getPlayerDownattack().size())));
	}
	
	BufferedImage getImagemAndar() {
		switch (getDirecao()) {
		case "baixo":
			return Arquivos.getPlayerDownwalk().get((getContadorFrames() % framesAnimacaoAndar)/(framesAnimacaoAndar/(Arquivos.getPlayerDownwalk().size())));
		case "cima":
			return Arquivos.getPlayerUpwalk().get((getContadorFrames() % framesAnimacaoAndar)/(framesAnimacaoAndar/(Arquivos.getPlayerUpwalk().size())));
		case "direita":
			return Arquivos.getPlayerRightwalk().get((getContadorFrames() % framesAnimacaoAndar)/(framesAnimacaoAndar/(Arquivos.getPlayerRightwalk().size())));
		case "esquerda":
			return Arquivos.getPlayerLeftwalk().get((getContadorFrames() % framesAnimacaoAndar)/(framesAnimacaoAndar/(Arquivos.getPlayerLeftwalk().size())));
		}
		return Arquivos.getPlayerDownwalk().get((getContadorFrames() % framesAnimacaoAndar)/(framesAnimacaoAndar/(Arquivos.getPlayerDownwalk().size())));
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
		
		int alturaImagem= 43*3;
		int larguraImagem = 43*3;
		
		if (!isSofrendoKnockback()) {
			if (atacando)
				image = getImagemAtaque();
			else if (andando)
				image = getImagemAndar();
			else image = getImagemParado();
		}
		
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

	
	public void usaPocao() {
		
		for(int i = 0; i<inventario.getListaItens().size(); i++) {
			
			if(inventario.getListaItens().get(i).getNome().equals("Pocao")) {

				Pocao pocao = (Pocao)inventario.getListaItens().get(i);
				setVida(Math.min(getVida() + pocao.getVida(), 6));
				inventario.getListaItens().remove(i);
				break;
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
		
		if(portaTeste.isTrancado()) {
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
	public void morrer() {
		JOptionPane.showMessageDialog(null, "Derrotado!", "Perdeu", JOptionPane.INFORMATION_MESSAGE); 
		getEngine().retornaFase();
	}

	@Override
	public void causarDano(Personagem personagem) {
		personagem.levarDano(1);		
	}
	
	public Entity checarColisaoEntidadesNeutras(ArrayList<Entity> listaEntidades) {
		int personagemLeftX = this.getX() + this.getHitBox().x;
        int personagemRightX = this.getX() + this.getHitBox().x + this.getHitBox().width;
        int personagemTopY = this.getY() + this.getHitBox().y;
        int personagemBottomY = this.getY() + this.getHitBox().y + this.getHitBox().height;

		switch(this.getDirecao()) {
            case "cima":
                personagemTopY -= this.getVelocidade();
                break;
            case "baixo":
                personagemBottomY += this.getVelocidade();
                break;
            case "esquerda":
                personagemLeftX -= this.getVelocidade();
                break;
            case "direita":
                personagemRightX += this.getVelocidade();
                break;
		}
		Rectangle hitBoxFutura = new Rectangle(personagemLeftX, personagemTopY, personagemRightX - personagemLeftX, personagemBottomY - personagemTopY);
		for (Entity entidade : listaEntidades) {
			Rectangle hitBoxEntidade = new Rectangle(entidade.getX() + entidade.getHitBox().x, entidade.getY() + entidade.getHitBox().y,
					entidade.getHitBox().width, entidade.getHitBox().height);
			if (hitBoxFutura.intersects(hitBoxEntidade)) {
				
				return entidade;
			}
		}
		return null;
	}
	
	public Inimigo checarColisaoInimigos(ArrayList<Inimigo> listaInimigos) {
		int personagemLeftX = this.getX() + this.getHitBox().x;
        int personagemRightX = this.getX() + this.getHitBox().x + this.getHitBox().width;
        int personagemTopY = this.getY() + this.getHitBox().y;
        int personagemBottomY = this.getY() + this.getHitBox().y + this.getHitBox().height;

		switch(this.getDirecao()) {
            case "cima":
                personagemTopY -= this.getVelocidade();
                break;
            case "baixo":
                personagemBottomY += this.getVelocidade();
                break;
            case "esquerda":
                personagemLeftX -= this.getVelocidade();
                break;
            case "direita":
                personagemRightX += this.getVelocidade();
                break;
		}
		Rectangle hitBoxFutura = new Rectangle(personagemLeftX, personagemTopY,
				personagemRightX - personagemLeftX, personagemBottomY - personagemTopY);
		for (Inimigo inimigo : listaInimigos) {
			Rectangle hitBoxInimigo = new Rectangle(inimigo.getX() + inimigo.getHitBox().x, inimigo.getY() + inimigo.getHitBox().y,
					inimigo.getHitBox().width, inimigo.getHitBox().height);
			if (hitBoxFutura.intersects(hitBoxInimigo)) {
				return inimigo;
			}
		}
		return null;
	}

	@Override
	public boolean checaColisoes() {
		//checa colisao com blocos
		boolean naoPodeAndar;
		naoPodeAndar = checarColisaoMapa();
		
		//checa colisao com objetos
		Entity entidadeColisao = checarColisaoEntidadesNeutras(getEngine().getListaEntidades());
		if(entidadeColisao == null) {
			objetoColidido = null;
		}else {
			naoPodeAndar = true;
			objetoColidido = entidadeColisao;
			if(entidadeColisao.getClass().getName().equals("main.Chave") ||
					entidadeColisao.getClass().getName().equals("main.Pocao")) {	
				coletaItem((Item )entidadeColisao);
			}
			else if(entidadeColisao.getClass().getName().equals("main.Porta")) {
				Porta porta = (Porta) entidadeColisao;
				if(!porta.isTrancado()) {
					getEngine().passaFase();
				}
			}
		}
		
		//checa colisao com inimigos
		Inimigo inimigo = checarColisaoInimigos(getEngine().getListaInimigos());
		if (inimigo != null && !getInvencivel()) {
			String direcaoKnockbackAux = null;
			switch(getDirecao()) {
			case "cima": direcaoKnockbackAux = "baixo"; break;
			case "baixo": direcaoKnockbackAux = "cima"; break;
			case "esquerda": direcaoKnockbackAux = "direita"; break;
			case "direita": direcaoKnockbackAux = "esquerda"; break;
			}
			setDirecaoKnockback(direcaoKnockbackAux);
			inimigo.causarDano(this);
			naoPodeAndar = true;
		}

		return naoPodeAndar;
	}

	public ArrayList<Inimigo> getInimigosInAttackRange(Rectangle hitBoxAtaque, ArrayList<Inimigo> listaInimigos){
		ArrayList<Inimigo> returnList = new ArrayList<Inimigo>();
		Rectangle hitBoxAtaqueReal = new Rectangle(getX() + hitBoxAtaque.x, getY() + hitBoxAtaque.y,hitBoxAtaque.width, hitBoxAtaque.height);
		for (Inimigo inimigo : listaInimigos) {
			Rectangle hitBoxInimigo = new Rectangle(inimigo.getX() + inimigo.getHitBox().x, inimigo.getY() + inimigo.getHitBox().y,
					inimigo.getHitBox().width, inimigo.getHitBox().height);
			if (hitBoxAtaqueReal.intersects(hitBoxInimigo)) {
				returnList.add(inimigo);
			}
		}
		return returnList;
	}
	
	public void aplicaAtaque(ArrayList<Inimigo> listaInimigos) {
		Rectangle hitBoxAtaque = hitBoxAtaqueBaixo; // so pra iniciar
		switch(getDirecao()) {
		case "cima": hitBoxAtaque = hitBoxAtaqueCima; break;
		case "baixo": hitBoxAtaque = hitBoxAtaqueBaixo; break;
		case "esquerda": hitBoxAtaque = hitBoxAtaqueEsquerda; break;
		case "direita": hitBoxAtaque = hitBoxAtaqueDireita; break;
		}
		for(Inimigo i : getInimigosInAttackRange(hitBoxAtaque, getEngine().getListaInimigos())) {
			//if(!i.getInvencivel()) {
				causarDano(i);
				i.setDirecaoKnockback(getDirecao());
			//}

				
		}
	}
}
