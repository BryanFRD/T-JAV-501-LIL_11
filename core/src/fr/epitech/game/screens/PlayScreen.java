package fr.epitech.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.epitech.game.EpiGame;
import fr.epitech.game.entitys.movablesEntitys.characters.Archer;
import fr.epitech.game.entitys.movablesEntitys.characters.Barbarian;
import fr.epitech.game.entitys.movablesEntitys.characters.Character;
import fr.epitech.game.entitys.movablesEntitys.characters.Wizard;
import fr.epitech.game.managers.EntityManager;
import fr.epitech.game.managers.WaveManager;
import fr.epitech.game.map.Chunk;
import fr.epitech.game.map.WorldMap;
import fr.epitech.game.scenes.Hud;

public class PlayScreen implements Screen {

    private final EpiGame game;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final Hud hud;
    private final WorldMap worldMap;
    private final EntityManager entityManager;
    private final WaveManager waveManager;

    public PlayScreen(EpiGame game, String selectedCharacter){
        this.game = game;
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(EpiGame.V_WIDTH / EpiGame.PPM, EpiGame.V_HEIGHT / EpiGame.PPM, camera);
        this.worldMap = new WorldMap(game.getBatch());

        this.entityManager = new EntityManager(game.getBatch(), worldMap.getWorld());
        this.waveManager = new WaveManager(entityManager);

        Vector2 playerSpawn = new Vector2(5, 50);

        Character player;

        switch(selectedCharacter) {
            case "Barbarian" :
                player = new Barbarian(game.getBatch(), worldMap.getWorld(), "Barbarian", playerSpawn, entityManager, waveManager);
                break;
            case "Archer" :
                player = new Archer(game.getBatch(), worldMap.getWorld(), "Archer", playerSpawn, entityManager, waveManager);
                break;
            default:
                player = new Wizard(game.getBatch(), worldMap.getWorld(), "Mage", playerSpawn, entityManager, waveManager);
        }

        Gdx.input.setInputProcessor(player);
        this.entityManager.setPlayer(player);
        this.hud = new Hud(new SpriteBatch(), waveManager, entityManager);

        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
    }

    @Override
    public void show() {
    }

    public void update(float delta){

        Vector2 position = entityManager.getPlayer().getPosition();

        float cameraX = Math.max(position.x, EpiGame.V_WIDTH / EpiGame.PPM / 2f);
        float cameraY = Math.max(Math.min(position.y, Chunk.SIZE_Y * Chunk.TILE_SIZE - EpiGame.V_HEIGHT / EpiGame.PPM), EpiGame.V_HEIGHT / EpiGame.PPM / 2f);

        camera.position.slerp(new Vector3(cameraX, cameraY, 0), 0.1f);
        camera.update();
        worldMap.update(delta);

        worldMap.updatePlayerPosition(camera.position.x, camera.position.y);

        entityManager.update(delta);

        waveManager.update(delta);

        hud.update(delta);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().setProjectionMatrix(camera.combined);
        worldMap.render();

        worldMap.getBox2DRenderer().render(worldMap.getWorld(), camera.combined);

        entityManager.render();
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
