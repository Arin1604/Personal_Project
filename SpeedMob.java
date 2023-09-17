package indy;

import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class SpeedMob extends Mob {



    /**
     * This is the constructor for speed mob, a class that inherits from mob
     */
    public SpeedMob(Pane mainPane, int PlatformXLoc, int PlatformYLoc, Timeline timeline, Player player) {
        super(mainPane, PlatformXLoc, PlatformYLoc, timeline, player);


    }

    /**
     * This method overrides the patrol method to increase the speed of patrolling
     */
    @Override
    public void patrol() {
        this.move(this.getDirection() * 4);
    }
}



