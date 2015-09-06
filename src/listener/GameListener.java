package listener;

import java.io.IOException;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import render.RenderDataBase;
import render.RenderItemSlot;
import render.RenderMain;
import skill.mage.MagicMissile;
import core.MainRoop;
import core.MainRoop.Gui;

public class GameListener {
	private static Gui ClickedSet;

	public static int beforexmouse;
	public static int beforeymouse;
	public static final int DoubleWidth = 20;
	public static boolean drag;
	public static Key Key_A;
	public static Key Key_D;
	public static Key Key_W;
	public static Key Key_Right;
	public static Key Key_Left;
	public static Key Key_Up;
	public static Key Key_Q;
	public static Key Key_I;
	public static Key Key_K;
	public static Key Key_J;
	public static Key Key_M;
	public static Key Key_Space;
	public static Key Key_Z;
	public static Key Key_X;
	public static Key Key_C;
	public static Key Key_V;
	public static Key Key_B;

	public static void Init() throws LWJGLException {
		drag = false;
		beforexmouse = -1;
		beforeymouse = -1;
		time = 0;
		Mouse.create();
		Keyboard.create();
		Key_A = new Key(Keyboard.KEY_A);
		Key_D = new Key(Keyboard.KEY_D);
		Key_W = new Key(Keyboard.KEY_W);
		Key_Left = new Key(Keyboard.KEY_LEFT);
		Key_Right = new Key(Keyboard.KEY_RIGHT);
		Key_Up = new Key(Keyboard.KEY_UP);
		Key_Q = new Key(Keyboard.KEY_Q);
		Key_I = new Key(Keyboard.KEY_I);
		Key_K = new Key(Keyboard.KEY_K);
		Key_J = new Key(Keyboard.KEY_J);
		Key_M = new Key(Keyboard.KEY_M);
		Key_Space = new Key(Keyboard.KEY_SPACE);
		Key_Z = new Key(Keyboard.KEY_Z);
		Key_X = new Key(Keyboard.KEY_X);
		Key_C = new Key(Keyboard.KEY_C);
		Key_V = new Key(Keyboard.KEY_V);
		Key_B = new Key(Keyboard.KEY_B);
		ClickedSet = Gui.none;
	}

	public static long time;

	public static void Check() {
		CheckDoubleClick();
		CheckKeyboard();
	}

	public static void CheckDoubleClick() {
		while (Mouse.next()) {
			if (Mouse.isInsideWindow()) {
				if (Mouse.getEventButton() == 0 && !Mouse.getEventButtonState()) {
					drag = false;
					ClickedSet = Gui.none;
					if (time == 0) {
						time = System.currentTimeMillis();
						beforexmouse = Mouse.getX();
						beforeymouse = Mouse.getX();
					} else {
						if (Mouse.getEventButton() == 0
								&& !Mouse.getEventButtonState()) {
							if (System.currentTimeMillis() - time < 250
									&& beforexmouse - DoubleWidth < Mouse
											.getX()
									&& beforexmouse + DoubleWidth > Mouse
											.getX()
									&& beforeymouse - DoubleWidth < Mouse
											.getY()
									&& beforeymouse + DoubleWidth > Mouse
											.getY()) {
								DoubleClickEvent(Mouse.getX(), Mouse.getY());
							} else {
								ClickEvent(Mouse.getX(), Mouse.getY());
							}
							beforexmouse = Mouse.getX();
							beforeymouse = Mouse.getY();
							time = 0;
						}
					}
				} else if (Mouse.getEventButton() == 0
						&& Mouse.getEventButtonState()) {
					beforexmouse = Mouse.getX();
					beforeymouse = Mouse.getY();
					drag = true;
					RenderMain.mage_Making.MoveBefore();
					RenderMain.skillslot.MoveBefore();
					RenderMain.itemslot.MoveBefore();
					RenderMain.minimap.MoveBefore();
					RenderMain.questslot.MoveBefore();
				} else if (Mouse.getEventButton() == 1){
				}
			}
		}
		if (drag) {
			DragEvent(Mouse.getX() - beforexmouse, beforeymouse - Mouse.getY());
		}
		if (System.currentTimeMillis() - time >= 250 && time != 0) {
			ClickEvent(beforeymouse, beforeymouse);
			beforexmouse = Mouse.getX();
			beforeymouse = Mouse.getY();
			time = 0;
		}
	}

	public static void DragEvent(int x, int y) {
		if (RenderDataBase.OpenGui.contains(Gui.SkillSlot)
				&& ((RenderMain.skillslot.CheckDragCollisionBox(Mouse.getX(),
						Display.getHeight() - Mouse.getY()) && ClickedSet == Gui.none && Mouse.getEventButtonState()) || ClickedSet == Gui.SkillSlot)) {
			RenderMain.skillslot.Move(x, y);
			ClickedSet = Gui.SkillSlot;
		} else if (RenderDataBase.OpenGui.contains(Gui.ItemSlot)
				&& ((RenderMain.itemslot.CheckDragCollisionBox(Mouse.getX(),
						Display.getHeight() - Mouse.getY()) && ClickedSet == Gui.none && Mouse.getEventButtonState()) || ClickedSet == Gui.ItemSlot)) {
			RenderMain.itemslot.Move(x, y);
			ClickedSet = Gui.ItemSlot;
		} else if (RenderDataBase.OpenGui.contains(Gui.QuestSlot)
				&& ((RenderMain.questslot.CheckDragCollisionBox(Mouse.getX(),
						Display.getHeight() - Mouse.getY()) && ClickedSet == Gui.none && Mouse.getEventButtonState()) || ClickedSet == Gui.QuestSlot)) {
			RenderMain.questslot.Move(x, y);
			ClickedSet = Gui.QuestSlot;
		} else if (RenderDataBase.OpenGui.contains(Gui.MiniMap)
				&& ((RenderMain.minimap.CheckDragCollisionBox(Mouse.getX(),
						Display.getHeight() - Mouse.getY()) && ClickedSet == Gui.none && Mouse.getEventButtonState()) || ClickedSet == Gui.MiniMap)) {
			RenderMain.minimap.Move(x, y);
			ClickedSet = Gui.MiniMap;
		} else if (RenderDataBase.OpenGui
				.contains(Gui.SkillMageMakingMagicSlot)
				&& ((RenderMain.mage_Making.CheckDragCollisionBox(
						Mouse.getX(), Display.getHeight() - Mouse.getY()) && ClickedSet == Gui.none && Mouse.getEventButtonState()) || ClickedSet == Gui.SkillMageMakingMagicSlot)) {
			RenderMain.mage_Making.Move(x, y);
			ClickedSet = Gui.SkillMageMakingMagicSlot;
		}
	}

	public static void ClickEvent(int x, int y) {
		if (RenderMain.skillslot.CheckDeleteCollisionBox(Mouse.getX(),
				Display.getHeight() - Mouse.getY())
				&& RenderDataBase.OpenGui.contains(Gui.SkillSlot)) {
			RenderDataBase.reverse(Gui.SkillSlot);
		} else if (RenderMain.itemslot.CheckDeleteCollisionBox(Mouse.getX(),
				Display.getHeight() - Mouse.getY())
				&& RenderDataBase.OpenGui.contains(Gui.ItemSlot)) {
			RenderDataBase.reverse(Gui.ItemSlot);
		}
	}

	public static void DoubleClickEvent(int x, int y) {
		if (RenderDataBase.OpenGui.contains(Gui.ItemSlot)){
			RenderItemSlot.ItemStackEvent(x, Display.getHeight() - y);
		}
	}

	public static void PressedEvent(int Key) {
		if (Key == Keyboard.KEY_UP) {
			boolean b = false;
			if(MainRoop.p.getMap().portal != null){
				for (int i = 0; i < MainRoop.p.getMap().portal.length; i++) {
					if (MainRoop.p.getMap().portal[i].CheckCollision(MainRoop.p.collisionBox)) {
						MainRoop.p.getMap().portal[i].PlayerMoveMap();
						b = true;
						break;
					}
				}
			}
			if (MainRoop.p.moveable && MainRoop.p.getMap().npc != null && !b) {
				for (int i = 0; i < MainRoop.p.getMap().npc.length; i++) {
					if (MainRoop.p.getMap().npc[i].CheckCollision(MainRoop.p.collisionBox)){
						MainRoop.p.moveable = false;
						MainRoop.p.npc = MainRoop.p.getMap().npc[i];
						MainRoop.p.npc.CheckFirst();
						break;
					}
				}
			} else if(!b){
				MainRoop.p.npc.Talk();
			}
		}
	}

	public static void RealeasedEvent(int Key) throws IOException {
		if (Key == Keyboard.KEY_I) {
			RenderDataBase.reverse(Gui.ItemSlot);
		} else if (Key == Keyboard.KEY_K) {
			RenderDataBase.reverse(Gui.SkillSlot);
		} else if (Key == Keyboard.KEY_M) {
			RenderDataBase.reverse(Gui.MiniMap);
		} else if (Key == Keyboard.KEY_Q) {
			RenderDataBase.reverse(Gui.QuestSlot);
		} else if (Key == Keyboard.KEY_J) {
			RenderDataBase.reverse(Gui.SkillMageMakingMagicSlot);
		} else if (Key == Keyboard.KEY_Z) {
			if (MainRoop.p.mp >= 10) {
				for (int i = 0; i < MainRoop.p.skill.length; i++) {
					if (MainRoop.p.skill[i] == null) {
						if (MainRoop.p.Dir)
							MainRoop.p.skill[i] = new MagicMissile(
									(int) MainRoop.p.getX() - 1 - 37,
									(int) MainRoop.p.getY() - 124,
									MainRoop.p.Dir);
						else
							MainRoop.p.skill[i] = new MagicMissile(
									(int) MainRoop.p.getX() + MainRoop.p.w + 1,
									(int) MainRoop.p.getY() - 124,
									MainRoop.p.Dir);
						MainRoop.p.setmp(MainRoop.p.mp -= 10);
						break;
					}
				}
			}
		}
	}

	public static void HoldingEvent() {
		if (MainRoop.p.getMap() != null && MainRoop.p.moveable) {
			if (Key_Right.state) {
				MainRoop.p.moveX(4);
			}
			if (Key_Left.state) {
				MainRoop.p.moveX(-4);
			}
			if (Key_Space.state) {
				MainRoop.p.jump();
			}
		}
	}

	public static void CheckKeyboard() {
		while (Keyboard.next()) {
			switch (Keyboard.getEventKey()) {
			case Keyboard.KEY_A:
				Key_A.setEvent(Keyboard.getEventKeyState());
				break;
			case Keyboard.KEY_D:
				Key_D.setEvent(Keyboard.getEventKeyState());
				break;
			case Keyboard.KEY_W:
				Key_W.setEvent(Keyboard.getEventKeyState());
				break;
			case Keyboard.KEY_RIGHT:
				Key_Right.setEvent(Keyboard.getEventKeyState());
				break;
			case Keyboard.KEY_LEFT:
				Key_Left.setEvent(Keyboard.getEventKeyState());
				break;
			case Keyboard.KEY_UP:
				Key_Up.setEvent(Keyboard.getEventKeyState());
				break;
			case Keyboard.KEY_Q:
				Key_Q.setEvent(Keyboard.getEventKeyState());
				break;
			case Keyboard.KEY_SPACE:
				Key_Space.setEvent(Keyboard.getEventKeyState());
				break;
			case Keyboard.KEY_I:
				Key_I.setEvent(Keyboard.getEventKeyState());
				break;
			case Keyboard.KEY_K:
				Key_K.setEvent(Keyboard.getEventKeyState());
				break;
			case Keyboard.KEY_J:
				Key_J.setEvent(Keyboard.getEventKeyState());
				break;
			case Keyboard.KEY_M:
				Key_M.setEvent(Keyboard.getEventKeyState());
				break;
			case Keyboard.KEY_Z:
				Key_Z.setEvent(Keyboard.getEventKeyState());
				break;
			}
		}
		HoldingEvent();
	}

	/*
	 * PressingEvnet();
	 * 
	 * 
	 * 
	 * if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && MainRoop.p.npc != null &&
	 * !MainRoop.p.moveable && pressedtimekeyboard == 0) {
	 * MainRoop.p.npc.showedNpcTalk = 0; MainRoop.p.npc = null;
	 * MainRoop.p.moveable = true; pressedtimekeyboard = 1; }
	 */
}
