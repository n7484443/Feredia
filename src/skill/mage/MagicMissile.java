package skill.mage;

import image.loader.SkillImageLoader;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;

import skill.Projectile;
import skill.Skill;
import skill.SkillImageNumber;
import skill.effect.AfterimageEffect;

public class MagicMissile extends Skill {
	public static int OwnSprite;
	public static int[] OwnSpriteWait;
	public static Texture texture;
	public Projectile[] projectile;
	public int j;

	public MagicMissile(int x, int y) throws IOException {
		super(x, y, SkillImageLoader.SkillTexture[0], OwnSprite, OwnSpriteWait);
		projectile = new Projectile[5];
		j = 1;
	}

	public void updateShow(int show) {
		if (!this.destroyon) {
			AfterimageEffect effect1 = new AfterimageEffect(this.x + 10,
					this.y, 16, 1, 20, 1);
			AfterimageEffect effect2 = new AfterimageEffect(this.x + 30,
					this.y + 31, 16, 1, 20, 1);
			AfterimageEffect effect3 = new AfterimageEffect(this.x + 50,
					this.y + 62, 16, 1, 20, 1);
			AfterimageEffect effect4 = new AfterimageEffect(this.x + 30,
					this.y + 93, 16, 1, 20, 1);
			AfterimageEffect effect5 = new AfterimageEffect(this.x + 10,
					this.y + 124, 16, 1, 20, 1);
			effect1.setHorizontal();
			effect2.setHorizontal();
			effect3.setHorizontal();
			effect4.setHorizontal();
			effect5.setHorizontal();
			if (show == 26 && projectile[0] == null) {
				projectile[0] = new Projectile(this.x + 10, this.y, 38, 21, 1,
						effect1);
				projectile[1] = new Projectile(this.x + 30, this.y + 31, 38,
						21, 1, effect2);
				projectile[2] = new Projectile(this.x + 50, this.y + 62, 38,
						21, 1, effect3);
				projectile[3] = new Projectile(this.x + 30, this.y + 93, 38,
						21, 1, effect4);
				projectile[4] = new Projectile(this.x + 10, this.y + 124, 38,
						21, 1, effect5);
			} else if (show > 26) {
				for (int i = 0; i < projectile.length; i++) {
					projectile[i] = null;
				}
				
			} else if (show == 26) {
				for (int i = 0; i < projectile.length; i++) {
					projectile[i].moveX(10);
					projectile[i].moveCollisionBoxX(10);
					projectile[i].effects[0].moveEffect(10, 0);
					if(j != 5){
						projectile[i].effects[0].addNumber(1);
					}
				}
				j++;
			}
		} else {
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
		for (int i = 0; i < projectile.length; i++) {
			if (projectile[i] != null) {
				projectile[i].Render();
			}
		}
	}

}
