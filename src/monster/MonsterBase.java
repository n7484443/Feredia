package monster;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class MonsterBase {
	private float x;
	private float y;
	
	public int image;
	
	public Texture[] movetexture;
	
	public MonsterBase(int x, int y, String... url){
		this.x = x;
		this.y = y;
		movetexture = new Texture[url.length];
		for(int i = 0; i < movetexture.length; i++){
			try {
				movetexture[i] = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream(url[i]));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		image = 0;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public Texture getTexture(){
		return movetexture[image];
	}
}
