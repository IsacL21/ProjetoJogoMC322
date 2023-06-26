package arquivos;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.Mapa;
import main.Mapas;

public class Arquivos {

	private final static ArrayList<BufferedImage> playerImages = new ArrayList<BufferedImage>();
	private final static ArrayList<BufferedImage> slimeImages = new ArrayList<BufferedImage>();
	private final static ArrayList<BufferedImage> morcegoImages = new ArrayList<BufferedImage>();
	private final static ArrayList<BufferedImage> zumbiImages = new ArrayList<BufferedImage>();
	private final static ArrayList<BufferedImage> textureImages = new ArrayList<BufferedImage>();
	private final static ArrayList<InputStream> mapList = new ArrayList<InputStream>();
	private static int[][][] vetorMapa = new int[Mapas.values().length][Mapa.QTDE_BLOCOS_VERTICAL.getPosicao()][Mapa.QTDE_BLOCOS_HORIZONTAL.getPosicao()];
	private static ArrayList<ArrayList<ArrayList<Integer>>> vetorMobs = new ArrayList<ArrayList<ArrayList<Integer>>>();
	
	public void loadFiles() throws IOException{
			
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Down1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Down2.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Up1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Up2.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Right1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Right2.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Left1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Left2.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-down1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-down2.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-up1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-up2.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-right1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-right2.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-left1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-left2.png")));
			
			slimeImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/slime/Idle_Left.png")));
			slimeImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/slime/JumpLeft2.png")));
			slimeImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/slime/Idle_Right.png")));
			slimeImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/slime/JumpRigh2.png")));
			
			morcegoImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/morcego/Down-1.png")));
			morcegoImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/morcego/Down-2.png")));
			morcegoImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/morcego/Up-1.png")));
			morcegoImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/morcego/Up-2.png")));
			morcegoImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/morcego/Right-1.png")));
			morcegoImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/morcego/Right-2.png")));
			morcegoImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/morcego/Left-1.png")));
			morcegoImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/morcego/Left-2.png")));
			
			zumbiImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/zumbi/Down-1.png")));
			zumbiImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/zumbi/Down-2.png")));
			zumbiImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/zumbi/Up-1.png")));
			zumbiImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/zumbi/Up-2.png")));
			zumbiImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/zumbi/Right-1.png")));
			zumbiImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/zumbi/Right-2.png")));
			zumbiImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/zumbi/Left-1.png")));
			zumbiImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/zumbi/Left-2.png")));
			
			textureImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/grass.png")));
			textureImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/earth.png")));
			textureImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/wall.png")));
			
			carregaBlocosMapa();
			
	}

	public static ArrayList<BufferedImage> getMorcegoimages() {
		return morcegoImages;
	}
	
	public static ArrayList<BufferedImage> getZumbiImages() {
		return zumbiImages;
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
	
	public static int[][] getVetorMapa(int index) {
		return vetorMapa[index];
	}

	public static ArrayList<ArrayList<Integer>> getVetorMobs(int index) {
		return vetorMobs.get(index);
	}

	public void carregaBlocosMapa() {
		
		for (int k = 0; k < Mapas.values().length; k++) {
			System.out.println("Here");
			System.out.println(Mapas.values()[k].getAdress());
			InputStream file = getClass().getResourceAsStream(Mapas.values()[k].getAdress());
			
			BufferedReader entrada = new BufferedReader(new InputStreamReader(file));
			
			try {
				for (int i=0; i < Mapa.QTDE_BLOCOS_VERTICAL.getPosicao(); i++) {
					String[] numeroColuna = entrada.readLine().split(" ");
					for (int j=0; j < Mapa.QTDE_BLOCOS_HORIZONTAL.getPosicao(); j++) {
						vetorMapa[k][i][j] = Integer.parseInt(numeroColuna[j]);
					}
				}
				
				int qtdMobs = Integer.parseInt(entrada.readLine());
				
				vetorMobs.add(new ArrayList<ArrayList<Integer>>());
					for (int i = 0; i < qtdMobs; i++) {
						System.out.println(i);
						String[] linha = entrada.readLine().split(" ");
						vetorMobs.get(k).add(new ArrayList<Integer>());
						for (int j=0; j < linha.length ; j++) {
							vetorMobs.get(k).get(i).add(Integer.parseInt(linha[j]));
						}
					}
				}
				
			
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("passei aqui");
		}
		
	}
}
	

