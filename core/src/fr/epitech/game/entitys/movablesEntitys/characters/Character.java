package fr.epitech.game.entitys.movablesEntitys.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;
import fr.epitech.game.entitys.movablesEntitys.MovableEntity;
import fr.epitech.game.inventorys.Inventory;
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;

public abstract class Character extends MovableEntity {

    protected int gold;
    protected int capacity;
    protected int maxCapacity;

    public Character(SpriteBatch batch, World world, String name, Vector2 coordinate, Texture texture, EntityManager entityManager, WaveManager waveManager, int gold, int maxCapacity, int capacity) {
        super(batch, world, name, coordinate, texture, entityManager, waveManager);
        this.gold = gold;
        this.maxCapacity = maxCapacity;
        this.capacity = capacity;
    }

    public Character(SpriteBatch batch, World world, String name, Vector2 coordinate, TextureRegion[] textureRegions, EntityManager entityManager, WaveManager waveManager, int gold, int maxCapacity, int capacity){
        super(batch, world, name, coordinate, textureRegions, entityManager, waveManager);
        this.gold = gold;
        this.maxCapacity = maxCapacity;
        this.capacity = capacity;
    }

}
