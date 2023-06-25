package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	private final Engine engine;
	
	private final static int tamanhoBloco = 48;
	private final static int numeroBlocosHorizontal = 16;
	private final static int numeroBlocosVertical = 12;	
	
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
		return tamanhoBloco * numeroBlocosHorizontal;
	}
	
	public static int getTelaAltura() {
		return tamanhoBloco * numeroBlocosVertical;
	}

	
	public GamePanel(Engine engine) {
		this.engine = engine;
		this.setPreferredSize(new Dimension(getTelaLargura(), getTelaAltura()));
		this.setBackground(Color.DARK_GRAY);
		this.setDoubleBuffered(true);
		
		this.addKeyListener(engine.getKeyInput());
		this.setFocusable(true);
	}
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		engine.getMapBuilder().draw(g2);
		
		for (Personagem i : engine.getListaInimigos())
			i.draw(g2);
		
		engine.getPlayer().draw(g2);

		
		g2.dispose();
	}
}
