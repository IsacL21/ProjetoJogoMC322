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

	private final static ArrayList<BufferedImage> slimeImages = new ArrayList<BufferedImage>();
	private final static ArrayList<BufferedImage> morcegoImages = new ArrayList<BufferedImage>();
	private final static ArrayList<BufferedImage> zumbiImages = new ArrayList<BufferedImage>();
	private final static ArrayList<BufferedImage> bauImages = new ArrayList<BufferedImage>();
	private final static ArrayList<BufferedImage> portaImages = new ArrayList<BufferedImage>();
	private final static ArrayList<BufferedImage> itemImages = new ArrayList<BufferedImage>();
	private final static ArrayList<BufferedImage> textureImages = new ArrayList<BufferedImage>();
	private final static ArrayList<InputStream> mapList = new ArrayList<InputStream>();
	private static int[][][] vetorMapa = new int[Mapas.values().length][Mapa.QTDE_BLOCOS_VERTICAL.getPosicao()][Mapa.QTDE_BLOCOS_HORIZONTAL.getPosicao()];
	private static ArrayList<ArrayList<ArrayList<Integer>>> vetorEntidades = new ArrayList<ArrayList<ArrayList<Integer>>>();
	

	static class PlayerImages{
		private final static ArrayList<BufferedImage> upWalk = new ArrayList<BufferedImage>();
		private final static ArrayList<BufferedImage> downWalk = new ArrayList<BufferedImage>();
		private final static ArrayList<BufferedImage> rightWalk = new ArrayList<BufferedImage>();
		private final static ArrayList<BufferedImage> leftWalk = new ArrayList<BufferedImage>();
		private final static ArrayList<BufferedImage> upAttack = new ArrayList<BufferedImage>();
		private final static ArrayList<BufferedImage> downAttack = new ArrayList<BufferedImage>();
		private final static ArrayList<BufferedImage> rightAttack = new ArrayList<BufferedImage>();
		private final static ArrayList<BufferedImage> leftAttack = new ArrayList<BufferedImage>();
		private final static ArrayList<BufferedImage> barraVida = new ArrayList<BufferedImage>();
	}
	
	
	public void loadFiles() throws IOException{
			
			PlayerImages.upWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Up1.png")));
			PlayerImages.upWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Up2.png")));
			PlayerImages.upWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Up3.png")));
			PlayerImages.upWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Up4.png")));
			PlayerImages.upWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Up5.png")));
			PlayerImages.upWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Up6.png")));
			PlayerImages.downWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Down1.png")));
			PlayerImages.downWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Down2.png")));
			PlayerImages.downWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Down3.png")));
			PlayerImages.downWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Down4.png")));
			PlayerImages.downWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Down5.png")));
			PlayerImages.downWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Down6.png")));
			PlayerImages.rightWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Right1.png")));
			PlayerImages.rightWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Right2.png")));
			PlayerImages.rightWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Right3.png")));
			PlayerImages.rightWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Right4.png")));
			PlayerImages.rightWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Right5.png")));
			PlayerImages.rightWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Right6.png")));
			PlayerImages.leftWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Left1.png")));
			PlayerImages.leftWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Left2.png")));
			PlayerImages.leftWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Left3.png")));
			PlayerImages.leftWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Left4.png")));
			PlayerImages.leftWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Left5.png")));
			PlayerImages.leftWalk.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/Left6.png")));
			PlayerImages.upAttack.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-up1.png")));
			PlayerImages.upAttack.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-up2.png")));
			PlayerImages.upAttack.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-up3.png")));
			PlayerImages.upAttack.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-up4.png")));
			PlayerImages.downAttack.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-down1.png")));
			PlayerImages.downAttack.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-down2.png")));
			PlayerImages.downAttack.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-down3.png")));
			PlayerImages.downAttack.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-down4.png")));
			PlayerImages.rightAttack.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-right1.png")));
			PlayerImages.rightAttack.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-right2.png")));
			PlayerImages.rightAttack.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-right3.png")));
			PlayerImages.rightAttack.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-right4.png")));
			PlayerImages.leftAttack.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-left1.png")));
			PlayerImages.leftAttack.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-left2.png")));
			PlayerImages.leftAttack.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-left3.png")));
			PlayerImages.leftAttack.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/ataque-left4.png")));
			PlayerImages.barraVida.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/heart_full.png")));
			PlayerImages.barraVida.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/heart_half.png")));
			PlayerImages.barraVida.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/heart_empty.png")));
			
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
			
			bauImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/bau/bau1.png")));
			bauImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/bau/bau2.png")));
			
			portaImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/porta/door1.png")));
			portaImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/porta/door2.png")));
			
			itemImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/items/chave.png")));
			itemImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/items/pocao.png")));
			
			textureImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/null.png")));
			textureImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/floor_1.png")));
			textureImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/wall_mid.png")));
			textureImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/floor_spikes.png")));
			
			carregaBlocosMapa();
	}
	
	public static ArrayList<BufferedImage> getZumbiimages() {
		return zumbiImages;
	}

	public static ArrayList<BufferedImage> getBauimages() {
		return bauImages;
	}

	public static ArrayList<BufferedImage> getPortaimages() {
		return portaImages;
	}

	public static ArrayList<BufferedImage> getItemimages() {
		return itemImages;
	}

	public static ArrayList<BufferedImage> getMorcegoimages() {
		return morcegoImages;
	}
	
	public static ArrayList<BufferedImage> getZumbiImages() {
		return zumbiImages;
	}

	public static ArrayList<BufferedImage> getPlayerUpwalk() {
		return PlayerImages.upWalk;
	}
	public static ArrayList<BufferedImage> getPlayerDownwalk() {
		return PlayerImages.downWalk;
	}
	public static ArrayList<BufferedImage> getPlayerRightwalk() {
		return PlayerImages.rightWalk;
	}
	public static ArrayList<BufferedImage> getPlayerLeftwalk() {
		return PlayerImages.leftWalk;
	}
	public static ArrayList<BufferedImage> getPlayerUpattack() {
		return PlayerImages.upAttack;
	}
	public static ArrayList<BufferedImage> getPlayerDownattack() {
		return PlayerImages.downAttack;
	}
	public static ArrayList<BufferedImage> getPlayerRightattack() {
		return PlayerImages.rightAttack;
	}
	public static ArrayList<BufferedImage> getPlayerLeftattack() {
		return PlayerImages.leftAttack;
	}
	public static ArrayList<BufferedImage> getPlayerBarraVida() {
		return PlayerImages.barraVida;
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

	public static ArrayList<ArrayList<Integer>> getVetorEntidades(int index) {
		return vetorEntidades.get(index);
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
				
				vetorEntidades.add(new ArrayList<ArrayList<Integer>>());
					for (int i = 0; i < qtdMobs; i++) {
						System.out.println(i);
						String[] linha = entrada.readLine().split(" ");
						vetorEntidades.get(k).add(new ArrayList<Integer>());
						for (int j=0; j < linha.length ; j++) {
							vetorEntidades.get(k).get(i).add(Integer.parseInt(linha[j]));
						}
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	
			}
				
		}

	}
}
	

