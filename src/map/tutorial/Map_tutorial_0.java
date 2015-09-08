package map.tutorial;

import java.io.IOException;

import map.Map;
import map.Maps;
import map.Tile;
import map.Tiles;
import npc.NpcBase;
import npc.list.Emilie;

import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import portal.Portal;
import render.RenderMain;
import render.RenderUnit;
import collision.CollisionBox;

public class Map_tutorial_0 extends Map {
	// width:40, height:30

	public Map_tutorial_0() throws IOException {
		name = "튜토리얼 1";
		BackGround = TextureLoader.getTexture("png", ResourceLoader
				.getResourceAsStream("image/map/background/background.png"));
	}

	public void Init() throws IOException {
		mapblock = new Tile[width / 32][height / 32];
		npc = new NpcBase[1];
		portal = new Portal[1];
		for (int i = 0; i < width / 32; i++) {
			for (int j = 0; j < height / 32; j++) {
				mapblock[i][j] = Tiles.air;
			}
		}
		for (int i = 0; i < width / 32; i++) {
			for (int j = 25; j < 30; j++) {
				if (i % 2 == 1) {
					mapblock[i][j] = Tiles.dirt_l;
				} else {
					mapblock[i][j] = Tiles.dirt_r;
				}
			}
		}
		for (int i = 0; i < width / 32 - 2; i ++) {
			if (i % 2 == 1) {
				mapblock[i][24] = Tiles.grass_l;
			} else {
				mapblock[i][24] = Tiles.grass_r;
			}
		}
		mapblock[width / 32 - 2][24] = Tiles.dirt_l;
		mapblock[width / 32 - 1][24] = Tiles.dirt_r;
		mapblock[width / 32 - 2][23] = Tiles.grass_l;
		mapblock[width / 32 - 1][23] = Tiles.grass_r;

		Collision = new CollisionBox[2];
		Collision[0] = new CollisionBox(0, this.height - 6 * 32, this.width,
				6 * 32);
		Collision[1] = new CollisionBox(38 * 32, this.height - 7 * 32, 2 * 32,
				32);

		npc[0] = new Emilie(this.width, this.height);
		portal[0] = new Portal(32 * 39, height - 32 * 8, 0, height - 32 * 7,
				Maps.tutorial_1);
	}

	public void Inter() {
		super.Inter();
		try {
			RenderMain.list.add(new RenderUnit(140, 180,
					"image/map/tutorial/space_bar.png", this));
			RenderMain.list.add(new RenderUnit(200, 180,
					"image/map/tutorial/left_bar.png", this));
			RenderMain.list.add(new RenderUnit(240, 180,
					"image/map/tutorial/right_bar.png", this));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
