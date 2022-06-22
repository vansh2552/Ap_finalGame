package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.Serializable;

public class WeaponChest implements Serializable {
    ImageView WeaponChest;
    Image image;
    Boolean checkColision=true;
    WeaponInterface weaponC;



    public ImageView getWeaponChest() {
        return WeaponChest;
    }

    public WeaponChest(Scene scene, int x, int y, WeaponInterface weaponC){
        image=new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\WeaponChest.png");
        WeaponChest =new ImageView(image);
        WeaponChest.setFitHeight(50);
        WeaponChest.setFitWidth(70);
        WeaponChest.setLayoutX(x);
        WeaponChest.setLayoutY(y);
        this.weaponC=weaponC;

    }
    public void translate(){
        TranslateTransition t = new TranslateTransition();
        t.setNode(WeaponChest);
        t.setDuration(Duration.millis(75));
        t.setCycleCount(1);
        t.setByX(WeaponChest.getX() -100);
        t.play();

    }

    public WeaponInterface getWeaponC() {
        return weaponC;
    }

    public void checkCollision(Hero hero){
        if (WeaponChest.getBoundsInParent().intersects(hero.getImageview().getBoundsInParent())){
            if(checkColision) {
                WeaponChest.setImage(new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\Openchest.png"));
                hero.getImageview().setImage(new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\helmet.png"));
                hero.getImageview().setPreserveRatio(false);
                hero.getImageview().setFitWidth(40);
                checkColision=false;
                hero.giveWeapon(this.getWeaponC());


            }

        }
    }

}
