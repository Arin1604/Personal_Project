package indy;

import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class CannonGun extends Gun {

    private Player gunPlayer;
    private Pane pane;

    private Bullet bulletUp;


    /**
     * This is the constructor for CannonGun, a class that inherits from gun.
     */
    public CannonGun(Pane mainPane, Player player){
        super(mainPane, player);
        this.pane = mainPane;
        this.gunPlayer = player;
    }

    /**
     * This method overrides the getBullet method in gun to return Cannon Gun's bullet
     */
    @Override
    public Bullet getBullet(int i){
        return this.bulletUp;
    }

    /**
     * This method overrides the fire method from gun to create the distinctive fire style of the cannonGun: Slow...
     * BUT DEADLY. The bullets generated move slower but have a bigger radius and are hence capable of damaging
     * enemies over time.
     */
    @Override
    public void fire(Timeline timeline, double bulletYVelocity){

        double Xvelocity;
        if(this.gunPlayer.getXVelocity() < 0){
            Xvelocity = -Constants.CANNON_GUN_BULLET_VELOC;
        } else{Xvelocity = Constants.CANNON_GUN_BULLET_VELOC;}


        this.bulletUp = new Bullet(this.pane, timeline, Xvelocity, 1 * bulletYVelocity);
        this.bulletUp.setBullet(this.gun);
        this.bulletUp.setColor(Color.GRAY);
        this.bulletUp.getNode().setRadius(Constants.CANNON_BULLET_RADIUS);

    }

}
