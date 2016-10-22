package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
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
            RectangleActor kasse = new RectangleActor(gameWorld, new Vector2(x, y), size, size, resources.getKasseTexture());
            stage.addActor(kasse);
            kasser.add(kasse);
        }
    }




    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //updateActors(delta);
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
        boolean skærmTrykket = Gdx.input.isTouched();
        boolean mellemrumTrykket = Gdx.input.isKeyPressed(Input.Keys.SPACE);
        boolean hopKnapTrykket = skærmTrykket || mellemrumTrykket;


//
//            float boldrotation = bold.getRotation();
//
//            if (hopKnapTrykket && bold.getY() == 0)
//            {
//                hopperOp = true;
//            }
//
//            if (hopperOp)
//            {
//                bold.moveBy(0, 12);
//            }
//            else
//            {
//                bold.moveBy(0, -12);
//            }
//
//            if (bold.getY() > 300)
//            {
//                hopperOp = false;
//            }
//            if (bold.getY() < 0)
//            {
//                bold.setY(0);
//            }
//            // Vinduet er 640 x 480
//            //  y 480
//            //  ---------------
//            //  \             \
//            //  \             \
//            // 0---------------  x 640
//
//            // Flyt bold
//            bold.setRotation(boldrotation);
//
//            if (bold.getX() < 10) bold.moveBy(1, 0);
//            if (boldenErIkkeRamt) bold.rotateBy(-4);
//
//            // Flyt kasse
//            kasse.moveBy(-6, 0);
//
//            if (kasse.getX() < -kasse.getWidth()) kasse.setPosition(720, 0);
//
//            Rectangle boldOmkreds = new Rectangle(bold.getX()+10, bold.getY()+10, bold.getWidth()-20, bold.getHeight()-20);
//            Rectangle kasseOmkreds = new Rectangle(kasse.getX(), kasse.getY(), kasse.getWidth(), kasse.getHeight());
//
//            // Rammer bold kassen?
//            if (boldenErIkkeRamt && Intersector.overlaps(boldOmkreds, kasseOmkreds))
//            {
//                // Sæt boldtegning til eksplosion
//                bold.setDrawable(new TextureRegionDrawable(new TextureRegion(resources.getExplosionTexture())));
//                boldenErIkkeRamt =false;
//                //resources.getBangLyd().play();
//            }
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
