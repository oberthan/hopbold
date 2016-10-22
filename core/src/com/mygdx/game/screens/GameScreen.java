package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.HopboldResources;

/**
 * Created by Søren on 22-10-2016.
 */

public class GameScreen implements Screen {
    private Stage stage = new Stage();
    private Image bold;
    private Image kasse;
    private Game game;
    private HopboldResources resources;

    public GameScreen(Game game, HopboldResources resources, Stage stage)
    {
        this.game = game;
        this.resources = resources;
        this.stage = stage;

        bold = new Image(resources.getBoldTexture());

        kasse = new Image(resources.getKasseTexture());
    }

    @Override
    public void show() {
        stage.clear();

        // Initialiser spillere
        bold.setAlign(Align.bottom);
        bold.setOrigin(Align.center);
        bold.setSize(50f, 50f);
        bold.setPosition(25, 0);
        bold.setRotation(0);
        stage.addActor(bold);

        kasse.setAlign(Align.bottom);
        bold.setOrigin(Align.center);
        kasse.setSize(80f, 80f);
        kasse.setPosition(720, 0);
        stage.addActor(kasse);
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateActors(delta);

        stage.act(delta);
        stage.draw();
    }

    boolean hopperOp = false;

    boolean boldenErIkkeRamt = true;


    private void updateActors(float delta) {


            boolean skærmTrykket = Gdx.input.isTouched();
            boolean mellemrumTrykket = Gdx.input.isKeyPressed(Input.Keys.SPACE);
            boolean hopKnapTrykket = skærmTrykket || mellemrumTrykket;

            float boldrotation = bold.getRotation();

            if (hopKnapTrykket && bold.getY() == 0)
            {
                hopperOp = true;
            }

            if (hopperOp)
            {
                bold.moveBy(0, 12);
            }
            else
            {
                bold.moveBy(0, -12);
            }

            if (bold.getY() > 300)
            {
                hopperOp = false;
            }
            if (bold.getY() < 0)
            {
                bold.setY(0);
            }
            // Vinduet er 640 x 480
            //  y 480
            //  ---------------
            //  \             \
            //  \             \
            // 0---------------  x 640

            // Flyt bold
            bold.setRotation(boldrotation);

            if (bold.getX() < 10) bold.moveBy(1, 0);
            if (boldenErIkkeRamt) bold.rotateBy(-4);

            // Flyt kasse
            kasse.moveBy(-6, 0);

            if (kasse.getX() < -kasse.getWidth()) kasse.setPosition(720, 0);

            Rectangle boldOmkreds = new Rectangle(bold.getX()+10, bold.getY()+10, bold.getWidth()-20, bold.getHeight()-20);
            Rectangle kasseOmkreds = new Rectangle(kasse.getX(), kasse.getY(), kasse.getWidth(), kasse.getHeight());

            // Rammer bold kassen?
            if (boldenErIkkeRamt && Intersector.overlaps(boldOmkreds, kasseOmkreds))
            {
                // Sæt boldtegning til eksplosion
                bold.setDrawable(new TextureRegionDrawable(new TextureRegion(resources.getExplosionTexture())));
                boldenErIkkeRamt =false;
                //resources.getBangLyd().play();
            }
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
