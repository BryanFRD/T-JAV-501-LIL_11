package fr.epitech.game.entitys;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;


public abstract class Entity extends Sprite {

    protected Body b2body; 

    protected String name;
    protected Vector2 coordinate;

    protected World world;

    public Entity(World world, String name, Vector2 coordinate, Texture texture) {
        super(texture);
        setColor(Color.RED);
        this.name = name;
        this.coordinate = coordinate;
        this.world = world;
        defineEntity();
    }

    public void defineEntity() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(coordinate.x,coordinate.y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.5f);

        fdef.shape = shape;
        b2body.createFixture(fdef);
        shape.dispose();
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

    public void render() {
    }


}
