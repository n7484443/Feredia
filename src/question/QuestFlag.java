package question;

import java.util.ArrayList;

public class QuestFlag {
	ArrayList<Integer> al = new ArrayList<Integer>();
	public static int QuestionSet0 = 0;
	
	public void Init(){
		al.add(QuestionSet0);
	}
	
	public boolean getFlag(int QuestionSet, int num){
		int i = 1 << num;
		return (((al.get(QuestionSet)&i)>>num)<<num) != 0;
	}
	
	public void setOnFlag(int QuestionSet, int num){
		al.set(QuestionSet, (al.get(QuestionSet) | (1 << num)));
	}
	
	public void setOffFlag(int QuestionSet, int num){
		al.set(QuestionSet, (al.get(QuestionSet) & ~(1 << num)));
	}
}
