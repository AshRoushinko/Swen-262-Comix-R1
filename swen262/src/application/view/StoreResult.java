package view;

import controller.Command;
import model.Comic;

import java.util.Iterator;

public class StoreResult extends Result{
    @Override
    public String visit(Command command) {
        String result = "SAVED THE FOLLOWING COMICS TO YOUR PROFILE\n";
        Iterator<Comic> comicIterator = command.getCollection().iterator();
        while (comicIterator.hasNext()){
            result = result+comicIterator.next().toString();
        }
        return buildString(result);
    }
}
