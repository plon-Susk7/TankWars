package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import players.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class SavedGameScreen implements Screen {
    final Tankwars game;
    private static OrthographicCamera camera;
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private static BitmapFont black;
    private static Table table;
    private TextButton savedGame1,savedGame2,savedGame3,savedGame4,backToMenu;
    private static int temp; //Number of saved games

    private static ArrayList<Texture> Players = new ArrayList<>();

    int tankAHealth,tankBHealth;
    float tankAXPos,tankBXPos,tankAYPos,tankBYPos;
    String tankATex,tankBTex;

    private static Texture finPlayerATex,finPlayerBTex;


    public SavedGameScreen(final Tankwars game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 640);
    }

    private void readData(File f){
        try{
            int i = 0;
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String l;
            while((l = reader.readLine())!=null){
                if(i==0){
                    tankAHealth = Integer.parseInt(l);
                }else if(i==1){
                    tankAXPos = Float.parseFloat(l);
                }else if(i==2){
                    tankAYPos = Float.parseFloat(l);
                }else if(i==3){
                    tankATex = l;
                }else if(i==4){
                    tankBHealth = Integer.parseInt(l);
                }else if(i==5){
                    tankBXPos = Float.parseFloat(l);
                }else if(i==6){
                    tankBYPos = Float.parseFloat(l);
                }else if(i==7){
                    tankBTex = l;
                }
                i++;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        finPlayerATex = new Texture(tankATex);
        finPlayerBTex = new Texture(tankBTex);
        System.out.println(tankATex);
        System.out.println(tankBTex);
        Players.add(finPlayerATex);
        Players.add(finPlayerBTex);
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


        savedGame1 = new TextButton("Game 1",textButtonStyle);
        savedGame2 = new TextButton("Game 2",textButtonStyle);
        savedGame3 = new TextButton("Game 3",textButtonStyle);
        savedGame4 = new TextButton("Game 4",textButtonStyle);
        backToMenu = new TextButton("Main Menu",textButtonStyle);

        try {
            temp = 0;
            BufferedReader reader = new BufferedReader(new FileReader(Gdx.files.internal("savedGames/number.txt").file()));
            String number;

            while ((number = reader.readLine()) != null) {
                System.out.println(number);
                temp = Integer.parseInt(number);
            }
            reader.close();
        }catch (Exception e){
            System.out.println(e);
        }

        if(temp==0){
            table.add(backToMenu).pad(20);
        }else if(temp==1){
            table.add(savedGame1).pad(20);
            table.row();
            table.add(backToMenu).pad(20);
        }else if(temp==2){
            table.add(savedGame1).pad(20);
            table.row();
            table.add(savedGame2).pad(20);
            table.row();
            table.add(backToMenu).pad(20);
        }else if(temp==3){
            table.add(savedGame1).pad(20);
            table.row();
            table.add(savedGame2).pad(20);
            table.row();
            table.add(savedGame3).pad(20);
            table.row();
            table.add(backToMenu).pad(20);
        }else{
            table.add(savedGame1).pad(20);
            table.row();
            table.add(savedGame2).pad(20);
            table.row();
            table.add(savedGame3).pad(20);
            table.row();
            table.add(savedGame4).pad(20);
            table.row();
            table.add(backToMenu).pad(20);
        }

        File folder = new File("savedGames");

        final File[] files = folder.listFiles();

        for(File file:files){
            System.out.println(file.getName());
        }

        backToMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });

        savedGame1.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                readData(files[1]);
                game.setScreen(new GameScreen(game,Players,tankAHealth,tankAXPos,tankAYPos,tankBHealth,tankBXPos,tankBYPos));
            }
        });

        savedGame2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                readData(files[2]);
                game.setScreen(new GameScreen(game,Players,tankAHealth,tankAXPos,tankAYPos,tankBHealth,tankBXPos,tankBYPos));
            }
        });

        savedGame3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                readData(files[3]);
                game.setScreen(new GameScreen(game,Players,tankAHealth,tankAXPos,tankAYPos,tankBHealth,tankBXPos,tankBYPos));
            }
        });

        savedGame4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                readData(files[4]);
                game.setScreen(new GameScreen(game,Players,tankAHealth,tankAXPos,tankAYPos,tankBHealth,tankBXPos,tankBYPos));
            }
        });




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
