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
//Purpose - A command that allows the user to mark a comic in their personal collection
public class Mark extends Command{
    //------------------------------------------------------------------------------------------------------------------
    private Collection<Comic> markResult;
    private String resultString;
    private String id;
    private String value;
    private Boolean applied;
    //------------------------------------------------------------------------------------------------------------------
    //STRING INFO FORMAT: (IF GRADED)'ID:GRADEVALUE' ||| (IF SLABBED)'ID'
    public Mark(CommandType type, String info, ComixDatabase db, User uc) {
        super(type, info, db, uc);
        init(type,info,db,uc);
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void init(CommandType commandType, String info, ComixDatabase db, User uc) {
        applied = false;
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public Collection<Comic> run() {
        markResult = new ArrayList<>();
        Comic comicToMark;
        if (commandType==CommandType.MARKGRADED){//TODO handle case where book is already graded
            String[] gradeinfosplit = info.split(":");
            id = gradeinfosplit[0];
            value = gradeinfosplit[1];
            comicToMark = uc.getComic(id);
            if (comicToMark==null){
            }
            else{
                applied = comicToMark.grade(value);
            }
        }
        else if (commandType==CommandType.MARKSIGN){
            comicToMark = uc.getComic(info);
            if (comicToMark==null){

            }
            else{
                applied = comicToMark.sign();
            }
        }
        else if (commandType==CommandType.MARKAUTHENTICATE){
            comicToMark = uc.getComic(info);
            if (comicToMark==null){

            }
            else{
                applied = comicToMark.authenticate();
            }
        }
        else{//SLABBED //TODO handle case where book is already slabbed or not graded
            comicToMark = uc.getComic(info);
            if (comicToMark==null){

            }
            else{
                applied = comicToMark.slab();
            }
        }
        markResult.add(comicToMark);
        Result markResultVisitor = new MarkResult();
        setResultString(getResult(markResultVisitor));
        return markResult;
    }
    //------------------------------------------------------------------------------------------------------------------
    //RESULT METHODS
    public Boolean isApplied(){
        return applied;
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
        if (applied){
            if (commandType==CommandType.MARKGRADED){
                uc.getComic(id).ungrade();
            }
            else if (commandType==CommandType.MARKSLABBED){
                uc.getComic(info).unslabb();
            }
            else if (commandType==CommandType.MARKSIGN){
                uc.getComic(info).unsign();
            }
            else if (commandType==CommandType.MARKAUTHENTICATE){
                uc.getComic(info).unAuthenticate();
            }
            applied = false;
        }
        return "Undid mark";
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
