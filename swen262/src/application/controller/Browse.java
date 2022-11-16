package controller;

import model.Comic;
import model.ComixDatabase;
import model.Series;
import model.User;
import view.Result;
import view.SearchResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Browse extends Command{
    //------------------------------------------------------------------------------------------------------------------
    private Collection<Comic> browseResult;
    private String resultString;
    //------------------------------------------------------------------------------------------------------------------
    public Browse(CommandType type, String info, ComixDatabase db, User uc) {
        super(type, info, db, uc);
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void init(CommandType commandType, String info, ComixDatabase db, User uc) {

    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public Collection<Comic> run() {
        browseResult = new ArrayList<>();
        Iterator<Series> seriesIterator = uc.getComiccollection().iterator();
        while (seriesIterator.hasNext()){
            Series currSeries = seriesIterator.next();
            if (commandType==CommandType.BROWSERUNS){
                Iterator<Comic> comicIterator = currSeries.getComics().iterator();
                Boolean isRun = false;
                int iterate = 0;
                int currID = 0;
                ArrayList<Integer> ids = new ArrayList<>();
                while(comicIterator.hasNext()){
                        ids.add(Integer.parseInt(comicIterator.next().getID()));
                }
                Collections.sort(ids);
                for (int i = 0; i<ids.size(); i++){
                    if (currID==0){
                        currID = ids.get(i);
                        iterate++;
                    }
                    else{
                        if (ids.get(i)-currID==1){
                            iterate++;
                            currID = ids.get(i);
                        }
                        else{
                            iterate = 0;
                            currID = 0;
                        }
                        if (iterate>=12){
                            isRun = true;
                        }
                        else{

                        }
                    }
                }
                if (isRun){
                    browseResult.addAll(currSeries.getComics());
                }
            }
            else if (commandType==CommandType.BROWSEGAPS){
                if (currSeries.getComics().size()>=10){
                    Series seriesFromDB = db.getSeries(currSeries.getName());
                    if (seriesFromDB==null){

                    }
                    else{
                        if (seriesFromDB.getComics().size()>=12){
                            browseResult.addAll(currSeries.getComics());
                        }
                    }
                }
            }
            else{
                Iterator<Comic> comicIterator = currSeries.getComics().iterator();
                while (comicIterator.hasNext()){
                    Comic currComic = comicIterator.next();
                    if (commandType==CommandType.BROWSECOLLECTION){
                        browseResult.add(currComic);
                    }
                    else if (commandType==CommandType.BROWSESERIES){
                        if(currComic.getSeries().contains(info)){
                            browseResult.add(currComic);
                        }
                    }
                    else if (commandType==CommandType.BROWSEISSUES){
                        if(currComic.getIssue().contains(info)){
                            browseResult.add(currComic);
                        }
                    }
                    else if (commandType==CommandType.BROWSEVOLUMES){
                        if(currComic.getTitle().contains(info)){
                            browseResult.add(currComic);
                        }
                    }
                    else if (commandType==CommandType.BROWSEPUBLISHERS){
                        if(currComic.getPublisher().contains(info)){
                            browseResult.add(currComic);
                        }
                    }
                    else if (commandType==CommandType.BROWSEGRADED){
                        if (currComic.isGraded()){
                            browseResult.add(currComic);
                        }
                    }
                    else if (commandType==CommandType.BROWSESLABBED){
                        if (currComic.isSlabbed()){
                            browseResult.add(currComic);
                        }
                    }
                    else if (commandType==CommandType.BROWSESIGNED){
                        if (currComic.isSigned()){
                            browseResult.add(currComic);
                        }
                    }
                    else if (commandType==CommandType.BROWSEAUTHENTICATED){
                        if (currComic.isAuthenticated()){
                            browseResult.add(currComic);
                        }
                    }
                }
            }
        }
        Result browseResultVisitor = new SearchResult();
        setResultString(getResult(browseResultVisitor));
        return browseResult;
    }
    //------------------------------------------------------------------------------------------------------------------
    //RESULT METHODS
    @Override
    public Collection<Comic> getCollection() {
        return browseResult;
    }

    @Override
    public String getResult(Result result) {
        return result.visit(this);
    }

    @Override
    public String undo() {
        return ("UNDID BROWSE COMMAND");
    }

    @Override
    public void setResultString(String s) {
        resultString = s;
    }

    @Override
    public String toString() {
        return resultString;
    }
}
