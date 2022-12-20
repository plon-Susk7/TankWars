package players;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import missiles.MissileA;

public interface Player {
    abstract public Body getBody();
    abstract void moveLeft(Body x);
    abstract void moveRight(Body x);
    abstract void render(SpriteBatch batch);

    abstract  MissileA shoot(World world,int strength,int angle,float currentXPosition,float currentYPosition);

    abstract public int getHealthPoints();
    abstract  public void setHealthPoints(int x);



    abstract void dispose();
}
