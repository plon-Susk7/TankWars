package missiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

public class MissileA implements Missile {
    public static BodyDef missileDef;
    public static Body missileBody;
    private static Texture texture;

    private static FixtureDef missileFixture;

    public MissileA(World world, Body playerBody){
        texture = new Texture("bullet.png");
        missileDef = new BodyDef();
        missileDef.type = BodyDef.BodyType.DynamicBody;
        missileDef.position.set(playerBody.getPosition().x+20, playerBody.getPosition().y+10);
        missileDef.fixedRotation = false;
        missileBody= world.createBody(missileDef);
        missileBody.setUserData("MissileA");
        CircleShape circle = new CircleShape();
        circle.setRadius(10f);

        missileFixture = new FixtureDef();
        missileFixture.shape = circle;
        missileFixture.density = 0.5f;
        missileFixture.friction = 1f;

    }

    public FixtureDef getFixture(){
        return missileFixture;
    }

    @Override
    public void launchMissile(Body body){
        body.setLinearVelocity(Gdx.input.getX(),640 - Gdx.input.getY());
    }

    @Override
    public void render(SpriteBatch batch,Body playerBody){
        batch.begin();
        batch.draw(texture, missileBody.getPosition().x,missileBody.getPosition().y);
        batch.end();
    }

    public void destroyBody(World world){
        world.destroyBody(missileBody);
    }

    @Override
    public void update(float delta){
        missileBody.getPosition().x = missileBody.getLinearVelocity().x*delta;
        missileBody.getPosition().y = missileBody.getLinearVelocity().y*delta;
        //System.out.println(missileBody.getPosition());
    }

    @Override
    public Body getMissileBody(){
        return missileBody;
    }

    @Override
    public void dispose(){
        texture.dispose();
    }
}
