package fr.epitech.game.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.epitech.game.EpiGame;

public class Hud {

    private final Stage stage;
    private final Viewport viewport;

    public Hud(SpriteBatch batch){
        this.viewport = new FitViewport(EpiGame.V_WIDTH, EpiGame.V_HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);
    }

    public Stage getStage() {
        return stage;
    }

}
