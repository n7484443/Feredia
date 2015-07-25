package player;

public class ItemStack {
	public Item i;
	public int StackSize;
	
	public Item getItem(){
		return i;
	}
	
	public ItemStack(int stackSize, Item i){
		this.StackSize = stackSize;
		this.i = i;
	}
}
