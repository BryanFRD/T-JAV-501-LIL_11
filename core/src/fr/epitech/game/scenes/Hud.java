package fr.epitech.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.epitech.game.EpiGame;
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;

public class Hud {

    private final SpriteBatch batch;
    private final BitmapFont font;
    private final GlyphLayout waveLayout, waveTimerPrefixLayout, waveTimerLayout;
    private Texture healthBackground, healthForeground;
    private final int healthBarWidth = 200, healthBarHeight = 30;
    private float x = 10, y = EpiGame.V_HEIGHT / 2 - healthBarHeight - 10;
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

        healthBackground = new Texture(Gdx.files.internal("Bars/bar_86.png"));
        healthForeground = new Texture(Gdx.files.internal("whitepixel.png"));
    }

    public void update(float delta){
        waveLayout.setText(font, "Wave: " + waveManager.getWave());
        waveTimerLayout.setText(font, String.format("%.2fs", waveManager.getWaveTimer()));
        entityManager.getPlayer().receiveDamage(10);
    }

    public void render() {
        batch.begin();

        batch.draw(healthBackground, x, y, healthBarWidth, healthBarHeight);
        batch.setColor(Color.RED);
        batch.draw(healthForeground, x + 15, y + healthBarHeight / 4f, (healthBarWidth - 20) * ((float) entityManager.getPlayer().getHealth() / entityManager.getPlayer().getMaxHealth()), healthBarHeight / 2f);
        batch.setColor(Color.WHITE);

        font.draw(batch, waveLayout, EpiGame.V_WIDTH / 2 - waveLayout.width - 20, EpiGame.V_HEIGHT / 2 - waveLayout.height / 2 - 10);

        if(waveManager.isNewWave()){
            font.draw(batch, waveTimerPrefixLayout, EpiGame.V_WIDTH / 4f - waveTimerPrefixLayout.width / 2f, EpiGame.V_HEIGHT / 2.5f - waveLayout.height / 2 - 10);
            font.draw(batch, waveTimerLayout, EpiGame.V_WIDTH/ 4f - waveTimerLayout.width / 2f, EpiGame.V_HEIGHT / 2.5f - waveLayout.height - waveTimerLayout.height / 2 - 30);
        }

        batch.end();
    }

}
