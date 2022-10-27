package controller;

import model.Comic;
import model.Series;
import view.Result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class AddFromDB extends Command{
    public AddFromDB(String type, String info, Collection<Series> db, Collection<Series> uc) {
        super(type, info, db, uc);
    }

    @Override
    public void init(String type, String info, Collection<Series> db, Collection<Series> uc) {

    }

    @Override
    public Collection<Comic> run() {
        Collection<Comic> addresult = new ArrayList<>();//Add array list to satisfy return type
        int x = 0;
        Iterator<Series> seriesIterator = db.iterator();
        while(seriesIterator.hasNext()){
            Iterator<Comic> comicIterator = seriesIterator.next().getComics().iterator();
            Comic currComic = comicIterator.next();
            if(currComic.getID().equals(info)){
                addresult.add(currComic);
            }
            //TODO add a case where there is no mating comic for the id the user entered.
        }
        return addresult;
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
