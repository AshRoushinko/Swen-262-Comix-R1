package controller;

import model.Comic;
import model.ComixDatabase;
import model.Series;
import model.User;
import view.Result;

import java.util.Collection;
//Purpose - A command that allows the user to store their collection in a file of their choice
public class StoreProfile extends Command{
    //------------------------------------------------------------------------------------------------------------------
    private Collection<Comic> storeResult;
    private String resultString;
    //------------------------------------------------------------------------------------------------------------------
    public StoreProfile(CommandType type, String info, ComixDatabase db, User uc) {
        super(type, info, db, uc);
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void init(CommandType commandType, String info, ComixDatabase db, User uc) {

    }
    //------------------------------------------------------------------------------------------------------------------
    //RESULT METHODS
    @Override
    public Collection<Comic> run() {
        return storeResult;
    }

    @Override
    public Collection<Comic> getCollection() {
        return storeResult;
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
        resultString = s;
    }

    @Override
    public String toString() {
        return resultString;
    }
}
