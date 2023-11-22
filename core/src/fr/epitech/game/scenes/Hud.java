package fr.epitech.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import fr.epitech.game.EpiGame;
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;

public class Hud {

    private final SpriteBatch batch;
    private final BitmapFont font;
    private final GlyphLayout waveLayout, waveTimerPrefixLayout, waveTimerLayout, levelLayout;
    private Texture healthBackground, healthForeground, experienceBackground, experienceForeground;
    private final int healthBarWidth = 200, healthBarHeight = 30 , experienceBarWidth = 150, experienceBarHeight = 30;
    private float healthbarX = 10, healthbarY = EpiGame.V_HEIGHT / 2 - healthBarHeight - 50;
    private float experienceX = 10, experienceY = EpiGame.V_HEIGHT / 2 - healthBarHeight - 50 - experienceBarHeight - 10;
    private final WaveManager waveManager;
    private final EntityManager entityManager;

    public Hud(SpriteBatch batch, WaveManager waveManager, EntityManager entityManager){
        this.batch = batch;
        this.waveManager = waveManager;
        this.entityManager = entityManager;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/pixelade.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        font = generator.generateFont(parameter);
        generator.dispose();

        waveLayout = new GlyphLayout(font, "Wave: 0");
        waveTimerPrefixLayout = new GlyphLayout(font, "Wave starts in ");
        waveTimerLayout = new GlyphLayout(font, "0s");
        levelLayout = new GlyphLayout(font, "Level: 1");

        healthBackground = new Texture(Gdx.files.internal("Bars/bar_86.png"));
        healthForeground = new Texture(Gdx.files.internal("whitepixel.png"));

        experienceBackground = new Texture(Gdx.files.internal("Bars/bar_86.png"));
        experienceForeground = new Texture(Gdx.files.internal("whitepixel.png"));
    }

    public void update(float delta){
        waveLayout.setText(font, "Wave: " + waveManager.getWave());
        waveTimerLayout.setText(font, String.format("%.2fs", waveManager.getWaveTimer()));
        levelLayout.setText(font, "Level: " + entityManager.getPlayer().getLevel());
    }

    public void render() {
        batch.begin();

        batch.draw(healthBackground, healthbarX, healthbarY, healthBarWidth, healthBarHeight);
        batch.setColor(Color.RED);
        batch.draw(healthForeground, healthbarX + 15, healthbarY + healthBarHeight / 4f, (healthBarWidth - 30) * ((float) entityManager.getPlayer().getHealth() / entityManager.getPlayer().getMaxHealth()), healthBarHeight / 2f);
        batch.setColor(Color.WHITE);

        batch.draw(experienceBackground, experienceX, experienceY, experienceBarWidth, experienceBarHeight);
        batch.setColor(Color.LIME);

        int level = entityManager.getPlayer().getLevel();
        batch.draw(experienceForeground, experienceX + 15, experienceY + experienceBarHeight / 4f, (experienceBarWidth - 30) * ((float) level / entityManager.getPlayer().calculateLevel(level+1)), experienceBarHeight / 2f);
        batch.setColor(Color.WHITE);

        font.draw(batch, levelLayout, 20, EpiGame.V_HEIGHT / 2 - levelLayout.height / 2 - 10);

        font.draw(batch, waveLayout, EpiGame.V_WIDTH / 2 - waveLayout.width - 20, EpiGame.V_HEIGHT / 2 - waveLayout.height / 2 - 10);

        if(waveManager.isNewWave()){
            font.draw(batch, waveTimerPrefixLayout, EpiGame.V_WIDTH / 4f - waveTimerPrefixLayout.width / 2f, EpiGame.V_HEIGHT / 2.5f - waveLayout.height / 2 - 10);
            font.draw(batch, waveTimerLayout, EpiGame.V_WIDTH/ 4f - waveTimerLayout.width / 2f, EpiGame.V_HEIGHT / 2.5f - waveLayout.height - waveTimerLayout.height / 2 - 30);
        }

        batch.end();
    }

}
