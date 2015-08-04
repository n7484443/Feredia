package listener;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import skill.mage.MagicMissile;
import core.MainRoop;

public class GameListener {
	public static int clickedtimemouse;
	public static int pressedtimemouse;
	public static int pressedtimekeyboard;
	public static int pressedtimekeyboardMax;
	public static int beforeimouse;
	public static int beforejmouse;
	public static boolean b;

	public static void Init() {
		b = false;
		pressedtimekeyboardMax = 10;
	}

	public static void CheckKeyboardPressed() {
		if (pressedtimekeyboard > 0) {
			pressedtimekeyboard++;
			if (pressedtimekeyboard == pressedtimekeyboardMax) {
				pressedtimekeyboard = 0;
				pressedtimekeyboardMax = 10;
			}
		}
		if (MainRoop.p.getMap() != null && MainRoop.p.moveable) {
			if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
				MainRoop.p.moveX(4);
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
				MainRoop.p.moveX(-4);
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_0) && pressedtimekeyboard == 0 && MainRoop.p.mp >= 10) {
				try {
					MainRoop.p.skill[0] = new MagicMissile((int)MainRoop.p.getX() + 16 + 1, (int)MainRoop.p.getY() - 62);
					MainRoop.p.setmp(MainRoop.p.mp -= 10);
				} catch (IOException e) {
					e.printStackTrace();
				}
				pressedtimekeyboard = 1;
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				for (int i = 0; i < MainRoop.p.getMap().portal.length; i++) {
					if (pressedtimekeyboard == 0 && (MainRoop.p.getX() + 16 > MainRoop.p.getMap().width ? MainRoop.p.getMap().portal[i].CheckCollision(MainRoop.p.getMap().width, MainRoop.p.getY())
							: MainRoop.p.getMap().portal[i].CheckCollision(
									MainRoop.p.getX() + 16, MainRoop.p.getY()))
							|| (MainRoop.p.getX() - 16 < 0 ? MainRoop.p.getMap().portal[i].CheckCollision(0, MainRoop.p.getY())
							: MainRoop.p.getMap().portal[i].CheckCollision(
									MainRoop.p.getX() - 16, MainRoop.p.getY())) || MainRoop.p.getMap().portal[i].CheckCollision(MainRoop.p.getX(), MainRoop.p.getY())) {
						MainRoop.p.getMap().portal[i].PlayerMoveMap();
						pressedtimekeyboard = 1;
						break;
					}
				}
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_Q) && pressedtimekeyboard == 0){
				if(MainRoop.p.getMap().npc != null)
					for (int i = 0; i < MainRoop.p.getMap().npc.length; i++) {
						if ((MainRoop.p.getX() + 16 > MainRoop.p.getMap().width ? MainRoop.p.getMap().npc[i].CheckCollision(MainRoop.p.getMap().width, MainRoop.p.getY())
								: MainRoop.p.getMap().npc[i].CheckCollision(
										MainRoop.p.getX() + 16, MainRoop.p.getY()))
								|| (MainRoop.p.getX() - 16 < 0 ? MainRoop.p.getMap().npc[i].CheckCollision(0, MainRoop.p.getY())
								: MainRoop.p.getMap().npc[i].CheckCollision(
										MainRoop.p.getX() - 16, MainRoop.p.getY())) || MainRoop.p.getMap().npc[i].CheckCollision(MainRoop.p.getX(), MainRoop.p.getY())) {
							MainRoop.p.moveable = false;
							MainRoop.p.npc = MainRoop.p.getMap().npc[i];
							pressedtimekeyboard = 4;
							break;
						}
					}
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {				
				if (pressedtimekeyboard == 0) {
					MainRoop.p.jump();
				}
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_I) && pressedtimekeyboard == 0) {
				if (b) {
					b = false;
				} else {
					b = true;
				}
				pressedtimekeyboard = 1;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && MainRoop.p.npc != null
				&& !MainRoop.p.moveable && pressedtimekeyboard == 0) {
			MainRoop.p.npc.showedNpcTalk = 0;
			MainRoop.p.npc = null;
			MainRoop.p.moveable = true;
			pressedtimekeyboard = 1;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_Q) && MainRoop.p.npc != null
				&& !MainRoop.p.moveable && pressedtimekeyboard == 0){
			MainRoop.p.npc.showedNpcTalk ++;
			if(MainRoop.p.npc.showedNpcTalk >= MainRoop.p.npc.showedNpcTalkMax){
				MainRoop.p.npc.showedNpcTalk = 0;
				MainRoop.p.npc = null;
				MainRoop.p.moveable = true;
			}
			pressedtimekeyboard = 5;
			pressedtimekeyboardMax = 20;
		}
	}
}
