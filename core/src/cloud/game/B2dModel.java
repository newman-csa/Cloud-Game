
package cloud.game;

import cloud.game.B2dContactListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import cloud.game.Boot;

/**
 * Class for creating bodies and adding them to the same world instance
 *
 * @author newman-csa
 */
public class B2dModel {
    private World world;

    public B2dModel(Boot boot) {
        world = new World(new Vector2(0, -25f), true);
        world.setContactListener(new B2dContactListener(this, boot));
    }

    public World getWorld() {
        return world;
    }

    /**
     * Creates a static body given a PolygonShape for the amount of pixels an object is
     *
     * @param shape YOU ARE RESPONSIBLE FOR DISPOSING THIS SHAPE AFTER ITS USE.
     * @param xCoord The x-axis of the CENTER of the created object in world units.
     * @param yCoord The y-axis of the CENTER of the created object in world units.
     * @return A static body with the center coordinates of (xCoord, yCoord)
     */
    public Body createStaticBody(PolygonShape shape, float xCoord, float yCoord) {
        // create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(xCoord, yCoord);
        // add it to the world
        Body body = world.createBody(bodyDef);
        // Fixture Definition
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0f;
        body.createFixture(fixtureDef);
        return body;
    }

    public Body createStaticBody(PolygonShape shape, float xCoord, float yCoord, float density) {
        // create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(xCoord, yCoord);
        // add it to the world
        Body body = world.createBody(bodyDef);
        // Fixture Definition
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.friction = 0f;
        body.createFixture(fixtureDef);
        return body;
    }


    /**
     * Creates a dynamic body given a PolygonShape for the amount of pixels an object is
     *
     * @param shape YOU ARE RESPONSIBLE FOR DISPOSING THIS SHAPE AFTER ITS USE.
     * @param xCoord The x-axis of the CENTER of the created object in world units.
     * @param yCoord The y-axis of the CENTER of the created object in world units.
     * @return A dynamic body with the center coordinates of (xCoord, yCoord)
     */
    public Body createDynamicBody(PolygonShape shape, float xCoord, float yCoord) {
        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        bodyDef.position.set(xCoord, yCoord);
        // add it to the world
        Body body = world.createBody(bodyDef);
        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.restitution = 0f;
        body.createFixture(fixtureDef);
        return body;
    }

    /**
     * Creates a kinematic body given a PolygonShape for the amount of pixels an object is
     *
     * @param shape YOU ARE RESPONSIBLE FOR DISPOSING THIS SHAPE AFTER ITS USE.
     * @param xCoord The x-axis of the CENTER of the created object in world units.
     * @param yCoord The y-axis of the CENTER of the created object in world units.
     * @return A kinematic body with the center coordinates of (xCoord, yCoord)
     */
    public Body createKinematicBody(PolygonShape shape, float xCoord, float yCoord) {
        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(xCoord, yCoord);
        // add it to the world
        Body body = world.createBody(bodyDef);
        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        body.createFixture(fixtureDef);
        return body;
    }

    // our game logic here
    public void logicStep(float delta) {
        world.step(delta, 6, 2);
    }

    public void disposeWorld() {
        world.dispose();
    }
}