package player;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Slot {
	public ItemStack itemStack;
	
	public Slot(ItemStack i){
		this.itemStack = i;
	}
	
	public static Texture texture;
	
	public static void Init() throws IOException{
		texture = TextureLoader.getTexture(".png", ResourceLoader.getResourceAsStream("image/gui/item_slot.png"));
	}
	
	public static Texture getTexture(){
		return texture;
	}
}
