package render;

import org.lwjgl.opengl.GL11;

import map.Map;
import core.MainRoop;

public class RenderMap {

	public static void Init() {

	}

	public synchronized static void update() {
		Map m = MainRoop.p.getMap();
		if (m.BackGround != null) {
			m.BackGround.bind();
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(0, 0);
			GL11.glTexCoord2f(0, m.BackGround.getHeight());
			GL11.glVertex2f(0, 960);
			GL11.glTexCoord2f(m.BackGround.getWidth(), m.BackGround.getHeight());
			GL11.glVertex2f(1280, 960);
			GL11.glTexCoord2f(m.BackGround.getWidth(), 0);
			GL11.glVertex2f(1280, 0);
			GL11.glEnd();
		}
		if (m != null) {
			for (int i = 0; i < m.width / 32; i++) {
				for (int j = 0; j < m.height / 32; j++) {
					if (m.mapblock[i][j] != 0) {
						m.getMapTexture(i, j).bind();
						GL11.glBegin(GL11.GL_QUADS);
						GL11.glTexCoord2f(0, 0);
						GL11.glVertex2f(i * 32, j * 32);
						GL11.glTexCoord2f(0, 1);
						GL11.glVertex2f(i * 32, (j + 1) * 32);
						GL11.glTexCoord2f(1, 1);
						GL11.glVertex2f(i * 32 + m.getMapTextureXSize(i,j), (j + 1) * 32);
						GL11.glTexCoord2f(1, 0);
						GL11.glVertex2f(i * 32 + m.getMapTextureXSize(i,j), j * 32);
						GL11.glEnd();
					}
				}
			}
		}
		
		
		GL11.glColor4f(0.0f, 1.0f, 1.0f, 1f);
		if (m.npc != null)
			for (int i = 0; i < m.npc.length; i++) {
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2d(m.npc[i].x,
						m.npc[i].y);
				GL11.glVertex2d(m.npc[i].x,
						m.npc[i].y + m.npc[i].height);
				GL11.glVertex2d(m.npc[i].x
						+ m.npc[i].width, m.npc[i].y
						+ m.npc[i].height);
				GL11.glVertex2d(m.npc[i].x
						+ m.npc[i].width, m.npc[i].y);
				GL11.glEnd();
			}

		GL11.glColor4f(0.0f, 0.0f, 1.0f, 0.5f);
		if (m.portal != null)
			for (int i = 0; i < m.portal.length; i++) {
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2d(m.portal[i].x,
						m.portal[i].y);
				GL11.glVertex2d(m.portal[i].x,
						m.portal[i].y
								+ m.portal[i].height);
				GL11.glVertex2d(m.portal[i].x
						+ m.portal[i].width,
						m.portal[i].y
								+ m.portal[i].height);
				GL11.glVertex2d(m.portal[i].x
						+ m.portal[i].width,
						m.portal[i].y);
				GL11.glEnd();
			}
	}
}
