package fr.epitech.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.physics.box2d.*;
import fr.epitech.game.SimplexNoise;

public class Chunk {

    public static final int SIZE_X = 16, SIZE_Y = 64, TILE_SIZE = 32;

    private final TiledMapTileLayer.Cell[][] cells;
    private final World world;
    private final double seed;
    private final TextureRegion[][] textures;
    private int offsetX;

    public Chunk(World world, double seed){
        this.world = world;
        this.cells = new TiledMapTileLayer.Cell[Chunk.SIZE_X][Chunk.SIZE_Y];
        this.seed = Math.abs(seed);
        this.textures = new TextureRegion(new Texture("tiles.png")).split(16, 16);
    }

    public void generateChunk(int offsetX){
        this.offsetX = offsetX;
        for(int x = 0; x < cells.length; x++){
            int blockX = x + offsetX * Chunk.SIZE_X;
            double noise = SimplexNoise.noise(blockX / 100f, seed) * 10 + 20;
            for(int y = 0; y < cells[x].length; y++){
                int blockY = y * Chunk.TILE_SIZE;
                double noiseCloud = SimplexNoise.noise(blockX / seed, blockY / seed);
                if(noise < y) {
                    if(noiseCloud > 0.9f && noise < y - 4){
                        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                        cell.setTile(new StaticTiledMapTile(textures[0][4]));
                        cells[x][y] = cell;
                    }
                    continue;
                }

                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                cell.setTile(new StaticTiledMapTile(noise > y + 1 ? textures[0][1] : textures[0][0]));
                cells[x][y] = cell;
            }
        }

        createGroundBodies(offsetX);
    }

    public void createGroundBodies(int offsetX){
        for(int x = 0; x < Chunk.SIZE_X; x++){
            for(int y = 0; y < Chunk.SIZE_Y; y++){
                if(cells[x][y] == null || cells[x][y].getTile() == null || cells[x][y].getTile().getTextureRegion() != textures[0][0])
                    continue;

                int blockX = x * Chunk.TILE_SIZE + offsetX * Chunk.SIZE_X * Chunk.TILE_SIZE;
                createGroundBody(blockX, y * Chunk.TILE_SIZE, Chunk.TILE_SIZE, Chunk.TILE_SIZE);
            }
        }
    }

    public void createGroundBody(float x, float y, float width, float height){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x + width / 2, y + height / 2);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);
        fdef.shape = shape;
        body.createFixture(fdef);
    }

    public TiledMapTileLayer.Cell getCell(int x, int y){
        return cells[x][y];
    }

    public void render(SpriteBatch batch){
        batch.begin();
        for(int x = 0; x < cells.length; x++){
            for(int y = 0; y < cells[x].length; y++) {
                int blockX = x * Chunk.TILE_SIZE + offsetX * Chunk.SIZE_X * Chunk.TILE_SIZE;
                batch.draw(textures[0][2], blockX, y * Chunk.TILE_SIZE, Chunk.TILE_SIZE, Chunk.TILE_SIZE);

                if (cells[x][y] == null || cells[x][y].getTile() == null)
                    continue;

                batch.draw(cells[x][y].getTile().getTextureRegion(), blockX, y * Chunk.TILE_SIZE, Chunk.TILE_SIZE, Chunk.TILE_SIZE);
            }
        }
        batch.end();
    }

}
