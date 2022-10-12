package controller;

import model.Comic;
import model.Series;
import view.Result;

import java.util.Collection;

public abstract class Command {

    String type;
    String info;
    Collection<Series> db;

    public Command(String type, String info, Collection<Series> db){
        this.type = type;
        this.info = info;
        this.db = db;
    }

    public abstract Collection<Comic> run();

    public abstract Result getResult();

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
