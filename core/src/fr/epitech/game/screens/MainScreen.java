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
import sun.font.TrueTypeFont;

public class MainScreen implements Screen {

    private final EpiGame game;
    private final Stage stage;
    private final Skin skin;

    public MainScreen(final EpiGame game){
        this.game = game;
        this.stage = new Stage(new FitViewport(EpiGame.V_WIDTH, EpiGame.V_HEIGHT));
        this.skin = new Skin();
        Gdx.input.setInputProcessor(stage);

        Texture backgroundTexture = new Texture(Gdx.files.internal("Forest_background_16.png"));
        Image backgroundImage = new Image(new TextureRegionDrawable(new TextureRegion(backgroundTexture)));
        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(backgroundImage);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/pixelade.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 25;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;

        skin.add("default", textButtonStyle);

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);

        Label title = new Label("EpiGame", labelStyle);
        TextButton playButton = new TextButton("Play", skin);
        TextButton settingsButton = new TextButton("Settings", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
            }
        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                System.out.println("Settings");
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.add(title).fillX().uniformX();
        table.row().pad(75, 0, 0, 0);
        table.add(playButton).fillX().uniformX();
        table.row().pad(10, 0, 0, 0);
        table.add(settingsButton).fillX().uniformX();
        table.row().pad(10, 0, 0, 0);
        table.add(exitButton).fillX().uniformX();
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
