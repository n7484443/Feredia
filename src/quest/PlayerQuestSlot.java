package quest;

import java.util.HashSet;
import java.util.Set;

public class PlayerQuestSlot {
	public Set<QuestBase> s_solve;
	public Set<QuestBase> s_has;
	public Set<QuestBase> s_canDo;
	
	
	public void Init(){
		s_solve = new HashSet<QuestBase>();
		s_has = new HashSet<QuestBase>();
		s_canDo = new HashSet<QuestBase>();
	}
	
	public Set<QuestBase> getSlot(int i){
		switch(i){
		case 0:
			return s_canDo;
		case 1:
			return s_has;
		case 2:
			return s_solve;
		default:
			return null;
		}
	}
	
	public String getSlotName(int i){
		switch(i){
		case 0:
			return "가능";
		case 1:
			return "보유";
		case 2:
			return "해결";
		default:
			return null;
		}
	}
}
