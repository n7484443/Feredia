package player;

import skill.Skill;
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

	public int Vy;

	public boolean isAir;

	public final int maxlevel = 100;

	public final int w = 32;
	public final int h = 32;

	private Map m;

	public String name;

	public String job;

	public boolean moveable;
	public boolean Dir;

	public NpcBase npc;
	
	public CollisionBox collisionBox;
	
	public Skill[] skill;

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
		this.Dir = false;
		this.collisionBox = new CollisionBox(x, y, w, h);
		npc = null;
		skill = new Skill[20];
	}

	public void jump() {
		if (checkCollisionUnderBlock()) {
			Vy = -15;
			isAir = true;
		}
	}

	public void moveX(int x) {
		MainRoop.CM.movePlayer(x);
	}

	public void addX(double a) {
		this.x += a;
		this.collisionBox.x += a;
		if (this.x + w > this.m.width){
			this.x = this.m.width - w;
			this.collisionBox.x = this.m.width - w;
		}else if (this.x < 0){
			this.x = 0;
			this.collisionBox.x = 0;
		}
	}

	public void addY(double a) {
		this.y += a;
		this.collisionBox.y += a;
		if (this.y + h > this.m.height){
			this.y = this.m.height - h;
			this.collisionBox.y = this.m.height - h;
		}else if (this.y < 0){
			this.y = 0;
			this.collisionBox.y = 0;
		}
	}

	public void setX(int x) {
		this.x = x;
		this.collisionBox.x = x;
	}

	public void setY(int y) {
		this.y = y;
		this.collisionBox.y = y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public boolean checkCollisionUnderBlock() {
		for (int i = 0; i < MainRoop.p.m.Collision.length; i++) {
			if (collisionBox.CheckCollisioned(MainRoop.p.m.Collision[i])) {
				return true;
			}
		}
		return false;
	}

	public boolean checkCollisionBlock(int x, int y) {
		for (int i = 0; i < MainRoop.p.m.Collision.length; i++) {
				if (collisionBox.CheckCollisioned(x, y, MainRoop.p.m.Collision[i])) {
					return true;
			}
		}
		return false;
	}

	public CollisionBox getCollisionBlock(int x, int y) {
		for (int i = 0; i < this.m.Collision.length; i++) {
			if (collisionBox.CheckCollisioned(x, y, MainRoop.p.m.Collision[i])){
				return this.m.Collision[i];
			}
		}
		return null;
	}

	public void setMap(Map m) {
		if(this.m != null){
			this.m.Outer();
		}
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
