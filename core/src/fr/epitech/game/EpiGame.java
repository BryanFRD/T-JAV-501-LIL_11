package fr.epitech.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.epitech.game.screens.MainScreen;

public class EpiGame extends Game {

	public static final int V_WIDTH = 16 * 50;
	public static final int V_HEIGHT = 9 * 50;
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
