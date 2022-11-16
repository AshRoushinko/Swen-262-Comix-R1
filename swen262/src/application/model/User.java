package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
//Purpose - This class represents the user object and includes all functionality relating to editing the users collection
public class User implements Database{
    //------------------------------------------------------------------------------------------------------------------
    Collection<Series> userCollection;//AN ARRAY LIST OF SERIES, EACH SERIES HAS AN ARRAY LIST OF COMICS (seriesname.getComics()); will return the list of comics in the series.
    public int currentNextID;
    //------------------------------------------------------------------------------------------------------------------
    //Constructor
    public User(){
        userCollection = new ArrayList<>();
        currentNextID = 14302;
    }

    public Collection<Series> getComiccollection() {
        return userCollection;
    }

    @Override
    public void sort(){
    }

    @Override
    public void parse(String filename) {
        //TODO STORE THE CURRENT NEXT ID IN THE FILE AND UPDATE IT WHEN PARSING FILE
    }

    @Override
    public void display() {
        Iterator sI = userCollection.iterator();
        while(sI.hasNext()){
            System.out.println(sI.next().toString());
        }
    }

    @Override
    public Series getSeries(String name) {
        Iterator<Series> seriesIterator = userCollection.iterator();
        while (seriesIterator.hasNext()){
            Series currSeries = seriesIterator.next();
            if (currSeries.getName().equals(name)){
                return currSeries;
            }
        }
        System.out.println("No series with the provided name");
        return null;
    }

    @Override
    public Comic getComic(String id) {
        Iterator<Series> seriesIterator = userCollection.iterator();
        while (seriesIterator.hasNext()){
            Iterator<Comic> comicIterator = seriesIterator.next().getComics().iterator();
            Comic currComic = comicIterator.next();
            if (currComic.getID().equals(id)){
                return currComic;
            }
        }
        System.out.println("No comic with the provided name");
        return null;
    }

    public void addComicToUser(Comic comic){
        String comicSeries = comic.getSeries();
        if (comicSeries.equals("")){//No Series
            System.out.println("No Series");
        }
        else{
            if (userCollection.contains(getSeries(comicSeries))){
                Series currSeries = getSeries(comicSeries);
                currSeries.addComic(comic);
            }
            else{
                Collection<Comic> newSeriesList = new ArrayList<>();
                newSeriesList.add(comic);
                Series newSeriesObj = new Series(comicSeries, newSeriesList);
                addSeries(newSeriesObj);
            }
        }
    }

    public int getCurrentNextID(){
        return this.currentNextID;
    }

    public Comic removeComic(String id){//TODO if there is no comic with the provided id
        Comic comicToRemove = getComic(id);
        String comicToRemoveSeriesName = comicToRemove.getSeries();
        Series comicToRemoveSeries = getSeries(comicToRemoveSeriesName);
        comicToRemoveSeries.removeComic(comicToRemove);
        return comicToRemove;
    }

    public void addCustomComicToUser(Comic comic){
        addComicToUser(comic);
        currentNextID++;
    }

    private void addSeries(Series series){
        userCollection.add(series);
    }
}
