package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import missiles.MissileA;
import players.Player;
import screen.GameScreen;
import screen.MainMenu;


public class Tankwars extends Game {
//	SpriteBatch batch;
//	Texture img;
//
//	private static  OrthographicCamera camera;
//	public static World world;
//	public static Box2DDebugRenderer debugRenderer;
//	public static Body playerBodyA;
//	public static Body missileBodyA;
//
//	public static CircleShape circle;
//
//	private static Player playerA;
//	private static MissileA missileA;
//	private static Texture tex;

	public static  SpriteBatch batch;
	public static OrthographicCamera camera;
	public BitmapFont font;




	@Override
	public void create () {
//		batch = new SpriteBatch();
//		camera = new OrthographicCamera();
//		camera.setToOrtho(false,960,640);
//		batch = new SpriteBatch();
//		tex = new Texture("bullet.png");
//		debugRenderer = new Box2DDebugRenderer();
//		camera = new OrthographicCamera();
//		camera.setToOrtho(false,960,640);
//		world = new World(new Vector2(0,-30),false);
//
//		//Defining player A
//		playerA = new Player(world);
//		playerBodyA = playerA.getBody();
//
//		//Code needed for debugging only
//		circle = new CircleShape();
//		circle.setRadius(20f);
//		FixtureDef fixtureDef = new FixtureDef();
//		fixtureDef.shape = circle;
//		fixtureDef.density = 0.5f;
//		fixtureDef.friction = 20f;
//		Fixture fixture = playerBodyA.createFixture(fixtureDef);
//
//
//
//
//
//// Create our fixture and attach it to the body
//
//		BodyDef groundBodyDef = new BodyDef();
//// Set its world position
//		groundBodyDef.position.set(new Vector2(0, 10));
//
//// Create a body from the definition and add it to the world
//		Body groundBody = world.createBody(groundBodyDef);
//
//// Create a polygon shape
//		PolygonShape groundBox = new PolygonShape();
//// Set the polygon shape as a box which is twice the size of our view port and 20 high
//// (setAsBox takes half-width and half-height as arguments)
//		groundBox.setAsBox(camera.viewportWidth, 10.0f);
//// Create a fixture from our polygon shape and add it to our ground body
//		groundBody.createFixture(groundBox, 0.0f);

		batch = new SpriteBatch();
		font = new BitmapFont(); // use libGDX's default Arial font
		this.setScreen(new MainMenu(this));
	}



	@Override
	public void render () {
//		world.step(1/60f,6,2);
//		ScreenUtils.clear(0, 0, 0, 1);
//		debugRenderer.render(world, camera.combined);
//
//		//Player A's mechanisms
//		playerA.render(batch);
//
//		if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
//			playerA.moveRight(playerBodyA);
//		}
//
//		if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
//			playerA.moveLeft(playerBodyA);
//		}
//
//
//		if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
//			System.out.println("Shots Fired!");
//			missileA = new MissileA(world,playerBodyA);
//			missileBodyA = missileA.getMissileBody();
//			missileBodyA.createFixture(missileA.getFixture());
//			missileA.launchMissile(missileBodyA);
//			System.out.println(Gdx.input.getX()+" "+Gdx.input.getY());
//			missileA.render(batch,playerBodyA);
//		}

		super.render();

	}

	@Override
	public void dispose () {
	//		circle.dispose();
	//		playerA.dispose();

	}
}
