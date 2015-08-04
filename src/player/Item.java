package player;

import image.loader.ItemImageLoader;
import org.newdawn.slick.opengl.Texture;

public class Item {
	public int image;
	
	public Item(int i){
		image = i;
	}	
	
	public Texture getImage(){
		return ItemImageLoader.ItemTexture[image];
	}
}
