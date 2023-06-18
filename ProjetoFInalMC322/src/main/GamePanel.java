package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private final static int tamanhoOriginalBloco = 16; //tamanho dos "blocos" em pixel
	private final static int fatorAjusteTamanho = 3; 
	
	private final static int tamanhoBloco = fatorAjusteTamanho * tamanhoOriginalBloco;
	private final static int numeroBlocosHorizontal = 16;
	private final static int numeroBlocosVertical = 12;	
	
	private final static int telaLargura = tamanhoBloco * numeroBlocosHorizontal; //768
	private final static int telaAltura = tamanhoBloco * numeroBlocosVertical; //576
	
	private final static int fps = 60; //576
	private final static double updateInterval = 1000/fps;
	
	public static int getTamanhoBloco() {
		return tamanhoBloco;
	}
	
	public static int getNumeroBlocosHorizontal() {
		return numeroBlocosHorizontal;
	}
	
	public static int getNumeroBlocosVertical() {
		return numeroBlocosVertical;
	}
	
	public static int getTelaLargura() {
		return telaLargura;
	}
	
	public static int getTelaAltura() {
		return telaAltura;
	}
	
	Thread gameThread;
	
	KeyboardInput keyI = new KeyboardInput();
	
	Player player = new Player(this, keyI);
	
	MapBuilder mapBuilder = new MapBuilder();
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(telaLargura, telaAltura));
		this.setBackground(Color.DARK_GRAY);
		this.setDoubleBuffered(true);
		
		this.addKeyListener(keyI);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
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
				update();
				repaint();
				framesDesenhados++;
				delta--;
			}
		}
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		mapBuilder.draw(g2);
		
		player.draw(g2);
		
		
		
		g2.setColor(Color.black);
		
		g2.fillRect(-25, -25, 50, 50);
		
		g2.dispose();
	}
}
