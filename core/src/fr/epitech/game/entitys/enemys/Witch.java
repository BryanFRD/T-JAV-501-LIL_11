package fr.epitech.game.entitys.enemys;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
public class Witch extends Enemy{
    public Witch(World world, String name, Vector2 coordinate, Texture texture, Integer health, Float speed) {
        super(world, "Witch", coordinate, texture, 100, 0.5f);
    }

    public void moveTo(float x, float y){
        super.moveTo(x, y);
    }
}
