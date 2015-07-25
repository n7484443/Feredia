package player.skill;

import java.io.IOException;

public class Skills_Mage {
	public static Skill MagicMissile;
	
	public static void Init() throws IOException{
		MagicMissile = new Skill("image/skill/mage_skill_magic_missile");
	}
}
