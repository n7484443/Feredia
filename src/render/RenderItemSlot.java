package render;

import item.ItemStack;
import item.Items;
import java.io.IOException;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import collision.CollisionBox;
import player.Slot;
import render.font.FontRenderer;

public class RenderItemSlot extends GuiBase{	
	public static Slot[] s;
	
	public static int x;
	public static int y;
	public static int beforex;
	public static int beforey;
	public static int beforeCollisionx;
	public static int beforeCollisiony;
	public static int showx;
	public static int showy;
	
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
				if(s[i + j*4] != null && s[i + j*4].itemStack.StackSize > 0){
					s[i + j*4].itemStack.i.getImage().bind();
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
					FontRenderer.renderReSizeableWithColor(i * 32 + x, j * 32 + 37 + y, 10, String.valueOf(s[i + j*4].itemStack.StackSize), Color.yellow, 1.0f);
				}
			}
		}
		FontRenderer.kor_black.bind();
		FontRenderer.renderReSizeableWithColor(x, y, 10, "아이템 창", Color.yellow, 1.0f);

		GL11.glDisable(GL11.GL_BLEND);
		
	}
	
	public static void ItemStackEvent(double mousex, double mousey){
		int x1 = (int) (mousex - x);
		int y1 = (int) (mousey - y - 37);
		if(x1 > 0 && y1 > 0 && x1 < 256 && y1 < 512){
			int i = (x1+5)/32;
			int j = y1/32;
			if(s[j * 5 + i] != null){
				s[j * 5 + i].useItem();
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
		s= new Slot[5*4];
		s[0] = new Slot(new ItemStack(2, Items.hp_potion_big));
		s[1] = new Slot(new ItemStack(5, Items.mp_potion_big));
		gui = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/gui/item_gui.png"));
		DragCollisionBox = new CollisionBox(x, y, 243, 11);
		DeleteCollisionBox = new CollisionBox(x + 244, y, 12, 12);
	}

}
