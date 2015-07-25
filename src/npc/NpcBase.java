package npc;

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
	
	public NpcBase(int x, int y, int width, int height, StringNpc... npcTalk){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.npcTalk = npcTalk;
		this.showedNpcTalk = 0;
		this.showedNpcTalkMax = npcTalk.length;
		this.collision = new CollisionBox(x, y, width, height);
	}
	

	
	public boolean CheckCollision(double x, double y){
		if(collision != null){
			return collision.CheckCollisioned(x, y);
		}
		return false;
	}
}
