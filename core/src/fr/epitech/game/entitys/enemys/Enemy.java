package fr.epitech.game.entitys.enemys;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import fr.epitech.game.entitys.movablesEntitys.MovableEntity;


public abstract class Enemy extends MovableEntity {
    public Enemy(World world, String name, Vector2 coordinate, Texture texture,  Integer health, Float speed) {
        super(world, name, coordinate, texture);
    }

    public void moveTo(float x, float y){
        super.moveTo(x, y);
    }
}