package fr.epitech.game.inventorys;

import fr.epitech.game.inventorys.items.Item;
import fr.epitech.game.inventorys.items.equipables.armors.Armor;
import fr.epitech.game.inventorys.items.equipables.weapons.Weapon;
import java.util.Map;

public class Inventory {
    protected Map<Item, Integer> items;


    public Map<Item, Integer> getItems() {
        return items;
    }

    public void addItem(Item item) {

    }

    public void removeItem(Item item) {

    }

    public Armor getArmor() {
        return null;
    }
    public Weapon getWeapon() {
        return null;
    }

    public Integer getDamage() {
        return null;
    }
}
