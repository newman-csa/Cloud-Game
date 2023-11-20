package cloud.game.levels;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import cloud.game.Boot;
import cloud.game.utils.AssetsLoader;

public class Level1 implements Screen {
    private final Boot boot;
    private final AssetsLoader assetsLoader;
    private OrthogonalTiledMapRenderer mapRenderer;
    private TiledMap map;
    private float unitScale;

    public Level1(final Boot boot) {
        this.boot = boot;

        assetsLoader = new AssetsLoader();
        map = assetsLoader.loadMap("level1/level1.tmx");
        unitScale = 1 / 64f;
        mapRenderer = new OrthogonalTiledMapRenderer(map, unitScale);

        boot.camera.setToOrtho(false, 20, 15);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0.5f, 1, 1);
        boot.batch.setProjectionMatrix(boot.camera.combined);

        mapRenderer.setView(boot.camera);
        mapRenderer.render();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        assetsLoader.assetManager.clear();
    }
}
