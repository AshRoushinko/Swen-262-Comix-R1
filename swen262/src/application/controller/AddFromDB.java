package controller;

import model.Comic;
import model.ComixDatabase;
import model.Series;
import model.User;
import view.Result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class AddFromDB extends Command{
    public AddFromDB(CommandType type, String info, ComixDatabase db, User uc) {
        super(type, info, db, uc);
    }

    @Override
    public void init(CommandType commandType, String info, ComixDatabase db, User uc) {

    }

    @Override
    public Collection<Comic> run() {
        Collection<Comic> addresult = new ArrayList<>();//Add array list to satisfy return type
        Comic currComic = db.getComic(info);
        uc.addComicToUser(currComic);
        addresult.add(currComic);

            //TODO add a case where there is no matcing comic for the id the user entered.

        return addresult;
    }

    @Override
    public Collection<Comic> getCollection() {
        return null;
    }

    @Override
    public String getResult(Result result) {
        return null;
    }


    @Override
    public String undo() {
        return null;
    }

    @Override
    public void setResultString(String s) {

    }

    @Override
    public String toString() {
        return null;
    }
}
