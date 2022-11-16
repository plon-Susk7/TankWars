package players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

public class Player {

    private static BodyDef bodyDef;
    private static Body body;
    private static Texture texture;

    public Player(World world){
        texture = new Texture("Frost.png");
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(100,100);
        bodyDef.fixedRotation = true;
        body = world.createBody(bodyDef);
    }

    public Body getBody(){
        return body;
    }

    public void moveLeft(Body x){
            System.out.println("LEFT PRESSED");
            x.setLinearVelocity(-50,0);
    }

    public void moveRight(Body x){
            System.out.println("Right PRESSED");
            x.setLinearVelocity(50,0);
    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(texture,body.getPosition().x-16,body.getPosition().y-16);
        batch.end();
    }

    public void dispose(){
        texture.dispose();
    }

}
