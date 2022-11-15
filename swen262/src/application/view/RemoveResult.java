package view;

import controller.Command;
import model.Comic;
//Purpose - A result that generates a string representing the outcome of removing a comic from the users collection
import java.util.Iterator;

public class RemoveResult extends Result{
    @Override
    public String visit(Command command) {
        String result = "REMOVED THE FOLLOWING COMIC FROM YOUR COLLECTION\n";
        Iterator<Comic> comicIterator = command.getCollection().iterator();
        while (comicIterator.hasNext()){
            result = result+comicIterator.next().toString();
        }
        return buildString(result);
    }
}
