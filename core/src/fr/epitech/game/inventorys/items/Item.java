package fr.epitech.game.inventorys.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import fr.epitech.game.managers.EntityManager;

public abstract class Item {

    protected SpriteBatch batch;
    protected World world;
    protected EntityManager entityManager;
    protected String name;

    protected Item(SpriteBatch batch, World world, EntityManager entityManager, String name) {
        this.batch = batch;
        this.world = world;
        this.entityManager = entityManager;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
