package fr.epitech.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import fr.epitech.game.SimplexNoise;

public class Chunk {

    public static final int SIZE_X = 16, SIZE_Y = 64, TILE_SIZE = 32;

    private final TiledMapTileLayer.Cell[][] cells;
    private final double seed;
    private final TextureRegion[][] textures;
    private int offsetX;

    public Chunk(double seed){
        this.cells = new TiledMapTileLayer.Cell[Chunk.SIZE_X][Chunk.SIZE_Y];
        this.seed = Math.abs(seed);
        this.textures = new TextureRegion(new Texture("tiles.png")).split(16, 16);
    }

    public void generateChunk(int offsetX){
        this.offsetX = offsetX;
        for(int x = 0; x < cells.length; x++){
            int blockX = x + offsetX * Chunk.SIZE_X;
            System.out.println(seed);
            double noise = SimplexNoise.noise(blockX / 100f, seed) * 10 + 20;
            for(int y = 0; y < cells[x].length; y++){
                if(noise < y) {
                    continue;
                }

                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                cell.setTile(new StaticTiledMapTile(noise > y + 1 ? textures[0][1] : textures[0][0]));
                cells[x][y] = cell;
            }
        }
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
