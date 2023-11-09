package fr.epitech.game.Entity.MovableEntity;

import com.badlogic.gdx.math.Vector2;
import fr.epitech.game.Direction.Direction;
import fr.epitech.game.Inventory.Inventory;
import fr.epitech.game.Inventory.Item.Equipable.Weapon.Weapon;

public abstract class MovableEntity extends fr.epitech.game.Entity.Entity{
    protected Integer health;
    protected Integer maxHealth;
    protected Inventory inventory;
    protected Float speed;
    public MovableEntity(String name, Vector2 coordinate) {
        super(name, coordinate);
        this.health = 100;
        this.maxHealth = 100;
        this.inventory = new Inventory();
        this.speed = 1.0f;
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

    public Integer getArmor(){
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
