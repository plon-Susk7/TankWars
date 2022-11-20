package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Tankwars;
import missiles.MissileA;
import players.Player;

import java.util.ArrayList;

public class GameScreen implements Screen{
    final Tankwars game;
    private static Texture tex;
    private static SpriteBatch batch;
    private static OrthographicCamera camera;
    public static World world;
    public static Box2DDebugRenderer debugRenderer;
    private static Body playerABody;
	public static Body missileBodyA;

    private static Player playerA;
	private static MissileA missileA;
    ArrayList<MissileA> bulletList = new ArrayList<>();

    public GameScreen(final Tankwars game){
        this.game = game;
        batch = this.game.batch;
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 640);
        tex = new Texture("Frost.png");
        world = new World(new Vector2(0,-30),false);

        playerA = new Player(world);
        playerABody = playerA.getBody();

       // Code needed for debugging only
		CircleShape circle = new CircleShape();
		circle.setRadius(20f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 20f;
		Fixture fixture = playerABody.createFixture(fixtureDef);




        BodyDef groundBodyDef = new BodyDef();
// Set its world position
		groundBodyDef.position.set(new Vector2(0, 10));

// Create a body from the definition and add it to the world
		Body groundBody = world.createBody(groundBodyDef);

// Create a polygon shape
		PolygonShape groundBox = new PolygonShape();
// Set the polygon shape as a box which is twice the size of our view port and 20 high
// (setAsBox takes half-width and half-height as arguments)
		groundBox.setAsBox(camera.viewportWidth, 10.0f);
// Create a fixture from our polygon shape and add it to our ground body
		groundBody.createFixture(groundBox, 0.0f);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.step(1/60f,6,2);
        ScreenUtils.clear(0, 0, 0, 1);
        debugRenderer.render(world, camera.combined);
        playerA.render(batch);
        		if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
			playerA.moveRight(playerABody);
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
			playerA.moveLeft(playerABody);
		}



        for(MissileA x: bulletList){
            x.update(Gdx.graphics.getDeltaTime());
        }

        for(MissileA x:bulletList){
            x.render(batch,playerABody);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
			System.out.println("Shots Fired!");
			missileA = new MissileA(world,playerABody);
			missileBodyA = missileA.getMissileBody();
			missileBodyA.createFixture(missileA.getFixture());
            missileBodyA.createFixture(missileA.getFixture());
			missileA.launchMissile(missileBodyA);
//			System.out.println(Gdx.input.getX()+" "+Gdx.input.getY());
            bulletList.add(missileA);
		}
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        tex.dispose();
    }
}