package fr.epitech.game.entitys.movablesEntitys.enemys;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.math.Vector2;
import fr.epitech.game.entitys.movablesEntitys.MovableEntity;
import fr.epitech.game.screens.PlayScreen;
import fr.epitech.game.map.WorldMap;


public abstract class Enemy extends MovableEntity {
    protected PlayScreen screen;
    protected World world;
    public Body b2body;

    public Enemy(World world, PlayScreen screen, String name) {
    super(world, name, new Vector2(32, 32), new Texture("assets/monster_58.png"));
    this.world = world;
    this.screen = screen;
    moveTo(32, 32);
    }

    protected abstract void defineEnemy();

    /*public void update(float dt){
        super.update(dt);
    }

    public void render(float dt){
        super.render(dt);
    }*/

    public void moveTo(float x, float y){
        super.moveTo(x, y);
    }
}