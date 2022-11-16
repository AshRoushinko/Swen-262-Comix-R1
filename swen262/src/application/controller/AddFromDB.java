package controller;

import model.Comic;
import model.ComixDatabase;
import model.Series;
import model.User;
import view.AddResult;
import view.Result;
import view.SearchResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

//Purpose - A command that allows the user to add a Comic from the database (See Command class for method documentation)
public class AddFromDB extends Command{
    //------------------------------------------------------------------------------------------------------------------
    private Collection<Comic> addResult;
    private String resultString;
    private Boolean added;
    //------------------------------------------------------------------------------------------------------------------
    public AddFromDB(CommandType type, String info, ComixDatabase db, User uc) {
        super(type, info, db, uc);
        init(type, info, db, uc);
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void init(CommandType commandType, String info, ComixDatabase db, User uc) {
        added = false;
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public Collection<Comic> run() {
        addResult = new ArrayList<>();//Add array list to satisfy return type
        Comic currComic = db.getComic(info);
        if (currComic==null){

        }
        else{
            uc.addComicToUser(currComic);
            addResult.add(currComic);
            added = true;
        }
        Result addResultVisitor = new AddResult();
        setResultString(getResult(addResultVisitor));
        return addResult;
    }
    //------------------------------------------------------------------------------------------------------------------
    //RESULT METHODS
    public Boolean isAdded(){
        return added;
    }

    @Override
    public Collection<Comic> getCollection() {
        return addResult;
    }

    @Override
    public String getResult(Result result) {
        return result.visit(this);
    }

    @Override
    public String undo() {
        uc.removeComic(info);
        added = false;
        return "Undid the add command";
    }

    @Override
    public void setResultString(String s) {
        this.resultString = s;
    }

    @Override
    public String toString() {
        return resultString;
    }

    //TODO FIX SEARCH AND BROWSE FOR COMMANDS THAT USE THEM
}
