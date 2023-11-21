package fr.epitech.game.entitys;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;

public class ExplosionEntity extends Entity {

    protected float timeLeft = 5;
    protected float damage;

    public ExplosionEntity(SpriteBatch batch, World world, Vector2 coordinate, EntityManager entityManager, WaveManager waveManager, float damage, short categoryBits, short maskBits, boolean defineEntity) {
        super(batch, world, "Explosion", coordinate, new TextureRegion(new Texture("explosiontip1_32x32.png")).split(32, 32)[0], entityManager, waveManager, categoryBits, maskBits, defineEntity);
        this.damage = damage;
        frameDuration = 0.05f;
    }

    @Override
    public void defineEntity() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(coordinate.x, coordinate.y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(1f);
        fdef.filter.categoryBits = this.categoryBits;
        fdef.filter.maskBits = this.maskBits;

        fdef.shape = shape;
        b2body.createFixture(fdef);
        b2body.setUserData(this);
        shape.dispose();

        entityDefined = true;
    }
}
