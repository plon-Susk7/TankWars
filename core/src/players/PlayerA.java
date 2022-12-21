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
    private int maxMoves;

    public PlayerA(World world,Texture tex,float xpos,float ypos){
        texture = tex;
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(xpos,ypos);
        bodyDef.fixedRotation = true;
        body = world.createBody(bodyDef);
        body.setUserData("playera");
        healthPoints = 150;
        this.maxMoves = 30;
    }

    @Override
    public Body getBody(){
        return body;
    }

    @Override
    public void moveLeft(Body x){

        x.setLinearVelocity(-50,0);
    }

    @Override
    public void moveRight(Body x){
        x.setLinearVelocity(50,0);
    }

    @Override
    //facade
    public void movementAndShooting(int option,Body x,World world,int strength,int degrees,float currentXPosition,float currentYPoisiton){
        switch (option){
            case 1:
                moveLeft(x);
                break;
            case 2:
                moveRight(x);
                break;
            case 3:
                shoot(world,strength,degrees,currentXPosition,currentYPoisiton);
        }
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

    public int getMaxMoves(){
        return this.maxMoves;
    }

    public void decrementMoves(){
        this.maxMoves -=1;
    }
    @Override
    public void setInitialHealth(int x){
        healthPoints = x;
    }
    public void setHealthPoints(int x){
        healthPoints-=x;
    }

    @Override
    public MissileA shoot(World world,int strength,int degrees,float currentXPosition,float currentYPoisiton) {
        MissileA missileA;
        missileA = new MissileA(world, body,0);
        missileBodyA = missileA.getMissileBody();
        missileBodyA.createFixture(missileA.getFixture());
        missileBodyA.createFixture(missileA.getFixture());

        System.out.println(currentXPosition+" "+currentYPoisiton);
        missileA.launchMissile(missileBodyA,degrees,strength,0);
        return missileA;
    }



    @Override
    public void dispose(){
        texture.dispose();
    }

}
