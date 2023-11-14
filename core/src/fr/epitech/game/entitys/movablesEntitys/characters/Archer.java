package fr.epitech.game.entitys.movablesEntitys.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

public class Archer extends Character {

    public Archer(SpriteBatch batch, World world, String name, Vector2 coordinate) {
        super(batch, world, name, coordinate, new TextureRegion(new Texture("rogue.png")).split(16, 16)[new Random().nextInt(4)], 0, 100, 100);
    }

}
