package cloud.game;

import cloud.game.levels.LevelSelect;
import com.badlogic.gdx.physics.box2d.*;

public class B2dContactListener implements ContactListener {

    private B2dModel parent;
    Boot boot;

    public B2dContactListener(B2dModel parent, final Boot boot){
        this.parent = parent;
        this.boot = boot;
    }

    @Override
    public void beginContact(Contact contact) {
        System.out.println("Contact");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        System.out.println(fa.getBody().getType()+" has hit "+ fb.getBody().getType());
        if(fa.getDensity()==0.1f || fb.getDensity()==0.1f){
            System.out.println("Flag Touched");
            fa.getBody().setUserData("Flag");
            boot.setScreen(new LevelSelect(boot));
        }else if(fa.getDensity()==0.2f){
            System.out.println("Coin Touched");
            fa.getBody().setUserData("Coin");
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

}
