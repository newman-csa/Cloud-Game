package cloud.game.utils;

import cloud.game.Boot;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static cloud.game.utils.Constants.UNIT_SCALE;

public class CreateBody {
    private World world;
    private Boot boot;

    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    public CreateBody(World world, Boot boot) {
        this.world = world;
        this.boot = boot;
    }

    public void parseMapObjects(MapObjects mapObjects) {
        for (MapObject mapObject : mapObjects) {
            if (mapObject instanceof PolygonMapObject) {
                createStaticBody((PolygonMapObject) mapObject);
            }
        }
    }

    public PolygonShape createPolygonShape(PolygonMapObject polygonMapObject) {
        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; i++) {
            Vector2 current = new Vector2(vertices[i * 2] / UNIT_SCALE, vertices[i * 2 + 1] / UNIT_SCALE);
            worldVertices[i] = current;
        }

        PolygonShape shape = new PolygonShape();
        shape.set(worldVertices);
        return shape;
    }

    public void createStaticBody(PolygonMapObject polygonMapObject) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        //WHY IS THIS LINE BEFORE THE DEFINITION OF THE POSITION VECTOR
        Body body = world.createBody(bodyDef);

        // TODO: MAKE THE COLLISION SPAWN AT THE RIGHT POINTS
        // * THIS IS THE POSITION OF THE BOXES
        Vector2 position = new Vector2(polygonMapObject.getProperties().get("x", Float.class) / UNIT_SCALE,
                polygonMapObject.getProperties().get("y", Float.class) / UNIT_SCALE);
        bodyDef.position.set(position);

        PolygonShape rect = createPolygonShape(polygonMapObject);
// Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rect;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit
        Fixture fixture = body.createFixture(fixtureDef);

        rect.dispose();
    }

    public void debugRender() {
        debugRenderer.render(world, boot.camera.combined.scl(UNIT_SCALE));
    }
}