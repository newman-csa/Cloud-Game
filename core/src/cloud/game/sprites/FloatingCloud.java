package cloud.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import cloud.game.Boot;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;

public class FloatingCloud {
    private final Boot boot;
    private Texture flyingEnemyTexture;
    private Sound attackSound;
    private Array<Rectangle> Enemies;

    public FloatingCloud(Boot boot) {
        this.boot = boot // You can call batch and other redundant object from boot 
    }

    private void moveEnemy(){}
}
