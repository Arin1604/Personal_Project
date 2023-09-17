package indy;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class Bullet {

    private Circle bullet;
    private Pane pane;

    private Timeline bulletTimeline;

    private double xVeloc;
    private double yVeloc;

    /**
     * This is the constructor for my Bullet Class. It is here that I associate Pane,
     * Timeline, XVelocity and Yvelocity
     */
    public Bullet(Pane mainPane, Timeline timeline, double XVelocity, double YVelocity){
        this.pane = mainPane;
        this.bulletTimeline = timeline;
        this.xVeloc = XVelocity;
        this.yVeloc = YVelocity;

    }

    /**
     * This method returns the node of the bullet.
     */
    public Circle getNode(){
        return this.bullet;
    }

    /**
     * This method sets the color of the bullet.
     */
    public void setColor(Color color){
        this.bullet.setFill(color);
    }

    /**
     * This method sets up the timeline for the bullet and calls the onBulletTimelineUpdate method.
     */

    private void setBulletTimeline(){
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION),
                (ActionEvent e) -> this.onBulletTimelineUpdate());
        this.bulletTimeline = new Timeline(kf);
        this.bulletTimeline.setCycleCount(Animation.INDEFINITE);
        this.bulletTimeline.play();
    }

    /**
     * This method updates the position of the bullet with respect to the X and Y velocities that are
     * passed in the constructor
     */

    private void onBulletTimelineUpdate() {
        this.updateBulletPosition(this.xVeloc, this.yVeloc);
    }

    /**
     * This method is responsible for creating the bullet, adding it to the pane and setting up its timeline
     */
    public void setBullet(Rectangle gun){
        this.bullet = new Circle(Constants.BULLET_RADIUS);
        this.bullet.setFill(Color.RED);
        this.bullet.setCenterX(gun.getX());
        this.bullet.setCenterY(gun.getY());
        this.pane.getChildren().add(this.bullet);
        this.setBulletTimeline();
    }

    /**
     * This method takes in X and Y velocities as parameters and updates the bullet's X and Y location using the
     * velocities
     */
    public void updateBulletPosition(double xVelocity, double yVelocity){
        this.bullet.setCenterX(this.bullet.getCenterX() + xVelocity * Constants.DURATION);
        this.bullet.setCenterY(this.bullet.getCenterY() + yVelocity * Constants.DURATION);

    }

    /**
     * This method is an accessor method. Returns the X location of the bullet's center
     */
    public double getX(){
        return this.bullet.getCenterX();
    }


    /**
     * This method is an accessor method. Returns the Y location of the bullet's center
     */
    public double getY(){
        return this.bullet.getCenterY();
    }
}
