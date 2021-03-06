package collision;

public class CollisionBox {
	public int x;
	public int y;
	public int width;
	public int height;
	
	public CollisionBox(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public CollisionBox GetCollisionBox(int x, int y){
		return new CollisionBox(this.x + x, this.y + y, width, height);
	}
	
	
	public boolean CheckCollisionedPoint(double x, double y){
		if(this.x <= x && this.y <= y && this.x + this.width >= x && this.y + this.height >= y)return true;
		return false;
	}
	
	public boolean CheckCollisioned(CollisionBox c){
		if(this.x + this.width < c.x || this.x > c.x + c.width || this.y + this.height < c.y || this.y > c.y+ c.height)return false;
		return true;
	}
	
	public boolean CheckCollisioned(int x, int y, CollisionBox c){
		if(this.x + x + this.width < c.x || this.x + x > c.x + c.width || this.y + y + this.height < c.y || this.y + y > c.y+ c.height)return false;
		return true;
	}
}
