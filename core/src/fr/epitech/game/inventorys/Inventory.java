package fr.epitech.game.Inventory;

import fr.epitech.game.Inventory.Item.Item;
import fr.epitech.game.Inventory.Item.Equipable.Armor.Armor;
import fr.epitech.game.Inventory.Item.Equipable.Weapon.Weapon;
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
