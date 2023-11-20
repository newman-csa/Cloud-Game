package cloud.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import cloud.game.levels.Level1;
import cloud.game.levels.Level1In16Bit;
import cloud.game.utils.AssetsLoader;

public class Boot extends Game {
	public SpriteBatch batch;
	public OrthographicCamera camera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();


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
