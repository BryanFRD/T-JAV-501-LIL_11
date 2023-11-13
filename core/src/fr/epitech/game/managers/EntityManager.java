package fr.epitech.game.managers;

import fr.epitech.game.entitys.movablesEntitys.characters.Character;
import fr.epitech.game.entitys.movablesEntitys.enemys.Enemy;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    public final Character player;
    public final List<Enemy> enemies;

    public EntityManager(Character player){
        this.player = player;
        this.enemies = new ArrayList<>();
    }

    public void update(float delta){
        player.update(delta);
        for(Enemy enemy : enemies){
            if(enemy.getHealth() == 0)
                enemies.remove(enemy);
            enemy.update(delta);
        }
    }

    public void render(){
        for(Enemy enemy : enemies){
            enemy.render();
        }

        player.render();
    }

    public void generateEnemies(int wave){

    }

    public Character getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

}
