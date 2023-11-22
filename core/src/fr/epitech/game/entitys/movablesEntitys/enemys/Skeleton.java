package fr.epitech.game.entitys.movablesEntitys.enemys;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import fr.epitech.game.EpiGame;
import fr.epitech.game.directions.Direction;
import fr.epitech.game.entitys.movablesEntitys.characters.Character;
import fr.epitech.game.entitys.projectiles.Fireball;
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;

public class Skeleton extends Enemy{

            private Character player;
    int x = 200;

    public Skeleton(SpriteBatch batch, World world, Vector2 coordinate, EntityManager entityManager, WaveManager waveManager) {
        super(batch, world, "Skeleton", coordinate,
                new TextureRegion(new Texture("monster_58.png")).split(16, 16)[0], entityManager, waveManager);
        this.player = entityManager.getPlayer();
            }

    @Override
    public void attack(float angle) {
        if (x!=0){
            x --;
        }else {
            Fireball fireball = new Fireball(batch, world, getPosition(), entityManager, angle, 25, getCategoryBits(), getMaskBits());
            entityManager.addProjectiles(fireball);
            System.out.println("fleche created");
            x = 200;
        }
    }
}
