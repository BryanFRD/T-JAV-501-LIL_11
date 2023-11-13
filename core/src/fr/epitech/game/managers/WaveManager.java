package fr.epitech.game.managers;

public class WaveManager {

    private final EntityManager entityManager;
    private int wave;
    private float waveTimer;
    private boolean newWave;

    public WaveManager(EntityManager entityManager){
        this.entityManager = entityManager;
        this.wave = 0;
        this.waveTimer = 0;
        this.newWave = true;
    }

    public void update(float delta){
        if(this.entityManager.getEnemies().isEmpty() && this.waveTimer == 0 && !this.newWave){
            this.waveTimer = 10;
            this.newWave = true;
        }

        if(this.waveTimer > 0){
            this.waveTimer -= Math.max(1 * delta, 0);
        }

        if(this.waveTimer <= 0 && this.newWave){
            this.waveTimer = 0;
            this.newWave = false;
            this.wave++;
            this.entityManager.generateEnemies(wave);
        }
    }

    public int getWave() {
        return wave;
    }

    public float getWaveTimer() {
        return waveTimer;
    }

}
