package players;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import missiles.MissileA;
import screen.MainMenu;

import static screen.GameScreen.missileBodyA;

public class PlayerA implements Player{


    private static BodyDef bodyDef;
    private static Body body;
    private static Texture texture;
    private static int healthPoints;

    public PlayerA(World world,Texture tex){
        texture = tex;
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(100,500);
        bodyDef.fixedRotation = true;
        body = world.createBody(bodyDef);
        healthPoints = 150;
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

    public int getHealthPoints(){
        return healthPoints;
    }

    public void setHealthPoints(int x){
        healthPoints-=x;
    }

    @Override
    public MissileA shoot(World world,int strength,int degrees,float currentXPosition,float currentYPoisiton) {
        MissileA missileA;
        missileA = new MissileA(world, body);
        missileBodyA = missileA.getMissileBody();
        missileBodyA.createFixture(missileA.getFixture());
        missileBodyA.createFixture(missileA.getFixture());
        float finalXPosition = currentXPosition + 100*strength;
        float finalYPosition = currentYPoisiton + 100*strength;

        if(finalYPosition>finalXPosition){
            finalXPosition+= finalYPosition-finalXPosition;
        }else{
            finalYPosition+=finalXPosition-finalYPosition;
        }
        System.out.println(currentXPosition+" "+currentYPoisiton);
        missileA.launchMissile(missileBodyA,finalXPosition,finalYPosition,degrees);
        return missileA;
    }



    @Override
    public void dispose(){
        texture.dispose();
    }

}
