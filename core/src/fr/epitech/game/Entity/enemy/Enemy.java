package fr.epitech.game.Entity.enemy;

import com.badlogic.gdx.math.Vector2;
import fr.epitech.game.Entity.MovableEntity.MovableEntity;


public abstract class enemy extends MovableEntity {
    public enemy(String name, Vector2 coordinate) {
        super(name, coordinate);
    }

    public void moveTo(float x, float y){
        super.moveTo(x, y);
    }

}
