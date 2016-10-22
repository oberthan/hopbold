package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.GameWorld;

/**
 * Created by Søren on 22-10-2016.
 */

public class BallActor extends Image {
    private Body body;


    public BallActor(GameWorld gameWorld, Vector2 position, float radius, Texture texture) {
        super(texture);

        this.body = gameWorld.addBall(position, radius);

        // Rotate
        this.body.setAngularVelocity(-10);

        // Order matters
        setAlign(Align.center);
        setSize(radius*2, radius*2);
        setOrigin(Align.center);


    }

    public void updateActor() {
        Vector2 position = body.getPosition();
        setPosition(position.x, position.y, Align.center);
        setRotation(MathUtils.radiansToDegrees * body.getAngle());
    }
}
