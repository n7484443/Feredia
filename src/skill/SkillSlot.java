package skill;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class SkillSlot {
	public Skill skill;
	public int skillpoint;
	public int maxSkillpoint;
	
	public SkillSlot(Skill i, int skillpoint, int maxSkillpoint){
		this.skill = i;
		this.skillpoint = skillpoint;
		this.maxSkillpoint = maxSkillpoint;
	}
	
	public static Texture texture;
	
	public static void Init() throws IOException{
		texture = TextureLoader.getTexture(".png", ResourceLoader.getResourceAsStream("image/gui/skil_slot.png"));
	}
	
	public static Texture getTexture(){
		return texture;
	}
}
