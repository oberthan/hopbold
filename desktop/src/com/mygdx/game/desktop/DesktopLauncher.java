package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.HopboldGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		HopboldGame game = new HopboldGame();
		config.width = game.getBoardWidth()*2;
		config.height = game.getBoardHeight()*2;
		new LwjglApplication(game, config);
	}
}
