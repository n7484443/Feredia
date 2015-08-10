package render;

import java.io.IOException;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import collision.CollisionBox;
import player.ItemStack;
import player.Items;
import player.Slot;
import render.font.FontRenderer;

public class RenderSlot{	
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
	
	public static CollisionBox DragCollisionBox;
	public static CollisionBox DeleteCollisionBox;
	public void render() {
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 5; j++){
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glColor4f(1.f, 1.f, 1.f, 0.7f);
				Slot.getTexture().bind();
				GL11.glBegin(GL11.GL_QUADS);
					GL11.glTexCoord2f(0, 0);
					GL11.glVertex2f(i*32, j*32);
					GL11.glTexCoord2f(0, 1);
					GL11.glVertex2f(i*32, (j+1)*32);
					GL11.glTexCoord2f(1, 1);
					GL11.glVertex2f((i+1)*32, (j+1)*32);
					GL11.glTexCoord2f(1, 0);
					GL11.glVertex2f((i+1)*32, j*32);
				GL11.glEnd();
				GL11.glColor4f(1.f, 1.f, 1.f, 1.0f);
				if(s[i + j*4] != null){
					s[i + j*4].itemStack.i.getImage().bind();
					GL11.glBegin(GL11.GL_QUADS);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
						GL11.glTexCoord2f(0, 0);
						GL11.glVertex2f(i*32+5, j*32+5);
						GL11.glTexCoord2f(0, 1);
						GL11.glVertex2f(i*32+5, (j+1)*32-5);
						GL11.glTexCoord2f(1, 1);
						GL11.glVertex2f((i+1)*32-5, (j+1)*32-5);
						GL11.glTexCoord2f(1, 0);
						GL11.glVertex2f((i+1)*32-5, j*32+5);
					GL11.glEnd();
					FontRenderer.kor_black.bind();
					FontRenderer.renderReSizeableWithColor(i * 32, j * 32, 10, String.valueOf(s[i + j*4].itemStack.StackSize), Color.yellow, 1.0f);
				}
			}
		}

		GL11.glDisable(GL11.GL_BLEND);
		
	}
	
	public static boolean CheckDragCollisionBox(double x, double y){
		return DragCollisionBox.CheckCollisioned(x, y);
	}
	
	public static boolean CheckDeleteCollisionBox(double x, double y){
		return DeleteCollisionBox.CheckCollisioned(x, y);
	}
	
	public static void MoveBefore(){
		beforex = x;
		beforey = y;
		beforeCollisionx = DragCollisionBox.x;
		beforeCollisiony = DragCollisionBox.y;
	}
	
	public static void Move(double Dx, double Dy){
		DragCollisionBox.x = beforeCollisionx + (int) Dx;
		DragCollisionBox.y = beforeCollisiony + (int) Dy;
		DeleteCollisionBox.x = beforeCollisionx + (int) Dx + 244;
		DeleteCollisionBox.y = beforeCollisiony + (int) Dy;
		x = beforex + (int)Dx;
		y = beforey + (int)Dy;
	}
	
	public static void Init() throws IOException{
		x = 0;
		y = 0;
		s= new Slot[5*4];
		s[5] = new Slot(new ItemStack(2, Items.i));
		s[10] = new Slot(new ItemStack(5, Items.i));
		gui = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/gui/slots_up.png"));
		DragCollisionBox = new CollisionBox(x, y, 243, 11);
		DeleteCollisionBox = new CollisionBox(x + 244, y, 12, 12);
	}

}
