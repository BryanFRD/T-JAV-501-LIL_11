package fr.epitech.game.entitys.movablesEntitys.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;
import fr.epitech.game.EpiGame;
import fr.epitech.game.entitys.movablesEntitys.MovableEntity;
import fr.epitech.game.inventorys.Inventory;
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;
import fr.epitech.game.screens.GameOverScreen;

public abstract class Character extends MovableEntity {

    protected int gold;
    protected int capacity;
    protected int maxCapacity;
    protected int level = 1, xp = 0, neededXp = calculateLevel(level+1);
    protected final EpiGame epiGame;

    public Character(SpriteBatch batch, World world, String name, Vector2 coordinate, Texture texture, EntityManager entityManager, WaveManager waveManager, int gold, int maxCapacity, int capacity, EpiGame epiGame) {
        super(batch, world, name, coordinate, texture, entityManager, waveManager, EpiGame.PLAYER_BIT, (short) (EpiGame.WORLD_BIT | EpiGame.ENEMY_BIT));
        this.gold = gold;
        this.maxCapacity = maxCapacity;
        this.capacity = capacity;
        this.epiGame = epiGame;
    }

    public Character(SpriteBatch batch, World world, String name, Vector2 coordinate, TextureRegion[] textureRegions, EntityManager entityManager, WaveManager waveManager, int gold, int maxCapacity, int capacity, EpiGame epiGame){
        super(batch, world, name, coordinate, textureRegions, entityManager, waveManager, EpiGame.PLAYER_BIT, (short) (EpiGame.WORLD_BIT | EpiGame.ENEMY_BIT));
        this.gold = gold;
        this.maxCapacity = maxCapacity;
        this.capacity = capacity;
        this.epiGame = epiGame;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if(this.health == 0){
            epiGame.setScreen(new GameOverScreen(epiGame));
        }

        this.health = Math.min(this.health + delta, this.maxHealth);
    }

    public void addExperience(int xp){
        this.xp += xp;
        while(this.xp >= neededXp) {
            this.xp -= neededXp;
            this.level++;
            this.neededXp = calculateLevel(level);

            entityManager.getPlayer().maxHealth = maxHealth + 50;
        }
    }

    public int getLevel(){
        return this.level;
    }

    public int getXp(){
        return this.xp;
    }

    public int getNeededXp(){
        return this.neededXp;
    }

    public int calculateLevel(int xp){
        return 1 + (xp*xp-xp)/2;
    }

}
