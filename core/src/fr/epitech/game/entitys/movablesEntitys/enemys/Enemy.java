package fr.epitech.game.entitys.movablesEntitys.enemys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.math.Vector2;
import fr.epitech.game.EpiGame;
import fr.epitech.game.directions.Direction;
import fr.epitech.game.entitys.movablesEntitys.MovableEntity;
import fr.epitech.game.entitys.movablesEntitys.characters.Character;
import fr.epitech.game.inventorys.items.equipables.weapons.Weapon;
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;
import fr.epitech.game.screens.PlayScreen;
import fr.epitech.game.map.WorldMap;


public abstract class Enemy extends MovableEntity {
    protected World world;
    protected float lastx = 1, curretx;
    protected int experienceGiven = 5;

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

        if (entityManager.getPlayer().getPosition().x - b2body.getPosition().x < 1.25 && entityManager.getPlayer().getPosition().x - b2body.getPosition().x > -1.25) {
            move(Direction.STOP);
            this.lastx = 0;


        } else if (curretx == lastx) {
            move(Direction.UP);


        } else if (entityManager.getPlayer().getPosition().x > b2body.getPosition().x) {
            move(Direction.RIGHT);
            this.lastx = curretx;
            //this.lastx = curretx;


        } else if (entityManager.getPlayer().getPosition().x < b2body.getPosition().x) {
            move(Direction.LEFT);
            this.lastx = curretx;
            //this.lastx = curretx;

        }
    }

    public int getExperienceGiven(){
        return experienceGiven;
    }

}