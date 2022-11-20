package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Tankwars;


public class MainMenu implements Screen {
    final Tankwars game;
    OrthographicCamera camera;

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private BitmapFont title,black;
    private Table table;
    private TextButton buttonPlay,buttonExit,saveButton;
    private Label heading;



    public MainMenu(Tankwars game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 640);
    }
    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        black = new BitmapFont(Gdx.files.internal("black.fnt"),false);
        title = new BitmapFont(Gdx.files.internal("label.fnt"),false);
        atlas = new TextureAtlas("button.pack");
        skin = new Skin(atlas);

        table = new Table(skin);
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");
        textButtonStyle.font = black;

        Label.LabelStyle header = new Label.LabelStyle(title, Color.BLACK);
        heading = new Label("Tank Wars",header);
        heading.setScale(3);

        buttonPlay = new TextButton("Play",textButtonStyle);
        buttonExit = new TextButton("Exit",textButtonStyle);
        saveButton = new TextButton("Save",textButtonStyle);


        //functionalities

        buttonExit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        buttonPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new tankSelectionScreen(game));
            }
        });



        table.add(heading);
        table.row();
        table.add(buttonPlay).pad(20);
        table.row();
        table.add(saveButton).pad(20);
        table.row();
        table.add(buttonExit).pad(20);


        table.setDebug(true);
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        stage.act(delta);
        stage.draw();



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
