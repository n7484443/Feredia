package render.font;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class FontRenderer {
	public static Texture kor_white;
	public static Texture kor_black;
	public static BufferedReader br;
	public static int koreanheight;
	public static int line;
	public static Map<Integer, Integer> x = new HashMap<Integer, Integer>();
	public static Map<Integer, Integer> y = new HashMap<Integer, Integer>();
	public static Map<Integer, Integer> width = new HashMap<Integer, Integer>();
	
	
	public static void Init() throws IOException{
		try {
			kor_white = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("font/korean_white.png"));
			kor_black = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("font/korean_black.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		br = new BufferedReader(new InputStreamReader(new FileInputStream("src/font/korean.fnt")));
		String str;
		boolean b = true;
		while((str = br.readLine()) != null){
			if(b){
				if(str.contains("height=")){
					koreanheight = Integer.valueOf(str.substring(str.indexOf("=") + 1));
				}else if(str.contains("line=")){
					line = Integer.valueOf(str.substring(str.indexOf("=") + 1));
					b = false;
				}
			}else{
				int i = Integer.valueOf(str.substring(str.indexOf("=") + 1, str.indexOf("x")));
				x.put(i, Integer.valueOf(str.substring(str.indexOf("x") + 2, str.indexOf("y"))));
				y.put(i, Integer.valueOf(str.substring(str.indexOf("y") + 2, str.indexOf("w"))));
				width.put(i, Integer.valueOf(str.substring(str.indexOf("h") + 2)));
			}
		}
		br.close();
	}
	
	public synchronized static void render(int x1, int y1, String str){
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Color.white.bind();
		for(int i = 0; i < str.length(); i++){
			int fontx = x.get((int)str.charAt(i));
			int fonty = y.get((int)str.charAt(i));
			int fontwidth = width.get((int)str.charAt(i));
			float texx1 = fontx/2048F;
			float texx2 = (fontx+fontwidth)/2048F;
			float texy1 = fonty/2048F;
			float texy2 = (fonty+koreanheight)/2048F;
			GL11.glColor4f(1.0f,1.0f,1.0f,0.5f); 
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2d(texx1, texy1);
				GL11.glVertex2f(x1, y1);
				GL11.glTexCoord2d(texx1, texy2);
				GL11.glVertex2f(x1, y1+koreanheight);
				GL11.glTexCoord2d(texx2, texy2);
	 			GL11.glVertex2f(x1+fontwidth, y1+koreanheight);
	 			GL11.glTexCoord2d(texx2, texy1);
	 			GL11.glVertex2f(x1+fontwidth, y1);
			GL11.glEnd();
			x1 += fontwidth;
		}
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	public synchronized static void renderReSizeable(int x1, int y1, int wsize, String str){
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Color.white.bind();
		for(int i = 0; i < str.length(); i++){
			int fontx = x.get((int)str.charAt(i));
			int fonty = y.get((int)str.charAt(i));
			int fontwidth = width.get((int)str.charAt(i));
			float texx1 = fontx/2048F;
			float texx2 = (fontx+fontwidth)/2048F;
			float texy1 = fonty/2048F;
			float texy2 = (fonty+koreanheight)/2048F;
			int he = koreanheight*wsize/fontwidth;
			GL11.glColor4f(1.0f,1.0f,1.0f,0.5f); 
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2d(texx1, texy1);
				GL11.glVertex2f(x1, y1);
				GL11.glTexCoord2d(texx1, texy2);
				GL11.glVertex2f(x1, y1+he);
				GL11.glTexCoord2d(texx2, texy2);
	 			GL11.glVertex2f(x1+wsize, y1+he);
	 			GL11.glTexCoord2d(texx2, texy1);
	 			GL11.glVertex2f(x1+wsize, y1);
			GL11.glEnd();
			x1 += wsize;
		}
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	public synchronized static void renderReSizeableWithColor(int x1, int y1, int wsize, String str, Color c, float Alpha){
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		c.bind();
		for(int i = 0; i < str.length(); i++){
			int fontx = x.get((int)str.charAt(i));
			int fonty = y.get((int)str.charAt(i));
			int fontwidth = width.get((int)str.charAt(i));
			float texx1 = fontx/2048F;
			float texx2 = (fontx+fontwidth)/2048F;
			float texy1 = fonty/2048F;
			float texy2 = (fonty+koreanheight)/2048F;
			int he = koreanheight*wsize/fontwidth;
			GL11.glColor4f(1.0f,1.0f,1.0f,Alpha); 
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2d(texx1, texy1);
				GL11.glVertex2f(x1, y1);
				GL11.glTexCoord2d(texx1, texy2);
				GL11.glVertex2f(x1, y1+he);
				GL11.glTexCoord2d(texx2, texy2);
	 			GL11.glVertex2f(x1+wsize, y1+he);
	 			GL11.glTexCoord2d(texx2, texy1);
	 			GL11.glVertex2f(x1+wsize, y1);
			GL11.glEnd();
			x1 += wsize;
		}
		GL11.glDisable(GL11.GL_BLEND);
	}
}
