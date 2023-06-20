package main;

public class Engine implements Runnable{
	private GamePanel gamePanel;
	private KeyboardInput keyInput;
	private Thread gameThread;
	private Player player;
	private InimigoEletron inimigo;
	private MapBuilder mapBuilder;
	
	private final static int fps = 60;
	private final static double updateInterval = 1000/fps;
	
	public Engine() {
		keyInput = new KeyboardInput();
		gamePanel = new GamePanel(this);
		player = new Player(100, false, 3, gamePanel, keyInput, "down", 0);		
		inimigo = new InimigoEletron(100, false, 3, gamePanel, "down", 0);		
		mapBuilder = new MapBuilder();
	}
	
	public KeyboardInput getKeyInput() {
		return keyInput;
	}

	public Player getPlayer() {
		return player;
	}

	public InimigoEletron getInimigo() {
		return inimigo;
	}

	public void setInimigo(InimigoEletron inimigo) {
		this.inimigo = inimigo;
	}

	public MapBuilder getMapBuilder() {
		return mapBuilder;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void atualizaJogo() {
		player.update();
		inimigo.update();
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
