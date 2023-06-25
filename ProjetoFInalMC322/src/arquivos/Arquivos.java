package arquivos;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Arquivos {

	private final static ArrayList<BufferedImage> playerImages = new ArrayList<BufferedImage>();
	private final static ArrayList<BufferedImage> slimeImages = new ArrayList<BufferedImage>();
	private final static ArrayList<BufferedImage> textureImages = new ArrayList<BufferedImage>();
	private final static ArrayList<InputStream> mapList = new ArrayList<InputStream>();
	private static int[][] vetorMapa = new int[GamePanel.getNumeroBlocosVertical()][GamePanel.getNumeroBlocosHorizontal()];
	private static ArrayList<ArrayList<Integer>> vetorMobs = new ArrayList<ArrayList<Integer>>();
	
	
	public void loadFiles() throws IOException{
			
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_up_1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_up_2.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_down_1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_down_2.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_left_1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_left_2.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_right_1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_right_2.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_attack_down_1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_attack_down_2.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_attack_up_1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_attack_up_2.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_attack_right_1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_attack_right_2.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_attack_left_1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_attack_left_2.png")));
			
			slimeImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/slime/Idle_Left.png")));
			slimeImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/slime/JumpLeft2.png")));
			slimeImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/slime/Idle_Right.png")));
			slimeImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/slime/JumpRigh2.png")));
			
			textureImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/grass.png")));
			textureImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/earth.png")));
			textureImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/wall.png")));
			
			carregaBlocosMapa();
			
	}

	public static ArrayList<BufferedImage> getPlayerimages() {
		return playerImages;
	}

	public static ArrayList<BufferedImage> getTextureimages() {
		return textureImages;
	}

	public static ArrayList<BufferedImage> getSlimeimages() {
		return slimeImages;
	}
	
	public static int[][] getVetorMapa() {
		return vetorMapa;
	}

	public static ArrayList<ArrayList<Integer>> getVetorMobs() {
		return vetorMobs;
	}

	public void carregaBlocosMapa() {
		
		InputStream file = getClass().getResourceAsStream("/rooms/room1");
		BufferedReader entrada = new BufferedReader(new InputStreamReader(file));
		try {
			for (int i=0; i < GamePanel.getNumeroBlocosVertical(); i++) {
				String[] numeroColuna = entrada.readLine().split(" ");
				for (int j=0; j < GamePanel.getNumeroBlocosHorizontal(); j++) {
					vetorMapa[i][j] = Integer.parseInt(numeroColuna[j]);
				}
			}
			int qtdMobs = Integer.parseInt(entrada.readLine());
			for (int i = 0; i < qtdMobs; i++) {
				String[] linha = entrada.readLine().split(" ");
				vetorMobs.add(new ArrayList<Integer>());
				for (int j=0; j < linha.length ; j++) {
					vetorMobs.get(i).add(Integer.parseInt(linha[j]));
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
