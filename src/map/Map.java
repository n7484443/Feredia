package map;

import java.io.IOException;
import java.util.Iterator;

import npc.NpcBase;

import org.newdawn.slick.opengl.Texture;

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
	
	public Texture getMapTexture(int i, int j){
		return null;
	}
	
	public void Inter(){}
	public void Outer(){
		Iterator<RenderUnit> i = RenderMain.list.iterator();
		while(i.hasNext()){
			i.next();
			i.remove();
		}
	}
	
	
}