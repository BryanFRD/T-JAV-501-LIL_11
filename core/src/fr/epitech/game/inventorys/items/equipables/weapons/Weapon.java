package fr.epitech.game.inventorys.items.equipables.weapons;


import com.badlogic.gdx.math.Vector2;
import fr.epitech.game.inventorys.items.equipables.EquipableItem;

public abstract class Weapon extends EquipableItem {

    public Weapon(String name) {
        super(name);
    }

    public abstract void attack(Vector2 playerLocation, float angle);

}
