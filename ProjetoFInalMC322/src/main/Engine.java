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

	private ArrayList<Bau> bausMapa = new ArrayList<Bau>();
	private Porta porta;
	private Item item;
	private Pocao pocao;
	
	private ArrayList<Inimigo> listaInimigos;

	private final static int fps = 60;
	private final static double updateInterval = 1000/fps;
	
	//Construtor
	public Engine() {
		keyInput = new KeyboardInput();
		gamePanel = new GamePanel(this);
		player = new Player(100, false, 3, 5, gamePanel, keyInput, this, bausMapa, porta);		
		mapBuilder = new MapBuilder(Arquivos.getVetorMapa(), gamePanel);

		listaInimigos = new ArrayList<Inimigo> ();
		porta = new Porta(336, 0, true, gamePanel, true);
		
		item = new Item(500, 500, true, gamePanel, false, "FUCK");
		
		bausMapa.add((new Bau(200, 200, true, gamePanel, true, item)));
		
		
		pocao = new Pocao(350, 300, true, gamePanel, 20);
		
		carregaMobs();
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
	
	public void setEstadoJogo(int estadoJogo) {
		this.estadoJogo = estadoJogo;
		System.out.println(this.estadoJogo);
	}

	public void carregaMobs() {
		
		for (ArrayList<Integer> mob : Arquivos.getVetorMobs()) {
			if (mob.get(0) == 1) 
				listaInimigos.add(new Zumbi(mob.get(1) * gamePanel.getTamanhoBloco(), mob.get(2) * gamePanel.getTamanhoBloco(), 100, false, 3, gamePanel, new ArrayList<Item>()));
			if (mob.get(0) == 2)
				listaInimigos.add(new Slime(mob.get(1) * gamePanel.getTamanhoBloco(), mob.get(2) * gamePanel.getTamanhoBloco(), gamePanel, 100, false, 2, new ArrayList<Item>(), player));
			if (mob.get(0) == 3)
				listaInimigos.add(new Morcego(mob.get(1) * gamePanel.getTamanhoBloco(), mob.get(2) * gamePanel.getTamanhoBloco(), gamePanel, 100, false, 2, new ArrayList<Item>(), mob.get(3) * gamePanel.getTamanhoBloco(), mob.get(4) * gamePanel.getTamanhoBloco(), player));
		}
	}

	public void startGameThread() {  
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void atualizaJogo() {
		if (estadoJogo == 0) { //updates do modo normal
		for (Personagem i : listaInimigos)
			i.update();
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
