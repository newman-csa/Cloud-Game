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
        boot.font12.draw(boot.batch, "Level 1", 100, 350);
        Rectangle level1Rect = new Rectangle("Level 1".length()*30,200,150,50);

        boot.font12.draw(boot.batch, "Level 2", 350, 350);
        Rectangle level2Rect = new Rectangle(600,200,"Level 2".length()*20,50);

        boot.font12.draw(boot.batch, "Level 3", 600, 350);
        Rectangle level3Rect = new Rectangle(1000,200,"Level 3".length()*20,50);

        //sorry I gave up on tables
        //rectangles are the only thing I know
        //all is rectangles
        boot.batch.end();

        Rectangle mouseRect = new Rectangle(Gdx.input.getX(), Gdx.input.getY(), 1, 1);

        if(Gdx.input.isTouched() && mouseRect.overlaps(level1Rect)) {
            //goes to level 1
            boot.setScreen(new Level1(boot));
            dispose();
        }else if(Gdx.input.isTouched() && mouseRect.overlaps(level2Rect)) {
            //goes to level 2
            boot.setScreen(new Level1(boot));
            dispose();
        }else if(Gdx.input.isTouched() && mouseRect.overlaps(level3Rect)) {
            //goes to level 3
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
