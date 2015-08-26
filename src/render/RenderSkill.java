package render;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import collision.CollisionBox;
import render.font.FontRenderer;
import skill.SkillSlot;

public class RenderSkill extends GuiBase{	
	public static SkillSlot[] s;
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
		for(int i = 0; i < 4; i++){
			
		}
		FontRenderer.kor_black.bind();
		FontRenderer.renderReSizeableWithColor(x, y, 10, "스킬 창", 1.0f);
		
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
	
	public RenderSkill() throws IOException{
		x = 0;
		y = 0;
		s = new SkillSlot[5];
		gui = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/gui/skill_gui.png"));
		DragCollisionBox = new CollisionBox(x, y, 243, 11);
		DeleteCollisionBox = new CollisionBox(x + 244, y, 12, 12);
	}
}
