package view;

import controller.AddFromDB;
import controller.Command;
import controller.CommandType;
import model.Comic;

import java.util.Iterator;
//Purpose - A result that generates a string representing the outcome of Adding a comic to the users personal collection
public class AddResult extends Result{
    @Override
    public String visit(Command command) {
        if (command.commandType== CommandType.ADDFROMDB){
            String result = "";
            AddFromDB addFromDBMask;
            if (command instanceof AddFromDB){
                addFromDBMask = (AddFromDB) command;
            }
            else{
                addFromDBMask = null;
            }
            if (addFromDBMask.isAdded()){
                Iterator<Comic> comicIterator = command.getCollection().iterator();
                while (comicIterator.hasNext()){
                    result = result+comicIterator.next().toString();
                }
            }
            else{
                result = result + "FAILED TO ADD COMIC; NO COMIC WITH THE PROVIDED ID";
            }
            return buildString(result);
        }
        else{
            String result = "ADDED THE FOLLOWING COMIC TO YOUR COLLECTION\n";
            Iterator<Comic> comicIterator = command.getCollection().iterator();
            while (comicIterator.hasNext()){
                result = result+comicIterator.next().toString();
            }
            return buildString(result);
        }
    }
}
