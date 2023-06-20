package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	private final Engine engine;
	private final static int tamanhoOriginalBloco = 16; //tamanho dos "blocos" em pixel
	private final static int fatorAjusteTamanho = 3; 
	
	private final static int tamanhoBloco = fatorAjusteTamanho * tamanhoOriginalBloco;
	private final static int numeroBlocosHorizontal = 16;
	private final static int numeroBlocosVertical = 12;	
	
	private final static int telaLargura = tamanhoBloco * numeroBlocosHorizontal; //768
	private final static int telaAltura = tamanhoBloco * numeroBlocosVertical; //576
	
	
	
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

	
	public GamePanel(Engine engine) {
		this.engine = engine;
		this.setPreferredSize(new Dimension(telaLargura, telaAltura));
		this.setBackground(Color.DARK_GRAY);
		this.setDoubleBuffered(true);
		
		this.addKeyListener(engine.getKeyInput());
		this.setFocusable(true);
	}
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		engine.getMapBuilder().draw(g2);
		engine.getPlayer().draw(g2);

		engine.getInimigo().draw(g2);
		g2.dispose();
	}
}
