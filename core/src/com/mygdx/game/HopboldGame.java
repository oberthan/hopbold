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

		Viewport viewport = new FitViewport(boardWidth, boardHeight);
		stage = new Stage(viewport);
		Gdx.input.setInputProcessor(stage);

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
