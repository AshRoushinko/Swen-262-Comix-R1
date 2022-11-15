package view;

import controller.Command;
import model.Comic;
//Purpose - A result that generates a string representing the outcome of searching through the database of comics
import java.util.Iterator;

public class SearchResult extends Result{
    @Override
    public String visit(Command command) {
        String result = "";
        Iterator<Comic> comicIterator = command.getCollection().iterator();
        while (comicIterator.hasNext()){
            result = result+comicIterator.next().toString();
        }
        return buildString(result);
    }

}
