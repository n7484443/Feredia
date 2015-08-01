package player;

import npc.NpcBase;
import collision.CollisionBox;
import core.MainRoop;
import map.Map;

public class PlayerInfo {
	public int level;
	public int maxhp;
	public int maxmp;
	public int maxexp;
	public int hp;
	public int mp;
	public int exp;

	private double x;
	private double y;

	public double Vy;

	public boolean isAir;

	public final int maxlevel = 100;

	public final int w = 16;
	public final int h = 16;

	private Map m;

	public String name;

	public String job;

	public boolean moveable;

	public NpcBase npc;

	public PlayerInfo(int level, int hp, int mp, int exp, int x, int y,
			String name, String job) {
		this.level = level;
		this.maxhp = hp;
		this.maxmp = mp;
		this.maxexp = exp;
		this.x = x;
		this.y = y;
		this.name = name;
		this.job = job;
		this.Vy = 0;
		this.isAir = false;
		this.moveable = true;
		npc = null;
	}

	public void jump() {
		if (checkCollisionUnderBlock()) {
			Vy = -15;
			isAir = true;
		}
	}

	public void moveX(double x) {
		MainRoop.CM.movePlayer(x);
	}

	public void addX(double a) {
		this.x += a;
		if (this.x + w > this.m.width)
			this.x = this.m.width - w;
		if (this.x - w < 0)
			this.x = w;
	}

	public void addY(double a) {
		this.y += a;
		if (this.y + h > this.m.height)
			this.y = this.m.height - h;
		if (this.y - h < 0)
			this.x = h;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public boolean checkCollisionUnderBlock() {
		for (int i = 0; i < MainRoop.p.m.Collision.length; i++) {
			if (MainRoop.p.m.Collision[i].CheckCollisioned(x, y + h + 1)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkCollisionBlock(double x, double y) {
		for (int i = 0; i < MainRoop.p.m.Collision.length; i++) {
				if (MainRoop.p.m.Collision[i] != null
						&& MainRoop.p.m.Collision[i].CheckCollisioned(x, y)) {
					return true;
			}
		}
		return false;
	}

	public CollisionBox getCollisionBlock(double x, double y) {
		for (int i = 0; i < this.m.Collision.length; i++) {
			if (this.m.Collision[i].CheckCollisioned(x, y))
				return this.m.Collision[i];
		}
		return null;
	}

	public void setMap(Map m) {
		if(this.m != null)this.m.Outer();
		m.Inter();
		this.m = m;
	}
	
	public Map getMap(){
		return m;
	}

	public void setLevel(int level) {
		this.level = level;
		if (this.level > this.maxlevel)
			this.level = this.maxlevel;
	}

	public void sethp(int hp) {
		this.hp = hp;
		if (this.hp > this.maxhp)
			this.hp = this.maxhp;
		if (this.hp < 0)
			this.hp = 0;
	}

	public void setmp(int mp) {
		this.mp = mp;
		if (this.mp > this.maxmp)
			this.mp = this.maxmp;
		if (this.mp < 0)
			this.mp = 0;
	}

	public void setexp(int exp) {
		this.exp = exp;
		if (this.exp > this.maxexp) {
			while (this.exp < maxexp) {
				this.exp -= this.maxexp;
				this.level++;
			}
		}
	}

	public void setMaxhp(int Maxhp) {
		this.maxhp = Maxhp;
	}

	public void setMaxmp(int Maxmp) {
		this.maxmp = Maxmp;
	}

	public void setMaxexp(int maxexp) {
		this.maxexp = maxexp;
	}
}
