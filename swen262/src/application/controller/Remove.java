package controller;

import model.Comic;
import model.ComixDatabase;
import model.Series;
import model.User;
import view.RemoveResult;
import view.Result;

import java.util.ArrayList;
import java.util.Collection;
//Purpose - A command that allows the user to remove a command from their personal collection
public class Remove extends Command{
    //------------------------------------------------------------------------------------------------------------------
    Collection<Comic> removeResult;
    private String resultString;
    //------------------------------------------------------------------------------------------------------------------
    public Remove(CommandType type, String info, ComixDatabase db, User uc) {
        super(type, info, db, uc);
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void init(CommandType commandType, String info, ComixDatabase db, User uc) {

    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public Collection<Comic> run() {
        removeResult = new ArrayList<>();
        Comic removedComic = uc.removeComic(info);
        removeResult.add(removedComic);
        Result removeResultVisitor = new RemoveResult();
        setResultString(getResult(removeResultVisitor));
        return this.removeResult;
    }
    //------------------------------------------------------------------------------------------------------------------
    //RESULT METHODS
    @Override
    public Collection<Comic> getCollection() {
        return removeResult;
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
