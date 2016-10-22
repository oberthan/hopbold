package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.GameWorld;

/**
 * Created by Søren on 22-10-2016.
 */

public class RectangleActor extends Image {
    private Body body;


    public RectangleActor(GameWorld gameWorld, Vector2 position, float width, float height, Texture texture) {
        super(texture);

        this.body = gameWorld.addRectangle(position, width, height);

        // Order matters
        setAlign(Align.center);
        setSize(width, height);
        setOrigin(Align.center);


    }

    public void updateActor() {
        Vector2 position = body.getPosition();
        setPosition(position.x, position.y, Align.center);
        setRotation(MathUtils.radiansToDegrees * body.getAngle());
    }
}
