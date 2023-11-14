package fr.epitech.game.entitys;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;


public abstract class Entity extends Sprite {

    protected SpriteBatch batch;
    protected Body b2body;
    protected String name;
    protected Vector2 coordinate;
    protected World world;
    protected TextureRegion[] textureRegions;
    protected Animation<TextureRegion> animation;
    private float stateTime;

    public Entity(SpriteBatch batch, World world, String name, Vector2 coordinate, Texture texture) {
        super(texture);
        this.batch = batch;
        this.name = name;
        this.coordinate = coordinate;
        this.world = world;
        defineEntity();
    }

    public Entity(SpriteBatch batch, World world, String name, Vector2 coordinate, TextureRegion[] textureRegions){
        this.batch = batch;
        this.name = name;
        this.coordinate = coordinate;
        this.world = world;
        this.textureRegions = textureRegions;
        this.animation = new Animation<>(0.25f, textureRegions);
        defineEntity();
    }

    public void defineEntity() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(coordinate.x, coordinate.y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16, 32);

        fdef.friction = 100;
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

    public void update(float delta){
        if(this.animation != null){
            this.stateTime += delta;
        }
    }

    public void render() {
        batch.begin();

        if(this.getTexture() != null){
            Texture texture = this.getTexture();
            batch.draw(texture, b2body.getPosition().x - texture.getWidth() * 2, b2body.getPosition().y - texture.getHeight() * 2, 64, 64);
        } else if(this.animation != null){
            TextureRegion textureRegion = this.animation.getKeyFrame(stateTime, true);
            batch.draw(textureRegion, b2body.getPosition().x - textureRegion.getRegionWidth() * 2, b2body.getPosition().y - textureRegion.getRegionHeight() * 2, 64, 64);
        }

        batch.end();
    }


}
