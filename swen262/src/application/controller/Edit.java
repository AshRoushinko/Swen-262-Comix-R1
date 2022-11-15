package controller;

import model.Comic;
import model.ComixDatabase;
import model.Series;
import model.User;
import view.AddResult;
import view.EditResult;
import view.Result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
//Purpose - A command that allows the user to edit a comic and change any attribute
public class Edit extends Command{
    //------------------------------------------------------------------------------------------------------------------
    private Collection<Comic> editResult;
    private String resultString;
    private String id;
    //------------------------------------------------------------------------------------------------------------------
    //STRING INFO FORMAT: 'ID:CHANGEVALUE'
    public Edit(CommandType type, String info, ComixDatabase db, User uc) {
        super(type, info, db, uc);
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void init(CommandType commandType, String info, ComixDatabase db, User uc) {
        //STRING INFO EXAMPLE: "312:Batman"
        //String id = 312
        //String editvalue = "Batman"
        //comic.setSeries(editvalue);
        //comic.setTitle(editvalue);
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public Collection<Comic> run() {
        //TODO - will do this if we have time but not important; setup code that will handle input with multiple :
        //TODO The above todo goes with the todo in editResult
        editResult = new ArrayList<>();
        String[] editValueSplit = info.split(":");
        id = editValueSplit[0];
        String value = editValueSplit[1];
        //Remove the comic and set it as a variable (Comic = uc.remove(id))
        //Depending on the command type change the value of one of the comics attributes using Comic.setAttribute(VALUE)
        //Then add it back to the user collection with uc.addComicToUser(Comic)
        //I made a test that will print whats in the users collection down below
        //Use the comic I hard coded in. It's ID is 1



        Result editResultVisitor = new EditResult();
        setResultString(getResult(editResultVisitor));
        return editResult;
    }
    //------------------------------------------------------------------------------------------------------------------
    //RESULT METHODS
    public String getID(){
        return this.id;
    }

    @Override
    public Collection<Comic> getCollection() {
        return editResult;
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
