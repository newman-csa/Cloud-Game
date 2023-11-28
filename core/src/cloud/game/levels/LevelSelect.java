package cloud.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import cloud.game.Boot;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;

public class LevelSelect implements Screen {
    final Boot boot;

    public LevelSelect(final Boot boot){
        this.boot = boot;
    }

    //implement mouse tracking and add level select here.
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0.3f,0.7f,1);
        boot.batch.begin();
        boot.font.getData().setScale(2);
        boot.font.draw(boot.batch, "Level 1", 100, 350);
        Rectangle level1Rect = new Rectangle("Level 1".length()*20,200,150,50);
        //sorry I gave up on tables
        //rectangles are the only thing I know
        //all is rectangles
        boot.batch.end();

        Rectangle mouseRect = new Rectangle(Gdx.input.getX(), Gdx.input.getY(), 1, 1);

        if(Gdx.input.isTouched() && mouseRect.overlaps(level1Rect)) {
            boot.setScreen(new Level1(boot));
            dispose();
        }
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
