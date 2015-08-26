package map;

public class Tile {
	public int image;
	public int num;
	
	public Tile(int image, int num){
		this.image = image;
		this.num = num;
	}
	
	public int getTexture(){
		return image;
	}
	
	public int getnum(){
		return num;
	}
}