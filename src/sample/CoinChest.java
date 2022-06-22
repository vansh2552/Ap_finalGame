package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.Serializable;

public class CoinChest implements Serializable {
    ImageView CoinChest;
    Image image;
    Boolean checkColision=true;

    public ImageView getCoinChest() {
        return CoinChest;
    }

    public CoinChest(Scene scene, int x, int y){
        image=new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\ChestClosed.png");
        CoinChest =new ImageView(image);
        CoinChest.setFitHeight(50);
        CoinChest.setFitWidth(70);
        CoinChest.setLayoutX(x);
        CoinChest.setLayoutY(y);

    }
    public void translate(){
        TranslateTransition t = new TranslateTransition();
        t.setNode(CoinChest);
        t.setDuration(Duration.millis(75));
        t.setCycleCount(1);
        t.setByX(CoinChest.getX() -100);
        t.play();

    }

    public void checkCollision( Hero hero){
        if (CoinChest.getBoundsInParent().intersects(hero.getImageview().getBoundsInParent())){
            if(checkColision) {
                CoinChest.setImage(new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\Openchest.png"));
                hero.setCoins(hero.getCoins()+5);
                checkColision=false;
                System.out.println(hero.getCoins());
            }

        }
    }

}
