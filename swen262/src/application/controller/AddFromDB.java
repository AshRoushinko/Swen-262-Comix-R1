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
    //------------------------------------------------------------------------------------------------------------------
    public AddFromDB(CommandType type, String info, ComixDatabase db, User uc) {
        super(type, info, db, uc);
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void init(CommandType commandType, String info, ComixDatabase db, User uc) {

    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public Collection<Comic> run() {
        addResult = new ArrayList<>();//Add array list to satisfy return type
        Comic currComic = db.getComic(info);
        uc.addComicToUser(currComic);
        addResult.add(currComic);

            //TODO add a case where there is no matching comic for the id the user entered.

        Result addResultVisitor = new AddResult();
        setResultString(getResult(addResultVisitor));
        return addResult;
    }
    //------------------------------------------------------------------------------------------------------------------
    //RESULT METHODS
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
        return null;
    }

    @Override
    public void setResultString(String s) {
        this.resultString = s;
    }

    @Override
    public String toString() {
        return resultString;
    }
}
