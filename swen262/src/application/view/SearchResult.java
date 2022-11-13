package view;

import controller.Command;
import model.Comic;

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
