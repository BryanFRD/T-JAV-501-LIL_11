package fr.epitech.game.entitys.enemys;

import com.badlogic.gdx.math.Vector2;

public class Skeleton extends Enemy{
    public Skeleton(String name, Vector2 coordinate) {
        super("Skeleton", coordinate, 50, 1.5f);
    }

    public void moveTo(float x, float y){
        super.moveTo(x, y);
    }
}
