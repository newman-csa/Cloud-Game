package cloud.game.levels;

import cloud.game.entities.Player;
import cloud.utils.B2dModel;
import cloud.utils.TiledMapUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import cloud.game.Boot;
import cloud.utils.AssetsLoader;
import com.badlogic.gdx.utils.ScreenUtils;

import static cloud.utils.Constants.UNIT_SCALE;

public class Level1 implements Screen {
    private final Player player;
    private final TiledMapUtils mapUtils;
    private OrthogonalTiledMapRenderer mapRenderer;
    private Box2DDebugRenderer debugRenderer;
    private B2dModel model;
    private TiledMap map;
    private OrthographicCamera camera;

    // TODO: Test variables to be put in a different class

    public Level1() {
        // Load Map and Collision
        camera = new OrthographicCamera();
        map = new AssetsLoader().loadMap("level1/level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / UNIT_SCALE);
        camera.setToOrtho(false, 32f , 18f);
        model = new B2dModel();
        mapUtils = new TiledMapUtils(map, model);
        mapUtils.parseMapObjects();

        // Debug shape creation of different body types and a player
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1/2f ,1/2f);
        Body body = model.createDynamicBody(shape, 10f, 10f);
        player = new Player(body, 10f, 5f);


        shape.setAsBox(50f, 0.5f);
        model.createStaticBody(shape,0, -10);
        shape.dispose();

        debugRenderer = new Box2DDebugRenderer(true,true,true,true,false,true);

    }

    private void update(float delta) {
        // Update Physics and clear screen for next frame
        Gdx.gl.glClearColor(0f, 0f, 0.5f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        model.logicStep(delta);
        camera.update();
        player.update();

        Vector3 position = camera.position;
        position.x = Math.round(player.getBody().getPosition().x * 100f) / 100f;
        position.y = Math.round(player.getBody().getPosition().y * 100f) / 100f ;
        camera.position.set(position);
        camera.update();


    }

    @Override
    public void render(float delta) {
        update(delta);

        mapRenderer.setView(camera);
        mapRenderer.render();
        //debugRenderer.render(model.getWorld(), camera.combined);
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
        map.dispose();
        mapRenderer.dispose();
        debugRenderer.dispose();
        model.disposeWorld();
    }
}
