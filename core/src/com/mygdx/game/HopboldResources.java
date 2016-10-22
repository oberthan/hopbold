package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class HopboldResources extends GameResources {
    // Menu
    private Texture playButtonTexture;

    // Spil
    private Texture boldTexture;
    private Texture kasseTexture;
    private Texture explosionTexture;

    // Lyde
    private Sound bangLyd;

    public HopboldResources() {
        initialize();
    }

    public HopboldResources(String themeBaseFolder) {
        super(themeBaseFolder);
        initialize();
    }

    private void initialize()
    {
        playButtonTexture = new Texture(findAsset("img/play_button.png"));

        boldTexture = new Texture(findAsset("img/bold.png"));
        kasseTexture = new Texture(findAsset("img/kasse.png"));
        explosionTexture = new Texture(findAsset("img/explosion.png"));

        //bangLyd = Gdx.audio.newSound(findAsset("sound/bang.wav"));
    }

    public Texture getPlayButtonTexture() {
        return playButtonTexture;
    }

    public Texture getBoldTexture() {
        return boldTexture;
    }

    public Texture getKasseTexture() {
        return kasseTexture;
    }

    public Texture getExplosionTexture() {
        return explosionTexture;
    }

    public Sound getBangLyd() {
        return bangLyd;
    }

    public void dispose() {
        boldTexture.dispose();
        kasseTexture.dispose();
        explosionTexture.dispose();
        bangLyd.dispose();
    }
}
