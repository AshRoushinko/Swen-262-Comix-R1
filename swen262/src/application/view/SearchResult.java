package view;

import controller.Command;
import controller.CommandType;
import model.Comic;
//Purpose - A result that generates a string representing the outcome of searching through the database of comics
import java.util.Iterator;

public class SearchResult extends Result{
    @Override
    public String visit(Command command) {
        String result = "";
        Iterator<Comic> comicIterator = command.getCollection().iterator();
        if (command.commandType== CommandType.BROWSERUNS){
            String currSeries = "";
            while(comicIterator.hasNext()){
                Comic currComic = comicIterator.next();
                if (currComic.getSeries().equals(currSeries)){
                    result = result+currComic.toString();
                }
                else{
                    currSeries = currComic.getSeries();
                    result = result +"RUN: "+currSeries+"\n"+currComic;
                }
            }

        }
        else if (command.commandType== CommandType.BROWSEGAPS){
            String currSeries = "";
            while(comicIterator.hasNext()){
                Comic currComic = comicIterator.next();
                if (currComic.getSeries().equals(currSeries)){
                    result = result+currComic.toString();
                }
                else{
                    currSeries = currComic.getSeries();
                    result = result +"GAP: "+currSeries+"\n"+currComic;
                }
            }
        }
        else{
            while (comicIterator.hasNext()){
                result = result+comicIterator.next().toString();
            }
        }
        return buildString(result);
    }

}
