package cloud.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static cloud.utils.Constants.UNIT_SCALE;

/**
 * Utilities for the creation of hit-boxes in our tiled maps.
 * Unironically took me 8 hours on a weekend :thumbs_up:
 *
 * @author newman-csa
 */
public class TiledMapUtils {

    private final TiledMap map;
    private final B2dModel model;

    public TiledMapUtils(TiledMap map, B2dModel model) {
        this.map = map;
        this.model = model;
    }

    /**
     * Creates static/kinematic boxes for all the objects in the collision layer
     */
    public void parseMapObjects() {
        MapObjects mapObjects = map.getLayers().get("collision").getObjects();

        // Parse World Boundaries
        for (MapObject mapObject : mapObjects) {
            if (mapObject instanceof PolygonMapObject) {
                //TODO: Find the world coords of the shape
                //TODO: Find the world shape of the object
                //float worldXCoord =
                //float worldYCoord =

                model.createStaticBody(toPolygonShape((PolygonMapObject) mapObject), 0f, 0f);

            } else if (mapObject instanceof RectangleMapObject) {
                RectangleMapObject rectangleMapObject = (RectangleMapObject) mapObject;
                //Find the world coodinates of the rectangle
                float worldXCoord = (rectangleMapObject.getRectangle().x
                        + rectangleMapObject.getRectangle().width / 2) / UNIT_SCALE;
                float worldYCoord = (rectangleMapObject.getRectangle().y
                        + rectangleMapObject.getRectangle().height / 2) / UNIT_SCALE;

                //Create new static body at world coordinates.
                model.createStaticBody(toPolygonShape(rectangleMapObject), worldXCoord, worldYCoord);
            }
        }

        mapObjects = map.getLayers().get("objects").getObjects();

        // Hit-boxes for flag
        for (MapObject mapObject : mapObjects) {
            if(mapObject.getName().equals("flag")) {

            } else if (mapObject.getName().equals("coin")) {

            }  else {
                System.out.println("Something's Gone Wrong");
            }

        }

    }

    /**TODO: this, so we can make triangles if we want, though not really needed
     * Takes a polygonMapObject and turns it into a PolygonShape with in the size of world units.
     *
     * @param polygonMapObject
     * @return PolygonShape representation of polygonMapObject
     */
    public PolygonShape toPolygonShape(PolygonMapObject polygonMapObject) {
        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; i++) {
            Vector2 current = new Vector2(vertices[i * 2] / Constants.UNIT_SCALE, vertices[i * 2 + 1] / Constants.UNIT_SCALE);
            worldVertices[i] = current;
        }

        PolygonShape shape = new PolygonShape();
        shape.set(worldVertices);
        return shape;
    }

    /**
     * Takes a RectangleMapObject and turns it into a PolygonShape with in the size of world units.
     *
     * @param rectangleMapObject
     * @return PolygonShape representation of rectangleMapObject
     */
    public PolygonShape toPolygonShape(RectangleMapObject rectangleMapObject) {
        // Must convert to world units for it align properly
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(rectangleMapObject.getRectangle().width / Constants.UNIT_SCALE / 2,
                rectangleMapObject.getRectangle().height / Constants.UNIT_SCALE / 2);
        return shape;
    }
}
