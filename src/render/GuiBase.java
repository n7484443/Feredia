package render;

import collision.CollisionBox;

public class GuiBase {

	public CollisionBox DragCollisionBox;
	public CollisionBox DeleteCollisionBox;
	public int width;
	public int height;
	
	public boolean CheckDragCollisionBox(double x, double y){
		return DragCollisionBox.CheckCollisionedPoint(x, y);
	}
	
	public boolean CheckDeleteCollisionBox(double x, double y){
		return DeleteCollisionBox.CheckCollisionedPoint(x, y);
	}
}
