package image.loader;

import java.io.IOException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class ItemImageLoader {
	public static Texture[] ItemTexture = new Texture[10];
	
	public static void Init() throws IOException{
		ItemTexture[0] = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/map/dirt.png"));
	}
}
