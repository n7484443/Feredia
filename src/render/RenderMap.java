package render;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import map.Map;
import map.Tiles;
import core.MainRoop;

public class RenderMap {

	public static void Init() {
	}

	public synchronized static void Render() {
		Map m = MainRoop.p.getMap();

		if (m != null) {
			if (m.BackGround != null) {
				Render.RenderImageBox(0, 0, m.width, m.height, m.BackGround);
			}
			for (int i = 0; i < m.width / 32; i++) {
				for (int j = 0; j < m.height / 32; j++) {
					if (m.mapblock[i][j] != Tiles.air && m.mapblock[i][j] != null && m.mapblock[i][j].getTexture() != -1) {
						m.getMapTexture(i, j).bind();
						int num = m.mapblock[i][j].getnum();
						Render.RenderImageBoxWidthHeight(i * 32, j * 32, (i + 1) * 32, (j + 1) * 32, m.getMapTexture(i, j), ((float)num * 32/m.getMapTexture(i, j).getImageWidth()), 0f, (float)32/m.getMapTexture(i, j).getImageWidth(), 1f);
					}
				}
			}
			if (m.portal != null) {
				for (int i = 0; i < m.portal.length; i++) {
					Render.RenderBox(m.portal[i].x, m.portal[i].y, m.portal[i].x + m.portal[i].width, m.portal[i].y + m.portal[i].height, new Color(0.f, 0.f, 1.f, .5f));
				}
			}
			if (m.npc != null) {
				for (int i = 0; i < m.npc.length; i++) {
					Texture texture = m.npc[i].getTexture();
					Render.RenderImageBoxColor(m.npc[i].x, m.npc[i].y, m.npc[i].x + texture.getImageWidth(), m.npc[i].y + texture.getImageHeight(), texture, new Color(1.f, 1.f, 1.f, 1f));
				}
			}
			if (MainRoop.Debug) {
				GL11.glDisable(GL11.GL_BLEND);
				for (int i = 0; i < m.Collision.length; i++) {
					Render.RenderLine(m.Collision[i].x, m.Collision[i].y, m.Collision[i].x + m.Collision[i].width, m.Collision[i].y + m.Collision[i].height, new Color(1.f, 0.f, 0.f, .5f));
				}
			}
		}

	}
}
