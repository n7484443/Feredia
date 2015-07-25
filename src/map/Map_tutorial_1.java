package map;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import portal.Portal;
import collision.CollisionBox;

public class Map_tutorial_1 extends Map{
	public Map_tutorial_1() throws IOException{
		name = "튜토리얼1";
		BackGround = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/map/background/background.png"));
	}
	
	public void Init() throws IOException{
		mapblock = new int[width/32][height/32];
		portal = new Portal[1];
		for(int i = 0; i < width/32; i++){
			for(int j = 0; j < height/32; j++){
				mapblock[i][j] = 0;
			}
		}
		for(int i = 0; i < width/32; i++){
			mapblock[i][29] = 1;
			mapblock[i][28] = 1;
			mapblock[i][27] = 1;
			mapblock[i][26] = 1;
			mapblock[i][25] = 1;
		}
		for(int i = 0; i < width/32; i++){
			mapblock[i][24] = 7;
		}
		
		Collision = new CollisionBox[1];
		Collision[0] = new CollisionBox(-16, this.height - 6*32, this.width, 6*32);
		
		portal[0] = new Portal(400, height-32*7, 400, height-32*7+16, Maps.tutorial_0);
	}

	public void Inter(){}
	
	public Texture getMapTexture(int i, int j){
		return MapImageLoader.blockTexture[mapblock[i][j]-1];
	}
}
