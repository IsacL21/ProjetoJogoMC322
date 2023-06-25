package main;

import java.util.ArrayList;

import arquivos.Arquivos;

public class Engine implements Runnable{
	private GamePanel gamePanel;
	private KeyboardInput keyInput;
	private Thread gameThread;
	private Player player;
	private MapBuilder mapBuilder;
	private ColisaoChecker colisaoChecker;
	private ArrayList<Bau> bausMapa = new ArrayList<Bau>();
	private Porta porta;
	private Morcego morcego;
	private Item item;
	private Pocao pocao;
	
	private ArrayList<Inimigo> listaInimigos;	

	private final static int fps = 60;
	private final static double updateInterval = 1000/fps;
	
	public Engine() {
		keyInput = new KeyboardInput();
		gamePanel = new GamePanel(this);
		mapBuilder = new MapBuilder(Arquivos.getVetorMapa());
		listaInimigos = new ArrayList<Inimigo> ();
		porta = new Porta(336, 0, true);
		
		///Vai ter que atualizar na criacao de mapa
		morcego = new Morcego(gamePanel, 100, 100, 2, 200, 200);
		bausMapa.add((new Bau(200, 200, false)));
		
		player = new Player(100, false, 3, 5, gamePanel, keyInput, "baixo", this, bausMapa, porta);		
		
		item = new Item(500, 500, false);
		pocao = new Pocao(350, 300, false);
		
		carregaMobs();
		colisaoChecker = new ColisaoChecker(this);
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

	public ColisaoChecker getColisaoChecker() {
		return colisaoChecker;
	}
	
	public ArrayList<Inimigo> getListaInimigos() {
		return listaInimigos;
	}
	
	public Porta getPorta() {
		return porta;
	}

	public void setPorta(Porta porta) {
		this.porta = porta;
	}

	public ArrayList<Bau> getBausMapa() {
		return bausMapa;
	}

	public void setBausMapa(ArrayList<Bau> bausMapa) {
		this.bausMapa = bausMapa;
	}

	public Morcego getMorcego() {
		return morcego;
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Pocao getPocao() {
		return pocao;
	}

	public void setPocao(Pocao pocao) {
		this.pocao = pocao;
	}

	public void setMorcego(Morcego morcego) {
		this.morcego = morcego;
	}

	public void carregaMobs() {
		for (ArrayList<Integer> mob : Arquivos.getVetorMobs()) {
			if (mob.get(0) == 1)
				listaInimigos.add(new Zumbi(mob.get(1) * gamePanel.getTamanhoBloco(), mob.get(2) * gamePanel.getTamanhoBloco(), 100, 3, gamePanel));
			
			if (mob.get(0) == 2)
				listaInimigos.add(new EntityFollower(gamePanel, mob.get(1) * gamePanel.getTamanhoBloco(), mob.get(2) * gamePanel.getTamanhoBloco(), 2, player));
		
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
