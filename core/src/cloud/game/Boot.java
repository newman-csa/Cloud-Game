package cloud.game;

import cloud.game.levels.Level1;
import cloud.game.levels.LevelSelect;
import cloud.game.levels.MainMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import static cloud.utils.Constants.UNIT_SCALE;

public class Boot extends Game {
	public SpriteBatch batch;
	public OrthographicCamera camera;
	public BitmapFont font12;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(32*UNIT_SCALE, 18*UNIT_SCALE);
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("NovaSquare-Regular.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 35;
		font12 = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!



		this.setScreen(new MainMenu(this));

	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		font12.dispose();
	}
}
