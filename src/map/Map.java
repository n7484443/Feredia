package map;

import image.loader.MapImageLoader;

import java.io.IOException;
import java.util.Iterator;

import npc.NpcBase;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import core.MainRoop;
import portal.Portal;
import render.RenderMain;
import render.RenderUnit;
import collision.CollisionBox;

public class Map {
	public final int width = 1280;
	public final int height = 960;

	public Tile[][] mapblock;
	
	public String name;
	
	public CollisionBox[] Collision;
	
	public NpcBase[] npc;
	
	public Portal[] portal;
	
	public Texture BackGround;
	
	public Color getColorMiniMap(int i){
		return MapImageLoader.blockColor[i];
	}
	
	public Texture getBackGround(){
		return BackGround;
	}
	
	public void Init() throws IOException{
		
	}
	
	public int getMapTextureXSize(int i, int j){
		if(mapblock[i][j].getnum() != -1){
			return MapImageLoader.blockTexturexSize[mapblock[i][j].getTexture()];
		}else{
			return 0;
		}
	}
	
	public Texture getMapTexture(int i, int j){
		if(mapblock[i][j].getnum() != -1){
			return MapImageLoader.blockTexture[mapblock[i][j].getTexture()];
		}else{
			return null;
		}
		
	}
	
	public void Inter(){
		for(int i = 0; i < MainRoop.p.skill.length; i++){
			if(MainRoop.p.skill[i] != null)
			MainRoop.p.skill[i].destroy();
		}
	}
	public void Outer(){
		Iterator<RenderUnit> i = RenderMain.list.iterator();
		while(i.hasNext()){
			i.next();
			i.remove();
		}
	}
	
	
}
