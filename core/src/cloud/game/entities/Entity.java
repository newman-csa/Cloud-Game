package cloud.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class Entity {
    protected float xCoord, yCoord, width, height;
    protected float xVel, yVel, speedRate;
    protected Body body;

    public Entity(Body body, float xCoord, float yCoord) {
        this.body = body;
        this.xCoord = xCoord;
        this.yCoord = yCoord;

        // From 1 to 0
        this.xVel = 0;
        this.yVel = 0;
        this.speedRate = 0;
    }
    public abstract void update();
    public abstract void render(SpriteBatch batch);

    public Body getBody() {
        return body;
    }
}
