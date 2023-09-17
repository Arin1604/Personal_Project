package indy;

import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class TerrainGenerator {

    private Player terrainPlayer;
    private Pane pane;
    private TerrainBlock block1;
    private double difference;

    private ArrayList<TerrainBlock> terrainBlockArrayList;

    private double playerVeloc;

    private Bridge bridge;

    private MobGenerator mobGenerator;


    /**
     * This is the constructor for TerrainGenerator
     */
    public TerrainGenerator(Pane mainPane, Player player, double playerVelocity, Timeline timeline, Gun gun){

        this.terrainPlayer = player;
        this.pane = mainPane;
        this.playerVeloc = playerVelocity;
        this.mobGenerator = new MobGenerator(mainPane, timeline, player, gun);
        this.setUpFirstTerrain();
        this.generateTerrain();
    }

    /**
     * This method sets up the first terrain
     */
    private void setUpFirstTerrain(){
        this.terrainBlockArrayList = new ArrayList<>();
        this.block1 = new TerrainBlock(this.pane, Constants.PLAYER_START_XLOC, Constants.SCENE_HEIGHT/2,
                this.terrainPlayer);
        this.block1.setColor(Color.LIGHTGREEN, Color.CHOCOLATE);
        this.terrainBlockArrayList.add(this.block1);
       this.mobGenerator.setUpMobTimeline();

    }

    /**
     * This method returns the mob array from mob generator
     */
    public ArrayList<Mob> getMobArrayList(){
        return this.mobGenerator.getMobArray();
    }

    /**
     * This method returns the mob array from mob generator
     */
    public ArrayList<Bullet> getMobBullets(){
        return this.mobGenerator.getMobBulletArray();
    }

    /**
     * This method uses a returnRandomTerrain helper method and sets up the location of the block
     * obtained from the helper method
     */
    private void generateTerrain(){


        TerrainBlock LatestBlock =  this.terrainBlockArrayList.get(terrainBlockArrayList.size() -1);
        while (LatestBlock.getXloc() + Constants.TOP_SOIL_WIDTH < Constants.SCENE_WIDTH){

            double newXLoc = LatestBlock.getXloc() + Constants.TOP_SOIL_WIDTH;
            double newYLoc = LatestBlock.getYloc();

           TerrainBlock newTerrainBlock =  this.returnRandomTerrain((int) newXLoc, (int) newYLoc);
           if(newTerrainBlock != null){
               newTerrainBlock.changePlayerSpeed(this.terrainPlayer);

           LatestBlock = newTerrainBlock;}


        }

    }

    /**
     * This method uses a factory pattern to space out the generation of mobs
     */
    private void mobSpacer(TerrainBlock Block){
        Mob mob;
        int randInt = ((int) (Math.random() * 2));
        TerrainBlock block = Block;

        switch (randInt){
            case 0:

                this.mobGenerator.generateMob(block);
                this.mobGenerator.setUpMobTimeline();
//

                break;

            default:

                break;

        }

    }

    /**
     * This method uses a factory pattern to create a new terrain block, adds that block to the arraylist and pane
     * and then passes it to mobspacer.
     */
    private TerrainBlock returnRandomTerrain(int XLoc, int YLoc){
        int randInt = ((int) (Math.random() * 8
        ));
        TerrainBlock block;

        switch (randInt){
            case 0:
                block = new SandyTerrainBlock(this.pane, XLoc, YLoc, this.terrainPlayer, this.playerVeloc);
                block.setColor(Color.BEIGE, Color.SANDYBROWN);
                this.terrainBlockArrayList.add(block);
                this.pane.getChildren().add(block.getTopSoil());
                this.pane.getChildren().add(block.getBedRock());
                this.mobSpacer(block);

                break;

            case 1:
                this.bridge = new Bridge(this.pane, this.terrainPlayer, XLoc, YLoc, this.terrainBlockArrayList);
                block = this.bridge.returnLastBlock();
                this.mobSpacer(block);
                break;

            case 2:
                block = new IceTerrainBlock(this.pane, XLoc, YLoc, this.terrainPlayer, this.playerVeloc);
                block.setColor(Color.WHITE, Color.LIGHTBLUE);
                this.terrainBlockArrayList.add(block/*this.bridgeQueue*/);
                this.pane.getChildren().add(block.getTopSoil());
                this.pane.getChildren().add(block.getBedRock());
                this.mobSpacer(block);
                break;


            default:
                block = new TerrainBlock(this.pane, XLoc, YLoc, this.terrainPlayer);
                block.setColor(Color.LIGHTGREEN, Color.CHOCOLATE);
                this.terrainBlockArrayList.add(block/*this.bridgeQueue*/);
                this.pane.getChildren().add(block.getTopSoil());
                this.pane.getChildren().add(block.getBedRock());
                this.mobSpacer(block);
                break;


        }

        return block;
    }
    /**
     * This method returns bridge is it is not null
     */
    public Bridge getCollidingBridge(){
        if(this.bridge != null) return this.bridge;

        else {return null;}
    }

    /**
     * This method scrolls the blocks and generates the terrain at the end
     */

    public void scroll(){
        if (this.terrainPlayer.getX() > Constants.SCENE_WIDTH/2){
            this.difference = Constants.SCENE_WIDTH/2 - this.terrainPlayer.getX();

            for(int i =0; i < this.terrainBlockArrayList.size(); i++){
                this.terrainBlockArrayList.get(i).setYLoc(this.terrainBlockArrayList.get(i).getYloc());
                this.terrainBlockArrayList.get(i).setXloc((int)
                        (this.terrainBlockArrayList.get(i).getXloc() + difference));
                this.remove(i);
                this.terrainPlayer.setXLoc(Constants.SCENE_WIDTH/2);
                this.terrainPlayer.setYloc(this.terrainPlayer.getY());
            }
        }
        this.mobGenerator.scrollMob();
        this.generateTerrain();
    }

    /**
     * This method removes the block
     */
    private void remove(int i){
        if(this.terrainBlockArrayList.get(i).getXloc() < -Constants.TOP_SOIL_WIDTH){
            this.terrainBlockArrayList.get(i).setXloc(-1000);
            this.terrainBlockArrayList.remove(this.terrainBlockArrayList.get(i));
        }

    }

    /**
     * This method returns the first block
     */
    public TerrainBlock getBlock(){
        return this.block1;
    }

    /**
     * This method checks the isColliding method of terrain blocks by iterating through the elements of
     * the array list
     */
    public Boolean isCollidingPrime(){
       for(int i =0;i< terrainBlockArrayList.size(); i++){
        if( this.terrainBlockArrayList.get(i).isColliding(this.terrainPlayer)== true){return true;}
       }
       return false;
    }

    /**
     * This method changes the player's speed according to the type of block player steps on
     */
    public void changePlayerSpeed(){
        for(int i =0;i< terrainBlockArrayList.size(); i++){
            if(this.terrainBlockArrayList.get(i).isColliding(this.terrainPlayer)== true
                    && this.terrainBlockArrayList.get(i).isIcy()){
                this.terrainBlockArrayList.get(i).changePlayerSpeed(this.terrainPlayer);
            } else if (this.terrainBlockArrayList.get(i).isColliding(this.terrainPlayer) == true
                    && this.terrainBlockArrayList.get(i).isSandy()) {
                this.terrainBlockArrayList.get(i).changePlayerSpeed(this.terrainPlayer);

            }
        }


    }

    /**
     * This method checks the isCollidingBedRock method of terrain blocks by iterating through the elements of
     * the array list
     */
    public Boolean isCollidingBedRockPrime(){
        for(int i =0;i< terrainBlockArrayList.size(); i++){
            if( this.terrainBlockArrayList.get(i).isCollidingBedRock()== true){return true;}
        }
        return false;
    }

}
