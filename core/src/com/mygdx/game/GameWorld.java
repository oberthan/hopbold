package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.items.BallActor;

/**
 * Created by Søren on 22-10-2016.
 */

public class GameWorld {
    private static final float TIME_STEP = 1 / 45f;
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;
    private float accumulator = 0;

    private static final int GRAVITY = 30;
    Vector2 gravityVector = new Vector2(0, -GRAVITY);
    World world = new World(gravityVector, true);


    private float worldWidth;
    private float worldHeight;

    public GameWorld(float worldWidth, float worldHeight) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;

        addWall(new Vector2(0, 0), new Vector2(worldWidth, 0));
        addWall(new Vector2(0, 0), new Vector2(0, worldHeight));
        addWall(new Vector2(worldWidth, 0), new Vector2(worldWidth, worldHeight));
        addWall(new Vector2(0, worldHeight), new Vector2(worldWidth, worldHeight));


        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Object userDataA = contact.getFixtureA().getBody().getUserData();
                Object userDataB = contact.getFixtureB().getBody().getUserData();
                if (userDataA instanceof BallActor)
                {
                    ((BallActor) userDataA).beginContact();
                }
                if (userDataB instanceof BallActor)
                {
                    ((BallActor) userDataB).beginContact();
                }
            }

            @Override
            public void endContact(Contact contact) {
                Object userDataA = contact.getFixtureA().getBody().getUserData();
                Object userDataB = contact.getFixtureB().getBody().getUserData();
                if (userDataA instanceof BallActor)
                {
                    ((BallActor) userDataA).endContact();
                }
                if (userDataB instanceof BallActor)
                {
                    ((BallActor) userDataB).endContact();
                }

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

    }

    public void render(float deltaTime) {
        // fixed time step
        // max frame time to avoid spiral of death (on slow devices)
        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        while (accumulator >= TIME_STEP) {
            world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
            accumulator -= TIME_STEP;
        }
    }

    private void addWall(Vector2 p1, Vector2 p2)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(0, 0));
        bodyDef.angularDamping = 0.0f;
        bodyDef.linearDamping = 0.0f;
        Body body = world.createBody(bodyDef);
        EdgeShape shape = new EdgeShape();
        shape.set(p1, p2);
        body.createFixture(shape, 0);
        shape.dispose();

    }

    public Body addBall(Vector2 position, float radius) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        bodyDef.angularDamping = 0.0f;
        bodyDef.linearDamping = 0.0f;
        Body body = world.createBody(bodyDef);
        CircleShape ballShape = new CircleShape();
        ballShape.setRadius(radius);
        Fixture fixture = body.createFixture(ballShape, 0.5f /* density */);
        fixture.setFriction(2f);
        fixture.setRestitution(0.7f);
        ballShape.dispose();
        return body;
    }

    public Body addRectangle(Vector2 position, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        bodyDef.angularDamping = 0.0f;
        bodyDef.linearDamping = 0.0f;
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2, height/2);
        Fixture fixture = body.createFixture(shape, 0.5f /* density */);
        fixture.setFriction(2f);
        fixture.setRestitution(0.7f);
        shape.dispose();
        return body;
    }

    public float getWidth() {
        return worldWidth;
    }

    public float getHeight() {
        return worldHeight;
    }

    public Vector2 getInputPosition() {
            float x = Gdx.input.getX();
            float y = Gdx.graphics.getHeight()-Gdx.input.getY();
            float worldX = worldWidth * x / Gdx.graphics.getWidth();
            float worldY = worldHeight * y / Gdx.graphics.getHeight();
            return new Vector2(worldX, worldY);
    }
}
