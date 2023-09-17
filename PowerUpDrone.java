package indy;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PowerUpDrone {

    private Rectangle drone;
    private Pane pane;

    private droneMotion droneStatus;

    /**
     * This is the constructor for the Power Up Drone
     */
    public PowerUpDrone(Pane mainPane){
        this.pane = mainPane;
        this.setUpDrone();
        this.droneStatus = droneMotion.DOWN;

    }

    /**
     * This method sets up the drone and adds it to the pane
     */
    private void setUpDrone(){
        this.drone = new Rectangle(Constants.DRONE_SIDE,Constants.DRONE_SIDE);
        this.drone.setX(-Constants.XDRONETRANSLATE);
        this.drone.setY(0);
        this.pane.getChildren().add(this.drone);
    }

    /**
     * This enum helps us guide the motion of the drone
     */
    private enum droneMotion{
        DOWN, UP
    }

    /**
     * This method increases drone's XLoc by 2
     */
    public void moveRight(){
        this.drone.setX(this.drone.getX()+ 2);

    }

    /**
     * This method returns the bounds of drone
     */
    public Bounds getBounds(){
        return this.drone.getBoundsInLocal();
    }

    /**
     * This method controls the vertical motion of the drone with the help of enums that are
     * stated in the verticalMotionChecker helper method.
     */
    public void verticalMotion(){
        this.verticalMotionChecker();
        if(this.droneStatus == droneMotion.UP){
            this.drone.setY(this.drone.getY() - 2);
        } else if (this.droneStatus == droneMotion.DOWN) {
            this.drone.setY(this.drone.getY() + 2);
        }
    }

    /**
     * This method returns the node of the rectangle;
     */
    public Rectangle getNode(){
         return this.drone;
    }

    /**
     * This method removes the drone
     */

    public void remove(){
        this.pane.getChildren().remove(this.drone);
    }

    /**
     * This method changes the drone status according to its height
     */
    private void verticalMotionChecker(){
        if(this.drone.getY()> Constants.SCENE_HEIGHT/6)
            this.droneStatus = droneMotion.UP;
        else if (this.drone.getY() < 0) {
            this.droneStatus = droneMotion.DOWN;


        }
    }
}
