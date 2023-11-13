package fr.epitech.game.entitys.movablesEntitys.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Barbarian extends Character{

    public Barbarian(SpriteBatch batch, World world, String name, Vector2 coordinate) {
        super(batch, world, name, coordinate, new TextureRegion(new Texture("knight1.png")).split(16, 16)[0][1].getTexture(), 0, 0);
    }

}
