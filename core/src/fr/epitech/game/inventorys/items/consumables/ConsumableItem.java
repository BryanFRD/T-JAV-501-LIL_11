package fr.epitech.game.inventorys.items.consumables;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import fr.epitech.game.inventorys.items.Item;
import fr.epitech.game.managers.EntityManager;

public abstract class ConsumableItem extends Item implements Consumable {
    public ConsumableItem(SpriteBatch batch, World world, EntityManager entityManager, String name) {
        super(batch, world, entityManager, name);
    }
}
