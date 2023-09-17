package indy;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BridgeBlock extends TerrainBlock{


    private Timeline timeline;

    /**
     * This is the constructor for bridgeBlock, a class that inherits from TerrainBlock.
     */
    public BridgeBlock(Pane mainPane, int Xloc, int Yloc, Player player){
        super(mainPane, Xloc, Yloc, player);


    }

    /**
     * This method makes the bridge block's y position change with a timeline
     */
 public void Fall() {
     KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION),
             (ActionEvent e) -> this.drop());
     this.timeline = new Timeline(kf);
     this.timeline.setCycleCount(Animation.INDEFINITE);
     this.timeline.play();
 }

    /**
     * This method increments the Yloc by 1 everytime it is called in the timeline
     */

   public void drop(){
        this.setYLoc((this.getYloc() + 1));
   }
}


