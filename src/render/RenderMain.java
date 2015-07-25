package render;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import listener.GameListener;
import map.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import player.skill.Skills_Mage;
import render.font.FontRenderer;
import core.MainRoop;

public class RenderMain extends Thread {
	public static List<RenderUnit> list = new LinkedList<RenderUnit>();
	public static Iterator<RenderUnit> iterator;

	@Override
	public synchronized void start() {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
		update();
		Display.sync(50);
	}

	private void init() throws IOException {
		MainRoop.bar = TextureLoader.getTexture("png",
				ResourceLoader.getResourceAsStream("image/gui/bar.png"));
		RenderSlot.Init();
	}

	public synchronized void update() {
		Map m = MainRoop.p.getMap();
		
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		if(m.BackGround != null){
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
						GL11.glVertex2f((i + 1) * 32, (j + 1) * 32);
						GL11.glTexCoord2f(1, 0);
						GL11.glVertex2f((i + 1) * 32, j * 32);
						GL11.glEnd();
					}
				}
			}

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);
			MainRoop.bar.bind();
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(0, 960);
			GL11.glTexCoord2f(MainRoop.bar.getWidth(), 0);
			GL11.glVertex2f(m.width, 960);
			GL11.glTexCoord2f(MainRoop.bar.getWidth(), MainRoop.bar.getHeight());
			GL11.glVertex2f(m.width, 960 - 64);
			GL11.glTexCoord2f(0, MainRoop.bar.getHeight());
			GL11.glVertex2f(0, 960 - 64);
			GL11.glEnd();
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

			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);
			int h = MainRoop.p.h;
			int w = MainRoop.p.w;

			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2d(MainRoop.p.getX() - w, MainRoop.p.getY() - h);
			GL11.glVertex2d(MainRoop.p.getX() - w, MainRoop.p.getY() + h);
			GL11.glVertex2d(MainRoop.p.getX() + w, MainRoop.p.getY() + h);
			GL11.glVertex2d(MainRoop.p.getX() + w, MainRoop.p.getY() - h);
			GL11.glEnd();

			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(225, 960 - 51);
			GL11.glVertex2f(225, 960 - 31);
			GL11.glVertex2f(225 + 300, 960 - 31);
			GL11.glVertex2f(225 + 300, 960 - 51);
			GL11.glEnd();
			
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(225, 960 - 27);
			GL11.glVertex2f(225, 960 - 7);
			GL11.glVertex2f(225 + 300, 960 - 7);
			GL11.glVertex2f(225 + 300, 960 - 27);
			GL11.glEnd();

			GL11.glColor4f(1.0f, 0.0f, 0.0f, 1f);
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(226, 960 - 32);
			GL11.glVertex2f(226, 960 - 50);
			GL11.glVertex2f(226 + MainRoop.p.hp * 298 / MainRoop.p.maxhp,
					960 - 50);
			GL11.glVertex2f(226 + MainRoop.p.hp * 298 / MainRoop.p.maxhp,
					960 - 32);
			GL11.glEnd();
			
			GL11.glColor4f(0.0f, 0.0f, 1.0f, 1f);
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(226, 960 - 8);
			GL11.glVertex2f(226, 960 - 26);
			GL11.glVertex2f(226 + MainRoop.p.hp * 298 / MainRoop.p.maxhp,
					960 - 26);
			GL11.glVertex2f(226 + MainRoop.p.hp * 298 / MainRoop.p.maxhp,
					960 - 8);
			GL11.glEnd();

			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			FontRenderer.kor_white.bind();
			FontRenderer.render(10, 960 - 60, MainRoop.p.name);
			FontRenderer.render(10, 960 - 30, "Level " + MainRoop.p.level);
			FontRenderer.render(130, 960 - 60, MainRoop.p.job);

			if (!MainRoop.p.moveable && MainRoop.p.npc != null) {
				for (int i = 0; i < MainRoop.p.npc.npcTalk[MainRoop.p.npc.showedNpcTalk].Dialogue.length; i++) {
					FontRenderer
							.render(110,
									360 + i * 30,
									MainRoop.p.npc.npcTalk[MainRoop.p.npc.showedNpcTalk].Dialogue[i]);
				}
			}

			if (GameListener.b) {
				new RenderSlot().render();
			}

			iterator = list.iterator();
			while (iterator.hasNext()) {
				RenderUnit RU = iterator.next();
				RU.firstdraw();
			}
			
			Skills_Mage.MagicMissile.RenderImage(0, 0);
		}
		Display.update();
	}
}
