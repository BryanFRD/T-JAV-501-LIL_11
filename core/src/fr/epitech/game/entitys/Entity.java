package fr.epitech.game.Entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;


public abstract class Entity implements fr.epitech.game.Updatable.Updatable, fr.epitech.game.Renderable.Renderable{
    protected String name;
    protected Sprite sprite;
    protected Vector2 coordinate;

    public Entity(String name, Vector2 coordinate) {
        this.name = getName();
        this.sprite = new Sprite();
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
