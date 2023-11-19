package fr.epitech.game.inventorys.items.consumables.potions;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import fr.epitech.game.inventorys.items.consumables.ConsumableItem;
import fr.epitech.game.managers.EntityManager;

public class Potion extends ConsumableItem {
    public Potion(SpriteBatch batch, World world, EntityManager entityManager, String name) {
        super(batch, world, entityManager, name);
    }
}
