package cloud.game.levels;

import cloud.game.Boot;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import static java.lang.System.exit;

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
        ScreenUtils.clear(0,0.3f,0.7f,1);

        camera.update();
        boot.batch.setProjectionMatrix(camera.combined);

        boot.batch.begin();
        //yippie I found this
//        boot.font.getData().setScale(2);
        boot.font.draw(boot.batch, "Welcome to Skyline!", 250, 350);
//        boot.font.draw(boot.batch, "Click anywhere to begin", 240, 300);
        boot.font.draw(boot.batch, "Level Select", 50, 250);
        Rectangle rectLevelSelect = new Rectangle(50,330,250,50);
        boot.font.draw(boot.batch, "Quit Game", 600, 250);
        Rectangle rectQuit = new Rectangle(950,330,250,50);
        boot.batch.end();


        Rectangle mouseRect = new Rectangle(Gdx.input.getX(), Gdx.input.getY(), 1, 1);

        if(Gdx.input.isTouched() && mouseRect.overlaps(rectLevelSelect)){
            boot.setScreen(new LevelSelect(boot));
            dispose();
        }else if (Gdx.input.isTouched() && mouseRect.overlaps(rectQuit)){
            dispose();
            exit(0);
            //wow that actually worked very easily
        }
        Vector3 mousePos = new Vector3();
        mousePos.set(Gdx.input.getX(),Gdx.input.getY(),0);
        camera.unproject(mousePos);
        mouseRect.x = mousePos.x;
        mouseRect.y = mousePos.y;
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
