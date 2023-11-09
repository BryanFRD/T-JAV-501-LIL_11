package fr.epitech.game.entitys.enemys;

import com.badlogic.gdx.math.Vector2;

public class Zombie extends Enemy{

    public Zombie(String name, Vector2 coordinate, Integer health, Float speed) {
        super("Zombie", coordinate, 80, 0.8f );
    }

    public void moveTo(float x, float y){
        super.moveTo(x, y);
    }
}
