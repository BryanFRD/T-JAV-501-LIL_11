package fr.epitech.game.entitys.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import fr.epitech.game.managers.EntityManager;

public class Fireball extends ProjectileEntity {

    public Fireball(SpriteBatch batch, World world, Vector2 coordinate, EntityManager entityManager, float angle, float damage, short categoryBits, short maskBits) {
        super(batch, world, coordinate, "Fireball", new TextureRegion[5], entityManager, angle, categoryBits, maskBits);

        this.damage = damage;

        this.textureRegions[0] = new TextureRegion(new Texture("fireball_V_1_1/FB500-1.png"));
        this.textureRegions[1] = new TextureRegion(new Texture("fireball_V_1_1/FB500-2.png"));
        this.textureRegions[2] = new TextureRegion(new Texture("fireball_V_1_1/FB500-3.png"));
        this.textureRegions[3] = new TextureRegion(new Texture("fireball_V_1_1/FB500-4.png"));
        this.textureRegions[4] = new TextureRegion(new Texture("fireball_V_1_1/FB500-5.png"));

        this.width = 0.25f;
        this.height = 0.5f;
        this.animation = new Animation<>(this.frameDuration, this.textureRegions);

        this.speed = 1000f;
        b2body.setGravityScale(0);
    }

    public void defineEntity() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(coordinate.x, coordinate.y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(0.4f);
        fdef.filter.categoryBits = this.categoryBits;
        fdef.filter.maskBits = this.maskBits;

        fdef.shape = shape;
        b2body.createFixture(fdef);
        b2body.setUserData(this);
        shape.dispose();
    }

}
