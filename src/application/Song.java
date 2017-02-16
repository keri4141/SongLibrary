/*
 * Developed By
 * Alvin Chau 2/15/2017
 * Andy Phan
 * 
 */
package application;

import java.io.Serializable;

/**
 * Created by Alvin-ACER on 2/12/2017.
 */
//serializable allows objects to write to file
public class Song implements Serializable, Comparable<Song> {

    private String name,artist,album,year;

    @Override
    public int compareTo(Song s)
    {
        return this.name.compareTo(s.name);
    }

    @Override
    public boolean equals(Object o) //checking if artist, and name is alrady in list or not
    {
        if(o==null || !(o instanceof Song))
        {
            return false;
        }

        else
        {
            Song os=(Song)o;
            return this.name.equals(os.name) && this.artist.equals(os.artist);

        }

    }


    public Song()
    {
        this.name="";
        this.artist="";
        this.album="";
        this.year="";

    }

    public Song(String name,String artist, String album, String year)
    {
        this.name=name;
        this.artist=artist;
        this.album=album;
        this.year=year;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public void setArtist(String artist)
    {
        this.artist=artist;
    }

    public void setAlbum(String album)
    {
        this.album=album;
    }

    public void setYear(String year)
    {
        this.year=year;
    }

    /*GETS */
    public String getName()
    {
        return this.name;
    }

    public String getArtist()
    {
        return artist;
    }

    public String getAlbum()
    {
        return album;
    }

    public String getYear()
    {
        return year;
    }

    public String toString()
    {
        return this.name;

    }

}
