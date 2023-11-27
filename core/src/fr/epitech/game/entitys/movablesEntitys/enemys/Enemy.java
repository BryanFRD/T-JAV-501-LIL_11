package fr.epitech.game.entitys.movablesEntitys.enemys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.math.Vector2;
import fr.epitech.game.EpiGame;
import fr.epitech.game.directions.Direction;
import fr.epitech.game.entitys.movablesEntitys.MovableEntity;
import fr.epitech.game.entitys.movablesEntitys.characters.Character;
import fr.epitech.game.entitys.projectiles.Fireball;
import fr.epitech.game.inventorys.items.equipables.weapons.Weapon;
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;
import fr.epitech.game.screens.PlayScreen;
import fr.epitech.game.map.WorldMap;


public abstract class Enemy extends MovableEntity {
    protected World world;
    protected float lastx = 1, curretx;
    protected int experienceGiven = 1;

    public Enemy(SpriteBatch batch, World world, String name, Vector2 coordinate, TextureRegion[] texture, EntityManager entityManager, WaveManager waveManager){
        super(batch, world, name, coordinate, texture, entityManager, waveManager, EpiGame.ENEMY_BIT, (short) (EpiGame.WORLD_BIT | EpiGame.PLAYER_BIT | EpiGame.ENEMY_BIT));
        this.world = world;
    }

    public Enemy(SpriteBatch batch, World world, String zombie, Vector2 coordinate, Texture texture, EntityManager entityManager, WaveManager waveManager) {
        super( batch, world, zombie, coordinate, texture, entityManager, waveManager, EpiGame.ENEMY_BIT, (short) (EpiGame.WORLD_BIT | EpiGame.PLAYER_BIT | EpiGame.ENEMY_BIT));
        this.world = world;
    }


    @Override
    public void update(float delta) {
        super.update(delta);

        if(!this.entityDefined){
            return;
        }

        curretx = b2body.getPosition().x;
        float angle = MathUtils.atan2(entityManager.getPlayer().getPosition().y - b2body.getPosition().y, entityManager.getPlayer().getPosition().x - b2body.getPosition().x);


        if (entityManager.getPlayer().getPosition().x - b2body.getPosition().x < 2f && entityManager.getPlayer().getPosition().x - b2body.getPosition().x > -2f) {
            directions.remove(Direction.UP);
            directions.remove(Direction.LEFT);
            directions.remove(Direction.RIGHT);


        } else if (curretx == lastx) {
            directions.add(Direction.UP);
        } else if (entityManager.getPlayer().getPosition().x > b2body.getPosition().x) {
            directions.remove(Direction.UP);
            directions.remove(Direction.LEFT);
            this.lastx = curretx;
            directions.add(Direction.RIGHT);

        } else if (entityManager.getPlayer().getPosition().x < b2body.getPosition().x) {
            directions.remove(Direction.UP);
            directions.remove(Direction.RIGHT);
            this.lastx = curretx;
            directions.add(Direction.LEFT);

        }

        attack(angle);
    }

    public int getExperienceGiven(){
        return experienceGiven;
    }
  
    public abstract void attack(float angle);
}