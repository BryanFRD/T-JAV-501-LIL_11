package fr.epitech.game.entitys.enemys;

import com.badlogic.gdx.math.Vector2;
import fr.epitech.game.entitys.movablesEntitys.MovableEntity;


public abstract class Enemy extends MovableEntity {
    public Enemy(String name, Vector2 coordinate, Integer health, Float speed) {
        super(name, coordinate);
    }

    public void moveTo(float x, float y){
        super.moveTo(x, y);
    }
}