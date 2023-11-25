package cloud.game.levels;

import cloud.utils.B2dModel;
import cloud.utils.TiledMapUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import cloud.game.Boot;
import cloud.utils.AssetsLoader;

import static cloud.utils.Constants.UNIT_SCALE;

public class Level1 implements Screen {
    private final Boot boot;
    private final AssetsLoader assetsLoader;
    private final TiledMapUtils mapUtils;
    private OrthogonalTiledMapRenderer mapRenderer;
    private Box2DDebugRenderer debugRenderer;
    private B2dModel model;
    private TiledMap map;

    // TODO: Test variables to be put in a different class
    private Body bodyD;

    public Level1(final Boot boot) {
        this.boot = boot;

        // Load Map and Collision
        assetsLoader = new AssetsLoader();
        map = assetsLoader.loadMap("level1/level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1/UNIT_SCALE);
        model = new B2dModel();
        mapUtils = new TiledMapUtils(map, model);
        mapUtils.parseMapObjects();

        // Debug shape creation of different body types
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16 / UNIT_SCALE / 2, 16 / UNIT_SCALE / 2);
        bodyD = model.createDynamicBody(shape, 10f, 5f);
        Body bodyK = model.createKinematicBody(shape, -0.75f, 6f);
        bodyK.setLinearVelocity(0, -1f);
        shape.setAsBox(50f, 0.5f);
        model.createStaticBody(shape,0, -10);
        shape.dispose();

        debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);

    }

    private void update(float delta) {
        // Update Physics and clear screen for next frame
        model.logicStep(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update camera's position
        Vector3 position = boot.camera.position;
        position.x = Math.round(bodyD.getPosition().x * UNIT_SCALE * 10f) / 10f;
        position.y = Math.round(bodyD.getPosition().y * UNIT_SCALE * 10f) / 10f;
        System.out.println(bodyD.getPosition().x);
        boot.camera.position.set(position);
        boot.camera.update();
    }

    @Override
    public void render(float delta) {
        update(delta);
        //boot.batch.setProjectionMatrix(boot.camera.combined);

        debugRenderer.render(model.getWorld(), boot.camera.combined.scl(UNIT_SCALE));

        // TODO: Camera does not move properly with player, causing the map to not render
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
        mapRenderer.dispose();
        model.disposeWorld();
    }
}
