package render;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import render.font.FontRenderer;
import core.MainRoop;

public class RenderMain extends Thread {
	public static List<RenderUnit> list = new LinkedList<RenderUnit>();
	public static Iterator<RenderUnit> iterator;
	@Override
	public synchronized void start() {
		try {
			Init();
		} catch (IOException e) {
			e.printStackTrace();
		}
		update();
		Display.sync(50);
	}

	private void Init() throws IOException {
		MainRoop.bar = TextureLoader.getTexture("png",
				ResourceLoader.getResourceAsStream("image/gui/bar.png"));
		RenderSlot.Init();
		RenderSkill.Init();
		RenderMap.Init();
	}

	public synchronized void update() {
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);
		
		RenderMap.update();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.6f);
		MainRoop.bar.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(0, 960);
		GL11.glTexCoord2f(MainRoop.bar.getWidth(), 0);
		GL11.glVertex2f(MainRoop.p.getMap().width, 960);
		GL11.glTexCoord2f(MainRoop.bar.getWidth(), MainRoop.bar.getHeight());
		GL11.glVertex2f(MainRoop.p.getMap().width, 960 - 64);
		GL11.glTexCoord2f(0, MainRoop.bar.getHeight());
		GL11.glVertex2f(0, 960 - 64);
		GL11.glEnd();

		RenderPlayer();

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
		GL11.glVertex2f(226 + MainRoop.p.hp * 298 / MainRoop.p.maxhp, 960 - 50);
		GL11.glVertex2f(226 + MainRoop.p.hp * 298 / MainRoop.p.maxhp, 960 - 32);
		GL11.glEnd();

		GL11.glColor4f(0.0f, 0.0f, 1.0f, 1f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(226, 960 - 8);
		GL11.glVertex2f(226, 960 - 26);
		GL11.glVertex2f(226 + MainRoop.p.mp * 298 / MainRoop.p.maxmp, 960 - 26);
		GL11.glVertex2f(226 + MainRoop.p.mp * 298 / MainRoop.p.maxmp, 960 - 8);
		GL11.glEnd();

		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		FontRenderer.kor_black.bind();
		FontRenderer.render(10, 960 - 60, MainRoop.p.name);
		FontRenderer.render(10, 960 - 30, "Level " + MainRoop.p.level);
		FontRenderer.render(130, 960 - 60, MainRoop.p.job);

		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);
		if (!MainRoop.p.moveable && MainRoop.p.npc != null) {
			for (int i = 0; i < MainRoop.p.npc.npcTalk[MainRoop.p.npc.showedNpcTalk].Dialogue.length; i++) {
				FontRenderer.render(110,360 + i * 30, MainRoop.p.npc.npcTalk[MainRoop.p.npc.showedNpcTalk].Dialogue[i]);
			}
		}

		if (RenderDataBase.IsItemSlotOpened) {
			new RenderSlot().render();
		}
		
		if (RenderDataBase.IsSkillSlotOpened) {
			new RenderSkill().render();
		}

		iterator = list.iterator();
		while (iterator.hasNext()) {
			RenderUnit RU = iterator.next();
			RU.firstdraw();
		}
		SkillRender();
		
		
		Display.update();
	}
	
	public void RenderPlayer() {
		int h = MainRoop.p.h;
		int w = MainRoop.p.w;
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(MainRoop.p.getX() - w, MainRoop.p.getY() - h);
		GL11.glVertex2d(MainRoop.p.getX() - w, MainRoop.p.getY() + h);
		GL11.glVertex2d(MainRoop.p.getX() + w, MainRoop.p.getY() + h);
		GL11.glVertex2d(MainRoop.p.getX() + w, MainRoop.p.getY() - h);
		GL11.glEnd();
	}

	public void SkillRender(){
		for(int i = 0; i < MainRoop.p.skill.length; i++){
			if(MainRoop.p.skill[i] != null){
				MainRoop.p.skill[i].render();
			}
		}
	}
}
