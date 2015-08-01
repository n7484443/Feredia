package map;

import java.io.IOException;
import java.util.Iterator;

import npc.NpcBase;

import org.newdawn.slick.opengl.Texture;

import core.MainRoop;
import portal.Portal;
import render.RenderMain;
import render.RenderUnit;
import collision.CollisionBox;

public class Map {
	public final int width = 1280;
	public final int height = 960;

	public int[][] mapblock;
	
	public String name;
	
	public CollisionBox[] Collision;
	
	public NpcBase[] npc;
	
	public Portal[] portal;
	
	public Texture BackGround;
	
	public Texture getBackGround(){
		return BackGround;
	}
	
	public void Init() throws IOException{
		
	}
	
	public int getMapTextureXSize(int i, int j){
		return MapImageLoader.blockTexturexSize[mapblock[i][j]-1];
	}
	
	public Texture getMapTexture(int i, int j){
		return MapImageLoader.blockTexture[mapblock[i][j]-1];
	}
	
	public void Inter(){
		for(int i = 0; i < MainRoop.p.skill.length; i++){
			MainRoop.p.skill[i] = null;
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
