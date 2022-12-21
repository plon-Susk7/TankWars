package missiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

public class MissileA implements Missile {
    public static BodyDef missileDef;
    public static Body missileBody;
    private static Texture texture;

    private static FixtureDef missileFixture;

    public MissileA(World world, Body playerBody,int flag){
        texture = new Texture("bullet.png");
        missileDef = new BodyDef();
        missileDef.type = BodyDef.BodyType.DynamicBody;
        if(flag==0){
            missileDef.position.set(playerBody.getPosition().x+30, playerBody.getPosition().y+10);
        }else{
            missileDef.position.set(playerBody.getPosition().x-30, playerBody.getPosition().y+10);
        }

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


    public void setVelocity(Body body, float speed, float angle) {
        float r = (float) Math.toRadians(angle);
        float x = (float) (speed * Math.cos(r));
        float y = (float) (speed * Math.sin(r));
        body.setLinearVelocity(new Vector2(x, y));
    }

    public void setVelocity1(Body body, float speed, float angle) {
        float r = (float) Math.toRadians(angle);
        float x = (float) (speed * Math.cos(r));
        float y = (float) (speed * Math.sin(r));
        x = -x;
        body.setLinearVelocity(new Vector2(x, y));
    }
    @Override
    public void launchMissile(Body body,float degrees,int strength,int flag) {
        if (flag == 0) {
            setVelocity(body, strength * 100, degrees);

        }else if(flag==1){
            setVelocity1(body, strength * 100, degrees);
        }
    }
    @Override
    public void render(SpriteBatch batch,Body playerBody){
        batch.begin();
        batch.draw(texture, missileBody.getPosition().x,missileBody.getPosition().y);
        batch.end();
    }



    @Override
    public void update(float delta){
        missileBody.getPosition().x = missileBody.getLinearVelocity().x*delta;
        missileBody.getPosition().y = missileBody.getLinearVelocity().y*delta;
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
