package quest;

public class QuestBase {
	public QuestTitle Title;
	public QuestMain Main;
	public QuestRequirment Requirment;
	
	public int state;// 0 = none, 1 = questing 2 = end
	
	public QuestBase(QuestTitle Title, QuestMain Main, QuestRequirment Requirment){
		this.Title = Title;
		this.Main = Main;
		this.Requirment = Requirment;
		state = 0;
	}
	
	public void updateState(){
		state++;
	}
	
	public void setState(int i){
		state = i;
	}
	
	public String[] getTitle(){
		return Title.Dialogue;
	}
}
