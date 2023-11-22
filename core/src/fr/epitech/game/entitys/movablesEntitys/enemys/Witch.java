package fr.epitech.game.entitys.movablesEntitys.enemys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import fr.epitech.game.EpiGame;
import fr.epitech.game.directions.Direction;
import fr.epitech.game.entitys.movablesEntitys.MovableEntity;
import fr.epitech.game.entitys.movablesEntitys.characters.Character;
import fr.epitech.game.entitys.projectiles.Fireball;
import fr.epitech.game.inventorys.items.equipables.weapons.WizardStaff;
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;

public class Witch extends Enemy{

        private Character player;

        public Witch(SpriteBatch batch, World world, Vector2 coordinate, EntityManager entityManager, WaveManager waveManager) {
            super(batch, world, "Witch", coordinate,
                    new TextureRegion(new Texture("sorcierMoove.png")).split(500/6, 150)[0], entityManager, waveManager);
            this.player = entityManager.getPlayer();
            float angle = MathUtils.atan2(player.getPosition().y - b2body.getPosition().y, player.getPosition().x - b2body.getPosition().x);
            attack(angle);
        }


}
