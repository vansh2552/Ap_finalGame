package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.Serializable;

public class Platform implements Serializable {
    ImageView platform;
    Image image;


    public Platform(Scene scene,int x,int y){
        image=new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\Islands1.png");
        platform=new ImageView(image);
        platform.setFitHeight(100);
        platform.setFitWidth(200);
        platform.setLayoutX(x);
        platform.setLayoutY(y);


    }
    public void translate(){
        TranslateTransition t = new TranslateTransition();
        t.setNode(platform);
        t.setDuration(Duration.millis(75));
        t.setCycleCount(1);
        t.setByX(platform.getX() - 100);
        t.play();

    }
    public Boolean checkCollision( ImageView h){
        boolean flag;
        Bounds boundH=h.getBoundsInParent();
        Bounds boundO= platform.getBoundsInParent();
        if(boundH.getMaxX()+5>boundO.getMinX() && boundH.getMinX()<boundO.getMaxX() && boundH.getMinY()>boundO.getMaxY()+20)
            return true;
        return false;

        }

    public ImageView getPlatform() {
        return platform;
    }


}
