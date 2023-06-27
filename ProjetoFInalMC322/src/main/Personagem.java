package main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Rectangle;

public abstract class Personagem extends Entity implements Atualizavel{
	
	//Propriedades
	private double vida;
	private boolean invencivel;
	private int velocidade;
	private boolean colidindo;
	private String direcao = "down";
	private Entity objetoColidido;
	
	//Construtor
	public Personagem(int x, int y, Engine engine, double vida, boolean invencivel, int velocidade) {
		super(x, y, false, engine);
		this.vida = vida;
		this.invencivel = invencivel;
		this.velocidade = velocidade;
		setHitBox(new Rectangle(50, 86, 28, 25));
		this.objetoColidido = null;
	}

	public Entity getObjetoColidido() {
		return objetoColidido;
	}

	public void setObjetoColidido(Entity objetoColidido) {
		this.objetoColidido = objetoColidido;
	}

	//Getters e Setters
	public double getVida() {
		return vida;
	}
	
	public void setVida(double vida) {
		this.vida = vida;
	}
	public boolean getInvencivel() {
		return invencivel;
	}
	public void setInvencivel(boolean invencivel) {
		this.invencivel = invencivel;
	}
	public int getVelocidade() {
		return velocidade;
	}
	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}
	public boolean getColidindo() {
		return colidindo;
	}
	public void setColidindo(boolean colisao) {
		this.colidindo = colisao;
	}
	public String getDirecao() {
		return direcao;
	}
	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}

	//Métodos
	public abstract void causarDano(Personagem personagem);

	public abstract boolean levarDano(int dano);

	public abstract void morrer();
	
	public void moveCima() {
		setY(getY() - velocidade);
	}
	
	public void moveBaixo() {
		setY(getY() + velocidade);
	}
	
	public void moveEsquerda() {
		setX(getX() - velocidade);
	}
	
	public void moveDireita() {
		setX(getX() + velocidade);
	}
	
	public abstract void update();
	
	public abstract void draw(Graphics2D tela);

	public boolean checarColisaoMapa(Personagem personagem) {
		int personagemLeftX = personagem.getX() + personagem.getHitBox().x;
        int personagemRightX = personagem.getX() + personagem.getHitBox().x + personagem.getHitBox().width;
        int personagemTopY = personagem.getY() + personagem.getHitBox().y;
        int personagemBottomY = personagem.getY() + personagem.getHitBox().y + personagem.getHitBox().height;

        int personagemLeftCol = personagemLeftX/Mapa.TAMANHO_BLOCO.getPosicao();
        int personagemRightCol = personagemRightX/Mapa.TAMANHO_BLOCO.getPosicao();
        int personagemTopRow = personagemTopY/Mapa.TAMANHO_BLOCO.getPosicao();
        int personagemBottomRow = personagemBottomY/Mapa.TAMANHO_BLOCO.getPosicao();

        int tileNum1 = 0, tileNum2 = 0;
        switch(personagem.getDirecao()) {
            case "cima":
                personagemTopY -= personagem.getVelocidade();
                personagemTopRow = personagemTopY/Mapa.TAMANHO_BLOCO.getPosicao();
                tileNum1 = getEngine().getMapBuilder().getMapa()[personagemTopRow][personagemLeftCol];
                tileNum2 = getEngine().getMapBuilder().getMapa()[personagemTopRow][personagemRightCol];
                break;
            case "baixo":
                personagemBottomY += personagem.getVelocidade();
                personagemBottomRow = personagemBottomY/Mapa.TAMANHO_BLOCO.getPosicao();
                tileNum1 = getEngine().getMapBuilder().getMapa()[personagemBottomRow][personagemLeftCol];
                tileNum2 = getEngine().getMapBuilder().getMapa()[personagemBottomRow][personagemRightCol];
                break;
            case "esquerda":
                personagemLeftX -= personagem.getVelocidade();
                personagemLeftCol = personagemLeftX/Mapa.TAMANHO_BLOCO.getPosicao();
                tileNum1 = getEngine().getMapBuilder().getMapa()[personagemTopRow][personagemLeftCol];
                tileNum2 = getEngine().getMapBuilder().getMapa()[personagemBottomRow][personagemLeftCol];
                break;
            case "direita":
                personagemRightX += personagem.getVelocidade();
                personagemRightCol = personagemRightX/Mapa.TAMANHO_BLOCO.getPosicao();
                tileNum1 = getEngine().getMapBuilder().getMapa()[personagemTopRow][personagemRightCol];
                tileNum2 = getEngine().getMapBuilder().getMapa()[personagemBottomRow][personagemRightCol];
                break;
        }
        if (getEngine().getMapBuilder().getBlocos()[tileNum1].isColidivel() || getEngine().getMapBuilder().getBlocos()[tileNum2].isColidivel()) {
            return true;
        }
		return false;
	}

	public Personagem checarColisaoPersonagens(Personagem personagem, ArrayList<Inimigo> listaPersonagens) {
		int personagemLeftX = personagem.getX() + personagem.getHitBox().x;
        int personagemRightX = personagem.getX() + personagem.getHitBox().x + personagem.getHitBox().width;
        int personagemTopY = personagem.getY() + personagem.getHitBox().y;
        int personagemBottomY = personagem.getY() + personagem.getHitBox().y + personagem.getHitBox().height;

        switch(personagem.getDirecao()) {
            case "cima":
                personagemTopY -= personagem.getVelocidade();
                break;
            case "baixo":
                personagemBottomY += personagem.getVelocidade();
                break;
            case "esquerda":
                personagemLeftX -= personagem.getVelocidade();
                break;
            case "direita":
                personagemRightX += personagem.getVelocidade();
                break;
        }
		Rectangle hitBoxFutura = new Rectangle(personagemLeftX, personagemTopY, personagemRightX - personagemLeftX, personagemBottomY - personagemTopY);
		for (Personagem personagem2 : listaPersonagens) {
			Rectangle hitBoxPersonagem2 = new Rectangle(personagem2.getX(), personagem2.getY(), 48, 48);
			if (hitBoxFutura.intersects(hitBoxPersonagem2)) {
				return personagem2;
			}
		}
		return null;
	}
	
	public boolean checarColisaoPlayer(Personagem personagem, Player player) {
		int personagemLeftX = personagem.getX() + personagem.getHitBox().x;
        int personagemRightX = personagem.getX() + personagem.getHitBox().x + personagem.getHitBox().width;
        int personagemTopY = personagem.getY() + personagem.getHitBox().y;
        int personagemBottomY = personagem.getY() + personagem.getHitBox().y + personagem.getHitBox().height;

        switch(personagem.getDirecao()) {
            case "cima":
                personagemTopY -= personagem.getVelocidade();
                break;
            case "baixo":
                personagemBottomY += personagem.getVelocidade();
                break;
            case "esquerda":
                personagemLeftX -= personagem.getVelocidade();
                break;
            case "direita":
                personagemRightX += personagem.getVelocidade();
                break;
        }
		Rectangle hitBoxFutura = new Rectangle(personagemLeftX, personagemTopY, personagemRightX - personagemLeftX, personagemBottomY - personagemTopY);
			Rectangle hitBoxPersonagem2 = new Rectangle(player.getX(), player.getY(), 48, 48);
			if (hitBoxFutura.intersects(hitBoxPersonagem2)) {
				return true;
			}
		return false;
	}

	public Entity checarColisaoEntidades(Personagem personagem, ArrayList<Entity> listaEntidades) {
		int personagemLeftX = personagem.getX() + personagem.getHitBox().x;
        int personagemRightX = personagem.getX() + personagem.getHitBox().x + personagem.getHitBox().width;
        int personagemTopY = personagem.getY() + personagem.getHitBox().y;
        int personagemBottomY = personagem.getY() + personagem.getHitBox().y + personagem.getHitBox().height;

		switch(personagem.getDirecao()) {
            case "cima":
                personagemTopY -= personagem.getVelocidade();
                break;
            case "baixo":
                personagemBottomY += personagem.getVelocidade();
                break;
            case "esquerda":
                personagemLeftX -= personagem.getVelocidade();
                break;
            case "direita":
                personagemRightX += personagem.getVelocidade();
                break;
		}
		Rectangle hitBoxFutura = new Rectangle(personagemLeftX, personagemTopY, personagemRightX - personagemLeftX, personagemBottomY - personagemTopY);
		for (Entity entidade : listaEntidades) {
			Rectangle hitBoxEntidade = new Rectangle(entidade.getX(), entidade.getY(), 48, 48);
			if (hitBoxFutura.intersects(hitBoxEntidade)) {
				
				return entidade;
			}
		}
		return null;
	}
}
