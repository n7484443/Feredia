package map;

import java.io.IOException;

import map.tutorial.Map_tutorial_0;
import map.tutorial.Map_tutorial_1;

public class Maps {
	public static Map_tutorial_0 tutorial_0;
	public static Map_tutorial_1 tutorial_1;
	
	public static void Init() throws IOException{
		tutorial_0 = new Map_tutorial_0();
		tutorial_1 = new Map_tutorial_1();
		
		tutorial_0.Init();
		tutorial_1.Init();
	}
}
