package skill.mage;

import java.io.IOException;

public class Skills_Mage {
	
	public static void Init() throws IOException{
		MagicMissile magicMissile = new MagicMissile();
		Missile Missile = new Missile();
		
		magicMissile.Init();
		Missile.Init();
	}
}
