package player.skill.mage;

import java.io.IOException;
import org.newdawn.slick.opengl.Texture;

import player.skill.Projectile;
import player.skill.Skill;
import player.skill.SkillImageLoader;
import player.skill.SkillImageNumber;

public class MagicMissile extends Skill {
	public static int OwnSprite;
	public static int[] OwnSpriteWait;
	public static Texture texture;
	public Projectile[] projectile;

	public MagicMissile(int x, int y) throws IOException {
		super(x, y, SkillImageLoader.SkillTexture[0], OwnSprite, OwnSpriteWait);
		projectile = new Projectile[5];
	}

	public void updateShow(int show) {
		if (!this.destroyon) {
			if (show == 26 && projectile[0] == null) {
				projectile[0] = new Projectile(this.x + 10, this.y, 38, 21,
						SkillImageLoader.SkillTexture[1]);
				projectile[1] = new Projectile(this.x + 30, this.y + 31, 38, 21,
						SkillImageLoader.SkillTexture[1]);
				projectile[2] = new Projectile(this.x + 50, this.y + 62, 38, 21,
						SkillImageLoader.SkillTexture[1]);
				projectile[3] = new Projectile(this.x + 30, this.y + 93, 38, 21,
						SkillImageLoader.SkillTexture[1]);
				projectile[4] = new Projectile(this.x + 10, this.y + 124, 38, 21,
						SkillImageLoader.SkillTexture[1]);
			} else if (show > 26) {
				for (int i = 0; i < projectile.length; i++) {
					projectile[i] = null;
				}
			} else if(show == 26){
				for (int i = 0; i < projectile.length; i++) {
					projectile[i].moveX(20);
					projectile[i].moveCollisionBoxX(20);
				}
			}
		}else{
			for (int i = 0; i < projectile.length; i++) {
				projectile[i] = null;
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

	public void RenderImageSecond(int x, int y, int Sprite) {
		for(int i = 0; i < projectile.length; i++){
			if(projectile[i] != null){
				projectile[i].Render();
			}
		}
	}

}
