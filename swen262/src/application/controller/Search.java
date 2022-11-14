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

public class Search extends Command {

    private Collection<Comic> searchResult;
    private String resultString;

    public Search(CommandType type, String info, ComixDatabase db, User uc) {
        super(type, info, db, uc);
    }

    @Override
    public void init(CommandType commandType, String info, ComixDatabase db, User uc) {

    }

    @Override
    public Collection<Comic> run() {
        //TODO Only looks through titles, needs to look through the other fields as well
        //TODO if the input is nothing it will loop through the error message
        searchResult = new ArrayList<>();
        String[] searchType = info.split(":");
        String exactOrPartial = searchType[0];
        String searchCriteria = searchType[1];
        System.out.println(searchCriteria);
        Iterator<Series> sCollection = db.getComicCollection().iterator();
        while(sCollection.hasNext()){
            Iterator<Comic> cCollection = sCollection.next().getComics().iterator();
            while(cCollection.hasNext()){
                Comic curr = cCollection.next();
                if (exactOrPartial.equals("EXACT")){
                    if(commandType==CommandType.SEARCHSERIES){
                        if (curr.getSeries().equals(searchCriteria)){
                            searchResult.add(curr);
                        }
                    }
                    else if (commandType==CommandType.SEARCHISSUE){
                        if (curr.getIssue().equals(searchCriteria)){
                            searchResult.add(curr);
                        }
                    }
                    else if (commandType==CommandType.SEARCHTITLE){
                        if (curr.getTitle().equals(searchCriteria)){
                            searchResult.add(curr);
                        }
                    }
                    else if (commandType==CommandType.SEARCHPUBLISHER){
                        if (curr.getPublisher().equals(searchCriteria)){
                            searchResult.add(curr);
                        }
                    }
                    else if (commandType==CommandType.SEARCHADDDATE){
                        if (curr.getAddDate().equals(searchCriteria)){
                            searchResult.add(curr);
                        }
                    }
                    else {//CREATORS
                        ArrayList<String> creators = curr.getCreators();
                        Collections.sort(creators);
                        ArrayList<String> creatorsTempList  = new ArrayList<>();
                        creatorsTempList.addAll(creators);
                        if (searchCriteria.contains("|")){
                            String[] separateCreators = searchCriteria.split("\\|");
                            for (int x = 0; x < separateCreators.length; x++){
                                int temp = -1;
                                Boolean didRemove = false;
                                for (int i = 0; i < creatorsTempList.size(); i++){
                                    if (creatorsTempList.get(i).contains(separateCreators[x])){
                                        temp = i;
                                        didRemove = true;
                                    }
                                }
                                if (didRemove){
                                    creatorsTempList.remove(temp);
                                }
                            }
                            if (creatorsTempList.isEmpty()){
                                searchResult.add(curr);
                            }
                        }
                        else{
                            if (creators.get(0).equals(searchCriteria)){
                                searchResult.add(curr);
                            }
                        }
                    }
                }
                else{
                    if(commandType==CommandType.SEARCHSERIES){
                        if (curr.getSeries().contains(searchCriteria)){
                            searchResult.add(curr);
                        }
                    }
                    else if (commandType==CommandType.SEARCHISSUE){
                        if (curr.getIssue().contains(searchCriteria)){
                            searchResult.add(curr);
                        }
                    }
                    else if (commandType==CommandType.SEARCHTITLE){
                        if (curr.getTitle().contains(searchCriteria)){
                            searchResult.add(curr);
                        }
                    }
                    else if (commandType==CommandType.SEARCHPUBLISHER){
                        if (curr.getPublisher().contains(searchCriteria)){
                            searchResult.add(curr);
                        }
                    }
                    else if (commandType==CommandType.SEARCHADDDATE){
                        if (curr.getAddDate().contains(searchCriteria)){
                            searchResult.add(curr);
                        }
                    }
                    else {//CREATORS
                        ArrayList<String> creators = curr.getCreators();
                        Collections.sort(creators);
                        ArrayList<String> creatorsTempList  = new ArrayList<>();
                        creatorsTempList.addAll(creators);
                        if (searchCriteria.contains("|")){
                            String[] separateCreators = searchCriteria.split("\\|");
                            Boolean didRemove = false;
                            for (int x = 0; x < separateCreators.length; x++){
                                for (int i = 0; i < creatorsTempList.size(); i++){
                                    if (creatorsTempList.get(i).contains(separateCreators[x])){
                                        didRemove = true;
                                    }
                                }
                            }
                            if (didRemove){
                                searchResult.add(curr);
                            }
                        }
                        else{
                            for (int i = 0; i < creators.size(); i++){
                                if (creators.get(i).equals(searchCriteria)){
                                    searchResult.add(curr);
                                }
                            }
                        }
                    }
                }
            }
        }
        Result searchResultVisitor = new SearchResult();
        setResultString(getResult(searchResultVisitor));
        return searchResult;
    }

    @Override
    public String getResult(Result result) {
        return result.visit(this);
    }

    @Override
    public String undo() {
        return ("UNDID SEARCH COMMAND");
    }

    @Override
    public void setResultString(String s) {
        resultString = s;
    }

    public Collection<Comic> getCollection(){
        return searchResult;
    }

    @Override
    public String toString() {
        return resultString;
    }
}
