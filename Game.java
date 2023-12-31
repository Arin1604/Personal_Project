package indy;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.ArrayList;

public class Game {


    private Timeline timeline;
    private Pane pane;

    private int score;

    TerrainBlock block1;
    private Player player;

    private playerStatus status;

    private gunDirectionStatus gunStatus;

    private bulletStatus bulletstatus;

    private Gun gun;
    private TerrainGenerator terrainGenerator;

    private double bulletYVelocity;


    private PowerUpDrone drone;

    private playerImageStat imageStat;

    private Image playerImage;
    private ImagePattern playerImagePattern;

    private Timeline animationTimeline;

    private Label scoreLabel;

    private Label hitPointsLabel;



    /**
     * This is the constructor for my top level logic class, game.
     */
    public Game(Pane mainPane) {

        this.pane = mainPane;
        this.setUpGame();
        this.setUpLabel();
        this.setUpTimeline();


        this.movePlayer();
        this.bulletstatus = bulletStatus.DEAD;

        this.status = playerStatus.UPRIGHT;
        this.gunStatus = gunDirectionStatus.STRAIGHT;
        this.bulletYVelocity = 0;
        this.block1 = this.terrainGenerator.getBlock();
        this.score = 0;
        this.setUpAnimationTimeline();

    }

    /**
     * This enum plays a crucial role in the running animation of the player.
     * It helps me efficiently switch to the appropriate image pattern.
     */
    private enum playerImageStat {
        C1, _C1, C2, _C2
    }


    /**
     * This method sets up the score and player health points label
     */
    private void setUpLabel() {
        this.scoreLabel = new Label("SCORE:" + this.score);
        this.scoreLabel.setPrefSize(Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.pane.getChildren().add(this.scoreLabel);
        this.scoreLabel.setTranslateY(Constants.TRANSLATE_Y);
        this.scoreLabel.setTranslateX(Constants.SCENE_WIDTH - Constants.LABEL_WIDTH);
        this.scoreLabel.setFont(Font.font("Verdana",
                FontWeight.BOLD, FontPosture.REGULAR, Constants.FONT_SIZE));
        this.changeLabelColor(this.scoreLabel);

        this.hitPointsLabel = new Label("SCORE:" + this.player.getPlayerHitPoint());
        this.hitPointsLabel.setPrefSize(Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.pane.getChildren().add(this.hitPointsLabel);
        this.hitPointsLabel.setTranslateY(Constants.TRANSLATE_Y);
        this.hitPointsLabel.setTranslateX(Constants.TRANSLATE_X);
        this.hitPointsLabel.setFont(Font.font("Verdana",
                FontWeight.BOLD, FontPosture.REGULAR, Constants.FONT_SIZE));
        this.changeLabelColor(this.hitPointsLabel);
    }

    /**
     * This method changes the color of my font used in label
     */
    private void changeLabelColor(Label myLabel) {
        int red = Constants.RED;
        int green = Constants.GREEN ;
        int blue = Constants.BLUE;
        Color customColor = Color.rgb(red, green, blue);
        myLabel.setTextFill(customColor);


    }

    /**
     * This method sets up a lot of the initial components of the game. The player is instantiated here.
     * The player's gun is also instantiated here along with terrainGenerator.
     * The player is given an image pattern and is
     * added to the pane. The block generated by terrainGenerator is also added to the pane
     */

    private void setUpGame() {
        this.player = new Player(this.pane, Constants.PLAYER_START_XLOC, Constants.SCENE_HEIGHT / 3);
        this.playerImage = new Image("indy/contra1.png");
        this.playerImagePattern = new ImagePattern(this.playerImage);
        this.player.getNode().setFill(this.playerImagePattern);
        this.imageStat = playerImageStat.C1;


        this.gun = new Gun(this.pane, this.player);


        this.terrainGenerator = new TerrainGenerator(this.pane, this.player,
                this.player.getXVelocity(), this.timeline, this.gun);
        TerrainBlock block = this.terrainGenerator.getBlock();
        block.addBlockToPane();
        this.player.addPlayerToPane();

    }

    /**
     * This is a helper method used in animating the player. This is where the updated Image Pattern is applied
     * to the player
     */
    private void updatePlayer() {
        this.player.getNode().setFill(this.updateImage());
    }

    /**
     * This helper method returns an updated image pattern with the help of the imageChanger helper
     * method
     */
    private ImagePattern updateImage() {
        this.imageChanger();
        ImagePattern imagePattern;
        imagePattern = new ImagePattern(this.playerImage);
        return imagePattern;
    }

    /**
     * This helper method uses conditional statements and the image enum to determine which image
     * should the player have. The outer conditional statements take care of the directional aspects of image
     * selection, while the inner statements decide which specific image to use for proper animation.
     */
    private void imageChanger() {
        if (this.player.getXVelocity() > 0) {
            if (this.imageStat == playerImageStat.C1 ||
                    imageStat == playerImageStat._C2 || imageStat == playerImageStat._C1) {
                this.playerImage = new Image("indy/Contra2.png");
                this.imageStat = playerImageStat.C2;
            } else if (this.imageStat == playerImageStat.C2 ||
                    imageStat == playerImageStat._C2 || imageStat == playerImageStat._C1) {
                this.playerImage = new Image("indy/contra1.png");
                this.imageStat = playerImageStat.C1;

            }
        } else if (this.player.getXVelocity() < 0) {
            if (this.imageStat == playerImageStat.C1 ||
                    imageStat == playerImageStat._C2 || imageStat == playerImageStat.C2) {
                this.playerImage = new Image("indy/contra-1.png");
                this.imageStat = playerImageStat._C1;
            } else if (this.imageStat == playerImageStat.C2 ||
                    imageStat == playerImageStat.C1 || imageStat == playerImageStat._C1) {
                this.playerImage = new Image("indy/Contra-2.png");
                this.imageStat = playerImageStat._C2;

            }


        } else if (this.player.getXVelocity() == 0) {
            this.playerImage = new Image("indy/contra1.png");
            this.imageStat = playerImageStat.C1;

        }

    }


    /**
     * This method checks if the player is hit by any of the bullets (from the mob bullet arraylist) that the mobs
     * fired. Upon intersection the player's fill briefly changes to dark red. The player's health is also reduced.
     * It is called with the timeline.
     */
    private void checkPlayerImpact(){
        ArrayList<Bullet> bulletArray = this.terrainGenerator.getMobBullets();
        for(int i = 0; i < bulletArray.size(); i++){
            if(bulletArray.get(i) != null){
                if (bulletArray.get(i).getNode().intersects(this.player.returnBounds())){
                    System.out.println("Player Hit");
                    this.player.HealthChange(-Constants.DECREMENT);
                    this.player.getNode().setFill(Color.DARKRED);
                    this.pane.getChildren().remove(bulletArray.get(i).getNode());
                }
            }

        }

    }

    /**
     * This method checks if the mob is hit by any of the player's bullets. This is also one of the places where
     * using polymorphism was extremely beneficial. The isTripleGun method helps decide whether the
     * collision is tested with bullets (in the case of all the other guns) or with an array of bullets
     * (in the case of the triple gun).
     *
     * The mob is eliminated if their health point is below 0. This method is called with timeline.
     */
    private void checkMobImpact() {
        ArrayList<Mob> mobArray = this.terrainGenerator.getMobArrayList();
        for (int i = 0; i < mobArray.size(); i++) {
            if (this.gun.isTripleGun() == false) {

                if (this.bulletstatus == bulletStatus.FIRED && this.gun.getBullet(0) != null) {
                    if (this.gun.getBullet(0).getNode().intersects(mobArray.get(i).returnBounds())) {
                        System.out.println("HITS");
                        mobArray.get(i).onHit();  //This method reduces the mob's health point
                        mobArray.get(i).setColor(Color.DARKRED);
                        System.out.println(mobArray.get(i).getMobHitPoint());
                        if (mobArray.get(i).getMobHitPoint() < 0) {
                            //This if statement is useful in determining if the mob's health point is less than 0;
                            // in which case the mob is eliminated.
                            this.score = this.score + Constants.INCREMENT;
                            //Upon Mob's elimination, the player is rewarded points.
                            mobArray.get(i).removeMob();
                            mobArray.remove(i);
                        }

                    }
                }

            } else {
                for (int j = 0; j < 4; j++) {
                    //This for-loop within a for-loop allows me to iterate through the array of bullets
                    // in the case of the triple gun

                    if (this.bulletstatus == bulletStatus.FIRED && this.gun.getBullet(j) != null) {
                        if (this.gun.getBullet(j).getNode().intersects(mobArray.get(i).returnBounds())) {
                            System.out.println("Hits");
                            mobArray.get(i).onHit();
                            mobArray.get(i).setColor(Color.DARKRED);
                            System.out.println(mobArray.get(i).getMobHitPoint());

                            }
                        }
                    }
                if (mobArray.get(i).getMobHitPoint() < 0) {
                    this.score = this.score + Constants.INCREMENT;
                    mobArray.get(i).removeMob();
                    mobArray.remove(i);
                }
            }
        }
    }

    /**
     * This method checks if the drone intersects with the player's bullets. If the drone is shot it calls the
     * helper method returnRandomGun to randomize the player's weapon and also give the player a health boost. Also
     * uses the isTripleGun method like in the previous method.
     */
    private void checkDroneImpact() {
        if (this.drone != null && this.gun.getBullet(0) != null) {
            if (this.gun.isTripleGun() == false) {

                if (this.bulletstatus == bulletStatus.FIRED) {
                    if (this.gun.getBullet(0).getNode().intersects(this.drone.getBounds())) {
                        System.out.println("GUN CHANGE");
                        this.drone.getNode().setFill(Color.RED);
                        this.drone.remove();
                        this.gun.remove();
                        this.gun = this.returnRandomGun();
                        this.player.HealthChange(+Constants.INCREMENT);

                    }
                }

            } else {
                for (int i = 0; i < 4; i++) {
                    if (this.bulletstatus == bulletStatus.FIRED && this.gun.getBullet(i) != null) {
                        if (this.gun.getBullet(i).getNode().intersects(this.drone.getBounds())) {
                            System.out.println("GUN CHANGE");
                            this.drone.getNode().setFill(Color.RED);
                            this.drone.remove();
                            this.gun.remove();
                            this.gun = this.returnRandomGun();


                        }
                    }
                }
            }
        }
    }

    /**
     * This method generates a random gun and returns it.
     */
    private Gun returnRandomGun() {
        Gun gun;
        int randInt = ((int) (Math.random() * 2));


        switch (randInt) {
            case 0:
                gun = new CannonGun(this.pane, this.player);
                gun.setColor(Constants.CANON_GUN_COLOR);

                break;

            case 1:
                gun = new TripleGun(this.pane, this.player);
                gun.setColor(Constants.TRIPLE_GUN_COLOR);
                break;


            default:
                gun = new Gun(this.pane, this.player);
                gun.setColor(Constants.GUN_COLOR);

                break;

        }
        return gun;
    }


    /**
     * This enum helps in dealing with crouch mechanics by setting the player status to one of two states
     */
    private enum playerStatus {
        CROUCHED, UPRIGHT

    }

    /**
     * This enum helps with changing the direction of the gun by setting the direction status
     * to one of three states.
     */
    private enum gunDirectionStatus {
        STRAIGHT, UP, DOWN
    }

    /**
     * This enum helps deal with bullets by setting the bulletStatus to one of two states Fired/Dead.
     */
    private enum bulletStatus {
        FIRED, DEAD
    }

    /**
     * This method sets up the main timeline and calls the onTimeline update method
     */
    private void setUpTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION),
                (ActionEvent e) -> this.onTimelineUpdate());
        this.timeline = new Timeline(kf);
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();

    }

    /**
     * This method sets up the animation timeline that is solely responsible for changing the player's image
     * with time to produce an animated effect of motion.
     */
    private void setUpAnimationTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.ANIMATION_DURATION),
                (ActionEvent e) -> this.updatePlayer());
        this.animationTimeline = new Timeline(kf);
        this.animationTimeline.setCycleCount(Animation.INDEFINITE);
        this.animationTimeline.play();

    }

    /**
     * This method calls all the methods that need to be called with the timeline
     */
    private void onTimelineUpdate() {
        this.gameOverChecker();
        this.updateVelocity();
        this.gun.updateGunsPosition(this.player.getX() + Constants.PLAYER_WIDTH,
                this.player.getY() + Constants.PLAYER_HEIGHT / 4);
        this.updateGunsDirection();
        this.player.move(this.player.getXVelocity());
        this.bedRockCollisionChecker();
        this.terrainGenerator.scroll();
        this.CollidingBridgeChecker();
        this.terrainGenerator.changePlayerSpeed();
        this.droneMover();
        this.checkDroneImpact();
        this.checkMobImpact();
        this.checkPlayerImpact();
        this.scoreLabel.setText("SCORE:" + this.score);
        this.hitPointsLabel.setText(("HIT POINTS:" + this.player.getPlayerHitPoint()));
    }

    /**
     * This method checks if the player is colliding with the bed rock. It prevents the player from going inside the
     * bed rock.
     */
    private void bedRockCollisionChecker(){
        if(this.terrainGenerator.isCollidingBedRockPrime() == true){
            this.player.setYVelocity(0);
            this.player.setXVelocity(0);
            this.player.setXLoc(this.player.getX() -Constants.INCREMENT);
        }
    }

    /**
     * This method creates a new PowerUpDrone every time the player reaches a certain score milestone
     * and calls the drone's move methods on itself
     */

    private void droneMover() {
            if (this.score == Constants.SCORE_MILESTONE_1 || this.score == Constants.SCORE_MILESTONE_2 ||
                this.score == Constants.SCORE_MILESTONE_3 || this.score == Constants.SCORE_MILESTONE_4 ||
                this.score == Constants.SCORE_MILESTONE_5 || this.score == Constants.SCORE_MILESTONE_6) {
            this.drone = new PowerUpDrone(this.pane);
                Image image = new Image("indy/download.png");
                ImagePattern imagePattern1 = new ImagePattern(image);
                this.drone.getNode().setFill(imagePattern1);
        }

        if (this.drone != null) {
            this.drone.moveRight();
            this.drone.verticalMotion();
        }
    }

    /**
     * This method calls the bridgeDestroyer method on the bridge that is returned by terrainGenerator.
     */
    private void CollidingBridgeChecker() {
        if (this.terrainGenerator.getCollidingBridge() != null) {
            this.terrainGenerator.getCollidingBridge().bridgeDestroyer();
        }
    }

    /**
     * This method updates the guns direction with the player's motion
     */

    private void updateGunsDirection() {
        if (this.player.getXVelocity() < 0) {
            this.gun.updateGunsPosition(this.player.getX() - Constants.PLAYER_WIDTH,
                    this.player.getY() + Constants.PLAYER_HEIGHT / 4);
        } else {
        }
    }

    /**
     * This method is in part responsible for the falling mechanism. It checks collision with the block and if
     * player is not colliding with the block it falls.
     */
    private void updateVelocity() {

        if (this.terrainGenerator.isCollidingPrime() == false) {

            this.player.setYVelocity(this.player.getYVelocity() + Constants.GRAVITY * Constants.DURATION);
            this.player.fallingPlayer(this.player.getYVelocity());
        } else if (this.terrainGenerator.isCollidingPrime()) {

        }
    }

    /**
     * This method together with the handleKeyPress method are responsible for the key inputs.
     */
    private void movePlayer() {
        this.pane.setOnKeyPressed((KeyEvent e) -> handleKeyPress(e));
        this.pane.setFocusTraversable(true);
    }

    /**
     * This method assigns what happens when a specific key is pressed.
     */

    private void handleKeyPress(KeyEvent e) {
        KeyCode keyPressed = e.getCode();
        if (keyPressed == KeyCode.SPACE) {
            this.gun.fire(this.timeline, this.bulletYVelocity);
            this.bulletstatus = bulletStatus.FIRED;

            System.out.println("CONTRA");
        }
        switch (keyPressed) {

            case RIGHT:
                if (this.player.getXVelocity() < Constants.MAX_PLAYER_SPEED) {
                    this.player.setXVelocity(this.player.getXVelocity() + Double.valueOf(Constants.SPEED_INCREMENT));
                    this.player.move(this.player.getXVelocity());
                }
                break;

            case LEFT:
                if (this.player.getXVelocity() > -Constants.MAX_PLAYER_SPEED) {
                    this.player.setXVelocity(this.player.getXVelocity() + Double.valueOf(-Constants.SPEED_INCREMENT));
                }

                break;

            case UP:
                if (this.terrainGenerator.isCollidingPrime() == true &&
                        this.terrainGenerator.isCollidingBedRockPrime() == false) {
                    this.player.setYVelocity(-Constants.JUMP_VELOC);
                    this.player.jump(this.player.getYVelocity());
                }

                break;


            case C:
                double oldXLoc = this.player.getX();//These local variables restore the player's upright location
                double oldYLoc = this.player.getY();
                if (this.status == playerStatus.UPRIGHT) {
                    this.player.crouch(Constants.PLAYER_HEIGHT, Constants.PLAYER_WIDTH);
                    this.status = playerStatus.CROUCHED;
                    this.player.setXVelocity(0);
                } else if (this.status == playerStatus.CROUCHED) {
                    this.player.crouch(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
                    this.player.setXLoc(oldXLoc);
                    this.player.setYloc(oldYLoc - Constants.PLAYER_WIDTH);
                    this.status = playerStatus.UPRIGHT;

                }


                break;

            case W:
                if (this.gunStatus == gunDirectionStatus.UP) {
                } else if (this.gunStatus == gunDirectionStatus.STRAIGHT) {
                    this.bulletYVelocity = -Constants.BULLET_VELOC;
                    this.gunStatus = gunDirectionStatus.UP;
                } else {
                    this.bulletYVelocity = 0;
                    this.gunStatus = gunDirectionStatus.STRAIGHT;
                }

                break;

            case S:
                if (this.gunStatus == gunDirectionStatus.DOWN) {
                } else if (this.gunStatus == gunDirectionStatus.STRAIGHT) {
                    this.bulletYVelocity = Constants.BULLET_VELOC;
                    this.gunStatus = gunDirectionStatus.DOWN;
                } else {
                    this.bulletYVelocity = 0;
                    this.gunStatus = gunDirectionStatus.STRAIGHT;
                }
                break;


            default:

                break;
        }
    }

    /**
     * This method checks if the two conditions for GameOver to be called are met. If they are met it calls the
     * gameOver method.
     */
    private void gameOverChecker() {
        if (this.player.getY() > Constants.LAVA_YLOC - Constants.PLAYER_HEIGHT ||
                this.player.getPlayerHitPoint() <= 0) {
            this.gameOver();

        } else {}
    }

    /**
     * This method creates a game over label. Stops the timelines and disables key input. It also changes the player's
     * image.
     */
    private void gameOver() {

            Label gameOverLabel = new Label("GAME OVER");
            gameOverLabel.setPrefSize(Constants.GAME_OVER_WIDTH, Constants.GAME_OVER_HEIGHT);
            this.pane.getChildren().add(gameOverLabel);
            gameOverLabel.setTranslateY(Constants.GAME_OVER_LABEL_TRANSLATE_Y);
            gameOverLabel.setTranslateX(Constants.SCENE_WIDTH / 2 - Constants.OFFSET_GAME_OVER_LABEL);
            gameOverLabel.setFont(Font.font("Verdana",
                    FontWeight.BOLD, FontPosture.REGULAR, Constants.GAME_OVER_FONT));
            this.changeLabelColor(gameOverLabel);

            this.timeline.stop();
            this.animationTimeline.stop();
            this.pane.setOnKeyPressed(null);

            Image image = new Image("indy/skeleton1.png");
            this.playerImagePattern = new ImagePattern(image);

            this.player.getNode().setFill(this.playerImagePattern);

        }
    }


