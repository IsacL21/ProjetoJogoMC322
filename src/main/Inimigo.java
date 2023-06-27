package main;

import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Inimigo extends Personagem{

	//Propriedades
	private ArrayList<Item> listaDrops = new ArrayList<Item>();
	
	//Construtor
	public Inimigo(int x, int y, Engine engine, int vida, boolean invencivel, int velocidade, ArrayList<Item> listaDrops, Rectangle hitBox) {
		super(x, y, engine, vida, invencivel, velocidade, hitBox);
		this.listaDrops = listaDrops;
	}
	
	//Getters e Setters
	public ArrayList<Item> getListaDrops() {
		return listaDrops;
	}

	public void setListaDrops(ArrayList<Item> listaDrops) {
		this.listaDrops = listaDrops;
	}

	//Métodos
	
	@Override
	public void causarDano(Personagem personagem) {
		personagem.levarDano(1);
	}
	
	@Override
	public void morrer() {
		getEngine().getListaInimigos().remove(this);
		
	}
	
	
	
	public boolean checarColisaoPlayer(Player player) {
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
		Rectangle hitBoxPlayer = new Rectangle(player.getX() + player.getHitBox().x, player.getY() + player.getHitBox().y,
				player.getHitBox().width, player.getHitBox().height);
			if (hitBoxFutura.intersects(hitBoxPlayer)) {
				return true;
			}
		return false;
	}

	public boolean checaColisoes() {
		///checa colisao com blocos
		boolean naoPodeAndar;
		naoPodeAndar = checarColisaoMapa();

		//checa colisao com o player
		Player player = getEngine().getPlayer();
		if (checarColisaoPlayer(player) && !player.getInvencivel()) {
			causarDano(player);
			player.setDirecaoKnockback(getDirecao());
			naoPodeAndar = true;
		}
		
		return naoPodeAndar;
	}
}
