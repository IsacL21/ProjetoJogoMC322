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
	private ArrayList<Inimigo> listaInimigos;

	//private ArrayList<Entity> listaEntidades;

	
	private final static int fps = 60;
	private final static double updateInterval = 1000/fps;
	
	//Construtor
	public Engine(int mapa_atual) {
		keyInput = new KeyboardInput();
		gamePanel = new GamePanel(this);
		player = new Player(100, false, 3, gamePanel, keyInput, this);		
		mapBuilder = new MapBuilder(Arquivos.getVetorMapa(mapa_atual), gamePanel);
		listaInimigos = new ArrayList<Inimigo> ();
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

	public void carregaMobs(int mapa_atual) {
		for (ArrayList<Integer> mob : Arquivos.getVetorMobs(mapa_atual)) {
			if (mob.get(0) == 1) {
				listaInimigos.add(new InimigoEletron(mob.get(1) * gamePanel.getTamanhoBloco(), mob.get(2) * gamePanel.getTamanhoBloco(), 100, false, 3, gamePanel, new ArrayList<Item>()));
				System.out.println("novo eletron");
			}
			if (mob.get(0) == 2)
				listaInimigos.add(new EntityFollower(mob.get(1) * gamePanel.getTamanhoBloco(), mob.get(2) * gamePanel.getTamanhoBloco(), gamePanel, 100, false, 2, new ArrayList<Item>(), 1, 1, player));
		}
	}

	public void startGameThread() {  
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void atualizaJogo() {
		for (Personagem i : listaInimigos)
			i.update();
		player.update();
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
