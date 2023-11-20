package fr.epitech.game.inventorys;

import fr.epitech.game.inventorys.items.Item;
import fr.epitech.game.inventorys.items.equipables.armors.Armor;
import fr.epitech.game.inventorys.items.equipables.weapons.Weapon;
import java.util.Map;

public class Inventory {

    //TODO: Implement this class

    protected Map<Item, Integer> items;
    protected Armor currentArmor;
    protected Weapon currentWeapon;

    public Map<Item, Integer> getItems() {
        return items;
    }

    public void addItem(Item item) {

    }

    public void removeItem(Item item) {

    }

    public Armor getArmor() {
        return this.currentArmor;
    }
    public Weapon getWeapon() {
        return this.currentWeapon;
    }

    public float getDamage() {
        return 0;
    }

    public void setCurrentWeapon(Weapon weapon) {
        this.currentWeapon = weapon;
    }

    public void setCurrentArmor(Armor armor) {
        this.currentArmor = armor;
    }

}
