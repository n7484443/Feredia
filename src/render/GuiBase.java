package render;

import collision.CollisionBox;

public class GuiBase {

	public CollisionBox DragCollisionBox;
	public CollisionBox DeleteCollisionBox;
	public int width;
	public int height;
	
	public boolean CheckDragCollisionBox(double x, double y){
		return DragCollisionBox.CheckCollisionedByXY((int)x, (int)y, width, height);
	}
	
	public boolean CheckDeleteCollisionBox(double x, double y){
		return DeleteCollisionBox.CheckCollisionedByXY((int)x, (int)y, width, height);
	}
}
