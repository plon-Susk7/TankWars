package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Tankwars;
import maps.TileMaps0;
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

    private Table table1,table2,table3,table4,table5;
    private TextButton forwardButtonA,backButtonA,forwardButtonB,backButtonB,resume,save,backToMenu;
    private TextButton powerA,directionA,powerB,directionB;
    private TextButton BackButton;
    private static ShapeRenderer shapeRendererA,shapeRendererB;
    private static BitmapFont playerAHud,playerBHud,playerApower,playerBpower,playerADirection,playerBDirection;
    private static boolean isPause = false;
    static int i = 0;

    private static ArrayList<Body> tobeDeleted = new ArrayList<>();

    private static OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private static TileMaps0 tileMaps;
    private static TiledMap map;
    public GameScreen(final Tankwars game,ArrayList<Texture> playersTexture){
        this.game = game;
        batch = this.game.batch;
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 640);
        this.playersTexture = playersTexture;
        this.tileMaps = new TileMaps0();
        this.orthogonalTiledMapRenderer = tileMaps.setupMap();

    }

    @Override
    public void show() {

        texA = playersTexture.get(0);
        System.out.println(texA);
        texB = playersTexture.get(1);
        System.out.println(texB);
        world = new World(new Vector2(0,-30),false);

        shapeRendererA = new ShapeRenderer();
        shapeRendererB= new ShapeRenderer();

        playerA = new PlayerA(world,playersTexture.get(0));
        playerABody = playerA.getBody();

        playerAHud = new BitmapFont(Gdx.files.internal("hud.fnt"),false);
        playerBHud = new BitmapFont(Gdx.files.internal("hud.fnt"),false);

        playerApower = new BitmapFont(Gdx.files.internal("hud.fnt"),false);
        playerADirection = new BitmapFont(Gdx.files.internal("hud.fnt"),false);
        // Code needed for debugging only
        CircleShape circle = new CircleShape();
        circle.setRadius(20f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 5f;
        Fixture fixture = playerABody.createFixture(fixtureDef);

        playerB = new PlayerB(world,playersTexture.get(1));
        playerBBody = playerB.getBody();

        // Code needed for debugging only
        CircleShape circle1 = new CircleShape();
        circle1.setRadius(20f);
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = circle;
        fixtureDef1.density = 0.5f;
        fixtureDef1.friction = 5f;
        Fixture fixture1 = playerBBody.createFixture(fixtureDef1);


        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont(Gdx.files.internal("black.fnt"),false);
        atlas = new TextureAtlas("button.pack");
        skin = new Skin(atlas);

        table1 = new Table(skin);
        table2 = new Table(skin);
        table3 = new Table(skin);
        table4 = new Table(skin);
        table5 = new Table(skin);

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


        powerA = new TextButton("Power",textButtonStyle);
        directionA = new TextButton("Direc",textButtonStyle);

        powerB = new TextButton("Power",textButtonStyle);
        directionB = new TextButton("Direc",textButtonStyle);

        table4.add(powerA).pad(20);
        table4.add(directionA).pad(20);

        table5.add(powerB);
        table5.add(directionB);

        table1.add(backButtonA).pad(10);
        table1.add(forwardButtonA).pad(10);

        table2.add(backButtonB).pad(10);
        table2.add(forwardButtonB).pad(10);

        table3.add(BackButton).pad(10);

        table4.setPosition(100,50);
        table5.setPosition(860,50);

        table1.setDebug(true);
        table2.setDebug(true);
        stage.addActor(table1);
        stage.addActor(table2);
        stage.addActor(table3);
        stage.addActor(table4);
        stage.addActor(table5);


        Pixmap bgPixmap = new Pixmap(1,1, Pixmap.Format.RGB565);
        bgPixmap.setColor(Color.RED);
        bgPixmap.fill();
        TextureRegionDrawable Bg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));


        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.titleFont = playerAHud;
        windowStyle.titleFontColor = Color.GREEN;
        windowStyle.background = Bg;


        resume = new TextButton("Resume",textButtonStyle);
        save = new TextButton("Save",textButtonStyle);
        backToMenu = new TextButton("Main Menu",textButtonStyle);

        final Window pause = new Window("PAUSE",windowStyle);
        pause.add(resume).pad(10).row();
        pause.add(save).pad(10).row();
        pause.add(backToMenu).pad(10).row();
        pause.setSize(stage.getWidth(),stage.getHeight());
        pause.setVisible(false);

        BackButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pause.setVisible(true);
                isPause = true;
            }
        });

        save.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Preferences prefs = Gdx.app.getPreferences("myPrefs");
                prefs.putInteger("name", i);
                i++;

                prefs.flush();
                System.out.println(prefs);

            }
        });

        backToMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                isPause = false;
                game.setScreen(new MainMenu(game));
            }
        });


        resume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pause.setVisible(false);
                isPause = false;
            }
        });
        stage.addActor(pause);
    }


    private void createCollisionListener() {
        world.setContactListener(new ContactListener() {

            @Override
            public void beginContact(Contact contact) {
                //System.out.println("Contact");
                Fixture fa = contact.getFixtureA();
                Fixture fb = contact.getFixtureB();


                if(fb.getBody().getUserData()=="MissileA" && fa.getBody().getType()== BodyDef.BodyType.StaticBody){
                    System.out.println(bulletList.size());
                    System.out.println("the bullet has hit");
                    if(bulletList.size()!=0){
                        tobeDeleted.add(bulletList.get(0).getMissileBody());
                        bulletList.remove(0);
                    }

                }

                if(fb.getBody().getUserData()=="MissileA" && fa.getBody().getUserData()=="playerb"){
                    System.out.println("the bullet has hit player2");
                    playerB.setHealthPoints(15);
                    if(bulletList.size()!=0){
                        tobeDeleted.add(bulletList.get(0).getMissileBody());
                        bulletList.remove(0);
                    }
                }

             }

            @Override
            public void endContact(Contact contact) {


            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
            }

        });


    }

    @Override
    public void render(float delta) {
        world.step(1/60f,6,2);
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(delta);
        stage.draw();


        createCollisionListener();


        if(tobeDeleted.size()!=0) {
            for (Body body : tobeDeleted) {
                //System.out.println("Destroying bodies");
                 world.destroyBody(body);
            }
            tobeDeleted.clear();
            //bulletList.clear();
        }


        orthogonalTiledMapRenderer.setView(camera);
        map = tileMaps.getMap();
        tileMaps.parseTileObjectLayer(world,map.getLayers().get("object").getObjects());
        debugRenderer.render(world, camera.combined);

        if(isPause==false) {
            orthogonalTiledMapRenderer.render();

            playerA.render(batch);
            playerB.render(batch);

            //Player Movement
            forwardButtonA.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    playerA.moveRight(playerABody);
                }
            });

            backButtonA.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    playerA.moveLeft(playerABody);
                }
            });

            forwardButtonB.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    playerB.moveRight(playerBBody);
                }
            });

            backButtonB.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    playerB.moveLeft(playerBBody);
                }
            });
            //Player Movement


            //pause screen
            if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
                System.out.println("B is pressed!");
                game.setScreen(new pauseScreen(game));
            }


            for (MissileA x : bulletList) {
                x.update(Gdx.graphics.getDeltaTime());
            }

            for (MissileA x : bulletList) {
                x.render(batch, playerABody);
            }


            if(bulletList.size()<1) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
                    System.out.println("Shots Fired!");
                    missileA = playerA.shoot(world);
                    bulletList.add(missileA);
                }
            }else{
                System.out.println("You cant shoot now!");
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
                System.out.println("Shots Fired!");
                missileA = playerB.shoot(world);
                bulletList.add(missileA);
            }







            batch.begin();
            playerAHud.draw(game.batch, "Player A", 150, 600);
            playerBHud.draw(game.batch, "Player B", 650, 600);
            playerApower.draw(game.batch,"1",300,50);
            playerADirection.draw(game.batch,"50",310,50);
            batch.end();

            shapeRendererA.begin(ShapeRenderer.ShapeType.Filled);
            if(playerA.getHealthPoints()<=50){
                shapeRendererA.setColor(Color.RED);
            }else if(playerA.getHealthPoints()<=100 && playerA.getHealthPoints()>=50){
                shapeRendererA.setColor(Color.YELLOW);
            }else {
                shapeRendererA.setColor(Color.GREEN);
            }
            shapeRendererA.rect(150, 550, playerA.getHealthPoints(), 10);
            shapeRendererA.end();

            shapeRendererB.begin(ShapeRenderer.ShapeType.Filled);
            if(playerB.getHealthPoints()<=50){
                shapeRendererB.setColor(Color.RED);
            }else if(playerB.getHealthPoints()<=100 && playerB.getHealthPoints()>50){
                shapeRendererB.setColor(Color.YELLOW);
            }
            else {
                shapeRendererB.setColor(Color.GREEN);
            }
            shapeRendererB.rect(650, 550, playerB.getHealthPoints(), 10);
            shapeRendererB.end();



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
        texA.dispose();
        tileMaps.dispose();
        texB.dispose();
        batch.dispose();
        world.dispose();
        playerA.dispose();
        playerB.dispose();
        stage.dispose();
        map.dispose();
        tileMaps.dispose();
        playerAHud.dispose();
        playerBHud.dispose();
    }
}