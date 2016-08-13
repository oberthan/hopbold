package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture boldTexture;
	Texture kasseTexture;
	Texture explosionTexture;

	Image bold;
	Image kasse;
	@Override
	public void create () {
		batch = new SpriteBatch();
		boldTexture = new Texture("bold.png");
		kasseTexture = new Texture("kasse.png");
		explosionTexture = new Texture("explosion.png");

		bold = new Image(boldTexture);
		kasse = new Image(kasseTexture);
	}

	float boldx = 0;
	float boldy = 0;
	float boldrotation = 0;

	float kassex = 640;

	@Override
	public void render () {
		// Vinduet er 640 x 480
		//  y 480
		//  ---------------
		//  \             \
		//  \             \
		// 0---------------  x 640

		// Flyt bold
		bold.setPosition(boldx, boldy);
		bold.setOrigin(Align.center);
		bold.setRotation(boldrotation);

		if (boldx < 10) boldx = boldx + 1;
		boldrotation = boldrotation - 4;

		// Flyt kasse
		kasse.setPosition(kassex,0);
		kassex = kassex -6;

		// Rammer bold kassen?
		if (Intersector.overlaps(
				new Rectangle(bold.getX(), bold.getY(), bold.getWidth(), bold.getHeight()),
				new Rectangle(kasse.getX(), kasse.getY(), kasse.getWidth(), kasse.getHeight())
				))
		{
			bold.setDrawable(new TextureRegionDrawable(new TextureRegion(explosionTexture)));
		}

		// Sæt skærmfarve
		Gdx.gl.glClearColor(0, 0, 1, 1);
		// slet skærm
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// tegn figur
		batch.begin();
		bold.draw(batch, 1);
		kasse.draw(batch,1);
		batch.end();

	}
	
	@Override
	public void dispose () {
		boldTexture.dispose();
		kasseTexture.dispose();
		explosionTexture.dispose();
		batch.dispose();
	}
}
