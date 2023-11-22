package fr.epitech.game.map;

import com.badlogic.gdx.physics.box2d.*;
import fr.epitech.game.entitys.projectiles.Fireball;
import fr.epitech.game.managers.EntityManager;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        final Fixture fixtureA = contact.getFixtureA(), fixtureB = contact.getFixtureB();

        if(fixtureA.getBody() == null || fixtureB.getBody() == null || fixtureA.getBody().getUserData() == null || fixtureB.getBody().getUserData() == null)
            return;

        final Object userDataA = fixtureA.getBody().getUserData(), userDataB = fixtureB.getBody().getUserData();

        if(userDataA instanceof Fireball || userDataB instanceof Fireball) {
            final boolean fireballIsFixtureA = userDataA instanceof Fireball;

            if(fireballIsFixtureA && userDataB instanceof Fireball)
                return;

            final Fireball fireball = fireballIsFixtureA ? (Fireball) userDataA : (Fireball) userDataB;
            final Object fixture = !fireballIsFixtureA ? userDataA : userDataB;

            fireball.getEntityManager().createExplosion(fireball.getPosition(), fireball.getDamage(), fireball.getCategoryBits(), fireball.getMaskBits());
            fireball.destroy();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
