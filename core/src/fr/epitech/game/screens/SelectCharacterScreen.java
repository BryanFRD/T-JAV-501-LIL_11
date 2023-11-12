package fr.epitech.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import fr.epitech.game.EpiGame;

public class SelectCharacterScreen implements Screen {

    private final Stage stage;
    private final Skin skin;
    private final TextButton selectButton, barbarianButton, mageButton, archerButton;
    private final ImageButton backButton;

    public SelectCharacterScreen(final EpiGame game) {
        this.stage = new Stage(new FitViewport(EpiGame.V_WIDTH, EpiGame.V_HEIGHT));
        this.skin = new Skin();
        Gdx.input.setInputProcessor(stage);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/pixelade.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 25;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        TextureRegionDrawable buttonBackgroundUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Buttons/Black/buttons_04.png"))));
        TextureRegionDrawable buttonBackgroundOver = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Buttons/Black/buttons_10.png"))));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = buttonBackgroundUp;
        textButtonStyle.over = buttonBackgroundOver;

        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
        checkBoxStyle.font = font;

        skin.add("default", textButtonStyle);
        skin.add("default", checkBoxStyle);

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);

        Label title = new Label("Select your character", labelStyle);
        backButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Arrows/arrow_124.png")))));
        backButton.setSize(50, 50);
        selectButton = new TextButton("Select", skin);
        barbarianButton = new CheckBox("Barbarian", skin);
        mageButton = new CheckBox("Mage", skin);
        archerButton = new CheckBox("Archer", skin);
        barbarianButton.setChecked(true);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.setScreen(new MainScreen(game));
            }
        });

        selectButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {

            }
        });

        table.add(title).fillX().uniformX();
        table.row().pad(75, 0, 0, 0);
        table.add(backButton).fillX().uniformX();
        table.row().pad(10, 0, 0, 0);
        table.add(barbarianButton, mageButton, archerButton);
        table.row().pad(10, 0, 0, 0);
        table.add(selectButton).fillX().uniformX();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();

        if(backButton.isOver() || selectButton.isOver()) {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Hand);
        } else {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Arrow);
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
