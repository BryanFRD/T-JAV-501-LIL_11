package fr.epitech.game.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.epitech.game.entitys.movablesEntitys.characters.Character;
import fr.epitech.game.entitys.movablesEntitys.enemys.Enemy;
import fr.epitech.game.entitys.movablesEntitys.enemys.Skeleton;
import fr.epitech.game.entitys.movablesEntitys.enemys.Witch;
import fr.epitech.game.entitys.movablesEntitys.enemys.Zombie;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    private Character player;
    private final List<Enemy> enemies;
    private final SpriteBatch batch;
    private final World world;

    public EntityManager(SpriteBatch batch, World world){
        this.enemies = new ArrayList<>();
        this.batch = batch;
        this.world = world;
    }

    public void update(float delta){
        player.update(delta);
        for(Enemy enemy : enemies){
            if(enemy.getHealth() == 0)
                enemies.remove(enemy);
            //enemy.update(delta);
        }
    }

    public void render(){
        for(Enemy enemy : enemies){
            enemy.render();
        }

        player.render();
    }

    public void generateEnemies(int wave){
        for(int i = 0; i < wave; i++){
            enemies.add(new Zombie(batch, world, new Vector2(), this, null));
            //enemies.add(new Witch(batch, world, new Vector2(), this, null));
            //enemies.add(new Skeleton(batch, world, new Vector2(), this, null));
        }
    }

    public Character getPlayer() {
        return player;
    }
    public void setPlayer(Character player) {
        this.player = player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

}
