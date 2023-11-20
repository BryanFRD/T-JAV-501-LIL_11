package fr.epitech.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.epitech.game.screens.MainScreen;

public class EpiGame extends Game {

	public static final float V_WIDTH = 3840, V_HEIGHT = 2160, PPM = 100;
	public static final short NOTHING_BIT = 0, WORLD_BIT = 1, PLAYER_BIT = 2, ENEMY_BIT = 4;
	private SpriteBatch batch;

	@Override
	public void create () {
		this.batch = new SpriteBatch();
		setScreen(new MainScreen(this));
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
