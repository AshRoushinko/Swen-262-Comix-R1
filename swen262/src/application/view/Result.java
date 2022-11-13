package view;

import controller.Command;
import model.Comic;

import java.util.Collection;

public abstract class Result {

    public final String RESULTHEADER = "Result: \n";
    public final String BARRIER = "-----------------------------------------------------------------------------------";

    public Result(){

    }

    public abstract String visit(Command command);

    public String buildString(String s){
        return RESULTHEADER+s+BARRIER+"\n";
    }

}
