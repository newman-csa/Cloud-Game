package cloud.game.levels;

import cloud.game.entities.Player;
import cloud.utils.B2dModel;
import cloud.utils.TiledMapUtils;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import cloud.game.Boot;
import com.badlogic.gdx.utils.ScreenUtils;

import static cloud.utils.Constants.UNIT_SCALE;

public class Level1 implements Screen {
    private final Boot boot;
    private final Player player;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final Box2DDebugRenderer debugRenderer;
    private final B2dModel model;
    private final TiledMap map;

    // TODO: Test variables to be put in a different class

    public Level1(final Boot boot) {
        this.boot = boot;

        // Load Map and Collision
        map = boot.assetsLoader.loadMap("level1/level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / UNIT_SCALE);

        // Load All Hit-boxes and Boundaries for Map
        model = new B2dModel();
        TiledMapUtils mapUtils = new TiledMapUtils(map, model);
        mapUtils.parseMapObjects();

        // Load Hit Boxes for Player
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1/2f ,1/2f);
        Body body = model.createDynamicBody(shape, 10f, 10f);
        player = new Player(body, 10f, 5f);

        // Load Boundary for the Floor
        shape.setAsBox(100f, 0.5f);
        model.createStaticBody(shape,-10, -10);
        shape.dispose();

        // DEBUG CODE
        debugRenderer = new Box2DDebugRenderer(true,true,true,true,false,true);
    }

    private void update(float delta) {
        // Update Physics and Clear Screen for Next Frame
        ScreenUtils.clear(0f, 0f, 0.5f, 1f);
        model.logicStep(delta);
        boot.camera.update();
        player.update();

        // Update Camera Position
        Vector3 position = boot.camera.position;
        position.x = Math.round(player.getBody().getPosition().x * 100f) / 100f;
        position.y = Math.round(player.getBody().getPosition().y * 100f) / 100f ;
        boot.camera.position.set(position);
        boot.camera.update();

    }

    @Override
    public void render(float delta) {
        update(delta);

        mapRenderer.setView(boot.camera);
        mapRenderer.render();

        // DEBUG CODE
        debugRenderer.render(model.getWorld(), boot.camera.combined);
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
        boot.assetsLoader.assetManager.clear();
        map.dispose();
        mapRenderer.dispose();
        debugRenderer.dispose();
        model.disposeWorld();
    }
}
