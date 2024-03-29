package missiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public interface Missile {
    abstract public FixtureDef getFixture();
    abstract public void launchMissile(Body body,float degrees,int strength,int flag);
    abstract public void render(SpriteBatch batch, Body playerBody);
    abstract public void update(float delta);
    abstract  public Body getMissileBody();

    abstract public void dispose();
}
