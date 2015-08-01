package player.skill;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class SkillImageLoader {
	public static Texture[] SkillTexture = new Texture[10];
	
	public static void Init() throws IOException{
		SkillTexture[0] = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/skill/mage_skill_magic_missile.png"));
		SkillTexture[1] = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/skill/mage_skill_magic_missile_effect.png"));
	}
}
