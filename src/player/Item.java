package player;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Item {
	public Texture image;
	
	public Item(String url){
		try {
			image = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream(url));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
