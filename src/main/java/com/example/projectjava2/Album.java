package com.example.projectjava2;


import java.util.ArrayList;

public class Album extends Media{

    private String artist;       //artist name
    private ArrayList<String> songs;      //songs list

    public Album(String id ,String title, int copiesAvailable, String artist, ArrayList<String> songs) {
        super(id ,title, copiesAvailable);
        this.artist = artist;
        this.songs = songs;
    }

    public String getArtist() {      //getters and setters
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public ArrayList<String> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<String> songs) {
        this.songs = songs;
    }


    @Override
    public int compareTo(Media o) {
        return Integer.compare(title.compareTo(o.title), 0);
    }

    @Override
    public String toString() {
        return "--- TYPE : "+"Album"+" ---\n"+super.toString()+"\n  - Artist : " + artist + "\n  - Songs : " + songs;
    }
}

//BY : OMAR QALALWEH