package image.loader;

import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class MapImageLoader {
	public static Texture[] blockTexture = new Texture[10];
	public static Texture[] decoTexture = new Texture[10];
	public static Color[] blockColor = new Color[10];
	public static int[] blockTexturexSize = new int[10];
	
	public static void Init() throws IOException{
		blockTexture[0] = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/map/dirt.png"));
		blockTexture[1] = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/map/grass_island_left.png"));
		blockTexture[2] = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/map/grass_island_right.png"));
		blockTexture[3] = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/map/grass_island.png"));
		blockTexture[4] = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/map/grass_left.png"));
		blockTexture[5] = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/map/grass_right.png"));
		blockTexture[6] = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/map/grass.png"));
		
		decoTexture[0] = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/map/deco/deco_grass.png"));
		
		blockColor[0] = new Color(Color.black.r, Color.black.g, Color.black.b, .6f);
		blockColor[1] = new Color(Color.black.r, Color.black.g, Color.black.b, .6f);
		blockColor[2] = new Color(Color.black.r, Color.black.g, Color.black.b, .6f);
		blockColor[3] = new Color(Color.black.r, Color.black.g, Color.black.b, .6f);
		blockColor[4] = new Color(Color.black.r, Color.black.g, Color.black.b, .6f);
		blockColor[5] = new Color(Color.black.r, Color.black.g, Color.black.b, .6f);
		blockColor[6] = new Color(Color.black.r, Color.black.g, Color.black.b, .6f);
		
		blockTexturexSize[0] = 64;
		blockTexturexSize[1] = 32;
		blockTexturexSize[2] = 32;
		blockTexturexSize[3] = 32;
		blockTexturexSize[4] = 32;
		blockTexturexSize[5] = 32;
		blockTexturexSize[6] = 64;
		blockTexturexSize[7] = 32;
		
	}
}
