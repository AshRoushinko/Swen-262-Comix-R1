package controller;

import model.Comic;
import model.Series;
import view.Result;

import java.util.ArrayList;
import java.util.Collection;

public class Remove extends Command{
    Collection<Comic> removeResult;

    public Remove(CommandType type, String info, Collection<Series> db, Collection<Series> uc) {
        super(type, info, db, uc);
    }

    @Override
    public void init(CommandType commandType, String info, Collection<Series> db, Collection<Series> uc) {

    }

    @Override
    public Collection<Comic> run() {
        removeResult = new ArrayList<>();
        //Iterate through the user collection until you find a comic that matches the id represented by the info variable
        //Collection<Series> = user.getCollection


        return null;
    }

    @Override
    public Result getResult() {
        return null;
    }

    @Override
    public String undo() {
        return null;
    }
}
