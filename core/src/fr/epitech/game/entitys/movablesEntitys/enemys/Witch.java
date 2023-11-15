package fr.epitech.game.entitys.movablesEntitys.enemys;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import fr.epitech.game.EpiGame;
import fr.epitech.game.directions.Direction;
import fr.epitech.game.entitys.movablesEntitys.characters.Character;
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;

public class Witch extends Enemy{

        private Character player;

        public Witch(SpriteBatch batch, World world, Vector2 velocity, EntityManager entityManager, WaveManager waveManager) {
            super(batch, world, "Witch", new Vector2(EpiGame.V_WIDTH / 6f, 1000),
                    new TextureRegion(new Texture("sorcierMoove.png")).split(500/6, 160)[0], entityManager, waveManager);

            this.player = entityManager.getPlayer();
        }
}
