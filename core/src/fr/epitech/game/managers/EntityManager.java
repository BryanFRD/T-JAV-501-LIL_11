package fr.epitech.game.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import fr.epitech.game.entitys.Entity;
import fr.epitech.game.entitys.ExplosionEntity;
import fr.epitech.game.entitys.movablesEntitys.enemys.Skeleton;
import fr.epitech.game.entitys.movablesEntitys.enemys.Witch;
import fr.epitech.game.entitys.projectiles.ProjectileEntity;
import fr.epitech.game.entitys.movablesEntitys.characters.Character;
import fr.epitech.game.entitys.movablesEntitys.enemys.Enemy;
import fr.epitech.game.entitys.movablesEntitys.enemys.Zombie;
import jdk.internal.icu.text.UnicodeSet;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityManager {

    private Character player;
    private final List<Enemy> enemies;
    private final List<ProjectileEntity> projectiles;
    private final SpriteBatch batch;
    private final World world;
    private final List<ProjectileEntity> deletedProjectiles;
    private final List<ExplosionEntity> explosionEntities;
    private final List<ExplosionEntity> deletedExplosionEntities;

    public EntityManager(SpriteBatch batch, World world){
        this.enemies = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.deletedProjectiles = new ArrayList<>();
        this.explosionEntities = new ArrayList<>();
        this.deletedExplosionEntities = new ArrayList<>();
        this.batch = batch;
        this.world = world;
    }

    public void update(float delta){
        for(Enemy enemy : enemies.toArray(new Enemy[0])){
            if(enemy.getHealth() == 0){
                enemy.delete();
                enemies.remove(enemy);
                continue;
            }
            enemy.update(delta);
        }

        if(!projectiles.isEmpty()){
            for (ProjectileEntity projectile : projectiles.toArray(new ProjectileEntity[0])) {
                projectile.update(delta);
            }
        }

        if(!deletedProjectiles.isEmpty()){
            for (ProjectileEntity deletedProjectile : deletedProjectiles.toArray(new ProjectileEntity[0])) {
                deletedProjectile.delete();
                deletedProjectiles.remove(deletedProjectile);
            }
        }

        if(!explosionEntities.isEmpty()){
            for (ExplosionEntity explosionEntity : explosionEntities.toArray(new ExplosionEntity[0])) {
                if(explosionEntity.isEntityDefined()){
                    explosionEntity.update(delta);
                } else {
                    explosionEntity.defineEntity();
                }
            }
        }

        player.update(delta);
    }

    public void render(){
        for(Enemy enemy : enemies){
            enemy.render();
        }

        for (ProjectileEntity projectile : projectiles) {
            projectile.render();
        }

        for (ExplosionEntity explosionEntity : explosionEntities) {
            explosionEntity.render();
        }

        player.render();
    }

    public void generateEnemies(int wave){
        while (0 != wave){
            enemies.add(new Zombie(batch, world, new Vector2(player.getPosition().x + 150, 100), this, null));
            enemies.add(new Witch(batch, world, new Vector2(player.getPosition().x + 200, 100), this, null));
            enemies.add(new Skeleton(batch, world, new Vector2(player.getPosition().x + 250, 100), this, null));
            wave--;
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

    public void addProjectiles(ProjectileEntity projectile){
        this.projectiles.add(projectile);
    }

    public List<ProjectileEntity> getProjectiles() {
        return projectiles;
    }

    public void removeProjectile(ProjectileEntity projectile){
        projectile.delete();
        this.projectiles.remove(projectile);
        this.deletedProjectiles.add(projectile);
    }

    public void createExplosion(Vector2 position, float damage, short categoryBits, short maskBits){
        ExplosionEntity explosionEntity = new ExplosionEntity(batch, world, position, this, null, damage, categoryBits, maskBits, false);
        explosionEntities.add(explosionEntity);
    }

    public void removeExplosion(ExplosionEntity explosionEntity){
        explosionEntity.delete();
        this.explosionEntities.remove(explosionEntity);
        this.deletedExplosionEntities.add(explosionEntity);
    }

}
