package controller;

import model.Comic;
import model.Series;
import view.Result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class AddFromDB extends Command{
    public AddFromDB(CommandType type, String info, Collection<Series> db, Collection<Series> uc) {
        super(type, info, db, uc);
    }

    @Override
    public void init(CommandType commandType, String info, Collection<Series> db, Collection<Series> uc) {

    }

    @Override
    public Collection<Comic> run() {
        Collection<Comic> addresult = new ArrayList<>();//Add array list to satisfy return type
        Iterator<Series> seriesIterator = db.iterator();
        while(seriesIterator.hasNext()){
            Series currSeries = seriesIterator.next();
            Collection<Comic> currSeriesComic = currSeries.getComics();
            Iterator<Comic> comicIterator = currSeriesComic.iterator();
            while(comicIterator.hasNext()){
                Comic currComic = comicIterator.next();
                if(currComic.getID().equals(info)){
                    //System.out.println(currComic.getID());
                    addresult.add(currComic);
                }
            }
            //TODO add a case where there is no matcing comic for the id the user entered.
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
