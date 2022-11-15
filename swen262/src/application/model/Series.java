package model;

import model.Comic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
//Purpose - This class represents a series object
public class Series {
    //------------------------------------------------------------------------------------------------------------------
    private String name;
    private Collection<Comic> comics;
    //------------------------------------------------------------------------------------------------------------------
    //Constructor
    /**
     * @param name - A string representing the name of the series
     * @param comics - A collection of comics within the series
     */
    public Series(String name, Collection<Comic> comics){
        this.comics = comics;
        this.name = name;
    }
    //------------------------------------------------------------------------------------------------------------------
    public Collection<Comic> getComics() {
        return comics;
    }

    public String getName(){
        return this.name;
    }

    public void addComic(Comic c){
        this.comics.add(c);
    }

    public void removeComic(Comic comic){
        Iterator<Comic> cI = comics.iterator();
        while (cI.hasNext()){
            Comic currComic = cI.next();
            if (currComic==comic){
                cI.remove();
                break;
            }
        }
    }

    @Override
    public String toString(){
        String seriesString = "||||||||||||||||||||\nSERIES: "+this.name+"\n--------------------";
        Iterator cI = comics.iterator();
        while(cI.hasNext()){
            seriesString = seriesString+"\n"+cI.next().toString();
        }
        return seriesString+"||||||||||||||||||||\n";
    }
}
