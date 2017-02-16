/*
 * Developed By
 * Alvin Chau 2/15/2017
 * Andy Phan
 * 
 */
package application;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alvin-ACER on 2/12/2017.
 */
public class ReadFile {

    public static List<Song> songs = new ArrayList<>();
    private static final String path = "./songlist.bin"; //stores the bytes for the list of songs

    public static void ReadFile()
    {
        try {
            ObjectInputStream objInputStream = new ObjectInputStream(new FileInputStream(path));
            songs = (List<Song>)objInputStream.readObject();
            objInputStream.close();
        }catch(Exception e){

        }
    }

    public static void WriteFile()
    {
        try {
            ObjectOutputStream objOutputStream = new ObjectOutputStream(new FileOutputStream(path));
            objOutputStream.writeObject(songs);
            objOutputStream.close();
        }catch(Exception e){

        }

    }

}
