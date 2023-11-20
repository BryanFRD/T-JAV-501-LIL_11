package fr.epitech.game.inventorys.items.equipables;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import fr.epitech.game.inventorys.items.Item;
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;

public abstract class EquipableItem extends Item implements Equipable {

    protected EquipableItem(SpriteBatch batch, World world, EntityManager entityManager, String name) {
        super(batch, world, entityManager, name);
    }

}
