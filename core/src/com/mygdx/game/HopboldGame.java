package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.screens.MenuScreen;

public class HopboldGame extends Game {
	private int boardWidth = 640;
	private int boardHeight = 480;

	private HopboldResources resources;

	private Stage stage;

	@Override
	public void create () {
		resources = new HopboldResources();

		Viewport viewport = new FitViewport(boardWidth/10, boardHeight/10);
		stage = new Stage(viewport);
		Gdx.input.setInputProcessor(stage);

		// Scale factor from world units (meters) to graphics (scaled pixels)
		// Needed to avoid rounding artifacts in box 2d.
		float worldToGfxScale = 10f; // 10 => 1 meter is 10 pixels


		setScreen(new MenuScreen(this, resources, stage));

	}
	
	@Override
	public void dispose () {
		resources.dispose();
		stage.dispose();
	}

	public int getBoardWidth() {
		return boardWidth;
	}

	public int getBoardHeight() {
		return boardHeight;
	}
}
