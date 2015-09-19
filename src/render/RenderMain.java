package render;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import render.font.FontRenderer;
import core.MainRoop;
import core.MainRoop.Gui;

public class RenderMain extends Thread {
	public static List<RenderUnit> list = new LinkedList<RenderUnit>();
	public static Iterator<RenderUnit> iterator;
	public static RenderItemSlot itemslot;
	public static RenderSkill skillslot;
	public static RenderQuest questslot;
	public static RenderSkill_Mage_MakingMagic mage_Making;
	public static RenderMiniMap minimap;
	@Override
	public void start() {
		try {
			Init();
			run();
			Display.sync(50);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void Init() throws IOException {
		setName("RenderFeredia");
		MainRoop.bar = TextureLoader.getTexture("png",
				ResourceLoader.getResourceAsStream("image/gui/bar.png"));
		itemslot = new RenderItemSlot();
		skillslot = new RenderSkill();
		mage_Making = new RenderSkill_Mage_MakingMagic();
		minimap = new RenderMiniMap();
		questslot = new RenderQuest();
		RenderMap.Init();
	}
	
	public void Update() {
		Render();
	}
	
	public void Render(){
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		RenderMap.Render();
		RenderPlayer();
		
		Render.RenderImageBoxColor(0, 960, MainRoop.p.getMap().width, 960 - 64, MainRoop.bar, new Color(1.f, 1.f, 1.f, 0.6f));

		
		Render.RenderBox(225, 960-51, 225+300, 960-31, new Color(1.f, 1.f, 1.f, 1.f));
		Render.RenderBox(225, 960-27, 225+300, 960-7, new Color(1.f, 1.f, 1.f, 1.f));
		
		Render.RenderBox(226, 960-50, 226+(MainRoop.p.hp * 298 / MainRoop.p.maxhp), 960 - 32, new Color(1.f, 0.f, 0.f, 1f));
		Render.RenderBox(226, 960-8, 226+(MainRoop.p.mp * 298 / MainRoop.p.maxmp), 960 - 26, new Color(0.f, 0.f, 1.f, 1f));

		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		FontRenderer.render(10, 960 - 60, MainRoop.p.name, 1);
		FontRenderer.render(10, 960 - 30, "Level " + MainRoop.p.level, 1);
		FontRenderer.render(130, 960 - 60, MainRoop.p.job, 1);
		
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);
		if (!MainRoop.p.moveable && MainRoop.p.npc != null) {
			for (int i = 0; i < MainRoop.p.npc.getNpcTalk().Dialogue.length; i++) {
				FontRenderer.render(110,360 + i * 30, MainRoop.p.npc.getNpcTalk().Dialogue[i], 1);
			}
		}

		if (RenderDataBase.OpenGui.contains(Gui.SkillMageMakingMagicSlot)) {
			mage_Making.render();
		}
		if (RenderDataBase.OpenGui.contains(Gui.ItemSlot)) {
			itemslot.render();
		}
		
		if (RenderDataBase.OpenGui.contains(Gui.MiniMap)) {
			minimap.render();
		}
		
		if (RenderDataBase.OpenGui.contains(Gui.SkillSlot)) {
			skillslot.render();
		}
		
		if (RenderDataBase.OpenGui.contains(Gui.QuestSlot)) {
			questslot.render();
		}

		iterator = list.iterator();
		while (iterator.hasNext()) {
			RenderUnit RU = iterator.next();
			RU.firstdraw();
		}
		SkillRender();
		if(MainRoop.Debug){
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);
			FontRenderer.render((int)MainRoop.p.getX(), (int)MainRoop.p.getY(), String.valueOf(MainRoop.p.getY()), 1);
			FontRenderer.render(0, 0, String.valueOf(MainRoop.averageFps), 1);
			for(int i = 0; i < MainRoop.p.getMap().Collision.length; i++){
				FontRenderer.render(MainRoop.p.getMap().Collision[i].x, MainRoop.p.getMap().Collision[i].y, String.valueOf(MainRoop.p.getMap().Collision[i].y), 1);
			}
		}
		
		Display.update();
	}
	
	public void RenderPlayer() {
		int h = MainRoop.p.h;
		int w = MainRoop.p.w;
		Render.RenderBox((int)MainRoop.p.getX(), (int)MainRoop.p.getY(), (int)MainRoop.p.getX() + w, (int)MainRoop.p.getY() + h, new Color(1.f, 1.f, 1.f, 1f));
	}

	public void SkillRender(){
		for(int i = 0; i < MainRoop.p.skill.length; i++){
			if(MainRoop.p.skill[i] != null){
				MainRoop.p.skill[i].render();
			}
		}
	}
}
