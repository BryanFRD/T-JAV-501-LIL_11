package fr.epitech.game.entitys;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;


public abstract class Entity extends Sprite {

    protected EntityManager entityManager;
    protected WaveManager waveManager;
    protected SpriteBatch batch;
    protected Body b2body;
    protected String name;
    protected Vector2 coordinate;
    protected World world;
    protected TextureRegion[] textureRegions;
    protected Animation<TextureRegion> animation;
    protected float stateTime;
    protected float frameDuration = 0.25f;
    protected float width = 0.5f;
    protected float height = 1;
    protected boolean reverted = true;

    public Entity(SpriteBatch batch, World world, String name, Vector2 coordinate, Texture texture, EntityManager entityManager, WaveManager waveManager) {
        super(texture);
        this.batch = batch;
        this.name = name;
        this.coordinate = coordinate;
        this.world = world;
        this.entityManager = entityManager;
        this.waveManager = waveManager;
        defineEntity();
    }

    public Entity(SpriteBatch batch, World world, String name, Vector2 coordinate, TextureRegion[] textureRegions, EntityManager entityManager, WaveManager waveManager){
        super(textureRegions[0]);
        this.batch = batch;
        this.name = name;
        this.coordinate = coordinate;
        this.world = world;
        this.textureRegions = textureRegions;
        this.animation = new Animation<>(this.frameDuration, textureRegions);
        this.entityManager = entityManager;
        this.waveManager = waveManager;
        defineEntity();
    }

    public void defineEntity() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(coordinate.x, coordinate.y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        world.setContinuousPhysics(true);
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.width, this.height);

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
        batch.begin();

        if(this.animation != null){
            TextureRegion textureRegion = this.animation.getKeyFrame(stateTime, true);
            batch.draw(textureRegion, b2body.getPosition().x - width * 2 * (reverted ? -1 : 1), b2body.getPosition().y - height, width * 4 * (reverted ? -1 : 1), height * 2);
        } else if(this.getTexture() != null){
            Texture texture = this.getTexture();
            batch.draw(texture, b2body.getPosition().x - width * 2 * (reverted ? -1 : 1), b2body.getPosition().y - height, width * 4 * (reverted ? -1 : 1), height * 2);
        }

        batch.end();
    }


}
