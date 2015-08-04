package image.loader;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class NpcImageLoader {
public static Texture[] NpcTexture = new Texture[10];
	
	public static void Init() throws IOException{
		NpcTexture[0] = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/character/npc/emilie.png"));
	}
}
