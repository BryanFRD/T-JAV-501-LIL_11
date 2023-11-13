package fr.epitech.game.entitys.movablesEntitys.enemys;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import fr.epitech.game.map.WorldMap;
import fr.epitech.game.screens.PlayScreen;

public class Zombie extends Enemy{

    private float StateTime;
    private Animation walkAnimation;
    private Array<Texture> frames;
    private Vector2 velocity = new Vector2(0, 0);

    public Zombie(SpriteBatch batch, World world, PlayScreen screen) {
        super(batch, world, screen, "Zombie");
        frames = new Array<Texture>();
        for (int i = 0; i < 4; i++)
            frames.add(new Texture("assets/monster_58.png"));
        walkAnimation = new Animation(0.4f, frames);
        StateTime = 0;
        defineEnemy();
    }

    public void update(float dt){
        StateTime += dt;
        b2body.setLinearVelocity(velocity);
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion((Texture) walkAnimation.getKeyFrame(StateTime, true));
    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32, 32);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(16);

        fdef.shape = shape;
        b2body.createFixture(fdef);

    }
}
