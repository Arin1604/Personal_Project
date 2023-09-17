package indy;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class MobGenerator {
    private Pane pane;
    private Timeline mobTimeline;
    private Player user;
    private Gun mobGun;


    private ArrayList<Mob> mobArray;

    private ArrayList<Bullet> mobBulletArray;

    /**
     * This is the constructor for MobGenerator
     */
    public MobGenerator(Pane mainPane, Timeline timeline, Player player, Gun gun) {
        this.pane = mainPane;
        this.mobTimeline = timeline;
        this.user = player;
        this.mobArray = new ArrayList<>();
        this.mobBulletArray = new ArrayList<>();
        this.setUpMobFiringTimeline();


    }


    /**
     * This method uses the returnMob helper method to add a new mob to the array list
     */
    public void generateMob(TerrainBlock block) {
        int XLoc = (block.getXloc() + Constants.TOP_SOIL_WIDTH) / 2;
        int YLoc = block.getYloc() - 80;
        //this.mobPlatform = block;


        this.mobArray.add(this.returnMob(XLoc, YLoc));
    }

    /**
     * This method returns the arrayList of mobs
     */
    public ArrayList getMobArray(){
        return this.mobArray;
    }

    /**
     * This method uses a factory pattern to get a random mob.
     */
    public Mob returnMob(int MobXLoc, int MobYLoc) {
        int randInt = ((int) (Math.random() * 3));
        Mob mob;
        switch (randInt) {
            case 0:
                mob = new SpeedMob(this.pane, MobXLoc, MobYLoc, this.mobTimeline, this.user);
                mob.setUpMobHitPoint(7);
                mob.setColor(Color.CADETBLUE);
                this.mobGun = new Gun(this.pane, mob);


                this.pane.getChildren().add(mob.getNode());


                break;


            case 1:
                mob = new BruteMob(this.pane, MobXLoc, MobYLoc, this.mobTimeline, this.user);
                mob.setUpMobHitPoint(50);
                mob.setColor(Color.BISQUE);
                this.mobGun = new CannonGun(this.pane, mob);
                this.pane.getChildren().add(mob.getNode());
                mob.changeSize(Constants.PLAYER_WIDTH * 2, Constants.PLAYER_HEIGHT);
                break;



            default:


                mob = new Mob(this.pane, MobXLoc, MobYLoc, this.mobTimeline, this.user);
                this.mobGun = new CannonGun(this.pane, mob);
                this.mobGun.setColor(Color.GRAY);
                mob.setUpMobHitPoint(20);

                mob.changeSize(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
                this.pane.getChildren().add(mob.getNode());

                break;


        }
        return mob;
    }

    /**
     * This method sets up the mobTimeline
     */
    public void setUpMobTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.MOB_DURATION),
                (ActionEvent e) -> this.onMobGeneratorTimeline());
        this.mobTimeline = new Timeline(kf);
        this.mobTimeline.setCycleCount(Animation.INDEFINITE);
        this.mobTimeline.play();
        //this.mob.
    }

    /**
     * This method sets up the mob firing timeline
     */
    public void setUpMobFiringTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.MOB_FIRING_DURATION),
                (ActionEvent e) -> this.onMobFiringTimeline());
        this.mobTimeline = new Timeline(kf);
        this.mobTimeline.setCycleCount(Animation.INDEFINITE);
        this.mobTimeline.play();
    }

    /**
     * This method iterates through the arrayList of mobs and calls the shoot method on the mobs
     */
    public void onMobFiringTimeline() {
        for (int i = 0; i < this.mobArray.size(); i++) {
            if (this.mobArray.get(i) != null) {
                this.mobArray.get(i).shoot((this.mobGun));
                this.mobBulletArray.add(this.mobArray.get(i).getBullet());

            }
        }
    }

    /**
     * This method returns the mobBulletArrayList;
     */
    public ArrayList<Bullet> getMobBulletArray(){
        return this.mobBulletArray;
    }

    /**
     * This method iterates through the array of mobs and calls the on mob timeline update method.
     * It also updates the location of the target circle with respect to the mob's motion.
     */

    public void onMobGeneratorTimeline() {
        for (int i = 0; i < this.mobArray.size(); i++) {
            this.mobArray.get(i).onMobTimeline();
            this.mobGun.updateGunsPosition(this.mobArray.get(i).getX() + this.mobArray.get(i).getNode().getWidth(),
                    this.mobArray.get(i).getY() + this.mobArray.get(i).getNode().getHeight() / 4);
            this.updateMobGunsDirection();
            this.mobArray.get(i).getTargetCircle().setCenterX(this.mobArray.get(i).getX() + Constants.MOB_WIDTH / 2);
            this.mobArray.get(i).getTargetCircle().setCenterY(this.mobArray.get(i).getY() + Constants.MOB_WIDTH / 2);


        }
    }

    /**
     * This method scrolls the mobs
     */
    public void scrollMob() {
        for (int i = 0; i < this.mobArray.size(); i++) {
            if(this.user.getX()> 2 * Constants.SCENE_WIDTH/3)
            this.mobArray.get(i).setXLoc(this.mobArray.get(i).getX() + 20);
        }
    }

    /**
     * This method updates the mobs direction
     */
    public void updateMobGunsDirection() {
        for (int i = 0; i < this.mobArray.size(); i++) {
            if (this.mobArray.get(i).getDirection() < 0) {
                this.mobGun.updateGunsPosition(this.mobArray.get(i).getX() - Constants.MOB_WIDTH / 2,
                        this.mobArray.get(i).getY() + Constants.MOB_WIDTH / 4);
            } else {
            }
        }
    }



}
