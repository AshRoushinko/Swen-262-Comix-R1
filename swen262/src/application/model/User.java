package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class User implements Database{

    Collection<Series> userCollection;//AN ARRAY LIST OF SERIES, EACH SERIES HAS AN ARRAY LIST OF COMICS (seriesname.getComics(); will return the list of comics in the series.

    public User(){
        userCollection = new ArrayList<>();
    }

    public Collection<Series> getComiccollection() {
        return userCollection;
    }

    @Override
    public void sort(String filters) {

    }

    @Override
    public void parse(String filename) {

    }

    @Override
    public void display() {
        Iterator sI = userCollection.iterator();
        System.out.println(sI.hasNext());
        while(sI.hasNext()){
            System.out.println("Test");
            System.out.println(sI.next().toString());
        }
    }

    @Override//TODO (WILL BE THE SAME FOR COMIXDATABASE)
    //TODO takes the provided name, searches through all of the series and returns a series object, returns null if no series are found
    //TODO Very similar to the addComicToUser method
    //TODO if you need help let me know
    public Series getSeries(String name) {
        return null;
    }

    @Override//TODO (WILL BE THE SAME FOR COMIXDATABASE)
    //TODO A little trickier than getSeries
    //TODO the integer id represents the number of the comic in the users collection (The first comic has an id of one, the second has an id of two and so on)
    //TODO iterate through userCollection using an iterator; each index will be a series/collection of comics,
    //TODO then iterate through each series/collection of comics using another iterator (There should be two loops)
    //TODO lets say the id is 1,205. After you iterate through 1,205 comics, return the current comic.
    //TODO if you need help let me know
    public Comic getComic(int id) {
        return null;
    }

    public void addComicToUser(Comic comic){
        String comicSeries = comic.getSeries();
        if (comicSeries.equals("")){//No Series

        }
        else{
            Iterator<Series> userIteratorSeries = userCollection.iterator();
            boolean searching = true;
            while(searching){
                if (userIteratorSeries.hasNext()){
                    Series currSeries = userIteratorSeries.next();
                    if (currSeries.getName().equals(comicSeries)){
                        currSeries.addComic(comic);
                        searching = false;
                    }
                    else{
                    }
                }
                else{
                    Collection<Comic> newSeriesList = new ArrayList<>();
                    newSeriesList.add(comic);
                    Series newSeriesObj = new Series(comicSeries, newSeriesList);
                    addSeries(newSeriesObj);
                }

            }
        }
    }

    private void addSeries(Series series){
        userCollection.add(series);
    }
}
