package main;

import java.util.ArrayList;

import arquivos.Arquivos;
import arquivos.InputEntidadesMapa.ArquivoBau;
import arquivos.InputEntidadesMapa.ArquivoEntidade;
import arquivos.InputEntidadesMapa.ArquivoMorcego;

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
		player = new Player(1, false, 3, 5, gamePanel, keyInput, this);		
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
		for(ArquivoEntidade i: Arquivos.getInputEntidadesMapas().get(mapa_atual).getListaZumbis())
				listaInimigos.add(new Zumbi(i.getPosicaoXBloco() * gamePanel.getTamanhoBloco(), i.getPosicaoYBloco() * gamePanel.getTamanhoBloco(), 100, false, 3, gamePanel, new ArrayList<Item>()));
		for(ArquivoEntidade i: Arquivos.getInputEntidadesMapas().get(mapa_atual).getListaSlimes())
			listaInimigos.add(new Slime(i.getPosicaoXBloco() * gamePanel.getTamanhoBloco(), i.getPosicaoYBloco() * gamePanel.getTamanhoBloco(), gamePanel, 100, false, 2, new ArrayList<Item>(), player));
		for(ArquivoMorcego i: Arquivos.getInputEntidadesMapas().get(mapa_atual).getListaMorcegos())
			listaInimigos.add(new Morcego(i.getPosicaoXBloco() * gamePanel.getTamanhoBloco(), i.getPosicaoYBloco() * gamePanel.getTamanhoBloco(), gamePanel, 100, false, 2, new ArrayList<Item>(), i.getPosicaoXFinalBloco() * gamePanel.getTamanhoBloco(), i.getPosicaoYFinalBloco() * gamePanel.getTamanhoBloco(), player));
		for(ArquivoEntidade i: Arquivos.getInputEntidadesMapas().get(mapa_atual).getListaPortas())
			listaEntidades.add(new Porta(i.getPosicaoXBloco() * gamePanel.getTamanhoBloco(), i.getPosicaoYBloco() * gamePanel.getTamanhoBloco(), true, gamePanel, true));
		for(ArquivoEntidade i: Arquivos.getInputEntidadesMapas().get(mapa_atual).getListaPocoes())
			listaEntidades.add(new Pocao(i.getPosicaoXBloco() * gamePanel.getTamanhoBloco(), i.getPosicaoYBloco() * gamePanel.getTamanhoBloco(), gamePanel));
		for(ArquivoEntidade i: Arquivos.getInputEntidadesMapas().get(mapa_atual).getListaChaves())
			listaEntidades.add(new Chave(i.getPosicaoXBloco() * gamePanel.getTamanhoBloco(), i.getPosicaoYBloco() * gamePanel.getTamanhoBloco(), gamePanel));
		for(ArquivoBau i: Arquivos.getInputEntidadesMapas().get(mapa_atual).getListaBaus()){
				Item tempItem = null;
				if (i.getTipoItem().equals("chave"))
					tempItem = new Chave(-100, -100, gamePanel);
				if (i.getTipoItem().equals("pocao"))
					tempItem = new Chave(-100, -100, gamePanel);
				listaEntidades.add(new Bau(i.getPosicaoXBloco() * gamePanel.getTamanhoBloco(), i.getPosicaoYBloco() * gamePanel.getTamanhoBloco(),gamePanel, false, tempItem));
		}
			player.setX(Arquivos.getInputEntidadesMapas().get(mapa_atual).getXBlocoInicialPlayer() * gamePanel.getTamanhoBloco());
			player.setY(Arquivos.getInputEntidadesMapas().get(mapa_atual).getYBlocoInicialPlayer() * gamePanel.getTamanhoBloco());
			
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
