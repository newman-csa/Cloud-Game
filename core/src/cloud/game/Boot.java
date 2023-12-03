package cloud.game;


import cloud.game.levels.Level1;
import com.badlogic.gdx.Game;



public class Boot extends Game {

	@Override
	public void create () {
		this.setScreen(new Level1());
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {

	}
}
