package skill.mage;

import image.loader.SkillImageLoader;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;

import core.MainRoop;
import skill.Skill;
import skill.SkillImageNumber;

public class MagicMissile extends Skill {
	public static int OwnSprite;
	public static int[] OwnSpriteWait;
	public static Texture texture;
	public Missile[] effect;
	public MagicMissile(int x, int y, boolean Dir) throws IOException {
		super(x, y, SkillImageLoader.SkillTexture[0], OwnSprite, OwnSpriteWait);
		this.Dir = Dir;
		effect = new Missile[5];
	}

	public void updateShow(int show) {
		if (!this.destroyon) {
			if (show == 26 && effect[0] == null) {
				try {
					effect[0] = new Missile(this.x + 10
							* (Dir ? -1 : 1), this.y - 17, Dir);
					effect[1] = new Missile(this.x + 30
							* (Dir ? -1 : 1), this.y + 14, Dir);
					effect[2] = new Missile(this.x + 50
							* (Dir ? -1 : 1), this.y + 45, Dir);
					effect[3] = new Missile(this.x + 30
							* (Dir ? -1 : 1), this.y + 76, Dir);
					effect[4] = new Missile(this.x + 10
							* (Dir ? -1 : 1), this.y + 107, Dir);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				int j = 0;
				for (int i = 0; i < MainRoop.p.skill.length; i++) {
					if (MainRoop.p.skill[i] == null) {
						MainRoop.p.skill[i] = effect[j];
						j++;
						if(j == 5)break;
					}
				}
			}
		}
	}

	public MagicMissile() {
	}

	public void Init() throws IOException {
		SkillImageNumber s = Init("image/skill/mage_skill_magic_missile", 0,
				OwnSprite, OwnSpriteWait, texture);
		OwnSprite = s.getNumber();
		OwnSpriteWait = s.getTime();
	}

}
