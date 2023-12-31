package main;

import java.util.ArrayList;
import javax.swing.JOptionPane;
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
	private int estadoJogo = 0; //utilizado para congelar jogo em menus, estado normal = 0
	private ArrayList<Inimigo> listaInimigos;
	private ArrayList<Entity> listaEntidades;
	private final static int fps = 60;
	private final static double updateInterval = 1000/fps;
	
	//Construtor
	public Engine() {
		keyInput = new KeyboardInput();
		gamePanel = new GamePanel(this);
		player = new Player(6, false, 3, 5, this, keyInput);		
		mapBuilder = new MapBuilder(Arquivos.getVetorMapa(Main.getMapa_atual()), gamePanel);
		listaInimigos = new ArrayList<Inimigo> ();
		listaEntidades = new ArrayList<Entity> ();
		carregaMobs(Main.getMapa_atual());
	}
	
	//Getters e Setters
	public KeyboardInput getKeyInput() {
		return keyInput;
	}

	public void setKeyInput(KeyboardInput keyInput) {
		this.keyInput = keyInput;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public MapBuilder getMapBuilder() {
		return mapBuilder;
	}

	public void setMapBuilder(MapBuilder mapBuilder) {
		this.mapBuilder = mapBuilder;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public ArrayList<Inimigo> getListaInimigos() {
		return listaInimigos;
	}

	public void setListaInimigo(ArrayList<Inimigo> listaInimigos) {
		this.listaInimigos = listaInimigos;
	}

	public int getEstadoJogo() {
		return estadoJogo;
	}
	
	public void setEstadoJogo(int estadoJogo) {
		this.estadoJogo = estadoJogo;
		System.out.println(this.estadoJogo);
	}

	public ArrayList<Entity> getListaEntidades() {
		return listaEntidades;
	}

	public void setListaEntidades(ArrayList<Entity> listaEntidades) {
		this.listaEntidades = listaEntidades;
	}

	public Thread getGameThread() {
		return gameThread;
	}

	public void setGameThread(Thread gameThread) {
		this.gameThread = gameThread;
	}

	//Métodos
	public void carregaMobs(int mapa_atual) {
		listaEntidades.clear();
		listaInimigos.clear();
		for(ArquivoEntidade i: Arquivos.getInputEntidadesMapas().get(mapa_atual).getListaZumbis())
			listaInimigos.add(new Zumbi(i.getPosicaoXBloco() * gamePanel.getTamanhoBloco(), i.getPosicaoYBloco() * gamePanel.getTamanhoBloco(), false, 3, this, new ArrayList<Item>()));
		for(ArquivoEntidade i: Arquivos.getInputEntidadesMapas().get(mapa_atual).getListaSlimes())
			listaInimigos.add(new Slime(i.getPosicaoXBloco() * gamePanel.getTamanhoBloco(), i.getPosicaoYBloco() * gamePanel.getTamanhoBloco(), this, false, 2, new ArrayList<Item>(), player));
		for(ArquivoMorcego i: Arquivos.getInputEntidadesMapas().get(mapa_atual).getListaMorcegos())
			listaInimigos.add(new Morcego(i.getPosicaoXBloco() * gamePanel.getTamanhoBloco(), i.getPosicaoYBloco() * gamePanel.getTamanhoBloco(), this, false, 2, new ArrayList<Item>(), i.getPosicaoXFinalBloco() * gamePanel.getTamanhoBloco(), i.getPosicaoYFinalBloco() * gamePanel.getTamanhoBloco(), player));
		for(ArquivoEntidade i: Arquivos.getInputEntidadesMapas().get(mapa_atual).getListaPortas())
			listaEntidades.add(new Porta(i.getPosicaoXBloco() * gamePanel.getTamanhoBloco(), i.getPosicaoYBloco() * gamePanel.getTamanhoBloco(), true, this, true));
		for(ArquivoEntidade i: Arquivos.getInputEntidadesMapas().get(mapa_atual).getListaPocoes())
			listaEntidades.add(new Pocao(i.getPosicaoXBloco() * gamePanel.getTamanhoBloco(), i.getPosicaoYBloco() * gamePanel.getTamanhoBloco(), this));
		for(ArquivoEntidade i: Arquivos.getInputEntidadesMapas().get(mapa_atual).getListaChaves())
			listaEntidades.add(new Chave(i.getPosicaoXBloco() * gamePanel.getTamanhoBloco(), i.getPosicaoYBloco() * gamePanel.getTamanhoBloco(), this));
		for(ArquivoBau i: Arquivos.getInputEntidadesMapas().get(mapa_atual).getListaBaus()) {
			Item tempItem = null;
			if (i.getTipoItem().equals("chave"))
				tempItem = new Chave(-100, -100, this);
			if (i.getTipoItem().equals("pocao"))
				tempItem = new Pocao(-100, -100, this);
			listaEntidades.add(new Bau(i.getPosicaoXBloco() * gamePanel.getTamanhoBloco(), i.getPosicaoYBloco() * gamePanel.getTamanhoBloco(),this, true, tempItem));
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
			for(int i = 0; i<listaInimigos.size(); i++) {
				listaInimigos.get(i).update();
			}
			player.update();
        }
		else if (estadoJogo == 1) { //modo de menu
		}
    }
	
	public void passaFase() {
		if(Main.getMapa_atual() < 2) {
			Main.setMapa_atual(Main.getMapa_atual()+1);;
			mapBuilder = new MapBuilder(Arquivos.getVetorMapa(Main.getMapa_atual()), gamePanel);
			carregaMobs(Main.getMapa_atual());
		}
		else {
			JOptionPane.showMessageDialog(null, "Voce chegou na ultima fase!", "Zerou", JOptionPane.INFORMATION_MESSAGE);
			keyInput.resetaValores();
		}
	}
	
	public void retornaFase() {
		mapBuilder = new MapBuilder(Arquivos.getVetorMapa(Main.getMapa_atual()), gamePanel);
		carregaMobs(Main.getMapa_atual());
		player.setVida(6);
		keyInput.resetaValores();
	}

	@Override
	public void run() {
		double delta = 0;
		long lastTime = System.currentTimeMillis();
		long currentTime;
		long timer = 0;
		while(gameThread != null) {
			 currentTime = System.currentTimeMillis();
			 delta += (currentTime - lastTime) / updateInterval; 
			 timer += (currentTime - lastTime);
			 lastTime = currentTime;
			 if (timer >= 1000) { //um segundo passou
				timer = 0;
			}
			if (delta >= 1) {
				atualizaJogo();
				gamePanel.repaint();
				delta--;
			}
		}
	}
}