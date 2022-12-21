package players;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import missiles.MissileA;

import static screen.GameScreen.missileBodyA;

public class PlayerB implements Player{


    private static BodyDef bodyDef;
    private static Body body;
    private static Texture texture;

    private static int healthPoints;

    private int maxMoves;

    public PlayerB(World world, Texture tex,float xpos,float ypos){
        texture = tex;
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(xpos,ypos);
        bodyDef.fixedRotation = true;
        body = world.createBody(bodyDef);
        body.setUserData("playerb");
        healthPoints = 150;
        this.maxMoves = 10;
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
    public void setInitialHealth(int x){
        healthPoints = x;
    }

    @Override
    public int getMaxMoves(){
        return this.maxMoves;
    }

    @Override
    public void decrementMoves(){
        this.maxMoves -=1;
    }

    @Override
    public void render(SpriteBatch batch){
        batch.begin();
        Sprite sprite = new Sprite(texture);
        sprite.flip(true,false);
        batch.draw(sprite,body.getPosition().x-50,body.getPosition().y-28,texture.getWidth()/3,texture.getHeight()/3);
        batch.end();
    }

    @Override
    public MissileA shoot(World world,int strength,int degrees,float currentXPosition,float currentYPoisiton) {
        MissileA missileA;
        missileA = new MissileA(world, body,1);
        missileBodyA = missileA.getMissileBody();
        missileBodyA.createFixture(missileA.getFixture());
        missileBodyA.createFixture(missileA.getFixture());
        missileA.launchMissile(missileBodyA,degrees,strength,1);
        return missileA;
    }

    public int getHealthPoints(){
        return healthPoints;
    }

    public void setHealthPoints(int x){
        healthPoints-=x;
    }

    @Override
    public void dispose(){
        texture.dispose();
        texture.dispose();
    }
}
