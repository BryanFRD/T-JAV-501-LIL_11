package fr.epitech.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import fr.epitech.game.EpiGame;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;

public class SettingsScreen implements Screen {
    private final EpiGame game;
    private final Stage stage;
    private final Skin skin;

    private final TextButton backButton, plusButton, minusButton, muteButton;
    private boolean isMuted;
    private float lastVolume;
    private Label volumeLabel;
    private Music backgroundMusic;

    public SettingsScreen(final EpiGame game) {
        this.game = game;
        this.stage = new Stage(new FitViewport(EpiGame.V_WIDTH, EpiGame.V_HEIGHT));
        this.skin = new Skin();
        this.isMuted = false;
        this.lastVolume = game.getVolume();
        Gdx.input.setInputProcessor(stage);

        Texture backgroundTexture = new Texture(Gdx.files.internal("background1.jpg"));
        Image backgroundImage = new Image(new TextureRegionDrawable(new TextureRegion(backgroundTexture)));
        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(backgroundImage);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/pixelade.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 100;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        TextureRegionDrawable buttonBackgroundUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Buttons/Black/buttons_04.png"))));
        TextureRegionDrawable buttonBackgroundOver = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Buttons/Black/buttons_10.png"))));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = buttonBackgroundUp;
        textButtonStyle.over = buttonBackgroundOver;
        skin.add("default", textButtonStyle);

        backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.setScreen(new MainScreen(game));
            }
        });
        backButton.getLabelCell().padBottom(40);

        plusButton = new TextButton("+", skin);
        plusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x,  float y) {
                game.setVolume((int) (game.getVolume() + 5));
                updateVolumeLabel();
            }
        });


        minusButton = new TextButton("-", skin);
        minusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setVolume((int) (game.getVolume() - 5));
                updateVolumeLabel();
            }
        });

        muteButton = new TextButton("Mute", skin);
        muteButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isMuted) {
                    game.setVolume((int) lastVolume);
                    isMuted = false;

                } else {
                    lastVolume = game.getVolume();
                    game.setVolume(0);
                    isMuted = true;
                }
                updateVolumeLabel();
            }
        });

        volumeLabel = new Label("Volume : " + game.getVolume() + "%", labelStyle);

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);
        table.row();
        table.add(plusButton, muteButton, minusButton);
        table.row();
        table.add(backButton).center().width(400).height(175);
        table.row();
        table.add(volumeLabel);

    }

    @Override
    public void show() {
    }

    private void updateVolumeLabel() {
        volumeLabel.setText("Volume : " + game.getVolume() + "%");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();

        if(backButton.isOver()) {
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
