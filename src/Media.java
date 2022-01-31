package com.example.projectjava2;


public abstract class Media implements Comparable<Media> {

    protected String code;
    protected String title;
    protected int numOfCopies;



    public Media(String code,String title, int copiesAvailable) {    //constructor
        this.code = code;
        this.title = title;
        this.numOfCopies = copiesAvailable;
    }


    public String getCode() {
        return code;
    }

    public String getTitle() {               //setters and getters
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumOfCopies() {
        return numOfCopies;
    }
    public void setNumOfCopies(int numOfCopies) {
        this.numOfCopies = numOfCopies;
    }

    @Override
    public String toString() {
        return "#Media code : "+ code + "\n  - title : " + title + "\n  - numOfCopies : " + numOfCopies ;
    }

    public boolean equals(Object o) {         //comparing two media on their name (return TRUE if equal / FALSE if Not Equal)
        if(o instanceof Media){   //testing if it is a media object
            return ((Media) o).title.equals(this.title);
        }
        return false;
    }                              //hmm

}
