package fr.epitech.game.inventorys.items.equipables.weapons;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.epitech.game.entitys.Entity;
import fr.epitech.game.inventorys.items.equipables.EquipableItem;
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;

public abstract class Weapon extends EquipableItem {

    protected float damage;
    protected Entity holder;

    public Weapon(SpriteBatch batch, World world, EntityManager entityManager, Entity holder, String name, float damage) {
        super(batch, world, entityManager, name);
        this.damage = damage;
        this.holder = holder;
    }

    public abstract void attack(float angle);

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

}
