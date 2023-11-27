package cloud.game.levels;

import cloud.game.Boot;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenu implements Screen{
    final Boot boot;
    OrthographicCamera camera;
    public MainMenu(final Boot boot){
        this.boot = boot;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);
    }

    @Override
    public void render(float delta){
        //TODO:change these colors
        ScreenUtils.clear(0,0,0.2f,1);

        camera.update();
        boot.batch.setProjectionMatrix(camera.combined);

        boot.batch.begin();
        boot.font.draw(boot.batch, "Welcome to Skyline!", 100, 50);
        boot.font.draw(boot.batch, "Click anywhere to begin", 100, 100);
        boot.batch.end();

        if(Gdx.input.isTouched()){
            boot.setScreen(new Level1(boot));
            dispose();
        }
    }


    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
