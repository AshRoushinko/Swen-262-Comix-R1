package controller;

import model.Comic;
import model.ComixDatabase;
import model.Series;
import model.User;
import view.Result;

import java.util.Collection;

public class StoreProfile extends Command{
    public StoreProfile(CommandType type, String info, ComixDatabase db, User uc) {
        super(type, info, db, uc);
    }

    @Override
    public void init(CommandType commandType, String info, ComixDatabase db, User uc) {

    }

    @Override
    public Collection<Comic> run() {
        return null;
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
