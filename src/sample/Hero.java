package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.PrintWriter;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Hero extends Application implements Serializable {
    ImageView hero;
    Image image;
    private int coins;
    private boolean didContinue=false;
    private boolean gameOver=false;

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    private ArrayList<WeaponInterface> weapons=new ArrayList<>();
    private Boolean crushedByorc=false;


    public boolean isDidContinue() {
        return didContinue;
    }

    public void setDidContinue(boolean didContinue) {
        this.didContinue = didContinue;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Boolean getCrushedByorc() {
        return crushedByorc;
    }

    public void setCrushedByorc(Boolean crushedByorc) {
        this.crushedByorc = crushedByorc;
    }

    public Hero(Scene scene) {
         image=new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\hero.png");
         hero=new ImageView(image);
        hero.setFitHeight(30);
        hero.setFitWidth(50);
        hero.setLayoutX(100);
        hero.setLayoutY(300);
    }

    @Override
    public void start(Stage stage) throws Exception {
        jump();

    }

    public ImageView getImageview() {

        return hero;
    }
    public void translate(ArrayList<Platform> p){
        TranslateTransition t = new TranslateTransition();
        t.setNode(hero);
        t.setDuration(Duration.millis(400));
        t.setCycleCount(1);

        t.play();

    }
    public void jump(){
        TranslateTransition t = new TranslateTransition();
        t.setNode(hero);
        t.setDuration(Duration.millis(450));
        t.setCycleCount(TranslateTransition.INDEFINITE);
        t.setByY(-75);
        t.setAutoReverse(true);
        t.play();
    }

    public ArrayList<WeaponInterface> getWeapons() {
        return weapons;
    }

    public void giveWeapon(WeaponInterface w){
        boolean present=false;
        for(WeaponInterface x:weapons){
            if(x.getClass()==w.getClass()){
                present=true;
            }
        }
        if(present){
            w.upgrade();
        }
        else{
            weapons.add(w);
            for(WeaponInterface a:weapons)
                System.out.println(a.getName());

        }
    }

}
