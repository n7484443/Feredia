package render;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class Render {
	public static void RenderLine(int x1, int y1, int x2, int y2, Color c){
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		c.bind();
		GL11.glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex2f(x1, y1);
		GL11.glVertex2f(x2, y1);
		GL11.glVertex2f(x2, y2);
		GL11.glVertex2f(x1, y2);
		GL11.glEnd();
	}
	
	public static void RenderBox(int x1, int y1, int x2, int y2, Color c){
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		c.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x1, y1);
		GL11.glVertex2f(x2, y1);
		GL11.glVertex2f(x2, y2);
		GL11.glVertex2f(x1, y2);
		GL11.glEnd();
	}
	
	public static void RenderBox(long x1, long y1, long x2, long y2, Color c) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		c.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x1, y1);
		GL11.glVertex2f(x2, y1);
		GL11.glVertex2f(x2, y2);
		GL11.glVertex2f(x1, y2);
		GL11.glEnd();
	}
	
	public static void RenderImageBox(int x1, int y1, int x2, int y2, Texture image){
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		image.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(x1, y1);
		GL11.glTexCoord2f(image.getWidth(), 0);
		GL11.glVertex2f(x2, y1);
		GL11.glTexCoord2f(image.getWidth(), image.getHeight());
		GL11.glVertex2f(x2, y2);
		GL11.glTexCoord2f(0, image.getHeight());
		GL11.glVertex2f(x1, y2);
		GL11.glEnd();
	}
	
	public static void RenderImageBoxWidthHeight(int x1, int y1, int x2, int y2, Texture image, int ix1, int iy1 ,int iwidth, int iheight){
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		image.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(ix1, iy1);
		GL11.glVertex2f(x1, y1);
		GL11.glTexCoord2f(ix1+iwidth, iy1);
		GL11.glVertex2f(x2, y1);
		GL11.glTexCoord2f(ix1+iwidth, iy1+iheight);
		GL11.glVertex2f(x2, y2);
		GL11.glTexCoord2f(ix1, iy1+iheight);
		GL11.glVertex2f(x1, y2);
		GL11.glEnd();
	}
	
	public static void RenderImageBoxColor(int x1, int y1, int x2, int y2, Texture image, Color c){
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		c.bind();
		image.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(x1, y1);
		GL11.glTexCoord2f(image.getWidth(), 0);
		GL11.glVertex2f(x2, y1);
		GL11.glTexCoord2f(image.getWidth(), image.getHeight());
		GL11.glVertex2f(x2, y2);
		GL11.glTexCoord2f(0, image.getHeight());
		GL11.glVertex2f(x1, y2);
		GL11.glEnd();
	}
	
	public static void RenderImageBoxWidthHeightColor(int x1, int y1, int x2, int y2, Texture image, int ix1, int iy1 ,int iwidth, int iheight, Color c){
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		c.bind();
		image.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(ix1, iy1);
		GL11.glVertex2f(x1, y1);
		GL11.glTexCoord2f(ix1+iwidth, iy1);
		GL11.glVertex2f(x2, y1);
		GL11.glTexCoord2f(ix1+iwidth, iy1+iheight);
		GL11.glVertex2f(x2, y2);
		GL11.glTexCoord2f(ix1, iy1+iheight);
		GL11.glVertex2f(x1, y2);
		GL11.glEnd();
	}

	public static void RenderImageBoxWidthHeight(int x1, int y1, int x2, int y2, Texture image, float ix1, float iy1 ,float iwidth, float iheight){
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		image.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(ix1, iy1);
		GL11.glVertex2f(x1, y1);
		GL11.glTexCoord2f(ix1+iwidth, iy1);
		GL11.glVertex2f(x2, y1);
		GL11.glTexCoord2f(ix1+iwidth, iy1+iheight);
		GL11.glVertex2f(x2, y2);
		GL11.glTexCoord2f(ix1, iy1+iheight);
		GL11.glVertex2f(x1, y2);
		GL11.glEnd();
	}

	public static void RenderImageBoxWidthHeightColor(int x1, int y1, int x2, int y2, Texture image, float ix1, float iy1 ,float iwidth, float iheight, Color c){
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		c.bind();
		image.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(ix1, iy1);
		GL11.glVertex2f(x1, y1);
		GL11.glTexCoord2f(ix1+iwidth, iy1);
		GL11.glVertex2f(x2, y1);
		GL11.glTexCoord2f(ix1+iwidth, iy1+iheight);
		GL11.glVertex2f(x2, y2);
		GL11.glTexCoord2f(ix1, iy1+iheight);
		GL11.glVertex2f(x1, y2);
		GL11.glEnd();		
	}
	
	public static void RenderImageBoxXYColor(int x1, int y1, int x2, int y2, Texture image, float ix1, float iy1 ,float ix2, float iy2, Color c){
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		c.bind();
		image.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(ix1, iy1);
		GL11.glVertex2f(x1, y1);
		GL11.glTexCoord2f(ix2, iy1);
		GL11.glVertex2f(x2, y1);
		GL11.glTexCoord2f(ix2, iy2);
		GL11.glVertex2f(x2, y2);
		GL11.glTexCoord2f(ix1, iy2);
		GL11.glVertex2f(x1, y2);
		GL11.glEnd();		
	}

	
}
