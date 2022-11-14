package view;

import controller.Command;
import controller.CommandType;
import model.Comic;

import java.util.Iterator;

public class MarkResult extends Result{
    @Override
    public String visit(Command command) {
        String result = "";
        if (command.commandType==CommandType.MARKSLABBED){
            result = result+ "THE FOLLOWING COMIC HAS BEEN SLABBED\n";
        }
        else{
            result = result+ "THE FOLLOWING COMIC HAS BEEN GRADED\n";
        }
        Iterator<Comic> comicIterator = command.getCollection().iterator();
        while (comicIterator.hasNext()){
            result = result+comicIterator.next().toString();
        }
        return buildString(result);
    }
}
