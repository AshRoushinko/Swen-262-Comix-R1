package controller;

import model.Comic;
import model.ComixDatabase;
import model.Series;
import model.User;
import view.AddResult;
import view.MarkResult;
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
        if (commandType==CommandType.MARKGRADED){//TODO handle case where book is already graded
            String[] gradeinfosplit = info.split(":");
            String id = gradeinfosplit[0];
            String value = gradeinfosplit[1];
            System.out.println("ID: "+id);
            System.out.println("Value: "+value);
            Comic comicToMark = uc.getComic(id);
            comicToMark.grade(value);
            markResult.add(comicToMark);
            System.out.println(comicToMark.getValue());
        }
        else{//SLABBED //TODO handle case where book is already slabbed or not graded
            Comic comicToMark = uc.getComic(info);
            comicToMark.slab();
            markResult.add(comicToMark);
        }
        Result markResultVisitor = new MarkResult();
        setResultString(getResult(markResultVisitor));
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
