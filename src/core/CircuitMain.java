package core;

import collision.CollisionBox;
import render.RenderDataBase;

public class CircuitMain extends Thread {
	@Override
	public synchronized void start() {
		init();
	}

	private synchronized void init() {
		RenderDataBase.Init();
	}

	public synchronized void update() {
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
			System.out.println(MainRoop.p.checkCollisionUnderBlock());
			if (!MainRoop.p.checkCollisionUnderBlock()) {
				MainRoop.p.Vy += 2;
				if (MainRoop.p.Vy > 15)
					MainRoop.p.Vy = 15;
				if (MainRoop.p.checkCollisionBlock(MainRoop.p.getX(),
						MainRoop.p.getY() + MainRoop.p.h + MainRoop.p.Vy + 1)
						|| MainRoop.p.checkCollisionBlock(
								MainRoop.p.getX() + 32, MainRoop.p.getY()
										+ MainRoop.p.h + MainRoop.p.Vy + 1)) {
					CollisionBox a1 = MainRoop.p.getCollisionBlock(
							MainRoop.p.getX(), MainRoop.p.getY() + MainRoop.p.h
									+ MainRoop.p.Vy + 1);
					CollisionBox a2 = MainRoop.p.getCollisionBlock(
							MainRoop.p.getX() + 32, MainRoop.p.getY()
									+ MainRoop.p.h + +MainRoop.p.Vy + 1);

					double b = (a1 == null ? (a2 == null ? 15 : a2.y)
							: (a2 == null ? a1.y : Math.min(a1.y, a2.y)))
							- MainRoop.p.getY() - MainRoop.p.h;
					if (b <= MainRoop.p.Vy) {
						MainRoop.p.addY(b);
						MainRoop.p.Vy = 0;
						MainRoop.p.isAir = false;
					} else {
						MainRoop.p.addY(MainRoop.p.Vy);
					}
				} else {
					MainRoop.p.addY(MainRoop.p.Vy);
				}
			} else {
				if (MainRoop.p.isAir) {
					MainRoop.p.addY(MainRoop.p.Vy);
					MainRoop.p.Vy -= 2;
				}
			}
		}
	}

	public void movePlayer(double x) {
		if (x < 0) {
			MainRoop.p.Dir = true;
		} else {
			MainRoop.p.Dir = false;
		}
		if (MainRoop.p.checkCollisionBlock(MainRoop.p.getX() + x
				+ (x < 0 ? -1 : MainRoop.p.w + 1), MainRoop.p.getY())) {
			CollisionBox c = MainRoop.p.getCollisionBlock(MainRoop.p.getX() + x
					+ (x < 0 ? -1 : MainRoop.p.w + 1), MainRoop.p.getY());
			if (c != null) {
				double a = (x < 0 ? MainRoop.p.getX() - c.x : c.x
						- MainRoop.p.getX() - MainRoop.p.w);
				if (a <= 3) {
					MainRoop.p.addX(a);
				} else {
					MainRoop.p.addX(x);
				}
			}
		} else {
			MainRoop.p.addX(x);
		}
	}
}
