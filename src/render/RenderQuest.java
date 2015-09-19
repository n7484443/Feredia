package render;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import core.MainRoop;
import collision.CollisionBox;
import render.font.FontRenderer;

public class RenderQuest extends GuiBase{
	public static int x;
	public static int y;
	public static int beforex;
	public static int beforey;
	public static int beforeCollisionx;
	public static int beforeCollisiony;
	public static int showx;
	public static int showy;
	
	public static int showedslot;
	
	public static Texture gui;
	
	public void render() {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.6f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Render.RenderImageBoxColor(x, y, x+256, y+512, gui, new Color(1.f, 1.f, 1.f, .6f));
		for(int i = 0; i < 4; i++){
			
		}
		FontRenderer.renderReSizeable(x, y, 8, "퀘스트 창", 1.f, 1);
		FontRenderer.renderReSizeable(x + 30, 11 + y + 3, 12, String.valueOf(MainRoop.p.questSlot.getSlotName(0)), 1.f, 1);
		FontRenderer.renderReSizeable(x + 85 + 30, 11 + y + 3, 12, String.valueOf(MainRoop.p.questSlot.getSlotName(1)), 1.f, 1);
		FontRenderer.renderReSizeable(x + 170 + 30, 11 + y + 3, 12, String.valueOf(MainRoop.p.questSlot.getSlotName(2)), 1.f, 1);
		
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
	
	public RenderQuest() throws IOException{
		x = 0;
		y = 0;
		showedslot = 1;
		gui = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/gui/quest_gui.png"));
		DragCollisionBox = new CollisionBox(x, y, 243, 11);
		DeleteCollisionBox = new CollisionBox(x + 244, y, 12, 12);
	}
}
