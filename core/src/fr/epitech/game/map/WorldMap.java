package fr.epitech.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.epitech.game.EpiGame;
import fr.epitech.game.SimplexNoise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldMap {

    private final SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer b2dr;
    private final List<Chunk> loadedChunks;
    private final double seed;
    private int playerX = 0, playerY = 0, renderDistance = 3;

    public WorldMap(SpriteBatch batch){
        this.batch = batch;
        this.loadedChunks = new ArrayList<>();
        this.seed = new Random().nextDouble();
        this.world = new World(new Vector2(0, -9.8f), true);
        System.out.println((this.world.getGravity()));
        this.b2dr = new Box2DDebugRenderer();
    }

    public void update(float delta){
        world.step(1/60f, 6, 2);
    }

    public void render(){
        int startX = Math.max(-1, this.playerX - renderDistance) + 1;
        int endX = this.playerX + renderDistance;

        for(int x = startX; x < endX + 10; x++){
            if(loadedChunks.size() <= x){
                Chunk chunk = new Chunk(world, seed);
                chunk.generateChunk(loadedChunks.size());
                loadedChunks.add(chunk);
            }

            if(x >= endX || loadedChunks.size() < x || loadedChunks.get(x) == null)
                continue;

            Chunk chunk = loadedChunks.get(x);
            chunk.render(batch);
        }
    }

    /*public void updatePlayerPosition(float playerX, float playerY){
        this.playerX = (int) playerX / (Chunk.TILE_SIZE * Chunk.SIZE_X);
        this.playerY = (int) playerY / (Chunk.TILE_SIZE * Chunk.SIZE_Y);
    }*/

    public World getWorld() {
        return world;
    }

    public Box2DDebugRenderer getBox2DRenderer() {
        return b2dr;
    }

}
