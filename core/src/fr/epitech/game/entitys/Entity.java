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

    protected short categoryBits, maskBits;
    protected EntityManager entityManager;
    protected WaveManager waveManager;
    protected SpriteBatch batch;
    protected Body b2body;
    protected String name;
    protected float angle = 0f;
    protected Vector2 coordinate;
    protected World world;
    protected TextureRegion[] textureRegions;
    protected Animation<TextureRegion> animation;
    protected float stateTime;
    protected float frameDuration = 0.20f;
    protected float width = 0.5f;
    protected float height = 1;
    protected boolean reverted = true;
    protected boolean forcedAnimation = false;
    protected boolean entityDefined = false;

    public Entity(SpriteBatch batch, World world, String name, Vector2 coordinate, Texture texture, EntityManager entityManager, WaveManager waveManager, short categoryBits, short maskBits) {
        super(texture);
        System.out.println("Entity created");
        this.batch = batch;
        this.name = name;
        this.coordinate = coordinate;
        this.world = world;
        this.entityManager = entityManager;
        this.waveManager = waveManager;
        this.categoryBits = categoryBits;
        this.maskBits = maskBits;
        defineEntity();
    }

    public Entity(SpriteBatch batch, World world, String name, Vector2 coordinate, TextureRegion[] textureRegions, EntityManager entityManager, WaveManager waveManager, short categoryBits, short maskBits){
        super(textureRegions[0] != null ? textureRegions[0] : new TextureRegion(new Texture("badlogic.jpg")));
        this.batch = batch;
        this.name = name;
        this.coordinate = coordinate;
        this.world = world;
        this.textureRegions = textureRegions;
        this.animation = new Animation<>(this.frameDuration, textureRegions);
        this.entityManager = entityManager;
        this.waveManager = waveManager;
        this.categoryBits = categoryBits;
        this.maskBits = maskBits;
        defineEntity();
    }

    public Entity(SpriteBatch batch, World world, String name, Vector2 coordinate, Texture texture, EntityManager entityManager, WaveManager waveManager, short categoryBits, short maskBits, boolean defineEntity) {
        super(texture);
        System.out.println("Entity created");
        this.batch = batch;
        this.name = name;
        this.coordinate = coordinate;
        this.world = world;
        this.entityManager = entityManager;
        this.waveManager = waveManager;
        this.categoryBits = categoryBits;
        this.maskBits = maskBits;

        if(defineEntity){
            defineEntity();
        }
    }

    public Entity(SpriteBatch batch, World world, String name, Vector2 coordinate, TextureRegion[] textureRegions, EntityManager entityManager, WaveManager waveManager, short categoryBits, short maskBits, boolean defineEntity){
        super(textureRegions[0] != null ? textureRegions[0] : new TextureRegion(new Texture("badlogic.jpg")));
        this.batch = batch;
        this.name = name;
        this.coordinate = coordinate;
        this.world = world;
        this.textureRegions = textureRegions;
        this.animation = new Animation<>(this.frameDuration, textureRegions);
        this.entityManager = entityManager;
        this.waveManager = waveManager;
        this.categoryBits = categoryBits;
        this.maskBits = maskBits;

        if(defineEntity){
            defineEntity();
        }
    }

    public void defineEntity() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(coordinate.x, coordinate.y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.width, this.height);
        fdef.filter.categoryBits = this.categoryBits;
        fdef.filter.maskBits = this.maskBits;

        fdef.shape = shape;
        b2body.createFixture(fdef);
        b2body.setUserData(this);
        shape.dispose();

        entityDefined = true;
    }

    public String getName() {
        return name;
    }

    public Vector2 getPosition() {
        return b2body.getPosition();
    }

    public void update(float delta) {
        if(!entityDefined){
            return;
        }

        if(this.b2body.getLinearVelocity().x != 0 || forcedAnimation){
            if(this.animation != null){
                this.stateTime += stateTime == 0 ? frameDuration : delta;
            }
        } else {
            this.stateTime = 0;
        }

        setBounds(b2body.getPosition().x - width, b2body.getPosition().y - height, width * 2, height * 2);
    }

    public void render() {
        if(!entityDefined){
            return;
        }

        batch.begin();

        float x = b2body.getPosition().x - width * 2 * (reverted ? -1 : 1);
        float y = b2body.getPosition().y - height;
        float width = this.width * 4 * (reverted ? -1 : 1);
        float height = this.height * 2;

        if(this.animation != null){
            TextureRegion textureRegion = this.animation.getKeyFrame(stateTime, true);
            batch.draw(textureRegion, x, y, width, height);
        } else if(this.getTexture() != null){
            Texture texture = this.getTexture();
            batch.draw(texture, x, y, width, height);
        }

        batch.end();
    }

    public short getCategoryBits() {
        return categoryBits;
    }

    public short getMaskBits() {
        return maskBits;
    }

    public Body getB2body() {
        return b2body;
    }

    public void dispose(){
        if(getTexture() != null){
            getTexture().dispose();
        }
    }

    public void delete(){
        if(!world.isLocked()){
            world.destroyBody(b2body);
            dispose();
        }
    }

    public World getWorld() {
        return world;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }

    public boolean isEntityDefined() {
        return entityDefined;
    }
}
