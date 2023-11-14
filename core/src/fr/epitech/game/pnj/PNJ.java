package fr.epitech.game.pnj;

import fr.epitech.game.inventorys.items.Item;

import java.util.Map;

public class PNJ {
    protected Map<Item, Integer> items;
    public Map<Item, Integer> getItems() {
        return items;
    }
}
