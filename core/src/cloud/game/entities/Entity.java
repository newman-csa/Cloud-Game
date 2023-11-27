package cloud.game.entities;

import com.badlogic.gdx.physics.box2d.Body;

public abstract class Entity {
    protected float xCoord;
    protected float yCoord;
    protected float width;
    protected float height;
    protected Body body;

    public void setup() {}
    public void update() {}
}
