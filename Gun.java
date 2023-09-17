package indy;

import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Gun {

    private Player gunPlayer;
    Rectangle gun;
    private Pane pane;
    private Bullet bullet;
    /**
     * This is the constructor for Gun. It sets up the gun.
     */
    public Gun(Pane mainPane, Player player){
        this.gunPlayer = player;
       // this.playerNode = playerNode;
        this.pane = mainPane;
        this.setUpGun();

    }


    /**
     * This method sets up the graphical element of gun
     */
    public void setUpGun(){
        this.gun = new Rectangle(40,10);
        this.gun.setX(this.gunPlayer.getX() + this.gunPlayer.getNode().getWidth());
        this.gun.setY(this.gunPlayer.getY() + this.gunPlayer.getNode().getHeight()/2);
        this.gun.setFill(Color.BLACK);
        this.pane.getChildren().add(this.gun);

    }

    /**
     * This method updates the guns position
     */
    public void updateGunsPosition(double x, double y){
        this.gun.setX(x);
        this.gun.setY(y);


    }

    /**
     * This method creates a bullet and fires it with a specified velocity
     */
    public void fire(Timeline timeline, double bulletYVelocity){
        double Xvelocity;
        if(this.gunPlayer.getXVelocity() < 0){
            Xvelocity = -Constants.BULLET_VELOC;
        } else{Xvelocity = Constants.BULLET_VELOC;}

        this.bullet = new Bullet(this.pane, timeline, Xvelocity, bulletYVelocity);
        this.bullet.setBullet(this.getNode());

    }


    /**
     * This method returns gun's XLoc
     */
    public double getX(){
        return this.gun.getX();
    }
    /**
     * This method returns gun's Yloc
     */
    public double getY(){
        return this.gun.getY();
    }

    /**
     * This method returns node
     */
    public Rectangle getNode(){
        return this.gun;
    }

    /**
     * This method returns teh bullet
     */

    public Bullet getBullet(int i){
        return this.bullet;
    }

    /**
     * This method returns false. It is overriden in TripleGun to return true.
     */

    public Boolean isTripleGun(){
        return false;
    }


    /**
     * This method removes the gun
     */
    public void remove(){this.pane.getChildren().remove(this.gun);}


    /**
     * This method sets the color of the gun
     */
    public void setColor(Color color){
        this.gun.setFill(color);
    }

}
