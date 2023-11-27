package cloud.game;

import cloud.game.levels.Level1;
import cloud.game.levels.MainMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static cloud.utils.Constants.UNIT_SCALE;

public class Boot extends Game {
	public SpriteBatch batch;
	public OrthographicCamera camera;
	public BitmapFont font;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(32*UNIT_SCALE, 18*UNIT_SCALE);
		font = new BitmapFont();


		this.setScreen(new MainMenu(this));

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
