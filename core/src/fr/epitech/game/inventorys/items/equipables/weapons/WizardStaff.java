package fr.epitech.game.inventorys.items.equipables.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import fr.epitech.game.EpiGame;
import fr.epitech.game.entitys.Entity;
import fr.epitech.game.entitys.projectiles.Fireball;
import fr.epitech.game.managers.EntityManager;

public class WizardStaff extends Weapon {

    public WizardStaff(SpriteBatch batch, World world, EntityManager entityManager, Entity holder, String name, float damage) {
        super(batch, world, entityManager, holder, name, damage);
    }

    @Override
    public void attack(float angle) {
        Fireball fireball = new Fireball(batch, world, holder.getPosition(), entityManager, angle, damage, holder.getCategoryBits(), holder.getMaskBits());
        entityManager.addProjectiles(fireball);
    }

}
