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
	public static  SpriteBatch batch;
	public static OrthographicCamera camera;
	public BitmapFont font;




	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont(); // use libGDX's default Arial font
		this.setScreen(new MainMenu(this));
	}



	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {

	}
}
