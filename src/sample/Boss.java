package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.Serializable;

public class Boss extends Application implements Serializable {
    private Image image;
    private ImageView boss;
    private int power;

    public ImageView getBoss() {
        return boss;
    }

    public Boss(Scene scene, int x, int y, String colour) {
        image=new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\Gorc.png");
        boss=new ImageView(image);
        boss.setFitHeight(90);
        boss.setFitWidth(99);
        boss.setLayoutX(x);
        boss.setLayoutY(y);
        power=100;
    }
    public void translate(ImageView img){
        TranslateTransition t = new TranslateTransition();
        t.setNode(img);
        t.setDuration(Duration.millis(75));
        t.setCycleCount(1);
        t.setByX(img.getX() -100);
        t.play();

    }
    public void jump(){
        TranslateTransition t = new TranslateTransition();
        t.setNode(boss);
        t.setDuration(Duration.millis(450));
        t.setCycleCount(TranslateTransition.INDEFINITE);
        t.setByY(-100);
        t.setAutoReverse(true);
        t.play();
    }

    @Override
    public void start(Stage stage) throws Exception {
        jump();
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean checkCollision(Hero hero){
        Bounds boundH=hero.getImageview().getBoundsInParent();
        Bounds boundO=boss.getBoundsInParent();

        if(boundH.getMaxY()>boundO.getMinY() && boundH.getMinY()<boundO.getMaxY() && power>0){
            for(int i=0;i<5;i++){
                if(boundH.getMaxX()+i>boundO.getMinX() ) {
                    System.out.println(power--);
                    TranslateTransition t = new TranslateTransition();
                    t.setNode(boss);
                    t.setDuration(Duration.millis(90));
                    t.setCycleCount(1);
                    t.setByX(30);
                    t.play();

                }
            }

        }
        if(power<0){
            boss.imageProperty().set(null);
        }
//        if(boundH.getMinY()<boundO.getMaxY() && boundH.getMaxX()-10>boundO.getMinX() && boundH.getMinX()<boundO.getMaxX())
//        {
//            TranslateTransition t = new TranslateTransition();
//            t.setNode(hero.getImageview());
//            t.setDuration(Duration.millis(450));
//            t.setCycleCount(1);
//            t.setByY(-75);
//            t.play();
//
//        }

        if((boundH.getMaxX()>boundO.getMinX() && boundH.getMinX()<boundO.getMaxX()) && boundH.getMaxY()<boundO.getMinY()) {
            return true;
        }

        return false;
    }

}
