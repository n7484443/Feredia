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
	
	public boolean CheckCollisioned(double x, double y){
		if(this.x < x && this.y < y && this.x + this.width > x && this.y + this.height > y)return true;
		return false;
	}
}
