package player.skill;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import collision.CollisionBox;

public class Projectile {
	public int x;
	public int y;
	public Texture texture;
	public CollisionBox collisionBox;

	public Projectile(int x, int y, int width, int height, Texture texture) {
		this.collisionBox = new CollisionBox(x, y, width, height);
		this.texture = texture;
		this.x = x;
		this.y = y;

	}

	public void moveX(int x) {
		this.x += x;
	}

	public void moveY(int y) {
		this.y += y;
	}

	public void moveCollisionBoxX(int x) {
		this.collisionBox.x += x;
	}

	public void moveCollisionBoxY(int y) {
		this.collisionBox.y += y;
	}

	public boolean CheckCollisionBox(double x, double y) {
		return this.collisionBox.CheckCollisioned(x, y);
	}

	public void Render() {
		this.texture.bind();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(x, y);
		GL11.glTexCoord2f(0, texture.getHeight());
		GL11.glVertex2f(x, y + texture.getImageHeight());
		GL11.glTexCoord2f(texture.getWidth(), texture.getHeight());
		GL11.glVertex2f(x + texture.getImageWidth(),
				y + texture.getImageHeight());
		GL11.glTexCoord2f(texture.getWidth(), 0);
		GL11.glVertex2f(x + texture.getImageWidth(), y);
		GL11.glEnd();
	}
}
