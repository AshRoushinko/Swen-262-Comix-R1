package controller;

import model.Comic;
import model.Series;
import view.Result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Browse extends Command{
    public Browse(String type, String info, Collection<Series> db, Collection<Series> uc) {
        super(type, info, db, uc);
    }

    @Override
    public void init(String type, String info, Collection<Series> db, Collection<Series> uc) {

    }

    @Override
    public Collection<Comic> run() {
        Collection<Comic> browseResult = new ArrayList<>();
        //This one is simple but might be hard to do at the same time.
        //You can refer to the search command for iterating through the user collection.
        //Inside the second while loop where you are iterating through comics you need to sort each comic into the arraylist browse result alphabetically
        //The info string will tell you what to sort by. String possibilities: "" = just set browseResult to uc, "publisher" sort by publishers, "series" sort by series,"volume" sort by volumes, "issue" sort by issues

        //TEST
        Iterator<Comic> test = browseResult.iterator();
        while(test.hasNext()){
            System.out.println(test.toString());
        }
        return browseResult;
    }

    @Override
    public Result getResult() {
        return null;
    }

    @Override
    public String undo() {
        return null;
    }
}
