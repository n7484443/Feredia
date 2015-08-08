package listener;

public class Key {
	public int key;
	public boolean state;
	// 0 = none 1 = held;
	public Key(int key){
		this.key = key;
		this.state = false;
	}
	
	public void setPressed(){
		pressedEvent();
	}
	
	public void setReleased(){
		RealeasedEvent();
	}
	
	public boolean getState(){
		return state;
	}
	
	public void pressedEvent(){
		state = true;
		GameListener.PressedEvent(key);
	}
	
	public void RealeasedEvent(){
		state = false;
		GameListener.RealeasedEvent(key);
	}
	
}
