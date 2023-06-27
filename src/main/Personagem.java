package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Personagem extends Entity implements Atualizavel{
	
	//Propriedades
	private int vida;
	private boolean invencivel = false;
	private int velocidade;
	private boolean sofrendoKnockback = false;
	private String direcao = "baixo";
	private String direcaoKnockback = null;
	private String direcaoAntesKnockback;
	private int contadorFrames = 0;

	
	//Construtor
	public Personagem(int x, int y, Engine engine, int vida, boolean invencivel, int velocidade, Rectangle hitBox) {
		super(x, y, false, engine, hitBox);
		this.vida = vida;
		this.invencivel = invencivel;
		this.velocidade = velocidade;
	}

	//Getters e Setters
	public int getVida() {
		return vida;
	}
	
	public void setVida(int vida) {
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
	
	public boolean isSofrendoKnockback() {
		return sofrendoKnockback;
	}

	public void setSofrendoKnockback(boolean sofrendoKnockback) {
		this.sofrendoKnockback = sofrendoKnockback;
	}

	public String getDirecao() {
		return direcao;
	}
	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}

	public String getDirecaoKnockback() {
		return direcaoKnockback;
	}
	
	public void setDirecaoKnockback(String direcaoKnockback) {
		this.direcaoKnockback = direcaoKnockback;
	}

	public int getContadorFrames() {
		return contadorFrames;
	}

	public void setContadorFrames(int contadorFrames) {
		this.contadorFrames = contadorFrames;
	}

	//Métodos
	public abstract void causarDano(Personagem personagem);

	public void levarDano(int danoRecebido) {
		
		////////////////////////A funcao levar dano retorna true quando o personagem morre
		///////////////////////Usem isso para apagar o personagem da lista de personagens
		
		///////////////////////O player fecha o jogo ao morrer
		if(!invencivel) {
			setVida(getVida()-danoRecebido);
			invencivel = true;
		}
		if(getVida() <= 0) {
			morrer();
		}
		
	}

	public abstract void morrer();
	
	public boolean moveCima() {
		boolean returnValue;
		if (!(returnValue = checaColisoes()))
			setY(getY() - velocidade);
		return returnValue;
	}
	
	public boolean moveBaixo() {
		boolean returnValue;
		if (!(returnValue = checaColisoes()))
			setY(getY() + velocidade);
		return returnValue;
	}
	
	public boolean moveEsquerda() {
		boolean returnValue;
		if (!(returnValue = checaColisoes()))
			setX(getX() - velocidade);
		return returnValue;
		
	}
	
	public boolean moveDireita() {
		boolean returnValue;
		if (!(returnValue = checaColisoes()))
			setX(getX() + velocidade);
		return returnValue;
		
	}
	
	
	public abstract void draw(Graphics2D tela);
	
	public boolean checarColisaoMapa() {
		int personagemLeftX = this.getX() + this.getHitBox().x;
        int personagemRightX = this.getX() + this.getHitBox().x + this.getHitBox().width;
        int personagemTopY = this.getY() + this.getHitBox().y;
        int personagemBottomY = this.getY() + this.getHitBox().y + this.getHitBox().height;

        int personagemLeftCol = personagemLeftX/Mapa.TAMANHO_BLOCO.getPosicao();
        int personagemRightCol = personagemRightX/Mapa.TAMANHO_BLOCO.getPosicao();
        int personagemTopRow = personagemTopY/Mapa.TAMANHO_BLOCO.getPosicao();
        int personagemBottomRow = personagemBottomY/Mapa.TAMANHO_BLOCO.getPosicao();

        int tileNum1 = 0, tileNum2 = 0;
        switch(this.getDirecao()) {
            case "cima":
                personagemTopY -= this.getVelocidade();
                personagemTopRow = personagemTopY/Mapa.TAMANHO_BLOCO.getPosicao();
                tileNum1 = getEngine().getMapBuilder().getMapa()[personagemTopRow][personagemLeftCol];
                tileNum2 = getEngine().getMapBuilder().getMapa()[personagemTopRow][personagemRightCol];
                break;
            case "baixo":
                personagemBottomY += this.getVelocidade();
                personagemBottomRow = personagemBottomY/Mapa.TAMANHO_BLOCO.getPosicao();
                tileNum1 = getEngine().getMapBuilder().getMapa()[personagemBottomRow][personagemLeftCol];
                tileNum2 = getEngine().getMapBuilder().getMapa()[personagemBottomRow][personagemRightCol];
                break;
            case "esquerda":
                personagemLeftX -= this.getVelocidade();
                personagemLeftCol = personagemLeftX/Mapa.TAMANHO_BLOCO.getPosicao();
                tileNum1 = getEngine().getMapBuilder().getMapa()[personagemTopRow][personagemLeftCol];
                tileNum2 = getEngine().getMapBuilder().getMapa()[personagemBottomRow][personagemLeftCol];
                break;
            case "direita":
                personagemRightX += this.getVelocidade();
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

	
	
	public abstract boolean checaColisoes();
	
	public void update() {
		contadorFrames = (contadorFrames + 1) % 60; 
		if (direcaoKnockback != null) {
			if (!sofrendoKnockback) {
				direcaoAntesKnockback = direcao;
				direcao = direcaoKnockback;
				contadorFrames = 0;
				sofrendoKnockback = true;
				velocidade *= 2;
			}
			switch(direcao) {
			case "cima": moveCima();System.out.println("Tentando mover"); break;
			case "baixo": moveBaixo();System.out.println("Tentando mover"); break;
			case "esquerda": moveEsquerda();System.out.println("Tentando mover"); break;
			case "direita": moveDireita();System.out.println("Tentando mover"); break;
			}
			
			if (contadorFrames == 10) {
				velocidade /= 2;
				sofrendoKnockback = false;
				invencivel = false;
				direcaoKnockback = null;
				direcao = direcaoAntesKnockback;
			}
				
		}
			
	}
}
