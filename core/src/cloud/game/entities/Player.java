package cloud.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import cloud.utils.B2dModel;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static cloud.utils.Constants.UNIT_SCALE;

public class Player extends Entity {
    private final float DAMPING = 0.83f;
    private boolean isGrounded;

    public Player(Body body, float xCoord, float yCoord) {
        // https://www.geeksforgeeks.org/difference-between-super-and-super-in-java-with-examples/
        super(body, xCoord, yCoord);
        this.speedRate = 10f;
        isGrounded = true;

    }

    @Override
    public void update() {
        userInput();
        xCoord = body.getPosition().x * UNIT_SCALE;
        yCoord = body.getPosition().y * UNIT_SCALE;
    }

    private void userInput() {
        // The 0 makes it not quadratic
        xVel = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            xVel = 1f;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            xVel = -1f;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W) && isGrounded) {
            float force = body.getMass() * 500;
            body.applyForce(new Vector2(0, force), body.getPosition(), true);
            isGrounded = false;
        }

        // This is just wrong but I'll fix later
        if(Math.abs(body.getLinearVelocity().y) <= 0) {
            isGrounded = true;
        }

        body.setLinearVelocity(xVel * speedRate, body.getLinearVelocity().y);
    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
