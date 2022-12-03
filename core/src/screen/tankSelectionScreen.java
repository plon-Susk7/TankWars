package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Tankwars;

import java.util.ArrayList;

public class tankSelectionScreen implements Screen {
    final Tankwars game;
    private static OrthographicCamera camera;
    private static Texture tank_1;
    private static Texture tank_2;
    private static Texture tank_3;

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private BitmapFont nextFont;
    private BitmapFont hud;
    private Table table1,table2,table3;
    private TextButton nextButton,selectButton,nextButtonB,selectButtonB;
    private TextButton BackButton;

    private static Texture background;

    private static ArrayList<Texture> textureArrayPlayerA = new ArrayList<>();
    private static ArrayList<Texture> textureArrayPlayerB = new ArrayList<>();

    private static ArrayList<Texture> passingArray = new ArrayList<>();

    static int i = 0;
    static int j = 0;

    public tankSelectionScreen(Tankwars game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 640);
        tank_1 = new Texture("Toxic.png");
        tank_2 = new Texture("Pinky.png");
        tank_3 = new Texture("Blazer.png");
        textureArrayPlayerA.add(tank_1);
        textureArrayPlayerA.add(tank_2);
        textureArrayPlayerA.add(tank_3);
        textureArrayPlayerB.add(tank_1);
        textureArrayPlayerB.add(tank_2);
        textureArrayPlayerB.add(tank_3);
    }

    @Override
    public void show() {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        background = new Texture(Gdx.files.internal("bg2.png"));
        hud = new BitmapFont(Gdx.files.internal("hud.fnt"),false);
        nextFont = new BitmapFont(Gdx.files.internal("black.fnt"),false);
        atlas = new TextureAtlas("button.pack");
        skin = new Skin(atlas);

        table1 = new Table(skin);
        table2 = new Table(skin);
        table3 = new Table(skin);

        table1.setBounds(0,0,450,100);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");
        textButtonStyle.font = nextFont;

        table2.setBounds(0,0,1500,100);
        table3.setBounds(0,0,200,1200);

        nextButton = new TextButton("Next",textButtonStyle);
        selectButton =new TextButton("Select",textButtonStyle);

        nextButtonB = new TextButton("Next",textButtonStyle);
        selectButtonB =new TextButton("Select",textButtonStyle);

        BackButton = new TextButton("Main Menu", textButtonStyle);

        table1.add(nextButton).pad(10);
        table1.add(selectButton).pad(10);

        table2.add(nextButtonB).pad(10);
        table2.add(selectButtonB).pad(10);

        table3.add(BackButton).pad(10);

        table1.setDebug(true);
        table2.setDebug(true);
        stage.addActor(table1);
        stage.addActor(table2);
        stage.addActor(table3);

        nextButtonB.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                j++;
                if(j>2){
                    j = 0;
                }
            };
        });

        nextButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                i++;
                if(i>2){
                    i = 0;
                }
            };
        });

        selectButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                passingArray.add(textureArrayPlayerA.get(i));
                if(passingArray.size()==2){
                    i = 0;
                    j = 0;
                    ArrayList<Texture> temp = new ArrayList<>();
                    for(int k=0;k<2;k++){
                        System.out.println(passingArray.get(k));
                        temp.add(passingArray.get(k));
                    }
                    passingArray.clear();
                    game.setScreen(new GameScreen(game,temp));

                }
            };
        });

        selectButtonB.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                passingArray.add(textureArrayPlayerB.get(j));
                if(passingArray.size()==2){
                    i = 0;
                    j = 0;
                    ArrayList<Texture> temp = new ArrayList<>();
                    for(int k=0;k<2;k++){
                        System.out.println(passingArray.get(k));
                        temp.add(passingArray.get(k));
                    }
                    passingArray.clear();
                    game.setScreen(new GameScreen(game,temp));
                }
            };
        });


    }


    public void incr(){
        i++;
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();

        game.batch.begin();
        game.batch.draw(background,0,0);
        game.batch.end();

        game.batch.setProjectionMatrix(camera.combined);
        stage.act(delta);
        stage.draw();



        game.batch.begin();
        game.batch.draw(textureArrayPlayerA.get(i),50,200);
        game.batch.end();

        Sprite sprite;
        game.batch.begin();
        sprite = new Sprite(textureArrayPlayerB.get(j));
        sprite.flip(true,false);
        game.batch.draw(sprite,600,200);
        game.batch.end();

        game.batch.begin();
        //nextFont.setScale(.2f);
        hud.draw(game.batch, "Player A",120 ,500);
        hud.draw(game.batch, "Player B",650 ,500);
        game.batch.end();

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
        stage.dispose();
        nextFont.dispose();
        tank_1.dispose();
        tank_2.dispose();
        tank_3.dispose();
        nextFont.dispose();
    }
}
