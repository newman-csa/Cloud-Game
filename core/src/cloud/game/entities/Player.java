package cloud.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player extends Entity {

    public Player() {
        // https://www.geeksforgeeks.org/difference-between-super-and-super-in-java-with-examples/
        super();

    }

    @Override
    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {

        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {

        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {

        }
    }

    @Override
    public void render() {

    }
}
