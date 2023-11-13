package fr.epitech.game.entitys.movablesEntitys.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;
import fr.epitech.game.entitys.movablesEntitys.MovableEntity;
import fr.epitech.game.inventorys.Inventory;

public abstract class Character extends MovableEntity {

    protected int gold;

    protected int capacity;

    public Character(SpriteBatch batch, World world, String name, Vector2 coordinate, Texture texture, int gold, int capacity) {
        super(batch, world, name, coordinate, texture);
        this.gold = gold;
        this.capacity = capacity;
    }

}
