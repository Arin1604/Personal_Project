package indy;

import javafx.scene.layout.Pane;

public class IceTerrainBlock extends TerrainBlock{

    private double iceVelocity;

    /**
     * This is the constructor for IceTerrainBlock that inherits from terrainBlock
     */
    public IceTerrainBlock(Pane mainPane, int Xloc, int Yloc, Player player, double Velocity){
        super(mainPane, Xloc, Yloc, player);
        this.iceVelocity = Velocity;
    }

    /**
     * This method overrides the method from TerrainBlock and makes it so that the player's
     * velocity is increased
     */
    @Override
    public void changePlayerSpeed(Player player){
        if(player.getXVelocity() > 0){
        player.setXVelocity(Constants.ICE_BLOCK_VELOC);
    } else if (player.getXVelocity() < 0) {
            player.setXVelocity(-Constants.ICE_BLOCK_VELOC);

        }

    }

    /**
     * This method overrides the isIcy method from terrainblock to return true
     */
    @Override
    public Boolean isIcy(){
        return true;
    }
}
