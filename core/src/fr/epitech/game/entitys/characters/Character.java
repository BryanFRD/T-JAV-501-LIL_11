package fr.epitech.game.Entity.Character;

import com.badlogic.gdx.math.Vector2;
import fr.epitech.game.Entity.MovableEntity.MovableEntity;

public abstract class Character extends MovableEntity {
    protected Integer gold;
    protected Integer capacity;

    public Character(String name, Vector2 coordinate) {
        super(name, coordinate);
    }

    public Integer getGold() {
        return gold;
    }
    public void addGold(Integer gold) {
        this.gold += gold;
    }

    public void removeGold(Integer gold) {
        this.gold -= gold;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void regenerateCapacity() {
    }

}
