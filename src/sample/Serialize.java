package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.*;
import java.util.ArrayList;

public class Serialize {
    ArrayList<Serialize> loadedGames=new ArrayList<>();

public static void serialize() throws IOException{
    Main game=new Main();
    ObjectOutputStream out=null;
    try {
        out=new ObjectOutputStream(new FileOutputStream("game.txt"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte [] data = bos.toByteArray();
        out.write(data);
    }
    finally {
        out.close();
    }
}
public static void deserialize() throws ClassNotFoundException, IOException {
    ObjectInputStream in=null;
    try {
        in=new ObjectInputStream(new FileInputStream("game.txt"));
        Main game=(Main)in.readObject();
    }
    finally {
        in.close();
    }
}







}
