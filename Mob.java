package indy;

import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Mob extends Player {

    private Timeline mobTimeline;
    private Player user;

    private int direction;

    private int platformX;

    private Circle targetCircle;


    private double bulletXVelocDirection;
    private double bulletYVelocDirection;


    private Pane pane;

    private Bullet bullet;

    private int mobHitPoint;

    /**
     * This is the constructor for Mob, a class that inherits from player
     */
    public Mob(Pane mainPane, int PlatformXLoc, int PlatformYLoc, Timeline timeline, Player player){
        super(mainPane, PlatformXLoc, PlatformYLoc);
        this.mobTimeline = timeline;


        this.user = player;
        this.direction = 1;
        this.platformX = PlatformXLoc;
        this.pane = mainPane;
        //this.setUpMovingPlatformTimeline();
        this.createTargetCircle();


    }

    /**
     * This method sets up the mob's hitpoint value to an int taken in as a parameter
     */
    public void setUpMobHitPoint(int value){
        this.mobHitPoint = value;

    }
    /**
     * This method reduces mob's hit point by 1.
     */
    public void onHit(){
        this.mobHitPoint = this.mobHitPoint -1;
    }

    /**
     * This method sets the color of the mob
     */
    public void setColor(Color color){
        this.getNode().setFill(color);
    }

    /**
     * This method returns mob's hit points
     */

    public int getMobHitPoint(){
        return this.mobHitPoint;
    }

    /**
     * This method determines what happens in the mobTimeline in mobGenerator
     */
    public void onMobTimeline(){
        this.edgeCase();
        this.patrol();
        this.playerInRange();
        this.setBulletDirection();

    }

    /**
     * This method is responsible for the patrolling mechanic of the mobs
     */
    public void patrol(){

        this.move(this.direction * 2);


    }

    /**
     * This method changes the mobs direction when it reaches the edge of a terrain block
     */
    public void edgeCase(){

        if(this.getX() >= this.platformX + Constants.TOP_SOIL_WIDTH/2){
            this.direction = -1;
        } else if (this.getX() <= this.platformX - Constants.TOP_SOIL_WIDTH/2) {
            this.direction = 1;

        }
    }


    /**
     * This method changes the size of the mobs
     */
    public void changeSize(int width, int height){
        this.getNode().setWidth(width);
        this.getNode().setHeight(height);
    }

    /**
     * This method creates the target circle for the aiming algorithm
     */
    public void createTargetCircle(){
        int Xcenter = (int) (this.getX() + (Constants.MOB_WIDTH/2));
        int Ycenter = (int) (this.getY() + (Constants.MOB_WIDTH/2));
        this.targetCircle = new Circle(Xcenter, Ycenter, Constants.TARGET_RADIUS);

    }

    /**
     * This helper method returns a boolean depending on whether the player is in range, i.e., whether the player
     * intersects the target circle
     */
    public boolean playerInRange(){
        if(this.targetCircle.intersects(this.user.returnBounds())){
            return true;
        } else {return false;}
    }


    /**
     * This method updates the bullet's direction by calculating the direction to the player.
     */
    public void setBulletDirection(){
        double x1 = this.user.getX();
        double y1 = this.user.getY();

        double x2 = this.getX();
        double y2 = this.getY();

        double slope = ((y1  - (y2 ))/ (x1 - x2));
        double angle = Math.atan(slope);

        this.bulletXVelocDirection = 1 * this.direction * Math.cos(angle);
        this.bulletYVelocDirection = Math.sin(angle);
    }

    /**
     * This method removes the mob
     */
    public void removeMob(){
        this.pane.getChildren().remove(this.getNode());
        this.pane.getChildren().remove(this.targetCircle);

    }

    /**
     * This method ultimately shoots the bullet by creating a new bullet and setting its velocity
     * to the updated instance variables
     */
    public void shoot(Gun gun) {

        if (playerInRange()) {

            if (this.user.getX() > this.getX() && this.direction == 1) {
                this.bulletXVelocDirection = this.bulletXVelocDirection * 1;

                this.bullet = new Bullet(this.pane, this.mobTimeline,
                        this.bulletXVelocDirection * Constants.BULLET_VELOC,
                        this.bulletYVelocDirection * Constants.BULLET_VELOC);
                this.bullet.setBullet(gun.getNode());
            } else if (this.user.getX() < this.getX() && this.direction == -1) {
                this.bulletXVelocDirection = this.bulletXVelocDirection * 1;
                this.bulletYVelocDirection = this.bulletYVelocDirection * -1;

                this.bullet = new Bullet(this.pane, this.mobTimeline,
                        this.bulletXVelocDirection * Constants.BULLET_VELOC,
                        this.bulletYVelocDirection * Constants.BULLET_VELOC);
                this.bullet.setBullet(gun.getNode());


            }
            {
            }
        }
    }

    /**
     * This method returns the bullet
     */
    public Bullet getBullet(){
        return this.bullet;
    }

    /**
     * This method returns the direction
     */
    public int getDirection(){
        return this.direction;
    }

    /**
     * This method returns the targetCircle;
     */
    public Circle getTargetCircle(){
        return this.targetCircle;
    }


    }








