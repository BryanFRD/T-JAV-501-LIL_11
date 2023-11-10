package fr.epitech.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.epitech.game.EpiGame;
import fr.epitech.game.map.WorldMap;
import fr.epitech.game.scenes.Hud;

public class PlayScreen implements Screen {

    private final EpiGame game;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final Hud hud;
    private final WorldMap worldMap;

    public PlayScreen(EpiGame game){
        this.game = game;
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(EpiGame.V_WIDTH, EpiGame.V_HEIGHT, camera);
        this.hud = new Hud(game.getBatch());
        this.worldMap = new WorldMap(this.camera, this.viewport);
    }

    public void handleInput(float delta){
        if(Gdx.input.isTouched()){
            camera.position.x += 100 * delta;
        }
    }

    @Override
    public void show() {
    }

    public void update(float delta){
        handleInput(delta);

        worldMap.getWorld().step(1/60f, 6, 2);

        camera.update();
        worldMap.getRenderer().setView(camera);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldMap.getRenderer().render();
        worldMap.getBox2DRenderer().render(worldMap.getWorld(), camera.combined);

        game.getBatch().setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.getStage().draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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

    }
}
