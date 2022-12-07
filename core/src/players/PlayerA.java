package players;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class PlayerA implements Player{


    private static BodyDef bodyDef;
    private static Body body;
    private static Texture texture;

    public PlayerA(World world,Texture tex){
        texture = tex;
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(100,500);
        bodyDef.fixedRotation = true;
        body = world.createBody(bodyDef);
    }

    @Override
    public Body getBody(){
        return body;
    }

    @Override
    public void moveLeft(Body x){
        System.out.println("LEFT PRESSED");
        x.setLinearVelocity(-50,0);
    }

    @Override
    public void moveRight(Body x){
        System.out.println("Right PRESSED");
        x.setLinearVelocity(50,0);
    }

    @Override
    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(texture,body.getPosition().x-50,body.getPosition().y-28,texture.getWidth()/4,texture.getHeight()/4);
        batch.end();
    }

    @Override
    public void dispose(){
        texture.dispose();
    }

}
