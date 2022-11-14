package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class User implements Database{

    Collection<Series> userCollection;//AN ARRAY LIST OF SERIES, EACH SERIES HAS AN ARRAY LIST OF COMICS (seriesname.getComics(); will return the list of comics in the series.

    public User(){
        userCollection = new ArrayList<>();

        ArrayList<String> creatortemp = new ArrayList<>();
        creatortemp.add("dwhuhdu");

        Comic test = new Comic("1", "TEST", "ewfeuj","dwdhwu","diwdi","diwdi","diwdi","diwdi","diwdi",creatortemp);

        Collection<Comic> temp1 = new ArrayList<>();
        temp1.add(test);

        Series temp = new Series("TEST", temp1);
        userCollection.add(temp);
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

    public Comic removeComic(String id){
        Comic comicToRemove = getComic(id);
        userCollection.remove(comicToRemove);
        return comicToRemove;
    }

    private void addSeries(Series series){
        userCollection.add(series);
    }
}
