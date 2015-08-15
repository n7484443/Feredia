package player;

import item.ItemStack;


public class Slot {
	public ItemStack itemStack;
	
	public Slot(ItemStack i){
		this.itemStack = i;
	}
	
	public void useItem(){
		itemStack.useItem();
	}
	
	public static void Init(){
	}
}
