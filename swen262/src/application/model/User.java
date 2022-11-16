package model;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import controller.ProfileFileType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
//Purpose - This class represents the user object and includes all functionality relating to editing the users collection
public class User implements Database{
    //------------------------------------------------------------------------------------------------------------------
    Collection<Series> userCollection;//AN ARRAY LIST OF SERIES, EACH SERIES HAS AN ARRAY LIST OF COMICS (seriesname.getComics()); will return the list of comics in the series.
    public int currentNextID;
    private ProfileFileType fileType;
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
    public void parse(String filename) throws FileNotFoundException {
            File file = new File(filename);
            FileReader csvInput = new FileReader(file);
            CSVReader reader = new CSVReader(csvInput);
            Iterator<String[]> readerIterator = reader.iterator();
            readerIterator.next();//GET RID OF HEADERS
            while(readerIterator.hasNext()) {
                String[] currComicData = readerIterator.next();
                String[] ccCreators = currComicData[9].split("\\|");
                ArrayList<String> currComicCreatorData = new ArrayList<>();
                currComicCreatorData.addAll(Arrays.asList(ccCreators));
                Comic currComic = new Comic(currComicData[0], currComicData[1], currComicData[2], currComicData[3], currComicData[4], currComicData[5], currComicData[6], currComicData[7], currComicData[8], currComicCreatorData);
                currComic.setValue(Double.parseDouble(currComicData[10]));
                if (currComicData[11].equals("true")) {
                    currComic.setIsGraded();
                    currComic.setGradeValue(Integer.parseInt(currComicData[12]));
                }
                if (currComicData[13].equals("true")) {
                    currComic.setIsSlabbed();
                }
                if (currComicData[14].equals("true")) {
                    currComic.setIsSigned();
                    currComic.setNumSigned(Integer.parseInt(currComicData[15]));
                }
                if (currComicData[16].equals("true")) {
                    currComic.setIsAuthenticated();
                    currComic.setNumAuthenticated(Integer.parseInt(currComicData[17]));
                }
                addComicToUser(currComic);
            }


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
    //FILE -------------------------------
    private ProfileFileType getFileType(){
        return this.fileType;
    }

    private void setFileType(ProfileFileType ft){
        this.fileType = ft;
    }
}
