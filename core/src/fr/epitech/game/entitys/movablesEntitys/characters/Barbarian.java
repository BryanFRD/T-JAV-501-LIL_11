package fr.epitech.game.entitys.movablesEntitys.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.epitech.game.EpiGame;
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;

import java.util.Random;

public class Barbarian extends Character {

    public Barbarian(SpriteBatch batch, World world, String name, Vector2 coordinate, EntityManager entityManager, WaveManager waveManager, EpiGame epiGame) {
        super(batch, world, name, coordinate, new TextureRegion(new Texture("knight.png")).split(16, 16)[new Random().nextInt(4)], entityManager, waveManager, 0, 100, 100, epiGame);
    }

    @Override
    public void attack(float angle) {
        System.out.println("Attack");
    }
    
}
