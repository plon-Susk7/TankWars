package missiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public class MissileA {
    public static BodyDef missileDef;
    public static Body missileBody;
    private static Texture texture;

    private static FixtureDef missileFixture;

    public MissileA(World world, Body playerBody){
        texture = new Texture("Frost.png");
        missileDef = new BodyDef();
        missileDef.type = BodyDef.BodyType.DynamicBody;
        missileDef.position.set(playerBody.getPosition().x+20, playerBody.getPosition().y+10);
        missileDef.fixedRotation = true;
        missileBody= world.createBody(missileDef);

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

    public void launchMissile(Body body){
        body.setLinearVelocity(Gdx.input.getX(),640 - Gdx.input.getY());
    }
    public void render(SpriteBatch batch,Body playerBody){
        batch.begin();
        System.out.println(playerBody.getPosition());
        //batch.draw(texture,playerBody.getPosition().x-16,playerBody.getPosition().y-16);
        //batch.draw(texture, 200,200);
        batch.end();
    }

    public Body getMissileBody(){
        return missileBody;
    }

    public void dispose(){
        texture.dispose();
    }
}
