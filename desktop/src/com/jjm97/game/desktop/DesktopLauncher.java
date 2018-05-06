package com.jjm97.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jjm97.game.Tetris;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Fetris";
		config.width = 600;
		config.height = 700;
		config.resizable = false;
		new LwjglApplication(new Tetris(), config);
	}
}
