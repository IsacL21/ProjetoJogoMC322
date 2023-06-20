package main;

import java.util.ArrayList;

public class Engine implements Runnable{
	private GamePanel gamePanel;
	private KeyboardInput keyInput;
	private Thread gameThread;
	private Player player;
	private MapBuilder mapBuilder;
	private InimigoSentinela inimigoSentinela;
	private EntityFollower inimigoPerseguidor;
	//private ArrayList<Entity> listaEntidades;
	
	private final static int fps = 60;
	private final static double updateInterval = 1000/fps;
	
	public Engine() {
		keyInput = new KeyboardInput();
		gamePanel = new GamePanel(this);
		player = new Player(gamePanel, keyInput);
		mapBuilder = new MapBuilder();
		inimigoSentinela = new InimigoSentinela(gamePanel, 150, 150, 3, 350, 500);
		inimigoPerseguidor = new EntityFollower(gamePanel, 400, 400, 2, player);
	}
	
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

	public InimigoSentinela getInimigoSentinela() {
		return inimigoSentinela;
	}
	

	public EntityFollower getInimigoPerseguidor() {
		return inimigoPerseguidor;
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void atualizaJogo() {
		inimigoSentinela.update();
		inimigoPerseguidor.update();
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
