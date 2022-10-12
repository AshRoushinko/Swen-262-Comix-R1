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

    public Search(String type, String info, Collection<Series> db) {
        super(type, info, db);
    }

    @Override
    public Collection<Comic> run() {

        //TODO partial or exact matches

        Collection<Comic> searchResult = new ArrayList<>();

        Iterator<Series> sCollection = db.iterator();
        while(sCollection.hasNext()){
            Iterator<Comic> cCollection = sCollection.next().getComics().iterator();
            while(cCollection.hasNext()){
                Comic curr = cCollection.next();
                if (curr.getTitle().contains(this.info)){
                    searchResult.add(curr);
                    //show = show+"\n"+curr.toString();
                }
            }
        }
        this.searchResult = searchResult;
        return searchResult;
    }

    @Override
    public Result getResult() {
        Result sResult = new SearchResult()
        //String show = "";
        //Iterator<Comic> results = searchResult.iterator();
        //while(results.hasNext()){

        //}
        return new ;
    }
}
