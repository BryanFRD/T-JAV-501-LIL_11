package fr.epitech.game.entitys.enemys;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Skeleton extends Enemy{
    public Skeleton(World world, String name, Vector2 coordinate, Texture texture, Integer health, Float speed) {
        super(world, "Skeleton", coordinate, texture, 50, 1.5f);
    }

    public void moveTo(float x, float y){
        super.moveTo(x, y);
    }
}
