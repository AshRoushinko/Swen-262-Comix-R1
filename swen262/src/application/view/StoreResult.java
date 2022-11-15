package view;

import controller.Command;
import model.Comic;
//Purpose - A result that generates a string representing the outcome of the user storing their profile
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
