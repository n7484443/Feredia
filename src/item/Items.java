package item;


public class Items {
	public static ItemRecovery hp_potion_big;
	public static ItemRecovery mp_potion_big;
	
	public static void Init(){
		hp_potion_big = new ItemRecovery(0, 1, 10, 0);
		mp_potion_big = new ItemRecovery(1, 1, 10, 1);
	}
}
