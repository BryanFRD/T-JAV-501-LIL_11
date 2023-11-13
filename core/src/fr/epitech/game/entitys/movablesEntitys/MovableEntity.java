package fr.epitech.game.entitys.movablesEntitys;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.epitech.game.directions.Direction;
import fr.epitech.game.inventorys.Inventory;
import fr.epitech.game.inventorys.items.equipables.armors.Armor;
import fr.epitech.game.inventorys.items.equipables.weapons.Weapon;
import org.w3c.dom.Text;

public abstract class MovableEntity extends fr.epitech.game.entitys.Entity{
    protected int health;
    protected int maxHealth;
    protected Inventory inventory;
    protected float speed;

    public MovableEntity(SpriteBatch batch, World world, String name, Vector2 coordinate, Texture texture) {
        super(batch, world, name, coordinate, texture);
        this.health = 100;
        this.maxHealth = 100;
        this.inventory = new Inventory();
        this.speed = 100;
    }

    public MovableEntity(SpriteBatch batch, World world, String name, Vector2 coordinate, TextureRegion[] textureRegions){
        super(batch, world, name, coordinate, textureRegions);
        this.health = 100;
        this.maxHealth = 100;
        this.inventory = new Inventory();
        this.speed = 100f;
    }

    public void update(float delta) {
        coordinate.x = b2body.getPosition().x - getWidth() / 2;
        coordinate.y = b2body.getPosition().y - getHeight() / 2;
    }


    public void moveTo(float x, float y){
        this.coordinate.x = x;
        this.coordinate.y = y;
    }

    public void moveTo(Vector2 coordinate){
        this.coordinate = coordinate;
    }

    public void move(Direction direction){
        Vector2 velocity = new Vector2();
        switch (direction){
            case LEFT:
                velocity.set(speed, b2body.getLinearVelocity().y);
                break;
            case RIGHT:
                velocity.set(-speed, b2body.getLinearVelocity().y);
                break;
        }

        b2body.setLinearVelocity(velocity);
    }

    public void jump(){
        if(b2body.getLinearVelocity().y == 0){
            b2body.applyLinearImpulse(new Vector2(0, 400), getCoordinate(), true);
        }
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
