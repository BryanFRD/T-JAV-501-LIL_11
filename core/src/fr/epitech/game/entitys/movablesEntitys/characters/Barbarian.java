package fr.epitech.game.entitys.movablesEntitys.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Barbarian extends Character{

    public Barbarian(World world, String name, Vector2 coordinate) {
        super(world, name, coordinate, new Texture("knight1.png"), 0, 0);
    }

}
