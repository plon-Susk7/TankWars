package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Tankwars;
import missiles.MissileA;
import players.Player;
import players.PlayerA;
import players.PlayerB;

import java.util.ArrayList;

public class GameScreen implements Screen{
    final Tankwars game;
    private static Texture texA,texB;

    private static SpriteBatch batch;
    private static OrthographicCamera camera;
    public static World world;
    public static Box2DDebugRenderer debugRenderer;
    private static Body playerABody;

    private static Body playerBBody;
	public static Body missileBodyA;

    private static Player playerA;
    private static Player playerB;
	private static MissileA missileA;
    private static ArrayList<MissileA> bulletList = new ArrayList<>();
    private ArrayList<Texture> playersTexture;

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private BitmapFont font;

    private Table table1,table2,table3;
    private TextButton forwardButtonA,backButtonA,forwardButtonB,backButtonB;
    private TextButton BackButton;
    private static ShapeRenderer shapeRendererA,shapeRendererB;
    private static BitmapFont playerAHud,playerBHud;

    public GameScreen(final Tankwars game,ArrayList<Texture> playersTexture){
        this.game = game;
        batch = this.game.batch;
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 640);
        this.playersTexture = playersTexture;

    }

    @Override
    public void show() {
        texA = playersTexture.get(0);
        texB = playersTexture.get(1);
        world = new World(new Vector2(0,-30),false);
        shapeRendererA = new ShapeRenderer();
        shapeRendererB= new ShapeRenderer();
        playerA = new PlayerA(world,playersTexture.get(0));
        playerABody = playerA.getBody();

        playerAHud = new BitmapFont(Gdx.files.internal("hud.fnt"),false);
        playerBHud = new BitmapFont(Gdx.files.internal("hud.fnt"),false);
        // Code needed for debugging only
        CircleShape circle = new CircleShape();
        circle.setRadius(20f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 20f;
        Fixture fixture = playerABody.createFixture(fixtureDef);

        playerB = new PlayerB(world,playersTexture.get(1));
        playerBBody = playerB.getBody();

        // Code needed for debugging only
        CircleShape circle1 = new CircleShape();
        circle1.setRadius(20f);
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = circle;
        fixtureDef1.density = 0.5f;
        fixtureDef1.friction = 20f;
        Fixture fixture1 = playerBBody.createFixture(fixtureDef1);


        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, 200));
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(camera.viewportWidth, 10.0f);
        groundBody.createFixture(groundBox, 0.0f);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont(Gdx.files.internal("black.fnt"),false);
        atlas = new TextureAtlas("button.pack");
        skin = new Skin(atlas);

        table1 = new Table(skin);
        table2 = new Table(skin);
        table3 = new Table(skin);
        table1.setPosition(60,100);
        table2.setPosition(900,100);
        //table1.setBounds(0,0,100,300);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");
        textButtonStyle.font = font;

        //table2.setBounds(0,0,100,100);
        table3.setBounds(0,0,100,1200);

        forwardButtonA = new TextButton("->",textButtonStyle);
        backButtonA =new TextButton("<-",textButtonStyle);

        forwardButtonB= new TextButton("->",textButtonStyle);
        backButtonB =new TextButton("<-",textButtonStyle);

        BackButton = new TextButton("Pause", textButtonStyle);

        table1.add(backButtonA).pad(10);
        table1.add(forwardButtonA).pad(10);

        table2.add(backButtonB).pad(10);
        table2.add(forwardButtonB).pad(10);

        table3.add(BackButton).pad(10);

        table1.setDebug(true);
        table2.setDebug(true);
        stage.addActor(table1);
        stage.addActor(table2);
        stage.addActor(table3);

    }

    @Override
    public void render(float delta) {
        world.step(1/60f,6,2);
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(delta);
        stage.draw();
        debugRenderer.render(world, camera.combined);
        playerA.render(batch);
        playerB.render(batch);
        		if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
			playerA.moveRight(playerABody);
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
			playerA.moveLeft(playerABody);
		}

        if(Gdx.input.isKeyJustPressed(Input.Keys.B)){
            System.out.println("B is pressed!");
            game.setScreen(new pauseScreen(game));
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
            bulletList.add(missileA);
		}

        batch.begin();
        playerAHud.draw(game.batch, "Player A",150 ,600);
        playerBHud.draw(game.batch, "Player B",650 ,600);
        batch.end();

        shapeRendererA.begin(ShapeRenderer.ShapeType.Filled);
        shapeRendererA.setColor(Color.GREEN);
        shapeRendererA.rect(150, 550, 150, 10);
        shapeRendererA.end();

        shapeRendererB.begin(ShapeRenderer.ShapeType.Filled);
        shapeRendererB.setColor(Color.GREEN);
        shapeRendererB.rect(650, 550, 150, 10);
        shapeRendererB.end();
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
        texA.dispose();
    }
}