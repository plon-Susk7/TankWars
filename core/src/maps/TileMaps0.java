package maps;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class TileMaps0 extends TileMaps {
    private TiledMap maps;
    private static Shape shape;
    public TileMaps0(){

    }

//    @Override
//    public OrthogonalTiledMapRenderer setupMap(){
//        maps = new TmxMapLoader().load("maps/map0.tmx");
//        return new OrthogonalTiledMapRenderer(maps);
//    }

//    public void parseTileObjectLayer(World world, MapObjects objects){
//        //code needs little cleaning
//
//        for(MapObject object : objects){
//
//            float[] vertices = ((PolygonMapObject) object).getPolygon().getTransformedVertices();
//            Vector2[] worldVertices = new Vector2[vertices.length/2];
//
//            for(int k=0;k<worldVertices.length;k++){
//                worldVertices[k] = new Vector2(vertices[k*2],vertices[k*2+1]);
//            }
//            ChainShape cs = new ChainShape();
//            cs.createChain(worldVertices);
//            shape = cs;
//
//
//            Body body;
//            BodyDef bodyDef = new BodyDef();
//            bodyDef.type = BodyDef.BodyType.StaticBody;
//            body = world.createBody(bodyDef);
//            body.createFixture(shape,1.0f);
//
//        }
//    }
//
//    public TiledMap getMap(){
//        return this.maps;
//    }
//
//    public void dispose(){
//        maps.dispose();
//        shape.dispose();
//    }


}
