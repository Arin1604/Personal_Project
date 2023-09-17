package indy;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class PaneOrganizer {


    private Pane mainPane;
    private BorderPane root;

    private lavaStat stat;

    private Image lavaImage;
    private Timeline timeline;

    private Rectangle rectangle;
    private ImagePattern imagePattern;


    /**
     * This method is the constructor for our top level graphic class
     */
    public PaneOrganizer(){
        this.root = new BorderPane();
        this.setUpMainPane();

        this.setUpButtonPane();
       Game game = new Game(mainPane);
        this.setUpLava();
        this.setUpTimeline();



    }
    /**
     * This method sets up the pane
     */
    private void setUpMainPane(){
        this.mainPane = new Pane();
        this.mainPane.setPrefSize(Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
        this.mainPane.setStyle(Constants.MAIN_PANE_COLOR);

        this.root.setCenter(this.mainPane);

        Image image = new Image("indy/bgFinal.png");
        ImagePattern imagePattern1 = new ImagePattern(image);

        Rectangle rectangle = new Rectangle(0,0,Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
        rectangle.setFill(imagePattern1);
        this.mainPane.getChildren().add(rectangle);



    }
    /**
     * This enum helps set up the animation for the lava
     */
    private enum lavaStat{
        IMAGE1, IMAGE2
    }

    /**
     * This method sets up a rectangle and sets its fill to the lava image pattern
     */
    private void setUpLava(){

        this.lavaImage = new Image("indy/lava1.png");
        this.imagePattern = new ImagePattern(this.lavaImage);

        this.rectangle = new Rectangle(0,Constants.SCENE_HEIGHT - Constants.LABEL_HEIGHT,
                Constants.SCENE_WIDTH,Constants.LABEL_HEIGHT);
        this.rectangle.setFill(this.imagePattern);
        this.root.getChildren().add(this.rectangle);
        this.stat = lavaStat.IMAGE1;

    }

    /**
     * This method updates the rectangle's image pattern
     */
    private void updateRectangle(){
        this.rectangle.setFill(this.updateImage());
    }

    /**
     * This method sets up the timeline for the lava animation
     */
    private void setUpTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.LAVA_DURATION),
                (ActionEvent e) -> this.updateRectangle());
        this.timeline = new Timeline(kf);
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();

    }

    /**
     * This method uses the enums to change the image instance variable to the appropriate one.
     */
    private void ImageChanger(){

        if(this.stat == lavaStat.IMAGE1){
            this.lavaImage  = new Image("indy/lava2.png");

            this.stat = lavaStat.IMAGE2;
        } else if (this.stat == lavaStat.IMAGE2) {
            this.lavaImage = new Image("indy/lava1.png");
            this.stat = lavaStat.IMAGE1;

        }

    }

    /**
     * This helper method returns the updated Image pattern
     */
    private ImagePattern updateImage(){
        ImagePattern imagePattern1;
        this.ImageChanger();
        imagePattern1 = new ImagePattern(this.lavaImage);

        return imagePattern1;

    }

    /**
     * This method sets up the button pane;
     */
    private void setUpButtonPane(){
        HBox buttonPane = new HBox();
        buttonPane.setPrefSize(Constants.BUTTON_PANE_WIDTH, Constants.SCENE_HEIGHT);
        buttonPane.setStyle(Constants.BUTTON_PANE_COLOR);
        this.root.setLeft(buttonPane);

        Button quitButton = new Button("Quit");
        buttonPane.getChildren().add(quitButton);
        quitButton.setOnAction((ActionEvent e) -> System.exit(0));
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setFocusTraversable(false);
        quitButton.setFocusTraversable(false);

    }
    /**
     * This method returns root;
     */
    public Pane getRoot(){
        return this.root;
    }
}