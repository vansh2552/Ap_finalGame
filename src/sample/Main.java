package sample;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;




import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.util.ArrayList;
/*
NOTES:
make every attribute private.
there can be hidden images in common multiples.
Weaponchest collision incomplete
Orc crushed animation
Not able to understand how to return from a popup

TNT explosion animation
 */
public class Main extends Application implements Serializable {

    private Hero hero;
    private ImageView tap;


    public double[][] getParr() {
        return parr;
    }

    private Boolean removeTapFlag=true;
    private Text ScoreText;
    private Group root;
    private Button yes;
    private boolean moved=false;


    private ArrayList<Platform> platforms;
    private ArrayList<CoinChest> coinChests;
    private  ArrayList<WeaponChest> weaponChests;
    private  ArrayList<TNT> tnts;
    private  ArrayList<Coin> coins;
    private  ArrayList<Orc> orcs;
    private  Stage stage;
    private Scene scene;
    private  Scene scene2;

    private   Scene scene3;

    private Boolean onPlatform;
    private Scene mainSc;
    private double [][] parr=new double[50][2];

    private int score=0;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Stage mainS=new Stage();
        mainS.setHeight(600);
        mainS.setWidth(800);
        Group mainR=new Group();
        mainSc=new Scene(mainR);

        Image bgi=new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\bgw.png");
        ImageView bgm=new ImageView(bgi);
        bgm.setFitWidth(800);
        bgm.setFitHeight(400);
        mainR.getChildren().add(bgm);

        Button ng=new Button("New Game");
        Button lg=new Button("Load Game");
        Button eg=new Button("Exit Game");
        ng.setPrefHeight(50);
        ng.setPrefWidth(800);
        ng.setLayoutY(400);
        lg.setPrefHeight(50);
        lg.setPrefWidth(800);
        lg.setLayoutY(450);
        eg.setPrefHeight(50);
        eg.setPrefWidth(800);
        eg.setLayoutY(500);
        mainR.getChildren().add(ng);
        mainR.getChildren().add(lg);
        mainR.getChildren().add(eg);
        ng.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                switchScene(scene);
                stage.setScene(scene);
                stage.show();
                ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
            }
        });
        lg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

//                try {
//                    Serialize serialize=new Serialize(this);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
        eg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
            }
        });
        mainS.setScene(mainSc);
        mainS.show();


        stage=new Stage();
        stage.setHeight(600);
        stage.setWidth(800);
        root=new Group();
        scene=new Scene(root);


        Image background=new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\Bg.png");   // setting the background
        ImageView bg=new ImageView(background);
        bg.setFitWidth(800);
        bg.setFitHeight(600);
        root.getChildren().add(bg);

        Image ttr=new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\ttrm.png");
        tap=new ImageView(ttr);
        tap.setFitWidth(300);
        tap.setFitHeight(200);
        tap.setLayoutX(250);
        root.getChildren().add(tap);

        ScoreText=new Text();

        ScoreText.setText(String.valueOf(score));
        ScoreText.setX(350);ScoreText.setY(100);
        ScoreText.setFont(Font.font("Arial Black",45));
        ScoreText.setFill(Color.WHITE);
        ScoreText.setOpacity(0);
        root.getChildren().add(ScoreText);

        Button pause =new Button("Pause Menu");
        pause.setLayoutX(700);
        pause.setLayoutY(25);
        root.getChildren().add(pause);
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Scene PauseSc=Pause();
                switchScene(PauseSc);
            }
        });




        hero=new Hero(scene);
        hero.start(stage);
       // platform=new Platform(scene);
        platforms=new ArrayList<>();

        for(int i=0;i<50;i++){
            Platform p=new Platform(scene,30+i*275,324);
            platforms.add(p);
           // System.out.println(p.getPlatform().getBoundsInParent().getMinX()+" "+p.getPlatform().getBoundsInParent().getMaxX());
            parr[i][0]=p.getPlatform().getBoundsInParent().getMinX();
            parr[i][1]=p.getPlatform().getBoundsInParent().getMaxX();

            root.getChildren().add(p.getPlatform());

        }
//        for(int i=0;i< parr.length;i++)
//            System.out.println(i+" "+parr[i][0]+" "+parr[i][1]);



        coinChests=new ArrayList<>();
        for(int i=0;i<7;i++){
            CoinChest c=new CoinChest(scene,1460+i*1650,280);
            coinChests.add(c);
            root.getChildren().add(c.getCoinChest());

        }
        weaponChests=new ArrayList<>();
        for(int i=0;i<8;i++){
            WeaponInterface x;
            if(i%2==0)
                x=new Sword(hero);
            else x=new Hammer(hero);
            //hero.giveWeapon(x);
            WeaponChest w=new WeaponChest(scene,620+i*1650,280,x);
            weaponChests.add(w);
            root.getChildren().add(w.getWeaponChest());

        }
        coins=new ArrayList<>();
        for(int i=0;i<8;i++){
            for(int j=0;j<3;j++){
                //890+i*1650+j*10
                Coin c1=new Coin(scene,850+j*30+i*1650,277);
                Coin c2=new Coin(scene,980+j*30+i*1650,277);
                coins.add(c1);
                coins.add(c2);
                root.getChildren().add(c1.getCoin());
                root.getChildren().add(c2.getCoin());
            }
        }
        orcs=new ArrayList<>();
        for(int i=0;i<8;i++){
            Orc o1=new Orc(scene,375+i*1650,297,"green");
            i++;
            Orc o2=new Orc(scene,375+i*1650,297,"red");
            orcs.add(o1);
            orcs.add(o2);
            o1.start(stage);
            o2.start(stage);
            root.getChildren().add(o1.getOrc());
            root.getChildren().add(o2.getOrc());
        }
//        orcs.get(orcs.size()-1).getOrc().setFitHeight(90);
//        orcs.get(orcs.size()-1).getOrc().setFitWidth(90);
//        System.out.println(orcs.get(orcs.size()-1).getOrc().getBoundsInParent().getCenterX());

         tnts=new ArrayList<>();

        for(int i=0;i<8;i++){
                    TNT t=new TNT(scene,935+i*1650,290);
                    tnts.add(t);
                    t.getTNT().toFront();
                    root.getChildren().add(t.getTNT());

                }
        Boss boss=new Boss(scene,13010,238,"green");   //13010
        boss.jump();

        root.getChildren().add(boss.getBoss());

        scene.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {


               // checkAbyss();
                if(removeTapFlag)
                    removetap();
                showscore();
                updateText();
                hero.translate(platforms);
//                for(Platform p:platforms){
//                    p.translate();
//                }
                Iterator<Platform> piter=platforms.iterator();
                while (piter.hasNext())
                    piter.next().translate();

                Iterator<CoinChest> citer=coinChests.iterator();
                while (citer.hasNext())
                    citer.next().translate();

//                for(CoinChest c:coinChests){
//                    c.translate();
//                }
                for(WeaponChest w:weaponChests){
                    w.translate();
                }for(TNT t:tnts){
                    t.translate();
                }
                for(Coin c:coins){
                    c.translate();
                }
                for(Orc o:orcs){
                    o.translate(o.getOrc());
                }
                boss.translate(boss.getBoss());

            }
        });


        AnimationTimer collisionTimer=new AnimationTimer() {
            @Override
            public void handle(long l)  {
//                for(Platform p:platforms){
//                    if(!p.checkCollision(hero.getImageview()))
//                }
                for(Coin c:coins){
                    c.Collision(root,hero);
                }
                for (CoinChest cc:coinChests){
                    cc.checkCollision(hero);

                } for (WeaponChest cc:weaponChests){
                    cc.checkCollision(hero);

               }

                for (TNT t:tnts){

                    boolean a= t.checkCollision(hero);
                    boolean hogaya=false;
                    if(a) {
//                            ScaleTransition scale = new ScaleTransition();
//                            scale.setNode(t.getTNT());
//                            scale.setDuration(Duration.millis(10));
//                            scale.setByX(2.0);
//                            scale.setByY(2.0);
//                            scale.play();
//                            t.getTNT().setImage(new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\Blast.png"));

                            hogaya=true;

                    }

                    if(a && !(hero.isDidContinue())){
                        if(hogaya) {
                            scene2=createScene2();
                            switchScene(scene2);
                            moveHero();
                        }

                    }
                    else if(a && hero.isDidContinue() && hogaya && !hero.isGameOver()){

                        ScaleTransition scale = new ScaleTransition();
                            scale.setNode(t.getTNT());
                            scale.setDuration(Duration.millis(10));
                            scale.setByX(2.0);
                            scale.setByY(2.0);
                            scale.play();
                            t.getTNT().setImage(new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\Blast.png"));
                            scene3=Gameover();
                            switchScene(scene3);

                    }

                    }
                for(Orc o:orcs){
                    boolean crushed= o.checkCollision(hero,parr);

                    if(crushed && !hero.isGameOver()) {
                        scene2 = createScene2();
                        switchScene(scene2);
                        moveHero();
                        o.getOrc().setX(o.getOrc().getX()-1000);
                        o.getOrc().setY(o.getOrc().getY()-1000);
                    }
                    else if(crushed && hero.isGameOver()){
                        scene3=Gameover();
                        switchScene(scene3);
                    }
                }
                boolean bossCrushed= boss.checkCollision(hero);
                if(bossCrushed){
                    scene2 = createScene2();
                    switchScene(scene2);
                }
            }







        };
        collisionTimer.start();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode()== KeyCode.S){
                    if(hero.getWeapons().size()!=0) {
                        for(Orc o:orcs){
                            Bounds herob=hero.getImageview().getBoundsInParent();
                            Bounds orcb=o.getOrc().getBoundsInParent();
                            if(herob.getCenterX()+100>orcb.getCenterX() && (herob.getCenterY()+100>orcb.getCenterY() || herob.getCenterY()+100<orcb.getCenterY()) ){
                                o.getOrc().imageProperty().set(null);
                                root.getChildren().remove(o.getOrc());
                            }
                        }
                        Bounds bossB=boss.getBoss().getBoundsInParent();
                        Bounds herob=hero.getImageview().getBoundsInParent();
                        if(herob.getCenterX()+100>bossB.getCenterX() && (herob.getCenterY()+100>bossB.getCenterY() || herob.getCenterY()+100<bossB.getCenterY()) ){
                            boss.setPower(boss.getPower()-10);
                            if (boss.getPower()<0)
                                boss.getBoss().imageProperty().set(null);
                        }

                        hero.getWeapons().get(0).degrade();
                    }
                    else System.out.println("no weapon left");
                }

            }

        });
        root.getChildren().add(hero.getImageview());




//        boolean gira=false;
//        while (!gira){
//            for (int i=0;i< parr.length;i++){
//
//                if(hero.getImageview().getBoundsInParent().getCenterX()> parr[i][0] && hero.getImageview().getBoundsInParent().getCenterX()<parr[i][1])
//                    System.out.println(i);
//                else gira=true;
//            }
//        }
//        if (gira)
//            System.out.println("yes");







    }
//    public void checkAbyss(){
//        if(moved){
//
//            boolean flag=false;
//            Bounds ob=hero.getImageview().getBoundsInParent();
//            for(Platform p:platforms){
//                Bounds pb=p.getPlatform().getBoundsInParent();
//
//                for(int i=0;i<50;i++){
//                   // if(ob.getCenterX()>parr[i][0] && ob.getCenterX()<parr[i][1]){
//                    double centrex=ob.getCenterX();
//                    double p0=parr[i][0];
//                    double p1=parr[i][1];
//
//                        //System.out.println(i+" " +centrex+" "+ p0+" "+ ob.getCenterX()+" "+p1);
//                    if(centrex>p0 && centrex<p1){
//                      //  System.out.println("inside "+i+" " +centrex+" "+p0 +" "+ centrex+" "+p1);
//                        flag = true;
//                      //  System.out.println("no");
//                    }
//
//                }
//
//                if(!flag)
//                    System.out.println("dead");
//            }
//            moved=false;
//        }
//    }
    public void createPopup(){
        Popup popup = new Popup();

//        popup.setX(400);
//        popup.setY(300);
        Text text=new Text(50,100,"Would You Like To Continue");
        ImageView Yes=new ImageView();

        popup.getContent().add(text);
        popup.getContent().add(Yes);


        text.setFont(Font.font(40));


        Image yes=new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\yes.png");
        Yes.setImage(yes);
        Yes.setFitHeight(100);
        Yes.setFitWidth(125);
        Yes.setY(150);
        Yes.setX(Yes.getX()+50);
        popup.show(stage);


        Yes.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(popup.isShowing()) {
                System.out.println("done");
                popup.hide();
                return;
            }
        });


    }
    public void moveHero(){
        hero.getImageview().setX( hero.getImageview().getX()+20);
    }
    public void createScreen(){

        Group group = new Group();
        Text text=new Text(50,100,"Would You Like To Continue");
        group.getChildren().add(text);
        ImageView Yes=new ImageView();
        group.getChildren().add(Yes);




        text.setFont(Font.font(40));


        Image yes=new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\yes.png");
        Yes.setImage(yes);
        Yes.setFitHeight(100);
        Yes.setFitWidth(125);
        Yes.setY(150);
        Yes.setX(Yes.getX()+50);
        Scene scene2 = new Scene(group, 400, 300);
        stage.setScene(scene2);
        stage.show();



        Yes.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            stage.setScene(scene);
            stage.show();
            System.out.println("hi");

            });


    }


    public static void main(String[] args) {
        launch(args);
    }
    public void removetap(){
        FadeTransition fadeTransition= new FadeTransition(Duration.millis(500), tap) ;
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        fadeTransition.setFromValue(0.0);
        removeTapFlag=false;
    }
    public void updateText(){
        score+=1;
        ScoreText.setText(String.valueOf(score));
        moved=true;
    }
    public void showscore(){
        FadeTransition fadeTransition= new FadeTransition(Duration.millis(500), ScoreText) ;
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        fadeTransition.setFromValue(0.0);
    }
    public void switchScene(Event event) throws IOException {
        root= FXMLLoader.load(getClass().getResource("reviveScreen.FXML"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene((root));
        stage.setScene(scene);
        stage.show();
    }
    private Scene Pause(){
        Stage PauseStage=new Stage();
        PauseStage.setHeight(600);
        PauseStage.setWidth(800);
        Image image=new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\bg.png");
        ImageView bg=new ImageView(image);
        bg.setFitWidth(800);
        bg.setFitHeight(600);
        Group PauseRoot=new Group();
        Scene PauseScene=new Scene(PauseRoot);
        Button resume=new Button("Resume Game");
        resume.setLayoutY(100);
        resume.setLayoutX(300);
        Button load=new Button("Load a Game");
        load.setLayoutY(200);
        load.setLayoutX(300);
        Button exit=new Button("Return to main menu");
        exit.setLayoutY(300);
        exit.setLayoutX(300);

        resume.setPrefWidth(100);
        resume.setPrefHeight(50);
        load.setPrefWidth(100);
        load.setPrefHeight(50);
        exit.setPrefWidth(100);
        exit.setPrefHeight(50);
        resume.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                switchScene(scene);
            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                switchScene(mainSc);
            }
        });
        PauseRoot.getChildren().add(bg);
        PauseRoot.getChildren().add(resume);
        PauseRoot.getChildren().add(load);
        PauseRoot.getChildren().add(exit);
        PauseStage.setScene(PauseScene);
        PauseStage.show();
        return PauseScene;



    }
    private Scene Gameover() {
        Stage gameover=new Stage();
        gameover.setHeight(600);
        gameover.setWidth(800);
        Image image=new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\bg.png");
        ImageView bg=new ImageView(image);
        bg.setFitWidth(800);
        bg.setFitHeight(600);
        Group gameoverRoot=new Group();
        Scene gameoverScene=new Scene(gameoverRoot);
        Text text = new Text("Game over");
        text.setFont(Font.font(40));
        text.setX(300);
        text.setY(100);
        Button mainMenu=new Button("Return to main menu");
        mainMenu.setLayoutX(300);
        mainMenu.setLayoutY(200);
        mainMenu.setPrefHeight(50);
        mainMenu.setPrefWidth(200);
        Button exit=new Button("Exit");
        exit.setLayoutX(300);
        exit.setLayoutY(300);
        exit.setPrefHeight(50);
        exit.setPrefWidth(200);
        mainMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                switchScene(mainSc);
            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
            }
        });
        hero.setGameOver(true);
        gameoverRoot.getChildren().add(bg);
        gameoverRoot.getChildren().add(text);
        gameoverRoot.getChildren().add(mainMenu);
       gameoverRoot.getChildren().add(exit);
        gameover.setScene(gameoverScene);
        gameover.show();
        return gameoverScene;
    }
    private Scene createScene2(){
        Stage mains2=new Stage();
        mains2.setHeight(600);
        mains2.setWidth(800);
        Image image=new Image("C:\\Users\\Veneet Gandhi\\IdeaProjects\\WillheroGame\\src\\sample\\bg.png");
        ImageView bg=new ImageView(image);
        bg.setFitWidth(800);
        bg.setFitHeight(600);

        Group roots2=new Group();
        Scene scene2=new Scene(roots2);
        Text text=new Text("Would you like to continue");
        text.setFont(Font.font(40));
        text.setX(150);
        text.setY(100);
        Button button1=new Button("YES");

        button1.setPrefWidth(100);
        button1.setPrefHeight(50);
        button1.setLayoutX(150);
        button1.setLayoutY(300);
        Button button2=new Button("NO");

        button2.setPrefWidth(100);
        button2.setPrefHeight(50);

        button2.setLayoutX(400);
        button2.setLayoutY(300);
        roots2.getChildren().add(bg);

        roots2.getChildren().add(text);
        roots2.getChildren().add(button1);
        roots2.getChildren().add(button2);
        mains2.setScene(scene2);
        mains2.show();




        button1.setOnMouseClicked(new EventHandler() {

            @Override
            public void handle(Event event) {
                try{if(hero.getCoins()>5){
                    hero.setCoins(hero.getCoins()-5);
                    switchScene(scene);
                    hero.setDidContinue(true);
                }
                else throw new InsufficientCoins("You need more than 5 coins to continue");
                }
                catch (Exception e){
                    switchScene(Gameover());
                }



            }
        });
        button2.setOnMouseClicked(new EventHandler() {

            @Override
            public void handle(Event event) {
                switchScene(mainSc);



            }
        });




        return scene2;
    }


    private void switchScene(Scene s){
        stage.setScene(s);
    }

//    public boolean checkCollisonAbyss(ImageView hero){
//        for(int i=0;i< parr.length;i++){
//            System.out.println(i+" "+hero.getBoundsInParent().getCenterX()+" "+parr[i][0]+"    "+hero.getBoundsInParent().getCenterX()+" "+parr[i][1]);
//
//            if(!(hero.getBoundsInParent().getCenterX()> parr[i][0] && hero.getBoundsInParent().getCenterX()<parr[i][1])) {
//
//                return false;
//            }
//        }
//        return true;
//    }

}
