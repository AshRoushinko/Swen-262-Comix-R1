package controller;

import model.Comic;
import model.Series;
import view.Result;

import java.util.Collection;

public class AddFromInput extends Command{
    public AddFromInput(CommandType type, String info, Collection<Series> db, Collection<Series> uc) {
        super(type, info, db, uc);
    }

    @Override
    public void init(CommandType commandType, String info, Collection<Series> db, Collection<Series> uc) {

    }

    @Override
    public Collection<Comic> run() {
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
