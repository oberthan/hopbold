package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.HopboldResources;

/**
 * Created by Søren on 22-10-2016.
 */

public class MenuScreen implements Screen {
    private Stage stage;

    private Image playImage;
    private Game game;
    private HopboldResources resources;

    public MenuScreen(Game game, HopboldResources resources, Stage stage)
    {
        this.game = game;
        this.resources = resources;
        this.stage = stage;

        playImage = new Image(resources.getPlayButtonTexture());
        playImage.setScale(0.1f);
        playImage.setAlign(Align.center);
    }


    @Override
    public void show() {
        stage.clear();

        playImage.setPosition(stage.getWidth() / 2, stage.getHeight() / 2);

        playImage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game, resources, stage));
                return true;
            }
        });

        stage.addActor(playImage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true); // true will center camera
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
