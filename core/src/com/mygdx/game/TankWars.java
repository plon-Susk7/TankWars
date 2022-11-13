package com.mygdx.game;

import bullet.CustomBullet;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class TankWars extends ApplicationAdapter {
	private Texture tankAImage;
	private Texture tankBImage;

	private Rectangle tankA;


	private OrthographicCamera camera;
	private SpriteBatch batch;

	CustomBullet bullet;

	ArrayList<CustomBullet> bullets = new ArrayList<>();

	@Override
	public void create () {
		tankAImage = new Texture(Gdx.files.internal("Frost.png"));

		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,480);
		batch = new SpriteBatch();
		tankA = new Rectangle();

////		tankA.width = 64;
////		tankA.height = 64;
	}

	@Override
	public void render () {

		//movement code
		ScreenUtils.clear(0,0,0,1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(tankAImage,tankA.x,tankA.y);
		batch.end();
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) tankA.x -= 2 ;
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) tankA.x += 2;

		if(tankA.x < 0) tankA.x = 0;
		if(tankA.x > 800 - 64) tankA.x = 800 - 64;

		for(CustomBullet x:bullets){
			x.render(batch);
		}

		for(CustomBullet x:bullets){
			x.x+=300*Gdx.graphics.getDeltaTime();
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			bullet = new CustomBullet(tankA.x,tankA.y);
			bullets.add(bullet);
		}

//		for(CustomBullet x:bullets){
//			x.render(batch);
//		}
//		for(CustomBullet x : bullets){
//			x.update(Gdx.graphics.getDeltaTime());
//		}
//
//		//shooting code
//		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
//			bullets.add(new CustomBullet(tankA.x,tankA.y));
//		}





	}

	@Override
	public void dispose () {
		batch.dispose();
		tankAImage.dispose();

	}
}
