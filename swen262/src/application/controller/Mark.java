package controller;

import model.Comic;
import model.ComixDatabase;
import model.Series;
import model.User;
import view.Result;

import java.util.ArrayList;
import java.util.Collection;

public class Mark extends Command{

    private Collection<Comic> markResult;
    private String resultString;

    public Mark(CommandType type, String info, ComixDatabase db, User uc) {
        super(type, info, db, uc);
    }

    @Override
    public void init(CommandType commandType, String info, ComixDatabase db, User uc) {

    }

    @Override
    public Collection<Comic> run() {
        markResult = new ArrayList<>();


        return markResult;
    }

    @Override
    public Collection<Comic> getCollection() {
        return markResult;
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
