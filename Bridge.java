package indy;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Bridge {
    private Queue<BridgeBlock> bridgeQueue;

    private BridgeBlock bridgeBlock1;
    private Pane pane;
    private Player terrainPlayer;
    private int XLoc;
    private int YLoc;
    private ArrayList arrayList;
    private BridgeBlock lastBlock;


    /**
     * The constructor for my Bridge Class. The Queue containing the bridge blocks is instantiated here.
     * The constructor also takes in an arrayList so that the Bridge can be appropriately added to the
     * arrayList containing the other terrain blocks.
     */

    public Bridge(Pane mainPane, Player player, int Xloc, int Yloc, ArrayList<TerrainBlock> arrayList) {
        this.arrayList = arrayList;
        this.bridgeQueue = new Queue<>();
        this.pane = mainPane;
        this.terrainPlayer = player;
        this.XLoc = Xloc;
        this.YLoc = Yloc;
        this.bridgeGenerator();
        System.out.println("new bridge");
    }

    /**
     * This method instantiates the first bridge block in the queues and adds it to the queue using the
     * enqueue method. It also fills the bridge with the appropriate ImagePattern.
     */

    private void setUpFirstBridge() {
        this.bridgeQueue = new Queue<>();

        this.bridgeBlock1 = new BridgeBlock(this.pane, this.XLoc, this.YLoc , this.terrainPlayer);
        this.arrayList.add(this.bridgeBlock1);
        this.bridgeBlock1.setColor(Color.HOTPINK, Color.DEEPPINK);
        this.bridgeBlock1.addBlockToPane();
        this.bridgeQueue.enqueue(this.bridgeBlock1);

    }

    /**
     * This method populates the Bridge with bridge blocks. Additionally, it also adds the bridge blocks
     * to the main array list containing all the terrain blocks
     */
    public Queue<BridgeBlock> bridgeGenerator() {
        this.setUpFirstBridge();
        BridgeBlock block;
        for (int i=1; i<7; i++) {
            System.out.println(i);
            block = new BridgeBlock(this.pane, this.bridgeBlock1.getXloc() + (Constants.TOP_SOIL_WIDTH * i) ,
                    this.YLoc, this.terrainPlayer);
            block.setColor(Color.HOTPINK, Color.DEEPPINK);
            block.addBlockToPane();
            this.arrayList.add(block);
            this.bridgeQueue.enqueue(block);
            this.lastBlock = block;
        }

        return this.bridgeQueue;
    }

    /**
     * This method is responsible for causing the bridge to fall after the player intersects with it
     */

    public void bridgeDestroyer() {
        if (this.bridgeQueue != null) {
            if (this.bridgeQueue.getList().getHead() != null) {

                if (this.bridgeQueue.peek().isColliding(this.terrainPlayer)) {
                    this.bridgeQueue.dequeue().Fall();
                }

            }
        }
    }

    /**
     * This method sets up the color of the bridgeBlock
     */

    public void setColor(Color color1, Color color2){
        this.bridgeBlock1.setColor(color1, color2);
    }

    /**
     * This method returns the last block that was added to the queue.
     */
    public BridgeBlock returnLastBlock(){
        return this.lastBlock;
    }


}
