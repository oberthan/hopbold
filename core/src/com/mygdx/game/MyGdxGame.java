package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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

    Sound bangLyd;

	@Override
	public void create () {
		batch = new SpriteBatch();
		boldTexture = new Texture("bold.png");
		kasseTexture = new Texture("kasse.png");
		explosionTexture = new Texture("explosion.png");

		bold = new Image(boldTexture);
        bold.setSize(50f, 50f);
		kasse = new Image(kasseTexture);
        kasse.setSize(80f, 80f);
        bangLyd = Gdx.audio.newSound(Gdx.files.internal("bang.wav"));
	}

	float bold_x = 0;
	float bold_y = 0;
	float boldrotation = 0;

	float kassex = 640;

	boolean hopperOp = false;

	boolean boldenErIkkeRamt = true;

	@Override
	public void render () {

		boolean skærmTrykket = Gdx.input.isTouched();
		boolean mellemrumTrykket = Gdx.input.isKeyPressed(Input.Keys.SPACE);
		boolean hopKnapTrykket = skærmTrykket || mellemrumTrykket;

		if (hopKnapTrykket && bold_y == 0)
		{
			hopperOp = true;
		}

		if (hopperOp)
		{
			bold_y = bold_y + 12;
		}
		else
		{
			bold_y = bold_y - 12;
		}

		if (bold_y > 300)
		{
			hopperOp = false;
		}
		if (bold_y < 0)
		{
			bold_y = 0;
		}
		// Vinduet er 640 x 480
		//  y 480
		//  ---------------
		//  \             \
		//  \             \
		// 0---------------  x 640

		// Flyt bold
		bold.setPosition(bold_x, bold_y);
		bold.setOrigin(Align.center);
		bold.setRotation(boldrotation);

		if (bold_x < 10) bold_x = bold_x + 1;
		if (boldenErIkkeRamt) boldrotation = boldrotation - 4;

		// Flyt kasse
		kasse.setPosition(kassex,0);
		kassex = kassex -6;

        if (kassex + kasse.getWidth() < 0)kassex =640;

        Rectangle boldOmkreds = new Rectangle(bold.getX()+10, bold.getY()+10, bold.getWidth()-20, bold.getHeight()-20);
        Rectangle kasseOmkreds = new Rectangle(kasse.getX(), kasse.getY(), kasse.getWidth(), kasse.getHeight());

		// Rammer bold kassen?
		if (boldenErIkkeRamt && Intersector.overlaps(boldOmkreds, kasseOmkreds))
		{
			// Sæt boldtegning til eksplosion
			bold.setDrawable(new TextureRegionDrawable(new TextureRegion(explosionTexture)));
			boldenErIkkeRamt =false;
            bangLyd.play();
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
