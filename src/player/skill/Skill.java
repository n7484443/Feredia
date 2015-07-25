package player.skill;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import collision.CollisionBox;
import render.RenderUnit;

public class Skill extends RenderUnit {
	public String url;
	public int Sprite;
	public int[] SpriteWait;
	public int show;
	public int wait;
	
	public CollisionBox collision;

	public Skill(String url) throws IOException {
		super(0, 0, url + ".png");
		this.url = url;
		show = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("src/" + this.url + ".skillinfo")));
		String str;
		while ((str = br.readLine()) != null) {
			if (str.contains("Sprite=")) {
				Sprite = Integer.valueOf(str.substring(str.indexOf("=") + 1));
				SpriteWait = new int[Sprite];
			} else if(str.contains("basicSpeed=")){
				for(int i = 0; i < Sprite; i++){
					SpriteWait[i] = Integer.valueOf(str.substring(str.indexOf("=") + 1));
				}
			} else if (str.contains("Wait:")) {
				while (!str.contains("end")) {
					str = br.readLine();
					System.out.println(str);
					if (!str.contains("end") && str.substring(str.indexOf("[") + 1, str.indexOf("]")) != "end") {
						SpriteWait[Integer.valueOf(str.substring(
								str.indexOf("[") + 1, str.indexOf("]")))] = Integer
								.valueOf(str.substring(str.indexOf("=") + 1));
					}
				}
			}
		}
		br.close();
		try {
			texture = TextureLoader.getTexture("png", ResourceLoader
					.getResourceAsStream(url + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addCollisionBox(int x, int y, int width, int height){
		this.collision = new CollisionBox(x, y, width, height);
	}
	
	public boolean CheckCollision(double x, double y){
		if(collision != null){
			return collision.CheckCollisioned(x - this.x, y - this.y);
		}
		return false;
	}

	public void RenderImage(int x, int y) {
		texture.bind();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(show * (texture.getWidth() / Sprite), 0);
		GL11.glVertex2f(x, y);
		GL11.glTexCoord2f(show * (texture.getWidth() / Sprite), texture.getHeight());
		GL11.glVertex2f(x, y + texture.getImageHeight());
		GL11.glTexCoord2f((show + 1) * (texture.getWidth() / Sprite),
				texture.getHeight());
		GL11.glVertex2f(x + texture.getImageWidth()/Sprite, y + texture.getImageHeight());
		GL11.glTexCoord2f((show + 1) * (texture.getWidth() / Sprite), 0);
		GL11.glVertex2f(x + texture.getImageWidth()/Sprite, y);
		GL11.glEnd();
		update();
	}
	
	public void update(){
		wait ++;
		if(wait >= SpriteWait[show]){
			wait = 0;
			show ++;
			if(show >= Sprite){
				show = 0;
			}
		}
	}
}
