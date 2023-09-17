package indy;

import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class BruteMob extends Mob {


    /**
     * This is the constructor for BruteMob, a class that inherits from Mob.
     */
    public BruteMob(Pane mainPane, int PlatformXLoc, int PlatformYLoc, Timeline timeline, Player player) {
        super(mainPane, PlatformXLoc, PlatformYLoc, timeline, player);
    }

    /**
     * This method overrides the patrol method in Mob and is responsible for the slow speed of the bruteMob.
     */
    @Override
    public void patrol() {
        this.move(this.getDirection() * 1);
    }
}

