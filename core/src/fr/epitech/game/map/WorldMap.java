package fr.epitech.game.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.Viewport;

public class WorldMap {

    private final TmxMapLoader mapLoader;
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;
    private final World world;
    private final Box2DDebugRenderer b2dr;

    public WorldMap(OrthographicCamera camera, Viewport viewport){
        this.mapLoader = new TmxMapLoader();
        this.map = mapLoader.load("test.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2), (rect.getY() + rect.getHeight() / 2));

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    }

    public TiledMap getMap() {
        return map;
    }

    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }

    public World getWorld() {
        return world;
    }

    public Box2DDebugRenderer getBox2DRenderer() {
        return b2dr;
    }

}
