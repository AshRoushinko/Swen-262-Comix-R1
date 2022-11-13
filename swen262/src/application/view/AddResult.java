package view;

import controller.Command;
import model.Comic;

import java.util.Iterator;

public class AddResult extends Result{
    @Override
    public String visit(Command command) {
        String result = "ADDED THE FOLLOWING COMIC TO YOUR COLLECTION\n";
        Iterator<Comic> comicIterator = command.getCollection().iterator();
        while (comicIterator.hasNext()){
            result = result+comicIterator.next().toString();
        }
        return buildString(result);
    }
}
