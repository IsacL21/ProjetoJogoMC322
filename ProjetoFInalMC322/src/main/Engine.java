package main;

import java.util.ArrayList;

import arquivos.Arquivos;

public class Engine implements Runnable{

	//Propriedades
	private GamePanel gamePanel;
	private KeyboardInput keyInput;
	private Thread gameThread;
	private Player player;
	private MapBuilder mapBuilder;
	private ColisaoChecker colisaoChecker;
	private int estadoJogo = 0; //utilizado para congelar jogo em menus, estado normal = 0
	
	private ArrayList<Inimigo> listaInimigos;
	private ArrayList<Entity> listaEntidades;

	private final static int fps = 60;
	private final static double updateInterval = 1000/fps;
	
	//Construtor
	public Engine(int mapa_atual) {
		keyInput = new KeyboardInput();
		gamePanel = new GamePanel(this);
		player = new Player(100, false, 3, 5, this, keyInput);		
		mapBuilder = new MapBuilder(Arquivos.getVetorMapa(mapa_atual), gamePanel);

		listaInimigos = new ArrayList<Inimigo> ();
		listaEntidades = new ArrayList<Entity> ();
		
		
		
		carregaMobs(mapa_atual);

		colisaoChecker = new ColisaoChecker(this);
	}
	
	//Getters e Setters
	public KeyboardInput getKeyInput() {
		return keyInput;
	}

	public Player getPlayer() {
		return player;
	}

	public MapBuilder getMapBuilder() {
		return mapBuilder;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public ColisaoChecker getColisaoChecker() {
		return colisaoChecker;
	}
	
	public ArrayList<Inimigo> getListaInimigos() {
		return listaInimigos;
	}
	
	public void setEstadoJogo(int estadoJogo) {
		this.estadoJogo = estadoJogo;
		System.out.println(this.estadoJogo);
	}
	


	public ArrayList<Entity> getListaEntidades() {
		return listaEntidades;
	}

	public void carregaMobs(int mapa_atual) {
		for (ArrayList<Integer> entidade : Arquivos.getVetorEntidades(mapa_atual)) {
			if (entidade.get(0) == 1) 
				listaInimigos.add(new Zumbi(entidade.get(1) * gamePanel.getTamanhoBloco(), entidade.get(2) * gamePanel.getTamanhoBloco(), 100, false, 3, this, new ArrayList<Item>()));
			if (entidade.get(0) == 2)
				listaInimigos.add(new Slime(entidade.get(1) * gamePanel.getTamanhoBloco(), entidade.get(2) * gamePanel.getTamanhoBloco(), this, 100, false, 2, new ArrayList<Item>(), player));
			if (entidade.get(0) == 3)
				listaInimigos.add(new Morcego(entidade.get(1) * gamePanel.getTamanhoBloco(), entidade.get(2) * gamePanel.getTamanhoBloco(), this, 100, false, 2, new ArrayList<Item>(), entidade.get(3) * gamePanel.getTamanhoBloco(), entidade.get(4) * gamePanel.getTamanhoBloco(), player));
			if(entidade.get(0) == 4)
				listaEntidades.add(new Porta(entidade.get(1) * gamePanel.getTamanhoBloco(), entidade.get(2) * gamePanel.getTamanhoBloco(), true, this, true));
			if(entidade.get(0) == 5)
				listaEntidades.add(new Pocao(entidade.get(1) * gamePanel.getTamanhoBloco(), entidade.get(2) * gamePanel.getTamanhoBloco(), this));
			if(entidade.get(0) == 6)
				listaEntidades.add(new Chave(entidade.get(1) * gamePanel.getTamanhoBloco(), entidade.get(2) * gamePanel.getTamanhoBloco(), this));
			if(entidade.get(0) == 7) {
					Item tempItem = null;
					if (entidade.get(3) == 5)
						tempItem = new Chave(-100, -100, this);
					if (entidade.get(3) == 6)
						tempItem = new Chave(-100, -100, this);
					listaEntidades.add(new Bau(entidade.get(1) * gamePanel.getTamanhoBloco(),entidade.get(2) * gamePanel.getTamanhoBloco(),this, false, tempItem));
			}
			if (entidade.get(0) == 0) {
				player.setX(entidade.get(1)* gamePanel.getTamanhoBloco());
				player.setY(entidade.get(2)* gamePanel.getTamanhoBloco());
			}
		}
	}

	public void startGameThread() {  
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void atualizaJogo() {
		if (estadoJogo == 0) { //updates do modo normal
		// for (Personagem i : listaInimigos)
		// 	i.update();
		player.update();
		}
		else if (estadoJogo == 1) { //modo de menu
			
			}
	}
	
	@Override
	public void run() {
		double delta = 0;
		long lastTime = System.currentTimeMillis();
		long currentTime;
		long timer = 0;
		int framesDesenhados = 0;
		
		while(gameThread != null) {
			 currentTime = System.currentTimeMillis();
			 delta += (currentTime - lastTime) / updateInterval; 
			 timer += (currentTime - lastTime);
			 lastTime = currentTime;
			 if (timer >= 1000) { //um segundo passou
				System.out.println("FPS:" + framesDesenhados);
				framesDesenhados = 0;
				timer = 0;
			}
			
			if (delta >= 1) {
				atualizaJogo();
				gamePanel.repaint();
				framesDesenhados++;
				delta--;
			}
		}
		
	}

}
