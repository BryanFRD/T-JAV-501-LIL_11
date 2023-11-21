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
        System.out.println("Updating wave manager");
        if(this.entityManager.getEnemies().isEmpty() && this.waveTimer == 0 && !this.newWave){
            this.waveTimer = 10;
            this.newWave = true;
            System.out.println("Wave reseted");
        }

        if(this.waveTimer > 0){
            this.waveTimer -= Math.max(1 * delta, 0);
            System.out.println("Change wave timer");
        }

        if(this.waveTimer <= 0 && this.newWave){
            this.waveTimer = 0;
            this.newWave = false;
            this.wave++;
            System.out.println("New wave");
            this.entityManager.generateEnemies(wave);
            System.out.println("Enemies generated");
        }
    }

    public int getWave() {
        return wave;
    }

    public float getWaveTimer() {
        return waveTimer;
    }

    public boolean isNewWave() {
        return newWave;
    }

}
