package npc;

import org.newdawn.slick.opengl.Texture;
import base.Strings;
import core.MainRoop;
import image.loader.NpcImageLoader;
import collision.CollisionBox;

public abstract class NpcBase implements NpcNeed{
	public int x;
	public int y;
	public int width;
	public int height;
	
	public Strings[] npcTalk;
	
	public int showedNpcTalk;
	public int showedNpcTalkMax;
	
	public CollisionBox collision;
	
	public int texture;
	
	public NpcBase(int x, int y, int width, int height, int texture, Strings... npcTalk){
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
	
	public void TalkFirst(){
		
	}
	
	public void TalkCheck(){
		MainRoop.p.npc.showedNpcTalk++;
		if (showedNpcTalk >= showedNpcTalkMax) {
			showedNpcTalk = 0;
			MainRoop.p.npc = null;
			MainRoop.p.moveable = true;
			End();
		}
	}
	
	public Strings getNpcTalk(){
		return this.npcTalk[MainRoop.p.npc.showedNpcTalk];
	}
}
