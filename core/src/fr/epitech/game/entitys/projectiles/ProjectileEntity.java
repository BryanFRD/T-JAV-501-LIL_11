package fr.epitech.game.entitys.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import fr.epitech.game.entitys.Entity;
import fr.epitech.game.entitys.movablesEntitys.enemys.Enemy;
import fr.epitech.game.managers.EntityManager;

import java.util.ArrayList;
import java.util.List;

public abstract class ProjectileEntity extends Entity {

    protected Vector2 startPosition;
    protected float speed = 1000f;
    protected float damage = 10f;
    protected float range = 1000f;

    public ProjectileEntity(SpriteBatch batch, World world, Vector2 coordinate, String name, TextureRegion[] textureRegions, EntityManager entityManager, float angle, short categoryBits, short maskBits) {
        super(batch, world, name, coordinate, textureRegions, entityManager, null, categoryBits, maskBits);
        this.startPosition = coordinate;
        this.angle = angle;
        this.frameDuration = 0.05f;
        this.forcedAnimation = true;
    }

    public void update(float delta){
        super.update(delta);

        if(this.startPosition.dst(this.getPosition()) > this.range){
            destroy();
        }

        float x = (float) (Math.cos(angle) * speed) * delta;
        float y = (float) (Math.sin(angle) * speed) * delta;

        this.b2body.setLinearVelocity(x, y);
    }

    public void destroy(){
        this.entityManager.removeProjectile(this);
    }

    public float getDamage() {
        return damage;
    }

}
