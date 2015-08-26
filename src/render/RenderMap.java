package render;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import map.Map;
import map.Tiles;
import core.MainRoop;

public class RenderMap {

	public static void Init() {
	}

	public synchronized static void update() {
		Map m = MainRoop.p.getMap();

		if (m != null) {
			if (m.BackGround != null) {
				m.BackGround.bind();
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(0, 0);
				GL11.glVertex2f(0, 0);
				GL11.glTexCoord2f(0, m.BackGround.getHeight());
				GL11.glVertex2f(0, 960);
				GL11.glTexCoord2f(m.BackGround.getWidth(),
						m.BackGround.getHeight());
				GL11.glVertex2f(1280, 960);
				GL11.glTexCoord2f(m.BackGround.getWidth(), 0);
				GL11.glVertex2f(1280, 0);
				GL11.glEnd();
			}
			for (int i = 0; i < m.width / 32; i++) {
				for (int j = 0; j < m.height / 32; j++) {
					if (m.mapblock[i][j] != Tiles.air && m.mapblock[i][j] != null && m.mapblock[i][j].getTexture() != -1) {
						m.getMapTexture(i, j).bind();
						int num = m.mapblock[i][j].getnum();
						GL11.glBegin(GL11.GL_QUADS);
						GL11.glTexCoord2f((float)num * 32/m.getMapTexture(i, j).getImageWidth(), 0);
						GL11.glVertex2f(i * 32, j * 32);
						GL11.glTexCoord2f((float)num * 32/m.getMapTexture(i, j).getImageWidth(), 1);
						GL11.glVertex2f(i * 32, (j + 1) * 32);
						GL11.glTexCoord2f((float)(num + 1)* 32/m.getMapTexture(i, j).getImageWidth(), 1);
						GL11.glVertex2f(i * 32 + 32, (j + 1) * 32);
						GL11.glTexCoord2f((float)(num + 1)* 32/m.getMapTexture(i, j).getImageWidth(), 0);
						GL11.glVertex2f(i * 32 + 32, j * 32);
						GL11.glEnd();
					}
				}
			}
			GL11.glColor4f(0.0f, 0.0f, 1.0f, 0.5f);
			if (m.portal != null) {
				for (int i = 0; i < m.portal.length; i++) {
					GL11.glBegin(GL11.GL_QUADS);
					GL11.glVertex2d(m.portal[i].x, m.portal[i].y);
					GL11.glVertex2d(m.portal[i].x, m.portal[i].y
							+ m.portal[i].height);
					GL11.glVertex2d(m.portal[i].x + m.portal[i].width,
							m.portal[i].y + m.portal[i].height);
					GL11.glVertex2d(m.portal[i].x + m.portal[i].width,
							m.portal[i].y);
					GL11.glEnd();
				}
			}

			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);
			if (m.npc != null) {
				for (int i = 0; i < m.npc.length; i++) {
					Texture texture = m.npc[i].getTexture();
					texture.bind();
					GL11.glBegin(GL11.GL_QUADS);
					GL11.glTexCoord2f(0, 0);
					GL11.glVertex2d(m.npc[i].x, m.npc[i].y);
					GL11.glTexCoord2f(texture.getWidth(), 0);
					GL11.glVertex2d(m.npc[i].x + texture.getImageWidth(),
							m.npc[i].y);
					GL11.glTexCoord2f(texture.getWidth(), texture.getHeight());
					GL11.glVertex2d(m.npc[i].x + texture.getImageWidth(),
							m.npc[i].y + texture.getImageHeight());
					GL11.glTexCoord2f(0, texture.getHeight());
					GL11.glVertex2d(m.npc[i].x,
							m.npc[i].y + texture.getImageHeight());
					GL11.glEnd();
				}
			}
			if (MainRoop.Debug) {
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glColor4f(1.f, 0.f, 0.f, 0.5f);
				GL11.glDisable(GL11.GL_TEXTURE);
				for (int i = 0; i < m.Collision.length; i++) {
					GL11.glBegin(GL11.GL_LINE_LOOP);
					GL11.glVertex2f(m.Collision[i].x, m.Collision[i].y);
					GL11.glVertex2f(m.Collision[i].x + m.Collision[i].width,
							m.Collision[i].y);
					GL11.glVertex2f(m.Collision[i].x + m.Collision[i].width,
							m.Collision[i].y + m.Collision[i].height);
					GL11.glVertex2f(m.Collision[i].x, m.Collision[i].y
							+ m.Collision[i].height);
					GL11.glEnd();
				}
			}
		}

	}
}
