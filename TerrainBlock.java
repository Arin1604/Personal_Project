package indy;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TerrainBlock{

    private Rectangle topSoil;
    private Rectangle bedRock;
    private Pane pane;

    private int xloc;
    private int yloc;

    private Player player;

    /**
     * This is the constructor for TerrainBlock
     */
    public TerrainBlock(Pane mainPane, int Xloc, int Yloc, Player player){
        this.topSoil = new Rectangle(Constants.TOP_SOIL_WIDTH,Constants.TOP_SOIL_HEIGHT);
        this.bedRock = new Rectangle(Constants.TOP_SOIL_WIDTH,Constants.SCENE_HEIGHT);


        this.pane = mainPane;
        this.player = player;


        this.setXloc(Xloc);
        this.setYLoc(Yloc);

    }

    /**
     * This method is overriden by the subclasses to change the speed of the player
     */
    public void changePlayerSpeed(Player player){
        player.setXVelocity(player.getXVelocity());

    }

    /**
     * This method sets the color of the terrain block
     */
    public void setColor(Color topsoil, Color bedrock){
        this.topSoil.setFill(topsoil);
        this.bedRock.setFill(bedrock);


    }

    /**
     * This method returns the XLoc of the block
     */
    public int getXloc(){
        return (int) this.topSoil.getX();
    }
    /**
     * This method returns the YLoc of the block
     */

    public int getYloc(){
        return (int) this.topSoil.getY();
    }

    /**
     * This method adds the topsoil and bedrock to the pane
     */

    public void addBlockToPane(){
        this.pane.getChildren().addAll(this.topSoil, this.bedRock);
    }

    /**
     * This method sets the YLoc of the shapes
     */
    public void setYLoc(int y){
        this.topSoil.setY(y);
        this.bedRock.setY(y + Constants.TOP_SOIL_HEIGHT);
    }

    /**
     * This method sets the XLoc of the shape
     */
    public void setXloc(int x){
        this.topSoil.setX(x);
        this.bedRock.setX(x);
    }


    /**
     * This method returns a boolean depending on whether the player intersects with the topsoil or not
     */
    public Boolean isColliding(Player player){
        if(this.topSoil.intersects(player.returnBounds())){
            return true;
        } else {
            return false;
        }
    }
    /**
     * This method returns a boolean depending on whether the player intersects with the bedrock or not
     */
    public Boolean isCollidingBedRock(){
        if(this.bedRock.intersects(this.player.returnBounds())){
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method returns the topsoil
     */
    public Rectangle getTopSoil(){
        return this.topSoil;
    }

    /**
     * This method returns the bedrock
     */
    public Rectangle getBedRock(){
        return this.bedRock;
    }

    /**
     * This method returns false. It is overriden by the Sandy Terrain Block to return true
     */
    public Boolean isSandy(){
        return false;
    }

    /**
     *  This method returns false. It is overriden by the Ice Terrain Block to return true
     */
    public Boolean isIcy(){
        return false;
    }
}
