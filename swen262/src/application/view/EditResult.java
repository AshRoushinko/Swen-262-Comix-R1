package view;

import controller.Command;
import model.Comic;

import java.util.Collection;
import java.util.Iterator;
//Purpose - A result that generates a string representing the outcome of Editing a comic in the users collection
public class EditResult extends Result{
    @Override
    public String visit(Command command) {
        String result = "EDITED THE FOLLOWING COMIC TO YOUR COLLECTION\nBEFORE:\n";
        //TODO - matches the todo in the edit command
        String[] commandInfoSplit = command.info.split(":");
        Comic beforeComic = command.uc.getComic(commandInfoSplit[0]);
        result = result+beforeComic.toString()+"AFTER: \n";
        Iterator<Comic> comicIterator = command.getCollection().iterator();
        while (comicIterator.hasNext()){
            result = result+comicIterator.next().toString();
        }
        return buildString(result);
    }
}
