package cloud.game.levels;

import cloud.game.utils.B2dModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import cloud.game.Boot;
import cloud.game.sprites.Player;
import cloud.game.utils.AssetsLoader;

import static cloud.game.utils.Constants.GRAVITY;
import static cloud.game.utils.Constants.UNIT_SCALE;

public class Level1In16Bit implements Screen {
    private final Boot boot;
    private final AssetsLoader assetsLoader;
    private OrthogonalTiledMapRenderer mapRenderer;
    private Box2DDebugRenderer debugRenderer;
    private B2dModel model;
    private TiledMap map;
    //private Player player;

    public Level1In16Bit(final Boot boot) {
        this.boot = boot;

        assetsLoader = new AssetsLoader();
        map = assetsLoader.loadMap("level1In16Bit/level1In16Bit.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, UNIT_SCALE);

        //boot.camera.setToOrtho(false, 32, 24);
        //boot.camera.position.set((new Vector3(-10f, -10f, 0f)));

        model = new B2dModel();
        debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);

    }

    @Override
    public void render(float delta) {
        //ScreenUtils.clear(0f, 0f, 0f, 1f);
        //boot.batch.setProjectionMatrix(boot.camera.combined);
        //boot.camera.update();

        model.logicStep(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(model.world, boot.camera.combined);

        mapRenderer.setView(boot.camera);
        mapRenderer.render();
        //player.update(Gdx.graphics.getDeltaTime());
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
        //assetsLoader.assetManager.clear();
        mapRenderer.dispose();
    }
}
