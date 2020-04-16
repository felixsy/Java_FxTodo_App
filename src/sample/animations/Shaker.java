package sample.animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shaker {

    TranslateTransition translateTransition;

    public Shaker(Node node) {
        translateTransition = new TranslateTransition(Duration.millis(8f), node);
        translateTransition.setFromX(0f);
        translateTransition.setToX(50f);
        translateTransition.setCycleCount(4);
        translateTransition.setAutoReverse(true);

    }

    public void shake(){
        translateTransition.playFromStart();
    }


}
