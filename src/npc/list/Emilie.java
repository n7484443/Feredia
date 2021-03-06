package npc.list;

import core.MainRoop;
import npc.DialogueNpc;
import npc.NpcBase;

public class Emilie extends NpcBase{

	public Emilie(int width, int height) {
		super(0, height - 32 * 7 - 20, 29, 53, 0, new DialogueNpc("안녕하세요.", "제가 지금부터 조작 방법을 알려드릴게요."), new DialogueNpc(
				"점프는 '스페이스 바'로 하고요,", "이동은 '왼쪽 화살표', '오른쪽 화살표'로 합니다."),
		new DialogueNpc("그리고 포탈을 들어가는 키는 '위쪽 화살표'입니다."));
	}
	
	public void End(){
		MainRoop.p.quest.setOnFlag(0, 0);
	}
	
	public boolean Check(){
		return !MainRoop.p.quest.getFlag(0, 0);
	}
	
	public void CheckFirst(){
		if(!Check()){
			showedNpcTalk = 0;
			MainRoop.p.npc = null;
			MainRoop.p.moveable = true;
			End();
		}
	}
}
