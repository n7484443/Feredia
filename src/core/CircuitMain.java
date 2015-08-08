package core;

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

	public void SkillUpdate(){
		for(int i = 0; i < MainRoop.p.skill.length; i++){
			if(MainRoop.p.skill[i] != null){
				if(MainRoop.p.skill[i].destroyon){
					MainRoop.p.skill[i] = null;
					continue;
				}
				MainRoop.p.skill[i].update();
			}
			
				
		}
	}
	
	public void jumpPlayer(){
		if (MainRoop.p.getMap() != null) {
			MainRoop.p.checkCollisionBlock(MainRoop.p.getX(), MainRoop.p.getY()
					+ 16 + MainRoop.p.Vy);
			if (!MainRoop.p.checkCollisionUnderBlock()) {
				MainRoop.p.Vy += 2;
				if (MainRoop.p.Vy > 15)
					MainRoop.p.Vy = 15;
				if (MainRoop.p.checkCollisionBlock(MainRoop.p.getX(),
						MainRoop.p.getY() + MainRoop.p.h + MainRoop.p.Vy + 1)) {
					double a = MainRoop.p.getCollisionBlock(MainRoop.p.getX(),
							MainRoop.p.getY() + MainRoop.p.h + MainRoop.p.Vy
									+ 1).y
							- MainRoop.p.getY() - MainRoop.p.h;
					if (a <= MainRoop.p.Vy) {
						MainRoop.p.addY(a);
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
		if (MainRoop.p.checkCollisionBlock(MainRoop.p.getX() + x
				+ (x < 0 ? -MainRoop.p.w - 1 : MainRoop.p.w + 1),
				MainRoop.p.getY())) {
			double a = MainRoop.p.getCollisionBlock(MainRoop.p.getX() + x
					+ (x < 0 ? -MainRoop.p.w - 1 : MainRoop.p.w + 1),
					MainRoop.p.getY()).x
					- MainRoop.p.getX();
			if (a <= 3) {
				MainRoop.p.addX(a);
			} else {
				MainRoop.p.addX(x);
			}
		} else {
			MainRoop.p.addX(x);
		}
		// GL11.glViewport((int)(MainRoop.p.getX() - MainRoop.m.width*3/5), 0,
		// MainRoop.m.width*6/5, MainRoop.m.height*6/5);
	}
}
