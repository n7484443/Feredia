package npc;

import org.newdawn.slick.opengl.Texture;

import image.loader.NpcImageLoader;
import collision.CollisionBox;

public class NpcBase {
	public int x;
	public int y;
	public int width;
	public int height;
	
	public StringNpc[] npcTalk;
	
	public int showedNpcTalk;
	public int showedNpcTalkMax;
	
	public CollisionBox collision;
	
	public int texture;
	
	public NpcBase(int x, int y, int width, int height, int texture, StringNpc... npcTalk){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.npcTalk = npcTalk;
		this.showedNpcTalk = 0;
		this.showedNpcTalkMax = npcTalk.length;
		this.texture = texture;
		this.collision = new CollisionBox(x, y, width, height);
	}
	

	
	public boolean CheckCollision(CollisionBox c){
		if(collision != null){
			return collision.CheckCollisioned(c);
		}
		return false;
	}
	
	public Texture getTexture(){
		return NpcImageLoader.NpcTexture[texture];
	}
}
