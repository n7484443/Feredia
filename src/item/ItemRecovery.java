package item;

import core.MainRoop;

public class ItemRecovery extends Item{
	public int type;
	public int RecoveryValue;
	//type : 0 = hp 1 = mp
	
	public ItemRecovery(int i, int UsingItem, int RecoveryValue, int type) {
		super(i, UsingItem);
		this.type = type;
		this.RecoveryValue = RecoveryValue;
	}
	
	public void UseItemEvent(){
		switch(type){
		case 0:
			MainRoop.p.hp += RecoveryValue;
			break;
		case 1:
			MainRoop.p.mp += RecoveryValue;
			break;
		}
	}
	
	public boolean UseAble(){
		if((type == 0 ? MainRoop.p.hp : MainRoop.p.mp) < (type == 0 ? MainRoop.p.maxhp : MainRoop.p.maxmp)){
			return true;
		}return false;
	}

}
