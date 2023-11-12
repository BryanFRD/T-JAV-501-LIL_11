package fr.epitech.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        this.hud = new Hud(new SpriteBatch());
        this.worldMap = new WorldMap(game.getBatch());

        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
    }

    public void handleInput(float delta){
        int cameraSpeed = 500;
        if(Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.A)){
            camera.position.x -= cameraSpeed * delta;
        }
        if(Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.D)){
            camera.position.x += cameraSpeed * delta;
        }
        if(Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.W)){
            camera.position.y += cameraSpeed * delta;
        }
        if(Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.S)){
            camera.position.y -= cameraSpeed * delta;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }

    @Override
    public void show() {
    }

    public void update(float delta){
        handleInput(delta);

        camera.update();
        worldMap.update(delta);
        worldMap.updatePlayerPosition(camera.position.x, camera.position.y);

        hud.update(delta);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().setProjectionMatrix(camera.combined);
        worldMap.render();

        hud.render();
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
