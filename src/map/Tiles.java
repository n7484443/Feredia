package map;

public class Tiles {
	public static Tile air;
	public static Tile dirt_l;
	public static Tile dirt_r;
	public static Tile grass_l;
	public static Tile grass_r;
	public static Tile grass_left;
	public static Tile grass_right;
	
	public static void Init(){
		air = new Tile(-1, 0);
		dirt_l = new Tile(0, 0);
		dirt_r = new Tile(0, 1);
		grass_l = new Tile(6, 0);
		grass_r = new Tile(6, 1);
		grass_left = new Tile(4,0);
		grass_right = new Tile(5,0);
	}
}
