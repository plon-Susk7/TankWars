package players;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public interface Player {
    abstract public Body getBody();
    abstract void moveLeft(Body x);
    abstract void moveRight(Body x);
    abstract void render(SpriteBatch batch);
    abstract void dispose();
}
