package map.tutorial;

import java.io.IOException;

import map.Map;
import map.Maps;
import map.Tile;
import map.Tiles;

import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import portal.Portal;
import collision.CollisionBox;

public class Map_tutorial_1 extends Map{
	public Map_tutorial_1() throws IOException{
		name = "튜토리얼 둘째";
		BackGround = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/map/background/background.png"));
	}
	
	public void Init() throws IOException{
		mapblock = new Tile[width/32][height/32];
		portal = new Portal[1];
		for(int i = 0; i < width/32; i++){
			for(int j = 0; j < height/32; j++){
				mapblock[i][j] = Tiles.air;
			}
		}
		for(int i = 0; i < width/32; i++){
			for (int j = 25; j < 30; j++) {
				if (i % 2 == 1) {
					mapblock[i][j] = Tiles.dirt_l;
				} else {
					mapblock[i][j] = Tiles.dirt_r;
				}
			}
		}
		for (int i = 0; i < width / 32; i++) {
			if (i % 2 == 1) {
				mapblock[i][24] = Tiles.grass_l;
			} else {
				mapblock[i][24] = Tiles.grass_r;
			}
		}
		mapblock[6][23] = Tiles.grass_l;
		mapblock[7][23] = Tiles.grass_r;
		mapblock[6][24] = Tiles.dirt_l;
		mapblock[7][24] = Tiles.dirt_r;
		Collision = new CollisionBox[2];
		Collision[0] = new CollisionBox(0, this.height - 6*32, this.width, 6*32);
		Collision[1] = new CollisionBox(6 * 32, this.height - 7*32, 2*32, 1*32);
		
		portal[0] = new Portal(0, height-32*7, 32*39, height-32*8, Maps.tutorial_0);
	}
}
