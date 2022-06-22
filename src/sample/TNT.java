package sample;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.Serializable;

public class TNT implements Serializable {
    ImageView TNT;
    Image image;
    Boolean checkColision=true;

    public ImageView getTNT() {
        return TNT;
    }

    public TNT(Scene scene, int x, int y){
        image=new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\tnt.png");
        TNT =new ImageView(image);
        TNT.setFitHeight(35);
        TNT.setFitWidth(30);
        TNT.setLayoutX(x);
        TNT.setLayoutY(y);

    }
    public void translate(){
        TranslateTransition t = new TranslateTransition();
        t.setNode(TNT);
        t.setDuration(Duration.millis(75));
        t.setCycleCount(1);
        t.setByX(TNT.getX() -100);
        t.play();

    }
    public boolean checkCollision( Hero hero){
        if (TNT.getBoundsInParent().intersects(hero.getImageview().getBoundsInParent())){
            if(checkColision) {
//                ScaleTransition scale=new ScaleTransition();
//                scale.setNode(TNT);
//                scale.setDuration(Duration.millis(10));
//                scale.setByX(2.0);
//                scale.setByY(2.0);
//                scale.play();
//                TNT.setImage(new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\Blast.png"));
                return true;
            }
        }
        return false;
    }

    public void TNTRevive(){
        TNT.setImage(image);
        TNT.setFitHeight(10);
        TNT.setFitWidth(8);
    }

}
