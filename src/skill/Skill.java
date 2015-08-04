package skill;

import image.loader.SkillImageLoader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import collision.CollisionBox;
public class Skill{
	public String url;
	public int Sprite;
	public int[] SpriteWait;
	public int show;
	public int wait;
	public int x;
	public int y;
	public Texture texture;
	public boolean destroyon;
	
	public CollisionBox[] collision;

	public SkillImageNumber Init(String url, int j, int Sprite, int[] SpriteWait, Texture texture) throws IOException{
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
					if (!str.contains("end") && str.substring(str.indexOf("[") + 1, str.indexOf("]")) != "end") {
						SpriteWait[Integer.valueOf(str.substring(
								str.indexOf("[") + 1, str.indexOf("]")))] = Integer
								.valueOf(str.substring(str.indexOf("=") + 1));
					}
				}
			}
		}
		br.close();
		texture = SkillImageLoader.SkillTexture[j];
		return new SkillImageNumber(Sprite, SpriteWait);
	}

	public Skill(int x, int y, Texture texture, int Sprite, int[] SpriteWait) throws IOException {
		this.Sprite = Sprite;
		this.SpriteWait = SpriteWait;
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.destroyon = false;
		RenderImage(x, y, texture, Sprite);
	}
	
	public Skill(){}
	
	public void addCollisionBox(int i, int x, int y, int width, int height){
		this.collision[i] = new CollisionBox(x, y, width, height);
	}
	
	public boolean CheckCollision(double x, double y){
		boolean b = false;
		for(int i = 0; i < this.collision.length; i++){
			if(collision[i] != null){
				if(collision[i].CheckCollisioned(x - this.x, y - this.y))b=true;
			}
		}
		return b;
	}

	public void RenderImage(int x, int y, Texture texture, int Sprite) {
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
	}
	
	public void RenderImageSecond(int x, int y, int Sprite){
	}
	
	public void update(){
		wait ++;
		if(wait >= SpriteWait[show]){
			wait = 0;
			show ++;
			if(show >= Sprite){
				this.destroy();
			}
		}
		this.updateShow(show);
	}
	
	public void updateShow(int show){
		
	}
	
	public void render(){
		RenderImage(this.x, this.y, this.texture, this.Sprite);
		RenderImageSecond(this.x, this.y, this.Sprite);
		
	}
	
	public void destroy(){
		destroyon = true;
	}
}
