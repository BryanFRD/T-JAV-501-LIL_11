package fr.epitech.game.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.epitech.game.entitys.movablesEntitys.enemys.Enemy;
import fr.epitech.game.entitys.projectiles.Fireball;
import fr.epitech.game.entitys.projectiles.ProjectileEntity;

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
        this.b2dr = new Box2DDebugRenderer();

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if(contact.getFixtureA().getUserData() == null || contact.getFixtureB().getUserData() == null)
                    return;

                if(contact.getFixtureA().getUserData() instanceof Fireball || contact.getFixtureB().getUserData() instanceof Fireball) {
                    boolean FireballIsFixtureA = contact.getFixtureA().getUserData() instanceof Fireball;
                    if(FireballIsFixtureA && contact.getFixtureB().getUserData() instanceof Fireball)
                        return;

                    Fireball fireball = FireballIsFixtureA ? (Fireball) contact.getFixtureA().getUserData() : (Fireball) contact.getFixtureB().getUserData();
                    Object fixture = !FireballIsFixtureA ? contact.getFixtureA().getUserData() : contact.getFixtureB().getUserData();
                    if(fixture instanceof Chunk){
                        fireball.destroy();
                    } else if(fixture instanceof Enemy){
                        Enemy enemy = (Enemy) fixture;
                        enemy.receiveDamage(fireball.getDamage());
                        fireball.destroy();
                    }
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    public void update(float delta){
        world.step(delta, 6, 2);
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

    public void updatePlayerPosition(float playerX, float playerY){
        this.playerX = (int) playerX / (Chunk.TILE_SIZE * Chunk.SIZE_X);
        this.playerY = (int) playerY / (Chunk.TILE_SIZE * Chunk.SIZE_Y);
    }

    public World getWorld() {
        return world;
    }

    public Box2DDebugRenderer getBox2DRenderer() {
        return b2dr;
    }

}
