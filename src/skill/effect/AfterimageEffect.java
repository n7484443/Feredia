package skill.effect;

import image.loader.SkillImageLoader;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class AfterimageEffect implements Effect {

	public boolean Horizontal;

	public int number;
	public int x;
	public int y;
	public int width;
	public int percent;
	public int texture;

	public AfterimageEffect(int x, int y, int width, int number, int percent,
			int texture) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.number = number;
		this.percent = percent;
		this.texture = texture;
		this.Horizontal = false;
	}
	
	public void setNumber (int number){
		this.number = number;
	}
	
	public void addNumber (int number){
		this.number += number;
	}
	
	public void moveEffect(int x, int y){
		this.x += x;
		this.y += y;
	}

	public void setHorizontal() {
		Horizontal = true;
	}

	@Override
	public void draw() {
		if (Horizontal) {
			for (int i = 0; i < number; i++) {
				Texture t = SkillImageLoader.SkillTexture[texture];
				t.bind();
				GL11.glColor4f(1.0f, 1.0f, 1.0f, (float) (1 - percent*(i+1) / 100f));
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(0, 0);
				GL11.glVertex2f(x - width * (i+1) + (i+1)*2, y + (i+1)*2);
				GL11.glTexCoord2f(t.getWidth(), 0);
				GL11.glVertex2f(x - width * (i+1) - (i+1)*2 + t.getImageWidth(), y + (i+1)*2);
				GL11.glTexCoord2f(t.getWidth(), t.getHeight());
				GL11.glVertex2f(x - width * (i+1) - (i+1)*2 + t.getImageWidth(),
						y + t.getImageHeight() - (i+1)*2);
				GL11.glTexCoord2f(0, t.getHeight());
				GL11.glVertex2f(x - width * (i+1) + (i+1)*2, y + t.getImageHeight() - (i+1)*2);
				GL11.glEnd();
			}
		}
	}
}
