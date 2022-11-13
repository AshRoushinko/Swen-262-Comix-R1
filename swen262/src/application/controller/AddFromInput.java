package controller;

import model.Comic;
import model.ComixDatabase;
import model.Series;
import model.User;
import view.Result;

import java.util.Collection;

public class AddFromInput extends Command{
    public AddFromInput(CommandType type, String info, ComixDatabase db, User uc) {
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
    public Result getResult() {
        return null;
    }

    @Override
    public String undo() {
        return null;
    }
}
