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
    private String originalValue;
    private ArrayList<String> originalCreators;
    private String value;
    //------------------------------------------------------------------------------------------------------------------
    //STRING INFO FORMAT: 'ID:CHANGEVALUE'
    public Edit(CommandType type, String info, ComixDatabase db, User uc) {
        super(type, info, db, uc);
        init(type,info,db,uc);
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void init(CommandType commandType, String info, ComixDatabase db, User uc) {
        String[] editValueSplit = info.split(":");
        id = editValueSplit[0];
        value = editValueSplit[1];
        //OR JUST STORE THE ORIGINAL VALUE OF THE CHANGED ATTRIBUTE
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public Collection<Comic> run() {
        editResult = new ArrayList<>();
        System.out.println(id);
        Comic curr = uc.getComic(id);
        if(commandType == CommandType.EDITSERIES){
            originalValue = curr.getSeries();
            curr.setSeries(value);
            editResult.add(curr);
        }
        else if(commandType == CommandType.EDITISSUE){
            originalValue = curr.getIssue();
            curr.setIssue(value);
            editResult.add(curr);
        }
        else if(commandType == CommandType.EDITTITLE){
            originalValue = curr.getTitle();
            curr.setTitle(value);
            editResult.add(curr);
        }
        else if(commandType == CommandType.EDITDESCRIPTION){
            originalValue = curr.getDescription();
            curr.setDescription(value);
            editResult.add(curr);
        }
        else if(commandType == CommandType.EDITPUBLISHER){
            originalValue = curr.getPublisher();
            curr.setPublisher(value);
            editResult.add(curr);
        }
        else if(commandType == CommandType.EDITRELEASEDATE){
            originalValue = curr.getReleaseDate();
            curr.setReleaseDate(value);
            editResult.add(curr);
        }
        else if(commandType == CommandType.EDITFORMAT){
            originalValue = curr.getFormat();
            curr.setFormat(value);
            editResult.add(curr);
        }
        else if(commandType == CommandType.EDITADDDATE){
            originalValue = curr.getAddDate();
            curr.setAddDate(value);
            editResult.add(curr);
        }
        else if(commandType == CommandType.EDITCREATORS){
            originalCreators = curr.getCreators();
            ArrayList<String> creators = new ArrayList<>();
            creators.add(value);
            curr.setCreators(creators);
            editResult.add(curr);
        }
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
        Comic curr = uc.getComic(id);
        if(commandType == CommandType.EDITSERIES){
            curr.setSeries(originalValue);
        }
        else if(commandType == CommandType.EDITISSUE){
            curr.setIssue(originalValue);
        }
        else if(commandType == CommandType.EDITTITLE){
            curr.setTitle(originalValue);
        }
        else if(commandType == CommandType.EDITDESCRIPTION){
            curr.setDescription(originalValue);
        }
        else if(commandType == CommandType.EDITPUBLISHER){
            curr.setPublisher(originalValue);
        }
        else if(commandType == CommandType.EDITRELEASEDATE){
            curr.setReleaseDate(originalValue);
        }
        else if(commandType == CommandType.EDITFORMAT){
            curr.setFormat(originalValue);
        }
        else if(commandType == CommandType.EDITADDDATE){
            curr.setAddDate(originalValue);
        }
        else if(commandType == CommandType.EDITCREATORS){
            curr.setCreators(originalCreators);
        }
        return "Undid edit";
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
