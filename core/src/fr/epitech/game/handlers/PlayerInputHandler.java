package fr.epitech.game.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import fr.epitech.game.directions.Direction;
import fr.epitech.game.entitys.movablesEntitys.characters.Character;
import fr.epitech.game.managers.EntityManager;

public class PlayerInputHandler {

    private final Character player;

    public PlayerInputHandler(EntityManager entityManager){
        this.player = entityManager.getPlayer();
    }

    public void handle(float delta){
        if(player != null){
            player.handleInputMovement();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }

}
