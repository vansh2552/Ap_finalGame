package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.Serializable;

public class Coin implements Serializable {


    ImageView coin;
    Image image;
    Boolean checkColision=true;

    public ImageView getCoin() {
        return coin;
    }

    public Coin(Scene scene, int x, int y){
        image=new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\coin.png");
        coin =new ImageView(image);
        coin.setFitHeight(20);
        coin.setFitWidth(20);
        coin.setLayoutX(x);
        coin.setLayoutY(y);

    }
    public void translate(){
        TranslateTransition t = new TranslateTransition();
        t.setNode(coin);
        t.setDuration(Duration.millis(75));
        t.setCycleCount(1);
        t.setByX(coin.getX() -100);
        t.play();
    }
    public void Collision(Group root,Hero hero){
        if (coin.getBoundsInParent().intersects(hero.getImageview().getBoundsInParent())){
            if(checkColision) {
                coin.setImage(null);
                hero.setCoins(hero.getCoins()+1);
                checkColision=false;
                System.out.println(hero.getCoins());
            }

        }
    }
}
