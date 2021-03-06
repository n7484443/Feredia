package core;

import collision.CollisionBox;
import render.RenderDataBase;

public class CircuitMain extends Thread {
	@Override
	public void start() {
		init();
	}

	private synchronized void init() {
		setName("RenderFeredia");
		RenderDataBase.Init();
		MainRoop.p.quest.Init();
		MainRoop.p.itemSlot.Init();
	}

	public void Update() {
		jumpPlayer();
		SkillUpdate();

	}

	public void SkillUpdate() {
		for (int i = 0; i < MainRoop.p.skill.length; i++) {
			if (MainRoop.p.skill[i] != null) {
				if (MainRoop.p.skill[i].destroyon) {
					MainRoop.p.skill[i] = null;
					continue;
				} else {
					MainRoop.p.skill[i].update();
				}
			}

		}
	}

	public void jumpPlayer() {
		if (MainRoop.p.getMap() != null) {
			if (!MainRoop.p.checkCollisionUnderBlock()) {
				MainRoop.p.Vy += 1.5;
				if (MainRoop.p.Vy > 15)
					MainRoop.p.Vy = 15;
				if (MainRoop.p.checkCollisionBlock(0, MainRoop.p.Vy)) {
					CollisionBox a1 = MainRoop.p.getCollisionBlock(0,MainRoop.p.Vy);
					double b = a1.y - MainRoop.p.getY() - MainRoop.p.h;
					if (b <= MainRoop.p.Vy) {
						MainRoop.p.addY(b);
						MainRoop.p.Vy = 0;
						MainRoop.p.isAir = false;
					} else {
						MainRoop.p.addY(MainRoop.p.Vy);
						MainRoop.p.isAir = false;
					}
				} else {
					MainRoop.p.addY(MainRoop.p.Vy);
				}
			} else {
				if (MainRoop.p.isAir) {
					MainRoop.p.addY(MainRoop.p.Vy);
				}
			}
		}
	}

	public void movePlayer(int x) {
		if (x < 0) {
			MainRoop.p.Dir = true;
		} else {
			MainRoop.p.Dir = false;
		}
		if (MainRoop.p.checkCollisionBlock(x + (x < 0 ? 1 : -1), -1)) {
			CollisionBox a1 = MainRoop.p.getCollisionBlock(x + (x < 0 ? 1 : -1),-1);
			MainRoop.p.setX(a1.x + (x < 0 ? a1.width + 1 : -MainRoop.p.w-1));
		}else{
			MainRoop.p.addX(x);
		}
	}
}
