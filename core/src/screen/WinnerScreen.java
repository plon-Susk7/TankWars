package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Tankwars;

public class WinnerScreen implements Screen {
    final Tankwars game;
    private static OrthographicCamera camera;
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private static BitmapFont black;
    private static Table table;
    private TextButton MainMenu;
    private int winner;

    private static BitmapFont player;

    public WinnerScreen(final Tankwars game,int winner){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 640);
        this.winner = winner;
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        black = new BitmapFont(Gdx.files.internal("black.fnt"),false);
        atlas = new TextureAtlas("button.pack");
        skin = new Skin(atlas);
        player = new BitmapFont(Gdx.files.internal("hud.fnt"),false);
        table = new Table(skin);
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");
        textButtonStyle.font = black;


        MainMenu = new TextButton("Goto MainMenu",textButtonStyle);


        table.add(MainMenu).pad(20);
        table.row();




        table.setDebug(true);
        stage.addActor(table);

        MainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        if(winner==0) {
            player.draw(game.batch, "Player A Wins the game!", 300, 600);
        }else{
            player.draw(game.batch, "Player B Wins the game!", 300, 600);
        }
        game.batch.end();
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
