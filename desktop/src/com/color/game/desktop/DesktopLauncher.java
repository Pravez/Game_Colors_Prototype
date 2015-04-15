package com.color.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.color.game.ColorGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Color Game Prototype";
        config.width = 1200;
        config.height = 600;
		new LwjglApplication(new ColorGame(), config);
	}
}
