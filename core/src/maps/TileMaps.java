package maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

public class TileMaps {
    private TiledMap maps;
    private static Shape shape;
    private static int temp; //map index in array

    private ArrayList<String> map = new ArrayList<>();
    public TileMaps(){
        map.add("map0.tmx");
        map.add("map1.tmx");
    }

    public OrthogonalTiledMapRenderer setupMap(){
        int temp = (int)Math.round(Math.random());
        this.temp = temp;
        System.out.println(temp);
        maps = new TmxMapLoader().load(String.valueOf(Gdx.files.internal(map.get(temp))));
        return new OrthogonalTiledMapRenderer(maps);
    }

    public void parseTileObjectLayer(World world, MapObjects objects){
        //code needs little cleaning

        for(MapObject object : objects){

                float[] vertices = ((PolygonMapObject) object).getPolygon().getTransformedVertices();
                Vector2[] worldVertices = new Vector2[vertices.length/2];

                for(int k=0;k<worldVertices.length;k++){
                    worldVertices[k] = new Vector2(vertices[k*2],vertices[k*2+1]);
                }
                ChainShape cs = new ChainShape();
                cs.createChain(worldVertices);
                shape = cs;


            Body body;
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bodyDef);
            body.createFixture(shape,1.0f);

        }
    }

    public TiledMap getMap(){
        return this.maps;
    }

    public void dispose(){
        maps.dispose();
        shape.dispose();
    }


}
