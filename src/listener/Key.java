package listener;

import java.io.IOException;

public class Key {
	public int key;
	public boolean state;
	// 0 = none 1 = held;
	public Key(int key){
		this.key = key;
		this.state = false;
	}
	
	public void setEvent(boolean b){
		if(b)this.setPressed();
		else this.setReleased();
	}
	
	public void setPressed(){
		pressedEvent();
	}
	
	public void setReleased(){
		try {
			RealeasedEvent();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean getState(){
		return state;
	}
	
	public void pressedEvent(){
		state = true;
		GameListener.PressedEvent(key);
	}
	
	public void RealeasedEvent() throws IOException{
		state = false;
		GameListener.RealeasedEvent(key);
	}
	
}
