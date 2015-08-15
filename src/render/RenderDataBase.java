package render;

import java.util.HashSet;
import java.util.Set;

import core.MainRoop.Gui;

public class RenderDataBase {
	public static Set<Gui> OpenGui;
	public static void Init(){
		OpenGui = new HashSet<Gui>();
	}
	
	public static void reverse(Gui gui){
		if(OpenGui.contains(gui)){
			OpenGui.remove(gui);
		}else{
			OpenGui.add(gui);
		}
	}
}
