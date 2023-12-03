package cloud.game;


import cloud.game.levels.Level1;
import cloud.utils.AssetsLoader;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;


public class Boot extends Game {
	public OrthographicCamera camera;
	public AssetsLoader assetsLoader;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		assetsLoader = new AssetsLoader();

		camera.setToOrtho(false, 32f , 18f);


		this.setScreen(new Level1(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		assetsLoader.assetManager.dispose();

	}
}
