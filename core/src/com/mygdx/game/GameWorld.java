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
    private final Body floor;
    private final Body ceiling;
//    private final Body leftWall;
//    private final Body rightWall;
    private float accumulator = 0;

    private static final int GRAVITY = 30;
    Vector2 gravityVector = new Vector2(0, -GRAVITY);
    World world = new World(gravityVector, true);


    private float viewX;
    private float viewY;
    private float viewWidth;
    private float viewHeight;

    public GameWorld(float viewWidth, float viewHeight) {
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;

        floor = addWall(new Vector2(-10000, 0), new Vector2(viewWidth+10000, 0));
        ceiling = addWall(new Vector2(0, viewHeight), new Vector2(viewWidth, viewHeight));
//        leftWall = addWall(new Vector2(-100, 0), new Vector2(0, viewHeight));
//        rightWall = addWall(new Vector2(viewWidth, 0), new Vector2(viewWidth, viewHeight));


        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                beginBodyContact(contact.getFixtureA().getBody());
                beginBodyContact(contact.getFixtureB().getBody());
            }

            // called twice with A/B swapped
            private void beginBodyContact(Body bodyA) {
                Object userDataA = bodyA.getUserData();
                if (userDataA instanceof BallActor)
                {
                    ((BallActor) userDataA).beginContact();
                }
            }

            @Override
            public void endContact(Contact contact) {
                endBodyContact(contact.getFixtureA().getBody());
                endBodyContact(contact.getFixtureB().getBody());
            }

            // called twice with A/B swapped
            private void endBodyContact(Body bodyA) {
                Object userDataA = bodyA.getUserData();
                if (userDataA instanceof BallActor)
                {
                    ((BallActor) userDataA).endContact();
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

    private Body addWall(Vector2 p1, Vector2 p2)
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
        return body;
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
        return viewWidth;
    }

    public float getHeight() {
        return viewHeight;
    }

    public float getViewX() {
        return viewX;
    }

    public void setViewX(float viewX) {
        this.viewX = viewX;
//        floor.setTransform(viewX, 0, 0);
//        ceiling.setTransform(viewX, 0, 0);
//        leftWall.setTransform(viewX, 0, 0);
//        rightWall.setTransform(viewX, 0, 0);
    }

    public float getViewY() {
        return viewY;
    }

    public void setViewY(float viewY) {
        this.viewY = viewY;
    }

    public Vector2 getInputPosition() {
        float x = Gdx.input.getX();
        float y = Gdx.graphics.getHeight()-Gdx.input.getY();
        float worldX = viewX + viewWidth * x / Gdx.graphics.getWidth();
        float worldY = viewY + viewHeight * y / Gdx.graphics.getHeight();
        return new Vector2(worldX, worldY);
    }
}
