package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	//Propriedades
	private static final long serialVersionUID = 1L;
	private final Engine engine;
	private final int tamanhoOriginalBloco = 16; //tamanho dos "blocos" em pixel
	private final int fatorAjusteTamanho = 3; 
	private final int tamanhoBloco = fatorAjusteTamanho * tamanhoOriginalBloco;
	private final int numeroBlocosHorizontal = 16;
	private final int numeroBlocosVertical = 12;	
	private final int telaLargura = tamanhoBloco * numeroBlocosHorizontal; //768
	private final int telaAltura = tamanhoBloco * numeroBlocosVertical; //576
	
	//Construtor
	public GamePanel(Engine engine) {
		this.engine = engine;
		this.setPreferredSize(new Dimension(getTelaLargura(), getTelaAltura()));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		
		this.addKeyListener(engine.getKeyInput());
		this.setFocusable(true);
	}

	//Getters e Setters
	public Engine getEngine() {
		return engine;
	}

	public int getTamanhoOriginalBloco() {
		return tamanhoOriginalBloco;
	}

	public int getFatorAjusteTamanho() {
		return fatorAjusteTamanho;
	}

	public int getTamanhoBloco() {
		return tamanhoBloco;
	}
	
	public int getNumeroBlocosHorizontal() {
		return numeroBlocosHorizontal;
	}
	
	public int getNumeroBlocosVertical() {
		return numeroBlocosVertical;
	}
	
	public int getTelaLargura() {
		return telaLargura;
	}
	
	public int getTelaAltura() {
		return telaAltura;
	}

	//Métodos	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		engine.getMapBuilder().draw(g2);
		for (Entity i : engine.getListaEntidades())
			i.draw(g2);
		for (Personagem i : engine.getListaInimigos())
			i.draw(g2);
		engine.getPlayer().draw(g2);
		g2.dispose();
	}
}
