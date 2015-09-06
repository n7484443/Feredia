package player;

import item.ItemStack;
import item.Items;

public class PlayerItemSlot {
	public Slot[] s;
	public Slot[] s_equid;
	public Slot[] s_use;
	public Slot[] s_material;
	public Slot[] s_weapon;
	public Slot[] s_others;
	
	
	public void Init(){
		s_equid = new Slot[5*4];
		s_use = new Slot[5*4];
		s_material = new Slot[5*4];
		s_weapon = new Slot[5*4];
		s_others = new Slot[5*4];

		s_use[0] = new Slot(new ItemStack(2, Items.hp_potion_big));
		s_use[1] = new Slot(new ItemStack(5, Items.mp_potion_big));
	}
	
	public Slot[] getSlot(int i){
		switch(i){
		case 0:
			return s_equid;
		case 1:
			return s_use;
		case 2:
			return s_material;
		case 3:
			return s_weapon;
		case 4:
			return s_others;
		default:
			return null;
		}
	}
	
	public String getSlotName(int i){
		switch(i){
		case 0:
			return "방어구";
		case 1:
			return "소비";
		case 2:
			return "재료";
		case 3:
			return "무기";
		case 4:
			return "기타";
		default:
			return null;
		}
	}
}
