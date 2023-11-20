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
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;
import fr.epitech.game.screens.PlayScreen;
import fr.epitech.game.map.WorldMap;


public abstract class Enemy extends MovableEntity {
    protected World world;
    float lastx, curretx;

    public Enemy(SpriteBatch batch, World world, String name, Vector2 coordinate, TextureRegion[] texture, EntityManager entityManager, WaveManager waveManager){
        super(batch, world, name, coordinate, texture, entityManager, waveManager, EpiGame.ENEMY_BIT, (short) (EpiGame.WORLD_BIT | EpiGame.PLAYER_BIT));
        this.world = world;
    }

    public Enemy(SpriteBatch batch, World world, String zombie, Vector2 coordinate, Texture texture, EntityManager entityManager, WaveManager waveManager) {
        super( batch, world, zombie, coordinate, texture, entityManager, waveManager, EpiGame.ENEMY_BIT, (short) (EpiGame.WORLD_BIT | EpiGame.PLAYER_BIT));
        this.world = world;
    }

    public void moveTo(float x, float y){

    }

    @Override
    public void update(float delta) {
        super.update(delta);
        curretx = b2body.getPosition().x;
        this.lastx = curretx;

        if ( curretx == this.lastx) {
            move(Direction.UP);
        }
        if (entityManager.getPlayer().getPosition().x - b2body.getPosition().x < 1.05 && entityManager.getPlayer().getPosition().x - b2body.getPosition().x > -1.05) {
            move(Direction.STOP);

        }else if (entityManager.getPlayer().getPosition().x > b2body.getPosition().x) {
            move(Direction.RIGHT);


        } else if (entityManager.getPlayer().getPosition().x < b2body.getPosition().x) {
            move(Direction.LEFT);


        }
    }
}