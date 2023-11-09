package fr.epitech.game.entitys.enemys;

import com.badlogic.gdx.math.Vector2;
public class Witch extends Enemy{
    public Witch(String name, Vector2 coordinate) {
        super("Witch", coordinate, 100, 0.5f);
    }

    public void moveTo(float x, float y){
        super.moveTo(x, y);
    }
}
