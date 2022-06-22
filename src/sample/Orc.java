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

public class Orc extends Application implements Serializable {
    ImageView orc;
    Image image;
    private String colour;
    private boolean Crushed;

    public ImageView getOrc() {
        return orc;
    }

    public Orc(Scene scene, int x, int y,String colour){
        this.colour=colour;
        if(colour.equals("green")){
            image=new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\Gorc.png");
        }
        else{image=new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\Rorc.png");}
        orc =new ImageView(image);
        orc.setPreserveRatio(false);
        orc.setFitHeight(30);
        orc.setFitWidth(33);
        orc.setLayoutX(x);
        orc.setLayoutY(y);

    }
    public void translate(ImageView img){
        TranslateTransition t = new TranslateTransition();
        t.setNode(img);
        t.setDuration(Duration.millis(75));
        t.setCycleCount(1);
        t.setByX(img.getX() -100);
        t.play();

    }
    public void jump(ImageView img){
        TranslateTransition t = new TranslateTransition();
        t.setNode(img);
        t.setDuration(Duration.millis(450));
        t.setCycleCount(TranslateTransition.INDEFINITE);
        t.setByY(-50);
        t.setAutoReverse(true);
        t.play();
    }

    @Override
    public void start(Stage stage) throws Exception {
        jump(orc);
    }
    public boolean checkCollision( Hero hero,double[][] parr){
        Bounds boundH=hero.getImageview().getBoundsInParent();
        Bounds boundO=orc.getBoundsInParent();

        if(boundH.getMaxY()>boundO.getMinY() && boundH.getMinY()<boundO.getMaxY() && !hero.getCrushedByorc()){
            boolean abyss=false;
            for(int j=0;j<5;j++){
                if(boundH.getMaxX()+j>boundO.getMinX() ) {
                    TranslateTransition t = new TranslateTransition();
                    t.setNode(orc);
                    t.setDuration(Duration.millis(90));
                    t.setCycleCount(1);
                    t.setByX(50);
                    t.play();

//                    for(int i=0;i<parr.length;i++){
//                        if((orc.getBoundsInParent().getCenterX()>parr[i][0] && orc.getBoundsInParent().getCenterX()<parr[i][1])){
//                            abyss=true;
//                            break;
//                        }
//                        if(!abyss){
//                            TranslateTransition t1 = new TranslateTransition();
//                            t1.setNode(orc);
//                            t1.setDuration(Duration.millis(90));
//                            t1.setCycleCount(1);
//                            t1.setByY(200);
//                            t1.play();
//
//                        }
//
//
//
////
//                    }

                }
            }

        }


        else if((boundH.getMaxX()>boundO.getMinX() && boundH.getMinX()<boundO.getMaxX())) {
            if (boundH.getMaxY() > boundO.getMinY()) {
                return true;
            } else if(boundH.getMinY()<boundO.getMinY()) {
                TranslateTransition t = new TranslateTransition();
            t.setNode(hero.getImageview());
            t.setDuration(Duration.millis(450));
            t.setCycleCount(1);
            t.setByY(-75);
            t.play();

            orc.setX(-5000000);




            }
        }
        return false;
    }

}
