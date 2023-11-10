package fr.epitech.game.entitys.enemys;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Zombie extends Enemy{
    private Texture texture = new Texture("assets/monster_58.png") ;

    public void setTexture (Texture texture) {
        this.texture = texture;
    }
    public Zombie(World world, String name, Vector2 coordinate, Texture texture, Integer health, Float speed) {
        super(world ,"Zombie",
                coordinate,
                texture,
                80,
                0.8f );
    }

    public void moveTo(float x, float y){
        super.moveTo(x, y);
    }
}
