package fr.epitech.game.entitys.movablesEntitys.enemys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.math.Vector2;
import fr.epitech.game.entitys.movablesEntitys.MovableEntity;
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;
import fr.epitech.game.screens.PlayScreen;
import fr.epitech.game.map.WorldMap;


public abstract class Enemy extends MovableEntity {
    protected World world;
    public Body b2body;

    public Enemy(SpriteBatch batch, World world, String name, Vector2 coordinate, TextureRegion[] texture, EntityManager entityManager, WaveManager waveManager){
        super(batch, world, name, coordinate, texture, entityManager, waveManager);
        this.world = world;
    }

    public Enemy(SpriteBatch batch, World world, String name, Vector2 coordinate, Texture texture, EntityManager entityManager, WaveManager waveManager) {
        super(batch, world, name, coordinate, texture, entityManager, waveManager);
        this.world = world;
        moveTo(0, 1000);
    }

    public void moveTo(float x, float y){
        super.moveTo(x, y);
    }
}