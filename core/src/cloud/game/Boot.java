package cloud.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import cloud.game.levels.Level1In16Bit;

import static cloud.game.utils.Constants.UNIT_SCALE;

public class Boot extends Game {
	public SpriteBatch batch;
	public OrthographicCamera camera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(32*UNIT_SCALE, 18*UNIT_SCALE);


		this.setScreen(new Level1In16Bit(this));

	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
