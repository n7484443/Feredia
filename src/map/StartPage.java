package map;

import java.io.IOException;

import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class StartPage extends Map{
	public StartPage() throws IOException{
		BackGround = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream(""));
	}
}
