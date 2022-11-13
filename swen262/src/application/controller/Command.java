package controller;

import model.Comic;
import model.ComixDatabase;
import model.Series;
import model.User;
import view.Result;

import java.util.Collection;

public abstract class Command {

    public String info;
    public CommandType commandType;
    public ComixDatabase db;
    public User uc;

    public Command(CommandType commandType, String info, ComixDatabase db, User uc){
        this.commandType = commandType;
        this.info = info;
        this.db = db;
        this.uc = uc;
    }

    public abstract void init(CommandType commandType, String info, ComixDatabase db, User uc);

    public abstract Collection<Comic> run();

    public abstract Collection<Comic> getCollection();

    public abstract String getResult(Result result);

    public abstract String undo();

    public CommandType getCommandType(){
        return this.commandType;
    }

    public abstract void setResultString(String s);

    public abstract String toString();
}
