package indy;

import javafx.scene.layout.Pane;

public class SandyTerrainBlock extends TerrainBlock{

    private double slowDownVelocity;


    /**
     * This is the constructor for SandyterrainBlock that inherits from TerrainBlock
     */
    public SandyTerrainBlock(Pane mainPane, int Xloc, int Yloc, Player player, double Velocity){
        super(mainPane, Xloc, Yloc, player);
        this.slowDownVelocity = Velocity;


    }

    /**
     * This method overrides the changePlayerSpeed method to slow the player's velocity.
     */
    @Override
    public void changePlayerSpeed(Player player){
        if(player.getXVelocity() > 0){
            player.setXVelocity(5);
        } else if (player.getXVelocity() < 0) {
            player.setXVelocity(-5);

        }
    }

    /**
     * This method overrides isSandy from TerrainBlock to return true
     */
    @Override
    public Boolean isSandy(){
        return true;
    }

}
