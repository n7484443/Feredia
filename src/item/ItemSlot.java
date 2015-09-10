package item;



public class ItemSlot {
	public ItemStack itemStack;
	
	public ItemSlot(ItemStack i){
		this.itemStack = i;
	}
	
	public void useItem(){
		itemStack.useItem();
	}
	
	public static void Init(){
	}
}
