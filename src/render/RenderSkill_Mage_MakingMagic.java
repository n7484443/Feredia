package render;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import render.font.FontRenderer;
import skill.SkillSlot;
import collision.CollisionBox;

public class RenderSkill_Mage_MakingMagic extends GuiBase{
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
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Render.RenderImageBoxColor(x, y, x+432, y+432, gui, Color.white);
		
		FontRenderer.renderReSizeable(x, y, 8, "스킬 제작 창", 1.f, 1);
		
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
	
	public RenderSkill_Mage_MakingMagic() throws IOException{
		x = 0;
		y = 0;
		gui = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/gui/skillmagemaking.png"));
		DragCollisionBox = new CollisionBox(x, y, 434, 14);
		DeleteCollisionBox = new CollisionBox(x + 434, y, 12, 12);
	}
}
