package bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class CustomBullet {
    public static float x,y;
    public static final int speed = 100;
    public static Texture bullet;
    public static  Rectangle hitbox;



    public CustomBullet(float x,float y){
        this.x = x;
        this.y = y;
        hitbox = new Rectangle();
        bullet  = new Texture(Gdx.files.internal("bullet.png"));

    }


    public void update(float delta){
        hitbox.x += speed;
    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(bullet,x,y);
        batch.end();
    }
}
