package fr.epitech.game.entitys.movablesEntitys.enemys;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import fr.epitech.game.EpiGame;
import fr.epitech.game.entitys.movablesEntitys.characters.Character;
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;
import fr.epitech.game.map.Chunk;

public class Zombie extends Enemy{

    private int enemx = 0, enemy = 0;

    public Zombie(SpriteBatch batch, World world, Vector2 velocity, EntityManager entityManager, WaveManager waveManager) {
        super(batch, world, "Zombie", new Vector2(EpiGame.V_WIDTH / 2f, 1000),  new TextureRegion(new Texture("monster_58.png")).split(16, 16)[0][1].getTexture(), entityManager, waveManager);
    }
}
