package map;

import java.io.IOException;

import npc.DialogueNpc;
import npc.NpcBase;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import portal.Portal;
import render.RenderMain;
import render.RenderUnit;
import collision.CollisionBox;

public class Map_tutorial_0 extends Map{
	//width:40, height:30
	
	public Map_tutorial_0() throws IOException{
		name = "튜토리얼0";
		BackGround = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("image/map/background/background.png"));
	}
	
	public void Init() throws IOException{
		mapblock = new int[width/32][height/32];
		npc = new NpcBase[1];
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
		for(int i = 0; i < width/32-2; i++){
			mapblock[i][24] = 7;
		}
		mapblock[width/32-2][24] = 1;
		mapblock[width/32-1][24] = 1;
		mapblock[width/32-2][23] = 5;
		mapblock[width/32-1][23] = 7;
		
		Collision = new CollisionBox[2];
		Collision[0] = new CollisionBox(-16, this.height - 6*32, this.width, 6*32);
		Collision[1] = new CollisionBox(38*32-16, this.height - 7*32, this.width-37*32+16, 32);
		
		npc[0] = new NpcBase(0, height-32*7, 32, 32, new DialogueNpc("안녕하세요.", "제가 지금부터 조작 방법을 알려드릴게요."), new DialogueNpc("일단,"));
		portal[0] = new Portal(200, height-32*7, 16, height-32*7+16, Maps.tutorial_1);
	}
	

	public void Inter(){
		try {
			RenderMain.list.add(new RenderUnit(140,40,"image/map/tutorial/space_bar.png", this));
			RenderMain.list.add(new RenderUnit(200,40,"image/map/tutorial/left_bar.png", this));
			RenderMain.list.add(new RenderUnit(240,40,"image/map/tutorial/right_bar.png", this));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Texture getMapTexture(int i, int j){
		return MapImageLoader.blockTexture[mapblock[i][j]-1];
	}
}
