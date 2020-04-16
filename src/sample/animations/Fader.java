package sample.animations;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Fader {
    FadeTransition fadeTransitionAnchorPane;

    public Fader(Node node){
        fadeTransitionAnchorPane= new FadeTransition(Duration.millis(2000), node);
        fadeTransitionAnchorPane.setFromValue(0f);
        fadeTransitionAnchorPane.setToValue(1f);
        fadeTransitionAnchorPane.setCycleCount(1);
        fadeTransitionAnchorPane.setAutoReverse(false);

    }

    public void Fade(){

        fadeTransitionAnchorPane.playFromStart();
    }
}
