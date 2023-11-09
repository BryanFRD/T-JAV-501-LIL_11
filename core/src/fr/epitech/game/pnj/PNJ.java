package fr.epitech.game.PNJ;

import java.util.Map;
import fr.epitech.game.Inventory.Item.Item;
public class PNJ {
    protected Map<Item, Integer> items;
    public Map<Item, Integer> getItems() {
        return items;
    }
}
