package fr.epitech.game.map;

import com.badlogic.gdx.physics.box2d.*;
import fr.epitech.game.entitys.movablesEntitys.enemys.Enemy;
import fr.epitech.game.entitys.projectiles.Fireball;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        final Fixture fixtureA = contact.getFixtureA(), fixtureB = contact.getFixtureB();

        if(fixtureA.getBody() == null || fixtureB.getBody() == null || fixtureA.getBody().getUserData() == null || fixtureB.getBody().getUserData() == null)
            return;

        Object userDataA = fixtureA.getBody().getUserData(), userDataB = fixtureB.getBody().getUserData();

        if(userDataA instanceof Fireball || userDataB instanceof Fireball) {
            boolean FireballIsFixtureA = userDataA instanceof Fireball;
            if(FireballIsFixtureA && userDataB instanceof Fireball)
                return;

            Fireball fireball = FireballIsFixtureA ? (Fireball) userDataA : (Fireball) userDataB;
            Object fixture = !FireballIsFixtureA ? userDataA : userDataB;

            if(fixture instanceof Chunk){
                fireball.destroy();
            } else if(fixture instanceof Enemy){
                Enemy enemy = (Enemy) fixture;
                enemy.receiveDamage(fireball.getDamage());
                fireball.destroy();
            }
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
