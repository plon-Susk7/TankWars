package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Tankwars;

public class pauseScreen implements Screen {
    final Tankwars game;
    private static OrthographicCamera camera;
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private static BitmapFont black;
    private static Table table;
    private TextButton returnToGame,saveGame,Main_Menu,restartGame;

    public pauseScreen(final Tankwars game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 640);
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        black = new BitmapFont(Gdx.files.internal("black.fnt"),false);
        atlas = new TextureAtlas("button.pack");
        skin = new Skin(atlas);

        table = new Table(skin);
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");
        textButtonStyle.font = black;


        returnToGame = new TextButton("Resume",textButtonStyle);
        Main_Menu = new TextButton("Main Menu",textButtonStyle);
        restartGame = new TextButton("Restart Game",textButtonStyle);
        saveGame = new TextButton("Save Game",textButtonStyle);

        table.add(returnToGame).pad(20);
        table.row();
        table.add(restartGame).pad(20);
        table.row();
        table.add(saveGame).pad(20);
        table.row();
        table.add(Main_Menu).pad(20);



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
        stage.dispose();
        atlas.dispose();
        black.dispose();
    }
}
