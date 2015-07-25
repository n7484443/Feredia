package render;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import player.ItemStack;
import player.Items;
import player.Slot;
import render.font.FontRenderer;

public class RenderSlot implements Renderer{
	public int Id = 0;
	
	public static Slot[] s;
	
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
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glColor4f(1.f, 1.f, 1.f, 0.3f);
				if(s[i + j*4] != null){
					s[i + j*4].itemStack.i.image.bind();
					GL11.glBegin(GL11.GL_QUADS);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ZERO);
						GL11.glTexCoord2f(0, 0);
						GL11.glVertex2f(i*32+5, j*32+5);
						GL11.glTexCoord2f(0, 1);
						GL11.glVertex2f(i*32+5, (j+1)*32-5);
						GL11.glTexCoord2f(1, 1);
						GL11.glVertex2f((i+1)*32-5, (j+1)*32-5);
						GL11.glTexCoord2f(1, 0);
						GL11.glVertex2f((i+1)*32-5, j*32+5);
					GL11.glEnd();
					FontRenderer.kor_white.bind();
					FontRenderer.renderReSizeableWithColor(i * 32, j * 32, 10, String.valueOf(s[i + j*4].itemStack.StackSize), Color.yellow, 1.0f);
				}
			}
		}
		
		
	}
	
	public static void Init(){
		s= new Slot[5*4];
		s[5] = new Slot(new ItemStack(2, Items.i));
		s[10] = new Slot(new ItemStack(5, Items.i));
	}
	
	public int getID() {
		return Id;
	}

}
