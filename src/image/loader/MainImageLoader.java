package image.loader;

import java.io.IOException;

public class MainImageLoader {
	public static void Init() throws IOException{
		MapImageLoader.Init();
		SkillImageLoader.Init();
		ItemImageLoader.Init();
		NpcImageLoader.Init();
	}
}
