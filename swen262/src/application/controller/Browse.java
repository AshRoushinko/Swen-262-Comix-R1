package controller;

import model.Comic;
import model.Series;
import view.Result;
import view.SearchResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Browse extends Command{

    private Collection<Comic> browseResult;

    public Browse(CommandType type, String info, Collection<Series> db, Collection<Series> uc) {
        super(type, info, db, uc);
    }

    @Override
    public void init(CommandType commandType, String info, Collection<Series> db, Collection<Series> uc) {

    }

    @Override
    public Collection<Comic> run() {
        browseResult = new ArrayList<>();
        Iterator<Series> seriesIterator = uc.iterator();
        while (seriesIterator.hasNext()){
            Iterator<Comic> comicIterator = seriesIterator.next().getComics().iterator();
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
                else{
                    if(currComic.getPublisher().contains(info)){
                        browseResult.add(currComic);
                    }
                }
            }
        }
        return browseResult;
    }

    @Override
    public Result getResult() {
        Result sr = new SearchResult(browseResult);
        return sr;
    }

    @Override
    public String undo() {
        return null;
    }
}
