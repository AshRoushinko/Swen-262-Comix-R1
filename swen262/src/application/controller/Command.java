package controller;

import model.Comic;
import model.Series;
import view.Result;

import java.util.Collection;

public abstract class Command {

    public String info;
    public CommandType commandType;
    public Collection<Series> db;
    public Collection<Series> uc;

    public Command(CommandType commandType, String info, Collection<Series> db, Collection<Series> uc){
        this.commandType = commandType;
        this.info = info;
        this.db = db;
        this.uc = uc;
    }

    public abstract void init(CommandType commandType, String info, Collection<Series> db, Collection<Series> uc);

    public abstract Collection<Comic> run();

    public abstract Result getResult();

    public abstract String undo();

    public CommandType getCommandType(){
        return this.commandType;
    }

    /**
     * This method handles the search operation.
     * Param - content: includes a string representing a name or partial name of the comic(s) to search for.
     * Return - The Book(s) that were found in the search.
     */

    /**
    private void addFromDB(){

    }

    private void addFromInput(){

    }

    private void remove(){

    }

    private void edit(){

    }

    private void mark(){

    }

    private void browse(){

    }

    private void storeProfile(){

    }**/

}
