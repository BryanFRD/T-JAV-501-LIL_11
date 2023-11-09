package fr.epitech.game.Inventory.Item;

public abstract class Item extends fr.epitech.game.Inventory.Inventory{

    protected String name;

    public Item(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
