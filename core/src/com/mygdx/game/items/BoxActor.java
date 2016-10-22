package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.GameWorld;
import com.mygdx.game.HopboldResources;

/**
 * Created by Søren on 22-10-2016.
 */

public class BoxActor extends Image {
    private Body body;
    private HopboldResources resources;


    public BoxActor(GameWorld gameWorld, Vector2 position, float width, float height, HopboldResources resources) {
        super(resources.getKasseTexture());
        this.resources = resources;

        this.body = gameWorld.addRectangle(position, width, height);
        body.setUserData(this);

        // Order matters
        setAlign(Align.center);
        setSize(width, height);
        setOrigin(Align.center);
    }

    public void updateActor(float delta) {
        // Update actor pose from body
        Vector2 position = body.getPosition();
        setPosition(position.x, position.y, Align.center);
        setRotation(MathUtils.radiansToDegrees * body.getAngle());
    }

    public void dispose() {
        body.getWorld().destroyBody(body);
    }
}
