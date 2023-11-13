package fr.epitech.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.epitech.game.EpiGame;

public class Hud {

    private final SpriteBatch batch;
    private Texture healthBackground, healthForeground;
    private final int healthBarWidth = 200, healthBarHeight = 30;
    private Vector2 healthBarPosition = new Vector2(10, EpiGame.V_HEIGHT - healthBarHeight - 10);
    private int health = 1000;

    public Hud(SpriteBatch batch){
        this.batch = batch;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/pixelade.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 25;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        healthBackground = new Texture(Gdx.files.internal("Bars/bar_86.png"));
        healthForeground = new Texture(Gdx.files.internal("Bars/bar_89.png"));
    }

    public void update(float delta){
        health = Math.max(0, health - 1);
    }

    public void render() {
        batch.begin();
        batch.draw(healthBackground, healthBarPosition.x, healthBarPosition.y, healthBarWidth, healthBarHeight);
        batch.draw(healthForeground, healthBarPosition.x + 5, healthBarPosition.y, healthBarWidth * (health / 1000f), healthBarHeight);
        batch.end();
    }

}
