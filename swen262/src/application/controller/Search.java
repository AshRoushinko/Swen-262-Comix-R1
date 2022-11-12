package controller;

import model.Comic;
import model.Series;
import view.Result;
import view.SearchResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Search extends Command {

    private Collection<Comic> searchResult;

    public Search(String type, String info, Collection<Series> db,Collection<Series> uc) {
        super(type, info, db, uc);
    }

    @Override
    public void init(String type, String info, Collection<Series> db, Collection<Series> uc) {

    }
    @Override
    public Collection<Comic> run() {
        //TODO Only looks through titles, needs to look through the other fields as well
        //TODO if the input is nothing it will loop through the error message
        //TODO display series in output
        Collection<Comic> searchResult = new ArrayList<>();

        Iterator<Series> sCollection = db.iterator();
        while(sCollection.hasNext()){
            Iterator<Comic> cCollection = sCollection.next().getComics().iterator();
            while(cCollection.hasNext()){
                Comic curr = cCollection.next();
                if (this.type.equals("11")){
                    if (curr.getTitle().equals(this.info)){
                        searchResult.add(curr);
                    }
                }
                else{
                    if (curr.getTitle().contains(this.info)){
                        searchResult.add(curr);
                    }
                }
            }
        }
        this.searchResult = searchResult;
        return this.searchResult;
    }

    @Override
    public Result getResult() {
        Result sr = new SearchResult(searchResult);
        return sr;
    }

    @Override
    public String undo() {
        return ("UNDID SEARCH COMMAND");
    }
}
