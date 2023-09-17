package indy;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Player {

    private Rectangle player;

    private Pane pane;

    private double XVelocity;

    private double YVelocity;


    private int playerHitPoint;


    /**
     * This is the Constructor for Player
     */
    public Player(Pane mainPane, int Xloc, int Yloc){
        this.player = new Rectangle(Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT);
        this.player.setFill(Color.RED);
        this.setXLoc(Xloc);
        this.setYloc(Yloc);
        this.XVelocity = 0;
        this.YVelocity = 0;
        this.pane = mainPane;
        this.playerHitPoint = Constants.PLAYER_HP;


    }
    /**
     * This method returns player hit point
     */
    public int getPlayerHitPoint(){
        return this.playerHitPoint;

    }

    /**
     * This method changes the player's hitpoint according to an integer parameter
     */
    public void HealthChange(int x){
        this.playerHitPoint = this.playerHitPoint + x;

    }

    /**
     * This method returns node
     */
    public Rectangle getNode(){
        return this.player;
    }

    /**
     * This method adds player to pane
     */

    public void addPlayerToPane(){
        this.pane.getChildren().add(player);
    }

    /**
     * This method implements the crouch mechanics
     */
    public void crouch(double x, double y){
        this.player.setWidth(x);
        this.player.setHeight(y);
    }

    /**
     * This method changes the XLoc of player according to a velocity passed in as a parameter
     */
    public void move(double velocity){
        this.XVelocity = velocity;
        double newXLoc = this.player.getX() + this.XVelocity * Constants.DURATION;
        this.player.setX(newXLoc);
    }


    /**
     * This method changes the YLoc of player according to a velocity passed in as a parameter
     */
    public void jump(double velocity){
        double newYloc = this.player.getY() + velocity * Constants.DURATION;
        this.player.setY(newYloc);
    }

    /**
     * This method returns player's x velocity
     */
    public double getXVelocity(){
        return this.XVelocity;
    }

    /**
     * This method sets the players x velocity
     */
    public void setXVelocity(double velocity){
        this.XVelocity = velocity;
    }

    /**
     * This method returns the player's Y velocity
     */
    public double getYVelocity() {
        return YVelocity;
    }

    /**
     * This method sets the player's Y velocity
     */
    public void setYVelocity(double velocity){
        this.YVelocity = velocity;

    }

    /**
     * This method is responsible for the falling mechanism
     */
    public void fallingPlayer(double velocity){
        double newYLoc = this.player.getY() + velocity * Constants.DURATION;
        this.player.setY(newYLoc);

    }

    /**
     * This method returns player's bounds
     */
    public Bounds returnBounds(){
        return this.player.getBoundsInLocal();
    }


    /**
     * This method returns player's XLoc
     */
    public double getX(){
        return this.player.getX();
    }

    /**
     * This method returns player's YLoc
     */

    public double getY(){
        return this.player.getY();
    }

    /**
     * This method sets player's XLoc
     */
    public void setXLoc(double XLoc){
        this.player.setX(XLoc);
    }

    /**
     * This method sets player's YLoc
     */
    public void setYloc(double YLoc){
        this.player.setY(YLoc);
    }
}
