package model;

import model.Comic;

import java.util.Collection;

public class Series {

    private String name;
    private Collection<Comic> comics;

    public Series(String name, Collection<Comic> comics){
        this.comics = comics;
        this.name = name;
    }

    public Collection<Comic> getComics() {
        return comics;
    }
}
