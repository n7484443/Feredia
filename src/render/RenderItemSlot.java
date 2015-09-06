package render;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import core.MainRoop;
import collision.CollisionBox;
import render.font.FontRenderer;

public class RenderItemSlot extends GuiBase{
	
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
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		gui.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(x, y);
		GL11.glTexCoord2f(gui.getWidth(), 0);
		GL11.glVertex2f(x+256, y);
		GL11.glTexCoord2f(gui.getWidth(), gui.getHeight());
		GL11.glVertex2f(x+256, y+512);
		GL11.glTexCoord2f(0, gui.getHeight());
		GL11.glVertex2f(x, y+512);
		GL11.glEnd();
		
		GL11.glDisable(GL11.GL_BLEND);
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 5; j++){
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glColor4f(1.f, 1.f, 1.f, 1.0f);
				if(MainRoop.p.itemSlot.getSlot(showedslot)[i + j*4] != null && MainRoop.p.itemSlot.getSlot(showedslot)[i + j*4].itemStack.StackSize > 0){
					MainRoop.p.itemSlot.getSlot(showedslot)[i + j*4].itemStack.i.getImage().bind();
					GL11.glBegin(GL11.GL_QUADS);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
						GL11.glTexCoord2f(0, 0);
						GL11.glVertex2f(i*32+5 + x, j*32+5 + 37 + y);
						GL11.glTexCoord2f(0, 1);
						GL11.glVertex2f(i*32+5 + x, (j+1)*32-5 + 37 + y);
						GL11.glTexCoord2f(1, 1);
						GL11.glVertex2f((i+1)*32-5 + x, (j+1)*32-5 + 37 + y);
						GL11.glTexCoord2f(1, 0);
						GL11.glVertex2f((i+1)*32-5 + x, j*32+5 + 37 + y);
					GL11.glEnd();
					FontRenderer.kor_black.bind();
					FontRenderer.renderReSizeable(i * 32 + x, j * 32 + 37 + y, 9, String.valueOf(MainRoop.p.itemSlot.getSlot(showedslot)[i + j*4].itemStack.StackSize), 1.0f);
					FontRenderer.renderReSizeable(x, 11 + y + 3, 12, String.valueOf(MainRoop.p.itemSlot.getSlotName(0)), 1.0f);
					FontRenderer.renderReSizeable(x + 51 + 10, 11 + y + 3, 12, String.valueOf(MainRoop.p.itemSlot.getSlotName(1)), 1.0f);
					FontRenderer.renderReSizeable(x + 102 + 10, 11 + y + 3, 12, String.valueOf(MainRoop.p.itemSlot.getSlotName(2)), 1.0f);
					FontRenderer.renderReSizeable(x + 153 + 10, 11 + y + 3, 12, String.valueOf(MainRoop.p.itemSlot.getSlotName(3)), 1.0f);
					FontRenderer.renderReSizeable(x + 204 + 10, 11 + y + 3, 12, String.valueOf(MainRoop.p.itemSlot.getSlotName(4)), 1.0f);
				}
			}
		}
		FontRenderer.kor_black.bind();
		FontRenderer.renderReSizeable(x, y, 8, "아이템 창", 1.0f);

		GL11.glDisable(GL11.GL_BLEND);
		
	}
	
	public static void ItemStackEvent(double mousex, double mousey){
		int x1 = (int) (mousex - x);
		int y1 = (int) (mousey - y - 37);
		if(x1 > 0 && y1 > 0 && x1 < 256 && y1 < 512){
			int i = (x1+5)/32;
			int j = y1/32;
			if(MainRoop.p.itemSlot.getSlot(showedslot)[j * 5 + i] != null){
				MainRoop.p.itemSlot.getSlot(showedslot)[j * 5 + i].useItem();
			}
			
		}
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
	
	public RenderItemSlot() throws IOException{
		x = 0;
		y = 0;
		showedslot = 1;
		gui = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/gui/item_gui.png"));
		DragCollisionBox = new CollisionBox(x, y, 243, 11);
		DeleteCollisionBox = new CollisionBox(x + 244, y, 12, 12);
	}

}
