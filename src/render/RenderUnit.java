package render;

import java.io.IOException;

import map.Map;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class RenderUnit {
	public Texture texture;
	public int x;
	public int y;
	
	public Map[] lookMap;
	
	public RenderUnit(int x, int y, String url, Map... m) throws IOException{
		this.x = x;
		this.y = y;
		this.texture = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream(url));
		lookMap = m;
	}
	
	public RenderUnit(int x, int y, String url) throws IOException{
		this.x = x;
		this.y = y;
		this.texture = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream(url));
	}
	
	public boolean isCanDrawing(Map m){
		for(int i = 0; i < lookMap.length; i++){
			if(lookMap[i] == m)return true;
		}
		return false;
	}
	
	public void firstdraw(){
		int width = this.texture.getImageWidth();
		int height = this.texture.getImageHeight();
		
		texture.bind();
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(x, y);
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(x, y + height);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(x + width, y + height);
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(x + width, y);
		GL11.glEnd();
	}
	
	public void afterdraw(){
		int width = this.texture.getImageWidth();
		int height = this.texture.getImageHeight();
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(x, y);
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(x, y + height);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(x + width, y + height);
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(x + width, y);
		GL11.glEnd();
	}
}
