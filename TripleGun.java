package indy;

import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class TripleGun extends Gun{
    private Pane mainPane;
    private Player gunPlayer;

    private Bullet[] bulletArray;


    /**
     * This is the constructor for TripleGun that inherits from gun
     */
    public TripleGun(Pane pane, Player player){
        super(pane, player);
        this.mainPane = pane;
        this.gunPlayer = player;
        this.bulletArray = new Bullet[4];
    }

    /**
     * This method overrides getBullet to pass in a bullet that is an element of an array
     */
    @Override
    public Bullet getBullet(int i){
        return this.bulletArray[i];
    }

    /**
     * This method overrides isTripleGun to return true
     */
    @Override
    public Boolean isTripleGun(){
        return true;
    }

    /**
     * This method overrides the fire method and uses a for loop to populate an array of bullets
     */
    @Override
    public void fire(Timeline timeline, double bulletYVeloc){
        double Xvelocity;
        if(this.gunPlayer.getXVelocity() < 0){
            Xvelocity = -Constants.BULLET_VELOC;
        } else{Xvelocity = Constants.TRIPLE_GUN_BULLET_VELOC;}

        bulletYVeloc = Constants.BULLET_VELOC;

        for (int i = 0; i < 4; i++) {
            int x;
            if(i == 1){x = 0;}else {x =1;}
            int direction = (int) (x * Math.pow(-1, i));
            this.bulletArray[i] = new Bullet(this.mainPane, timeline, Xvelocity, bulletYVeloc * direction);
            //this.bulletArray[i].setColor(Color.AQUAMARINE);
            this.bulletArray[i].setBullet(this.gun);


        }
        }

    }



