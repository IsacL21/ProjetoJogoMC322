package arquivos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Arquivos {

	private final static ArrayList<BufferedImage> playerImages = new ArrayList<BufferedImage>();
	private final static ArrayList<BufferedImage> textureImages = new ArrayList<BufferedImage>();
	private final static ArrayList<InputStream> mapList = new ArrayList<InputStream>();
	
	public void loadFiles() throws IOException{
			
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_up_1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_up_2.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_down_1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_down_2.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_left_1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_left_2.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_right_1.png")));
			playerImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/player/boy_right_2.png")));
			
			textureImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/grass.png")));
			textureImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/earth.png")));
			textureImages.add(ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/wall.png")));
			
			mapList.add(getClass().getResourceAsStream("/rooms/room1"));
	}

	public static ArrayList<BufferedImage> getPlayerimages() {
		return playerImages;
	}


	public static ArrayList<BufferedImage> getTextureimages() {
		return textureImages;
	}
	
}
