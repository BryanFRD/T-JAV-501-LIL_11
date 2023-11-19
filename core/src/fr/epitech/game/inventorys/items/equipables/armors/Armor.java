package fr.epitech.game.inventorys.items.equipables.armors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import fr.epitech.game.managers.EntityManager;

public class Armor extends fr.epitech.game.inventorys.items.equipables.EquipableItem {

    protected float defense;

    public Armor(SpriteBatch batch, World world, EntityManager entityManager, String name, float defense) {
        super(batch, world, entityManager, name);
        this.defense = defense;
    }

    public float getDefense() {
        return defense;
    }

}
