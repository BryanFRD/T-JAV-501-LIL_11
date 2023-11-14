package fr.epitech.game.entitys.movablesEntitys.enemys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.math.Vector2;
import fr.epitech.game.entitys.movablesEntitys.MovableEntity;
import fr.epitech.game.screens.PlayScreen;
import fr.epitech.game.map.WorldMap;


public abstract class Enemy extends MovableEntity {
    protected World world;
    public Body b2body;

    public Enemy(SpriteBatch batch,World world, String name, Vector2 coordinate, TextureRegion[] texture){
    super(batch, world, name, coordinate, texture);
    this.world = world;
    moveTo(32, 32);
    }

    public Enemy(SpriteBatch batch, World world, String zombie, Vector2 coordinate, Texture texture) {
        super( batch, world, zombie, coordinate, texture);
        this.world = world;
        moveTo(0, 1000);
    }

    public void moveTo(float x, float y){
        super.moveTo(x, y);
    }
}