package cloud.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import cloud.game.Boot;
import cloud.game.sprites.Player;
import cloud.game.utils.AssetsLoader;

import static cloud.game.utils.Constants.UNIT_SCALE;

public class Level1In16Bit implements Screen {
    private final Boot boot;
    private final AssetsLoader assetsLoader;
    private OrthogonalTiledMapRenderer mapRenderer;
    private TiledMap map;
    private Player player;

    public Level1In16Bit(final Boot boot) {
        this.boot = boot;

        assetsLoader = new AssetsLoader();
        map = assetsLoader.loadMap("level1In16Bit/level1In16Bit.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, UNIT_SCALE);

        boot.camera.setToOrtho(false, 20, 15);

        player = new Player(boot, 3f, 5f);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0.5f, 1, 1);
        boot.batch.setProjectionMatrix(boot.camera.combined);
        boot.batch.begin();
        player.render();
        boot.batch.end();

        boot.camera.position.x = player.getPosition().x;
        boot.camera.position.y = player.getPosition().y;
        boot.camera.update();

        boot.camera.position.set(player.getPosition(),0);
        mapRenderer.setView(boot.camera);
        mapRenderer.render();

        float deltaTime = Gdx.graphics.getDeltaTime();
        player.update(deltaTime);

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
        player.playerTexture.dispose();
        mapRenderer.dispose();
    }
}