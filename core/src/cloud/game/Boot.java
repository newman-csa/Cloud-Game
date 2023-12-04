package cloud.game;

import cloud.game.levels.Level1;
import cloud.game.levels.LevelSelect;
import cloud.game.levels.MainMenu;
import cloud.utils.AssetsLoader;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.physics.box2d.ContactListener;

import static cloud.utils.Constants.UNIT_SCALE;

public class Boot extends Game {
    public OrthographicCamera camera;
    public BitmapFont font12;
    public AssetsLoader assetsLoader;
    public SpriteBatch batch;

    @Override
    public void create() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("NovaSquare-Regular.ttf"));
        camera = new OrthographicCamera();
        assetsLoader = new AssetsLoader();
        batch = new SpriteBatch();

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 35;
        font12 = generator.generateFont(parameter);
        generator.dispose();

        camera.setToOrtho(false, 32f, 18f);

        this.setScreen(new MainMenu(this));

    }



    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font12.dispose();
        assetsLoader.assetManager.dispose();

    }
}
