package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.StageFactory;

/**
 * Created by Søren on 22-10-2016.
 */

public class HopboldStageFactory implements StageFactory {
    private int boardWidth = 480;
    private int boardHeight = 800;

    @Override
    public Stage create() {
        Viewport viewport = new FitViewport(boardWidth, boardHeight);
        Stage stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        return stage;
    }
}
