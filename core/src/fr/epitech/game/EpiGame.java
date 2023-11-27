package fr.epitech.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.epitech.game.screens.MainScreen;

public class EpiGame extends Game {

	public static final float V_WIDTH = 3840, V_HEIGHT = 2160, PPM = 100;
	public static final short NOTHING_BIT = 0, WORLD_BIT = 1, PLAYER_BIT = 2, ENEMY_BIT = 4;
	private SpriteBatch batch;
	private Music backgroundMusic;
	private int percentVolume = 10;

	@Override
	public void create () {
		this.batch = new SpriteBatch();


		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("musique.mp3"));
		backgroundMusic.setLooping(true);
		backgroundMusic.setVolume(percentVolume / 100f);
		backgroundMusic.play();

		setScreen(new MainScreen(this));

	}

	public SpriteBatch getBatch() {
		return batch;
	}

	@Override
	public void dispose() {
		backgroundMusic.dispose();
	}

	public void setVolume(int percentVolume	) {
		this.percentVolume = Math.min(Math.max(percentVolume, 0), 100);
		backgroundMusic.setVolume(this.percentVolume / 100f);
	}

	public float getVolume() {
		return percentVolume;
	}

}
