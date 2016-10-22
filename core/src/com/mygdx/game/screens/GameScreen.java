package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GameWorld;
import com.mygdx.game.HopboldResources;
import com.mygdx.game.items.BallActor;
import com.mygdx.game.items.RectangleActor;

import java.util.ArrayList;

/**
 * Created by Søren on 22-10-2016.
 */

public class GameScreen implements Screen {
    private Stage stage = new Stage();
    private BallActor bold;
    private ArrayList<RectangleActor> kasser = new ArrayList<RectangleActor>();
    private Game game;
    private HopboldResources resources;
    private GameWorld gameWorld;

    public GameScreen(Game game, HopboldResources resources, Stage stage)
    {
        this.game = game;
        this.resources = resources;
        this.stage = stage;

        gameWorld = new GameWorld(stage.getWidth(), stage.getHeight());

    }

    @Override
    public void show() {
        stage.clear();

        // Initialiser spillere

        bold = new BallActor(gameWorld, new Vector2(25, 25), 2f, resources.getBoldTexture());
        stage.addActor(bold);

        for (int i = 0; i < 20; i++) {
            float size = MathUtils.random(1, 5);
            float x = MathUtils.random(30f, stage.getWidth());
            float y = MathUtils.random(size, stage.getHeight());
            addNewBox(new Vector2(x, y), size);
        }
    }




    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateActors(delta);
        gameWorld.render(delta);
        bold.updateActor();
        for (RectangleActor kasse:kasser) {
            kasse.updateActor();
        }

        stage.act(delta);
        stage.draw();
    }

    boolean hopperOp = false;

    boolean boldenErIkkeRamt = true;


    private void updateActors(float delta) {
        boolean mellemrumTrykket = Gdx.input.isKeyPressed(Input.Keys.SPACE);

        if (bold.canJump() && Gdx.input.isKeyPressed(Input.Keys.SPACE))
            bold.jump();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            bold.left();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            bold.right();
        if (Gdx.input.justTouched())
        {
            addNewBox(gameWorld.getInputPosition(), MathUtils.random(1, 6));
        }
    }

    private void addNewBox(Vector2 position, float size) {
        RectangleActor kasse = new RectangleActor(gameWorld, position, size, size, resources.getKasseTexture());
        stage.addActor(kasse);
        kasser.add(kasse);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
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
