package fr.epitech.game.entitys.movablesEntitys;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import fr.epitech.game.Direction.Direction;
import fr.epitech.game.inventorys.Inventory;
import fr.epitech.game.inventorys.items.equipables.armors.Armor;
import fr.epitech.game.inventorys.items.equipables.weapons.Weapon;
import org.w3c.dom.Text;

public abstract class MovableEntity extends fr.epitech.game.entitys.Entity{
    protected Integer health;
    protected Integer maxHealth;
    protected Inventory inventory;
    protected Float speed;
    public MovableEntity(String name, Vector2 coordinate, Texture texture) {
        super(name, coordinate, texture);
        this.health = 100;
        this.maxHealth = 100;
        this.inventory = new Inventory();
        this.speed = 1.0f;

    }
    public MovableEntity(String name, Vector2 coordinate, Texture texture, Integer health, Float speed) {
        super(name, coordinate, texture);
        this.health = health;
        this.speed = speed;
    }

    public void moveTo(float x, float y){
        this.coordinate.x = x;
        this.coordinate.y = y;
    }

    public void moveTo(Vector2 coordinate){
        this.coordinate = coordinate;
    }

    public void move(Direction direction){
        switch (direction){
            case UP:
                this.coordinate.y += this.speed;
                break;
            case LEFT:
                this.coordinate.x -= this.speed;
                break;
            case RIGHT:
                this.coordinate.x += this.speed;
                break;
        }
    }

    public void jump(){
        this.coordinate.y += this.speed;
    }

    public Integer getHealth(){
        return this.health;
    }

    public Integer getMaxHealth(){
        return this.maxHealth;
    }

    public Armor getArmor(){
        return this.inventory.getArmor();
    }

    public Weapon getWeapon(){
        return this.inventory.getWeapon();
    }

    public Inventory getInventory(){
        return this.inventory;
    }

    public Integer getDamage(){
        return this.inventory.getDamage();
    }
}
