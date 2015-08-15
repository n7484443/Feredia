package item;

import image.loader.ItemImageLoader;

import org.newdawn.slick.opengl.Texture;

public class Item {
	public int image;
	public int UsingItem;
	
	public Item(int i, int UsingItem){
		this.image = i;
		this.UsingItem = UsingItem;
	}	
	
	public Texture getImage(){
		return ItemImageLoader.ItemTexture[image];
	}
	
	public void UseItemEvent(){
	}
	
	public boolean UseAble(){
		return true;
	}
}
