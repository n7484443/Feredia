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
	public static final int showper = 10; 
	public Color playerColor;
	public Color npcColor;
	public Color portalColor;
	 
	public void render() { 
		GL11.glDisable(GL11.GL_TEXTURE_2D); 
		GL11.glEnable(GL11.GL_BLEND); 
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); 
		for(int i = 0; i < MainRoop.p.getMap().width / 32; i++){ 
			for(int j = 0; j < MainRoop.p.getMap().height / 32; j++){ 
				if(MainRoop.p.getMap().mapblock[i][j].getTexture() != -1){ 
					Render.RenderBox(x + i*showper, y + j*showper, x + (i+1)*showper, y + (j+1)*showper, MainRoop.p.getMap().getColorMiniMap(MainRoop.p.getMap().mapblock[i][j].getTexture()));
				}
			}
		}
		
		if(MainRoop.p.getMap().npc != null){
			for(int i = 0; i < MainRoop.p.getMap().npc.length; i++){
				Render.RenderBox(x + Math.round(MainRoop.p.getMap().npc[i].x/32*showper), y + Math.round(MainRoop.p.getMap().npc[i].y/32*showper),
						x + Math.round((MainRoop.p.getMap().npc[i].x/32+1)*showper), y + Math.round((MainRoop.p.getMap().npc[i].y + MainRoop.p.getMap().npc[i].height)/32*showper),npcColor);
			}
		}
		
		if(MainRoop.p.getMap().portal != null){
			for(int i = 0; i < MainRoop.p.getMap().portal.length; i++){
				Render.RenderBox(x + Math.round(MainRoop.p.getMap().portal[i].x/32*showper), y + Math.round(MainRoop.p.getMap().portal[i].y/32*showper),
						x + Math.round((MainRoop.p.getMap().portal[i].x/32+1)*showper), y + Math.round((MainRoop.p.getMap().portal[i].y + MainRoop.p.getMap().portal[i].height)/32*showper),portalColor);
			}
		}
		Render.RenderBox(x + Math.round(MainRoop.p.getX()/32*showper), y + Math.round(MainRoop.p.getY()/32*showper), x + Math.round((MainRoop.p.getX()/32+1)*showper), y + Math.round((MainRoop.p.getY()/32+1)*showper), playerColor);
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		FontRenderer.renderReSizeable(x, y, 9, "미니 맵", 1.f, 1);
		FontRenderer.renderReSizeable(x, y+11, 9, MainRoop.p.getMap().name, 1.f, 1);
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
		playerColor = new Color(Color.green.r, Color.green.g, Color.green.b, .6f);
		npcColor = new Color(Color.red.r, Color.red.g, Color.red.b, .6f);
		portalColor = new Color(Color.blue.r, Color.blue.g, Color.blue.b, .6f);
	}
}
