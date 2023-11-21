package fr.epitech.game.entitys.movablesEntitys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.epitech.game.directions.Direction;
import fr.epitech.game.inventorys.Inventory;
import fr.epitech.game.inventorys.items.equipables.armors.Armor;
import fr.epitech.game.inventorys.items.equipables.weapons.Weapon;
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;

public abstract class MovableEntity extends fr.epitech.game.entitys.Entity implements InputProcessor {
    protected float health;
    protected float maxHealth;
    protected Inventory inventory;
    protected float speed;
    protected float jumpDuration = 0;
    protected Vector2 velocity = new Vector2(0, 0);
    protected boolean isJumping = false, isFalling = false;
    protected float lastY = 0;
    protected float invincibilityTimer = 0;

    public MovableEntity(SpriteBatch batch, World world, String name, Vector2 coordinate, Texture texture, EntityManager entityManager, WaveManager waveManager, short categoryBits, short maskBits){
        super(batch, world, name, coordinate, texture, entityManager, waveManager, categoryBits, maskBits);
        this.health = 100;
        this.maxHealth = 100;
        this.inventory = new Inventory();
        this.speed = 10f;
    }

    public MovableEntity(SpriteBatch batch, World world, String name, Vector2 coordinate, TextureRegion[] textureRegions, EntityManager entityManager, WaveManager waveManager, short categoryBits, short maskBits){
        super(batch, world, name, coordinate, textureRegions, entityManager, waveManager, categoryBits, maskBits);
        this.health = 100;
        this.maxHealth = 100;
        this.inventory = new Inventory();
        this.speed = 10f;
    }

    public void move(Direction direction){
        if(direction == Direction.LEFT){
            this.velocity.x = -this.speed;
            reverted = false;
        } else if(direction == Direction.RIGHT){
            this.velocity.x = this.speed;
            reverted = true;
        } else if (direction == Direction.STOP){
            this.velocity.x = 0;
        }

        if(direction == Direction.UP && !isFalling && !isJumping){
            isJumping = true;
            jumpDuration = 0.75f;
            this.velocity.y = 4;
        }
    }

    public void update(float delta) {
        super.update(delta);

        if(invincibilityTimer > 0){
            invincibilityTimer -= delta;
        }

        float currentY = b2body.getPosition().y;
        isFalling = lastY != currentY;

        lastY = currentY;

        if(jumpDuration > 0){
            jumpDuration -= delta;
        } else {
            velocity.y = world.getGravity().y;
            isJumping = false;
        }

        if(b2body.getPosition().x <= 5){
            velocity.x = speed;
        }

        b2body.setLinearVelocity(velocity.x, 0);
        b2body.applyLinearImpulse(0, velocity.y, b2body.getWorldCenter().x, b2body.getWorldCenter().y, true);

        coordinate.x = b2body.getPosition().x - getWidth() / 2;
        coordinate.y = b2body.getPosition().y - getHeight() / 2;
    }

    public void attack(float angle){
        if(this.inventory.getWeapon() == null){
            return;
        }

        this.inventory.getWeapon().attack(angle);
    }

    public void receiveDamage(float damage){
        //TODO get armor
        if(this.invincibilityTimer > 0){
            return;
        }
        invincibilityTimer = 1;
        float armor = 0;
        if(this.inventory.getArmor() != null){
            armor = this.inventory.getArmor().getDefense();
        }

        this.health = Math.max(0, this.health - Math.max(1, damage - armor));
    }

    public float getHealth(){
        return this.health;
    }

    public float getMaxHealth(){
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

    public float getDamage(){
        return this.inventory.getDamage();
    }


    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.A){
            this.velocity.x = -this.speed;
            reverted = false;
        } else if(keycode == Input.Keys.D){
            this.velocity.x = this.speed;
            reverted = true;
        } else if(keycode == Input.Keys.W && !isFalling && !isJumping){
            this.velocity.y = 7.5f;
            isJumping = true;
            jumpDuration = 0.25f;
        } else {
            jumpDuration = 0;
        }

        if(keycode == Input.Keys.ESCAPE){
            Gdx.app.exit();
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.A){
            this.velocity.x = 0;
        } else if(keycode == Input.Keys.D){
            this.velocity.x = 0;
        } else if(keycode == Input.Keys.W){
            this.velocity.y = 0;
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == 0){
            //TODO: Get angle from mouse position and player position
            float angle = MathUtils.atan2(Gdx.graphics.getHeight() - screenY - Gdx.graphics.getHeight() / 2f, screenX - Gdx.graphics.getWidth() / 2f);
            attack(angle);
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}
