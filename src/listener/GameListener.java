package listener;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import render.RenderDataBase;
import render.RenderSkill;
import skill.mage.MagicMissile;
import core.MainRoop;

public class GameListener {
	public static int beforexmouse;
	public static int beforeymouse;
	public static final int DoubleWidth = 20;
	public static boolean drag;
	public static Key Key_A;
	public static Key Key_D;
	public static Key Key_W;
	public static Key Key_Q;
	public static Key Key_I;
	public static Key Key_K;
	public static Key Key_Space;
	public static Key Key_Z;
	public static Key Key_X;
	public static Key Key_C;
	public static Key Key_V;
	public static Key Key_B;
	public static boolean SkillSlotClicked;
	public static void Init() throws LWJGLException {
		drag = false;
		beforexmouse = -1;
		beforeymouse = -1;
		time = 0;
		SkillSlotClicked = false;
		Mouse.create();
		Keyboard.create();
		Key_A = new Key(Keyboard.KEY_A);
		Key_D = new Key(Keyboard.KEY_D);
		Key_W = new Key(Keyboard.KEY_W);
		Key_Q = new Key(Keyboard.KEY_Q);
		Key_I = new Key(Keyboard.KEY_I);
		Key_K = new Key(Keyboard.KEY_K);
		Key_Space = new Key(Keyboard.KEY_SPACE);
		Key_Z = new Key(Keyboard.KEY_Z);
		Key_X = new Key(Keyboard.KEY_X);
		Key_C = new Key(Keyboard.KEY_C);
		Key_V = new Key(Keyboard.KEY_V);
		Key_B = new Key(Keyboard.KEY_B);
	}

	public static long time;

	public static void Check(){
		CheckDoubleClick();
		CheckKeyboard();
	}
	
	public static void CheckDoubleClick() {
		while (Mouse.next()) {
			if (Mouse.isInsideWindow()) {
					if (Mouse.getEventButton() == 0
							&& !Mouse.getEventButtonState()) {
						drag = false;
						SkillSlotClicked = false;
						if (time == 0) {
							time = System.currentTimeMillis();
							beforexmouse = Mouse.getX();
							beforeymouse = Mouse.getX();
						} else {
							if (Mouse.getEventButton() == 0 && !Mouse.getEventButtonState()) {
								if (System.currentTimeMillis() - time < 200 && beforexmouse - DoubleWidth < Mouse.getX() && beforexmouse + DoubleWidth > Mouse.getX()
										&& beforeymouse - DoubleWidth < Mouse.getY() && beforeymouse + DoubleWidth > Mouse.getY()) {
									DoubleClickEvent(Mouse.getX(), Mouse.getY());
								} else {
									ClickEvent(Mouse.getX(), Mouse.getY());
								}
								beforexmouse = Mouse.getX();
								beforeymouse = Mouse.getY();
								time = 0;
							}
						}
					}else if(Mouse.getEventButton() == 0 && Mouse.getEventButtonState()){
						beforexmouse = Mouse.getX();
						beforeymouse = Mouse.getY();
						drag = true;
						RenderSkill.MoveSkillBefore();
					}
			}
		}
		if(drag){
			DragEvent(Mouse.getX() - beforexmouse, beforeymouse - Mouse.getY());
		}
		if(System.currentTimeMillis() - time >= 200 && time != 0){
			ClickEvent(beforeymouse, beforeymouse);
			beforexmouse = Mouse.getX();
			beforeymouse = Mouse.getY();
			time = 0;
		}
	}
	
	public static void DragEvent(int x, int y){
		if(RenderSkill.CheckDragCollisionBox(Mouse.getX(), Display.getHeight() - Mouse.getY()) || SkillSlotClicked){
			RenderSkill.MoveSkill(x, y);
			SkillSlotClicked = true;
		}
	}
	
	public static void ClickEvent(int x, int y) {
		if(RenderSkill.CheckDeleteCollisionBox(Mouse.getX(), Display.getHeight() - Mouse.getY())){
			RenderDataBase.IsSkillSlotOpened = false;
		}
	}

	public static void DoubleClickEvent(int x, int y) {
		
	}

	public static void PressedEvent(int Key) {
		if (Key == Keyboard.KEY_W) {
			for (int i = 0; i < MainRoop.p.getMap().portal.length; i++) {
				if ((MainRoop.p.getX() + 16 > MainRoop.p.getMap().width ? MainRoop.p
						.getMap().portal[i].CheckCollision(
						MainRoop.p.getMap().width, MainRoop.p.getY())
						: MainRoop.p.getMap().portal[i].CheckCollision(
								MainRoop.p.getX() + 16, MainRoop.p.getY()))
						|| (MainRoop.p.getX() - 16 < 0 ? MainRoop.p.getMap().portal[i]
								.CheckCollision(0, MainRoop.p.getY())
								: MainRoop.p.getMap().portal[i].CheckCollision(
										MainRoop.p.getX() - 16,
										MainRoop.p.getY()))
						|| MainRoop.p.getMap().portal[i].CheckCollision(
								MainRoop.p.getX(), MainRoop.p.getY())) {
					MainRoop.p.getMap().portal[i].PlayerMoveMap();
					break;
				}
			}
		}
	}

	public static void RealeasedEvent(int Key) {
		if (Key == Keyboard.KEY_Q && MainRoop.p.getMap().npc != null) {
			if (MainRoop.p.moveable) {
				for (int i = 0; i < MainRoop.p.getMap().npc.length; i++) {
					if ((MainRoop.p.getX() + 16 > MainRoop.p.getMap().width ? MainRoop.p
							.getMap().npc[i].CheckCollision(
							MainRoop.p.getMap().width, MainRoop.p.getY())
							: MainRoop.p.getMap().npc[i].CheckCollision(
									MainRoop.p.getX() + 16, MainRoop.p.getY()))
							|| (MainRoop.p.getX() - 16 < 0 ? MainRoop.p
									.getMap().npc[i].CheckCollision(0,
									MainRoop.p.getY())
									: MainRoop.p.getMap().npc[i]
											.CheckCollision(
													MainRoop.p.getX() - 16,
													MainRoop.p.getY()))
							|| MainRoop.p.getMap().npc[i].CheckCollision(
									MainRoop.p.getX(), MainRoop.p.getY())) {
						MainRoop.p.moveable = false;
						MainRoop.p.npc = MainRoop.p.getMap().npc[i];
						break;
					}
				}
			} else {
				MainRoop.p.npc.showedNpcTalk++;
				if (MainRoop.p.npc.showedNpcTalk >= MainRoop.p.npc.showedNpcTalkMax) {
					MainRoop.p.npc.showedNpcTalk = 0;
					MainRoop.p.npc = null;
					MainRoop.p.moveable = true;
				}
			}
		}
		if(Key == Keyboard.KEY_I){
			if(RenderDataBase.IsItemSlotOpened){
				RenderDataBase.IsItemSlotOpened = false;
			} else {
				RenderDataBase.IsItemSlotOpened = true;
			}
		}
		if(Key == Keyboard.KEY_K){
			if(RenderDataBase.IsSkillSlotOpened){
				RenderDataBase.IsSkillSlotOpened = false;
			} else {
				RenderDataBase.IsSkillSlotOpened = true;
			}
		}
	}

	public static void HoldingEvent() {
		if (MainRoop.p.getMap() != null && MainRoop.p.moveable) {
			if (Key_D.state) {
				MainRoop.p.moveX(4);
			}
			if (Key_A.state) {
				MainRoop.p.moveX(-4);
			}
			if (Key_Space.state) {
				MainRoop.p.jump();
			}
		}
	}

	public static void CheckKeyboard() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_A) {
				if (Keyboard.getEventKeyState()) {
					Key_A.setPressed();
				} else {
					Key_A.setReleased();
				}
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_D) {
				if (Keyboard.getEventKeyState()) {
					Key_D.setPressed();
				} else {
					Key_D.setReleased();
				}
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_W) {
				if (Keyboard.getEventKeyState()) {
					Key_W.setPressed();
				} else {
					Key_W.setReleased();
				}
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_Q) {
				if (Keyboard.getEventKeyState()) {
					Key_Q.setPressed();
				} else {
					Key_Q.setReleased();
				}
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
				if (Keyboard.getEventKeyState()) {
					Key_Space.setPressed();
				} else {
					Key_Space.setReleased();
				}
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_I) {
				if (Keyboard.getEventKeyState()) {
					Key_I.setPressed();
				} else {
					Key_I.setReleased();
				}
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_K) {
				if (Keyboard.getEventKeyState()) {
					Key_K.setPressed();
				} else {
					Key_K.setReleased();
				}
			}
		}
		HoldingEvent();
	}

	/*
	 * PressingEvnet(); if (pressedtimekeyboard > 0) { pressedtimekeyboard++; if
	 * (pressedtimekeyboard == pressedtimekeyboardMax) { pressedtimekeyboard =
	 * 0; pressedtimekeyboardMax = 10; } } if (MainRoop.p.getMap() != null &&
	 * MainRoop.p.moveable) { if (Keyboard.isKeyDown(Keyboard.KEY_0) &&
	 * pressedtimekeyboard == 0 && MainRoop.p.mp >= 10) { try {
	 * MainRoop.p.skill[0] = new MagicMissile( (int) MainRoop.p.getX() + 16 + 1,
	 * (int) MainRoop.p.getY() - 62); MainRoop.p.setmp(MainRoop.p.mp -= 10); }
	 * catch (IOException e) { e.printStackTrace(); } pressedtimekeyboard = 1; }
	 * 
	 * 
	 * 
	 *  if
	 * (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && MainRoop.p.npc != null &&
	 * !MainRoop.p.moveable && pressedtimekeyboard == 0) {
	 * MainRoop.p.npc.showedNpcTalk = 0; MainRoop.p.npc = null;
	 * MainRoop.p.moveable = true; pressedtimekeyboard = 1; }
	 */
}
