package core;

import image.loader.MainImageLoader;
import listener.GameListener;
import map.Maps;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import player.Items;
import player.PlayerInfo;
import player.Slot;
import render.RenderMain;
import render.font.FontRenderer;
import skill.mage.Skills_Mage;

public class MainRoop {
	
	public static boolean Gamerun = true;
	public static int roop;
	public static RenderMain RM = new RenderMain();
	public static CircuitMain CM = new CircuitMain();
	public static PlayerInfo p;
	
	public static Texture bar;
	
	public synchronized static void main(String[] args) throws Exception{
		Init(1280, 960);
		while (Gamerun) {
			update();
		}
		end();
		System.exit(0);
	}

	public synchronized static void end() {
		Display.destroy();
	}

	public synchronized static void Init(int width, int height)
			throws Exception {
		setDisplayAndGL(width, height);
		FontRenderer.Init();
		MainImageLoader.Init();
		Maps.Init();
		Items.Init();
		Skills_Mage.Init();
		p = new PlayerInfo(1, 100, 100, 0, 10, 730, "플레이어", "초보자");
		p.sethp(100);
		p.setmp(100);
		p.setMap(Maps.tutorial_0);
		Slot.Init();
		GameListener.Init();
		CM.start();
		RM.start();
	}

	public static void setDisplayAndGL(int width, int height)
			throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(width, height));
		Display.setTitle("window");
		Display.setResizable(false);
		Display.create();

		GL11.glEnable(GL11.GL_TEXTURE_2D);

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glViewport(0, 0, width, height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	public synchronized static void update() {
		if (Display.isCloseRequested()) {
			Gamerun = false;
		} else {
			long before = System.currentTimeMillis();
			Check();
			CM.update();
			RM.update();
			long after = System.currentTimeMillis();
			if ((50 - (after - before)) > 0) {
				try {
					Thread.sleep(50 - (after - before));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			roop++;
			//if (roop % 20 == 0)
				//System.out.println((50 - (after - before)) * 20);
		}
	}

	private static void Check() {
		GameListener.Check();
	}
}
