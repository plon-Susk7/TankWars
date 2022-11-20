package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private Table table1,table2;
    private TextButton nextButton,selectButton,nextButtonB,selectButtonB;

    private static ArrayList<Texture> textureArrayPlayerA = new ArrayList<>();
    private static ArrayList<Texture> textureArrayPlayerB = new ArrayList<>();
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
//        tank_1 = new Texture("Abrams.png");
//        tank_2 = new Texture("Frost.png");
//        tank_3 = new Texture("Blazer.png");
//        textureArray.add(tank_1);
//        textureArray.add(tank_2);
//        textureArray.add(tank_3);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        nextFont = new BitmapFont(Gdx.files.internal("black.fnt"),false);
        atlas = new TextureAtlas("button.pack");
        skin = new Skin(atlas);

        table1 = new Table(skin);
        table2 = new Table(skin);

        table1.setBounds(0,0,350,100);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");
        textButtonStyle.font = nextFont;

        table2.setBounds(0,0,1600,100);

        nextButton = new TextButton("Next",textButtonStyle);
        selectButton =new TextButton("Select",textButtonStyle);

        nextButtonB = new TextButton("Next",textButtonStyle);
        selectButtonB =new TextButton("Select",textButtonStyle);

        table1.add(nextButton).pad(10);
        table1.add(selectButton).pad(10);

        table2.add(nextButtonB).pad(10);
        table2.add(selectButtonB).pad(10);

        table1.setDebug(true);
        table2.setDebug(true);
        stage.addActor(table1);
        stage.addActor(table2);

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
    }


    public void incr(){
        i++;
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 1, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        stage.act(delta);
        stage.draw();

        game.batch.begin();
        game.batch.draw(textureArrayPlayerA.get(i),50,200);
        game.batch.end();

        game.batch.begin();
        game.batch.draw(textureArrayPlayerB.get(j),600,200);
        game.batch.end();

//        nextButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event,float x,float y){
//                i++;
//            }
//        });


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

    }
}
