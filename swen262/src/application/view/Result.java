package view;

import controller.Command;
import model.Comic;

import java.util.Collection;
//Purpose - This is an asbtract class that defines the functionality for classes of the result type (Uses the visitor Pattern)
//          Interacts with classes of the command type and generates a string that displays information about the result of a command
public abstract class Result {
    //------------------------------------------------------------------------------------------------------------------
    public final String RESULTHEADER = "Result: \n";
    public final String BARRIER = "-----------------------------------------------------------------------------------";
    //------------------------------------------------------------------------------------------------------------------
    public Result(){
    }
    //------------------------------------------------------------------------------------------------------------------
    //Visitor Methods
    public abstract String visit(Command command);
    public String buildString(String s){
        return RESULTHEADER+s+BARRIER+"\n";
    }

}
