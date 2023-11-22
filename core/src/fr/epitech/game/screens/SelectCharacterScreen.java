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
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import fr.epitech.game.EpiGame;
import org.w3c.dom.Text;

public class SelectCharacterScreen implements Screen {

    private final Stage stage;
    private final Skin skin;
    private final TextButton selectButton, barbarianButton, mageButton, archerButton, backButton;

    private String selectedCharacter = "Mage";

    public SelectCharacterScreen(final EpiGame game) {

        this.stage = new Stage(new FitViewport(EpiGame.V_WIDTH, EpiGame.V_HEIGHT));
        this.skin = new Skin();
        Gdx.input.setInputProcessor(stage);

        Texture backgroundTexture = new Texture(Gdx.files.internal("tavern.png"));
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(backgroundImage);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/pixelade.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 100;
        parameter2.size = 100;
        parameter2.borderColor = Color.BLACK;
        parameter2.borderWidth = 3;
        BitmapFont font = generator.generateFont(parameter);
        BitmapFont font2 = generator.generateFont(parameter2);
        generator.dispose();

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        TextureRegionDrawable buttonBackgroundUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Buttons/Black/buttons_04.png"))));
        TextureRegionDrawable buttonBackgroundOver = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Buttons/Black/buttons_10.png"))));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = buttonBackgroundUp;
        textButtonStyle.over = buttonBackgroundOver;

        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
        checkBoxStyle.font = font2;

        skin.add("default", textButtonStyle);
        skin.add("default", checkBoxStyle);

        Label title = new Label("Select your character", labelStyle);
        Label emptyLabel = new Label("", labelStyle);

        selectButton = new TextButton("Select", skin);
        selectButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game, selectedCharacter));
            }
        });
        selectButton.getLabelCell().padBottom(35);

        backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.setScreen(new MainScreen(game));
            }
        });
        backButton.getLabelCell().padBottom(35);

        barbarianButton = new CheckBox("Barbarian", skin);
        barbarianButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                selectedCharacter = "Barbarian";
                resetButtonColors();
                barbarianButton.getLabel().setColor(Color.GREEN);
            }
        });
        barbarianButton.getLabelCell().padBottom(35);

        mageButton = new CheckBox("Mage", skin);
        mageButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                selectedCharacter = "Mage";
                resetButtonColors();
                mageButton.getLabel().setColor(Color.GREEN);
            }
        });
        mageButton.getLabelCell().padBottom(35);

        archerButton = new CheckBox("Archer", skin);
        archerButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                selectedCharacter = "Archer";
                resetButtonColors();
                archerButton.getLabel().setColor(Color.GREEN);
            }
        });
        archerButton.getLabelCell().padBottom(35);

        Texture barbarianTexture = new Texture(Gdx.files.internal("knight.png"));
        TextureRegion barbarianTextureRegion = new TextureRegion(barbarianTexture, 16,16);
        Image barbarianImage = new Image(barbarianTextureRegion);
        Container<Image> barbarianContainer = new Container<>(barbarianImage);
        barbarianContainer.size(barbarianImage.getWidth() * 16, barbarianImage.getHeight() * 16);

        Texture mageTexture = new Texture(Gdx.files.internal("wizard.png"));
        TextureRegion mageTextureRegion = new TextureRegion(mageTexture, 16, 16);
        Image mageImage = new Image(mageTextureRegion);
        Container<Image> mageContainer = new Container<>(mageImage);
        mageContainer.size(mageImage.getWidth() * 16, mageImage.getHeight() * 16);

        Texture archerTexture = new Texture(Gdx.files.internal("rogue.png"));
        TextureRegion archerTextureRegion = new TextureRegion(archerTexture, 16,16);
        Image archerImage = new Image(archerTextureRegion);
        Container<Image> archerContainer = new Container<>(archerImage);
        archerContainer.size(mageImage.getWidth() * 16, mageImage.getHeight() * 16);

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);
        table.add(title).center().colspan(3).padBottom(40);
        table.row().pad(75, 0, 0, 0);
        table.row().pad(40, 0, 40, 0);
        table.add(barbarianContainer, mageContainer, archerContainer).center().row();
        table.row().pad(0, 0, 40, 0);
        table.add(barbarianButton, mageButton, archerButton);
        table.row().pad(0, 100, 0, 100);
        table.add(selectButton).width(300).height(100).colspan(3);
        table.row().pad(20,100,0,100);
        table.add(backButton).width(300).height(100).colspan(3);
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

    private void resetButtonColors() {
        barbarianButton.getLabel().setColor(Color.WHITE);
        mageButton.getLabel().setColor(Color.WHITE);
        archerButton.getLabel().setColor(Color.WHITE);
    }

}
