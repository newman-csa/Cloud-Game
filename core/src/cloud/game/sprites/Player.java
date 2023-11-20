package my.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import my.game.Boot;

import static my.game.utils.Constants.GRAVITY;
import static my.game.utils.Constants.UNIT_SCALE;

public class Player {
    private final Boot boot;
    //Public for testing reasons, put this in AssetManager later
    public final Texture playerTexture;
    public float WIDTH;
    public float HEIGHT;
    public float MAX_VELOCITY = 10f;
    public float JUMP_VELOCITY = 40f;
    public float DAMPING = 0.87f;

    enum State {
        Standing, Walking, Jumping;
    }

    private Vector2 position = new Vector2();
    private Vector2 movementThisFrame = new Vector2();
    private Vector2 velocity = new Vector2();
    private State state;
    private float stateTime = 0;
    private boolean facesRight = true;
    private boolean grounded = false;

    public Player(final Boot boot, float xPos, float yPos) {
        this.boot = boot;

        position.set(xPos, yPos);
        state = State.Standing;
        // TODO: Put this in AssetManager later
        playerTexture = new Texture(Gdx.files.internal("DebugSquare16.png"));
    }

    public void render() {
        // the 16 should be replaced with the size of the character
        // this assumes that the player is always 16 by 16
        boot.batch.draw(playerTexture, position.x, position.y, UNIT_SCALE * 16, UNIT_SCALE * 16);
    }

    public void update(float deltaTime) {
        if (deltaTime == 0) return;

        if (deltaTime > 0.1f) deltaTime = 0.1f;

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            velocity.x += MAX_VELOCITY;
            facesRight = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            velocity.x -= MAX_VELOCITY;
            facesRight = false;
        }

        // Add gravity so we don't infinitely move up
        // Clamp velocity.x, so we don't gain infinite speed
        // velocity.y += GRAVITY;
        velocity.x = MathUtils.clamp(velocity.x, -MAX_VELOCITY, MAX_VELOCITY);

        // Set to zero if velocity is small enough so that we don't have a memory overload velocity.x
        if (Math.abs(velocity.x) < 0.01f) {
            velocity.x = 0;
            state = State.Standing;
        }

        // Multiplying the velocity by the time between frames
        // gives us the distance we move each frame (i.e. movementThisFrame)
        // that we then add to position
        movementThisFrame.set(velocity);
        movementThisFrame.scl(deltaTime);
        position.add(movementThisFrame);

        // Inverse the multiplication to give us back velocity for next loop
        System.out.println(movementThisFrame);
        // Dampen velocity.x, so we don't infinity move
        velocity.x *= DAMPING;
    }
}
