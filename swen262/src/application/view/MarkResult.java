package view;

import controller.Command;
import controller.CommandType;
import controller.Mark;
import model.Comic;

import java.util.Iterator;
//Purpose - A result that generates a string representing the outcome of marking a comic in the users collection
public class MarkResult extends Result{
    @Override
    public String visit(Command command) {
        Mark markCommandCast;
        if (command instanceof Mark){
            markCommandCast = (Mark) command;
        }
        else{
            markCommandCast = null;
        }
        String result = "";
        if (command.commandType==CommandType.MARKSLABBED){
            if (markCommandCast.isApplied()){
                result = result+ "THE FOLLOWING COMIC HAS BEEN SLABBED\n";
            }
            else{
                result = result+"UNABLE TO SLAB COMIC\n";
            }
        }
        else if (command.commandType==CommandType.MARKSIGN){
            if (markCommandCast.isApplied()){
                result = result+ "THE FOLLOWING COMIC HAS BEEN SIGNED\n";
            }
            else{
                result = result+ "UNABLE TO SIGN COMIC\n";
            }
        }
        else if (command.commandType==CommandType.MARKAUTHENTICATE){
            if (markCommandCast.isApplied()){
                result = result+ "THE FOLLOWING COMIC HAS BEEN AUTHENTICATED\n";
            }
            else{
                result = result+ "UNABLE TO AUTHENTICATE COMIC\n";
            }
        }
        else{
            if (markCommandCast.isApplied()){
                result = result+ "THE FOLLOWING COMIC HAS BEEN GRADED\n";
            }
            else{
                result = result+ "UNABLE TO GRADE COMIC\n";
            }
        }
        Iterator<Comic> comicIterator = command.getCollection().iterator();
        while (comicIterator.hasNext()){
            result = result+comicIterator.next().toString();
        }
        return buildString(result);
    }
}
