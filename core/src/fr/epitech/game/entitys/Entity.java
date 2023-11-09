package fr.epitech.game.entitys;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;


public abstract class Entity extends Sprite {
    protected String name;
    protected Vector2 coordinate;

    public Entity(String name, Vector2 coordinate, Texture texture) {
        super(texture)
        this.name = getName();
        this.coordinate = new Vector2();
    }

    public String getName() {
        return name;
    }

    public Vector2 getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Vector2 coordinate) {
        this.coordinate = coordinate;
    }
}
