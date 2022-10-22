package model;

import model.Comic;

import java.util.Collection;
import java.util.Iterator;

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

    public String getName(){
        return this.name;
    }

    public void addComic(Comic c){
        this.comics.add(c);
    }

    @Override
    public String toString(){
        String seriesString = "||||||||||||||||||||\nSERIES: "+this.name+"--------------------";
        Iterator cI = comics.iterator();
        while(cI.hasNext()){
            seriesString = seriesString+"\n"+cI.next().toString();
        }
        return seriesString+"||||||||||||||||||||\n";
    }
}
