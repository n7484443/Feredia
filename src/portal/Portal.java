package portal;

import core.MainRoop;
import collision.CollisionBox;
import map.Map;

public class Portal {
	public int x;
	public int y;
	public int movex;
	public int movey;
	public final int width = 32;
	public final int height = 32;
	public Map MoveMap;
	public CollisionBox collision;
	
	public Portal(int x, int y, int movex, int movey, Map MoveMap){
		this.x = x;
		this.y = y;
		this.movex = movex;
		this.movey = movey;
		this.MoveMap = MoveMap;
		this.collision = new CollisionBox(x, y, width, height);
	}
	
	public boolean CheckCollision(CollisionBox c){
		return collision.CheckCollisioned(c);
	}
	
	public void PlayerMoveMap(){
		MainRoop.p.setX(movex);
		MainRoop.p.setY(movey);
		MainRoop.p.setMap(this.MoveMap);
		MainRoop.p.Vy = 0;
	}
}
