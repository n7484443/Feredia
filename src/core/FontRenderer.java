package core;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class FontRenderer {
	public static TrueTypeFont font;
	public static float size =80f;
	
	public static void init(){
		try{
			InputStream inputStream = ResourceLoader.getResourceAsStream("font/ExpensiveSolutions.ttf");

			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(size);
			font = new TrueTypeFont(awtFont, true);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void render(int x, int y, TrueTypeFont f, String str){
		Color.white.bind();
		f.drawString(x, y, str);
	}
	
	public static void render(int x, int y, TrueTypeFont f, String str, Color c){
		Color.white.bind();
		f.drawString(x, y, str, c);
	}
}
