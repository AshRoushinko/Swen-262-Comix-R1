package controller;

import model.Comic;
import model.ComixDatabase;
import model.Series;
import model.User;
import view.Result;

import java.util.Collection;

public abstract class Command {

    public String info;
    public CommandType commandType;
    public ComixDatabase db;
    public User uc;

    public Command(CommandType commandType, String info, ComixDatabase db, User uc){
        this.commandType = commandType;
        this.info = info;
        this.db = db;
        this.uc = uc;
    }

    public abstract void init(CommandType commandType, String info, ComixDatabase db, User uc);

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
