package render;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import core.MainRoop;
import render.font.FontRenderer;
import collision.CollisionBox;

public class RenderMiniMap extends GuiBase{
	public static int x;
	public static int y;
	public static int beforex;
	public static int beforey;
	public static int beforeCollisionx;
	public static int beforeCollisiony;
	public static int showx;
	public static int showy;
	
	public void render() {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.f);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		for(int i = 0; i < MainRoop.p.getMap().width / 32; i++){
			for(int j = 0; j < MainRoop.p.getMap().height / 32; j++){
				if(MainRoop.p.getMap().mapblock[i][j] != 0){
					MainRoop.p.getMap().getColorMiniMap(MainRoop.p.getMap().mapblock[i][j] - 1).bind();
					GL11.glBegin(GL11.GL_QUADS);
					GL11.glVertex2f(x + i, y + j);
					GL11.glVertex2f(x + i + 1, y + j);
					GL11.glVertex2f(x + i + 1, y + j + 1);
					GL11.glVertex2f(x + i, y + j + 1);
					GL11.glEnd();
				}
				
			}
		}
		System.out.println(1);
		

		FontRenderer.kor_black.bind();
		FontRenderer.renderReSizeableWithColor(x, y, 10, "미니 맵", Color.yellow, 1.0f);
		
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	public void MoveBefore(){
		beforex = x;
		beforey = y;
		beforeCollisionx = DragCollisionBox.x;
		beforeCollisiony = DragCollisionBox.y;
	}
	
	public void Move(double Dx, double Dy){
		DragCollisionBox.x = beforeCollisionx + (int) Dx;
		DragCollisionBox.y = beforeCollisiony + (int) Dy;
		DeleteCollisionBox.x = beforeCollisionx + (int) Dx + 244;
		DeleteCollisionBox.y = beforeCollisiony + (int) Dy;
		x = beforex + (int)Dx;
		y = beforey + (int)Dy;
	}
	
	public RenderMiniMap() throws IOException{
		x = 0;
		y = 0;
		DragCollisionBox = new CollisionBox(x, y, 434, 14);
		DeleteCollisionBox = new CollisionBox(x + 434, y, 12, 12);
	}
}
