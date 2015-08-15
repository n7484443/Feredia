package skill.mage;

import image.loader.SkillImageLoader;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import core.MainRoop;
import collision.CollisionBox;
import skill.Skill;
import skill.SkillImageNumber;

public class Missile extends Skill {
	public static int OwnSprite;
	public static int[] OwnSpriteWait;
	public static int OwnSpriteCollision;
	public static int[] OwnSpriteWaitCollision;
	public static Texture texture1;

	public Missile(int x, int y, boolean Dir) throws IOException {
		super(x, y, SkillImageLoader.SkillTexture[1], OwnSprite, OwnSpriteWait);
		cis = false;
		collisioned = false;
		this.collision = new CollisionBox[1];
		collision[0] = new CollisionBox(x + 16, y + 16, 32, 32);
		this.Dir = Dir;
		this.j = 0;
	}

	public Missile() {
	}

	public void Init() throws IOException {
		SkillImageNumber s = Init("image/skill/mage_skill_missile", 0,
				OwnSprite, OwnSpriteWait, texture1);
		OwnSprite = s.getNumber();
		OwnSpriteWait = s.getTime();
		SkillImageNumber s1 = Init("image/skill/mage_skill_missile_collision",
				0, OwnSprite, OwnSpriteWait, texture1);
		OwnSpriteCollision = s1.getNumber();
		OwnSpriteWaitCollision = s1.getTime();
	}
	int j;
	public void update() {
		wait++;
		if (!collisioned) {
			if (wait >= SpriteWait[show]) {
				wait = 0;
				show++;
				if (show >= Sprite) {
					show = 0;
					j++;
				}
			}
			CheckCollision();
			if(j >= 5)this.destroy();
		}else{
			if (wait >= SpriteWait[show]) {
				wait = 0;
				show++;
				if (show >= Sprite) {
					show = 0;
					this.destroy();
				}
			}
		}
	}

	public boolean collisioned;
	public boolean cis;

	public void CheckCollision() {
		if (checkCollisionBlock(
				Dir ? this.x - 10 : this.x + this.texture.getImageWidth() + 10,
				this.y)) {
			collisioned = true;
		}
		if (!cis && collisioned) {
			this.texture = SkillImageLoader.SkillTexture[2];
			this.wait = 0;
			this.show = 0;
			this.Sprite = OwnSpriteCollision;
			this.SpriteWait = OwnSpriteWaitCollision;
			cis = true;
		}
		if (!collisioned) {
			x += (Dir ? -1 : 1) * 10;
			collision[0].x += (Dir ? -1 : 1) * 10;
		} else if (this.show == this.Sprite - 1) {
			this.destroy();
		}
	}

	@Override
	public void RenderImageSecond(int x, int y, int Sprite) {
		if (MainRoop.Debug) {
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glColor4f(1.f, 0.f, 0.f, 0.5f);
			GL11.glDisable(GL11.GL_TEXTURE);
			GL11.glBegin(GL11.GL_LINE_LOOP);
			GL11.glVertex2f(collision[0].x, collision[0].y);
			GL11.glVertex2f(collision[0].x + collision[0].width, collision[0].y);
			GL11.glVertex2f(collision[0].x + collision[0].width, collision[0].y
					+ collision[0].height);
			GL11.glVertex2f(collision[0].x, collision[0].y
					+ collision[0].height);
			GL11.glEnd();
		}
	}
}
