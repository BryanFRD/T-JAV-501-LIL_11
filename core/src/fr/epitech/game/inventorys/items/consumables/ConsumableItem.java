package fr.epitech.game.inventorys.items.consumables;

import fr.epitech.game.inventorys.items.Item;
public abstract class ConsumableItem extends Item implements Consumable {
    public ConsumableItem(String name) {
        super(name);
    }
}
