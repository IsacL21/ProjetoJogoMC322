package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background {
	
	private BufferedImage image1;
	private GamePanel gamePanel;
	
	
	public Background(GamePanel gamepanel) {
		this.gamePanel = gamepanel;
		getBackground();
	}
	
	public void getBackground() {
		try {
			image1 = ImageIO.read(getClass().getResourceAsStream("/background/AAA.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D tela) {
		tela.drawImage(image1, 0, 0, GamePanel.getTelaLargura(), GamePanel.getTelaAltura(), null);
	}
	
	
}
