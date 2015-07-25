package render;

public class ToRender {
	public static Renderer[] needtoRender;
	
	public static Renderer[] needRender() {
		return needtoRender;
	}

	public static void addneedtoRender(Renderer c) {
		if(needtoRender == null)needtoRender = new Renderer[1];
		Renderer[] sam = needtoRender.clone();
		needtoRender = new Renderer[needtoRender.length+1];
		for(int i = 0; i < needtoRender.length - 1; i++){
			needtoRender[i] = sam[i];
		}
		needtoRender[needtoRender.length - 2] = c;
	}
	
	public static void delRenderer(Renderer R) {
		if(needtoRender == null)return ;
		for(int i = 0; i < needtoRender.length; i++){
			if(needtoRender[i].getID() == R.getID()){
				needtoRender[i] = null;
			}
		}
	}
}
