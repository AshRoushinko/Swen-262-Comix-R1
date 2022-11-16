package controller;

import model.Comic;
import model.ComixDatabase;
import model.Series;
import model.User;
import view.AddResult;
import view.Result;

import java.util.ArrayList;
import java.util.Collection;
//Purpose - A command that allows the user to add a comic to their personal collection by manually entering its attributes
public class AddFromInput extends Command{
    //------------------------------------------------------------------------------------------------------------------
    private Collection<Comic> addResult;
    private String resultString;
    private String id;
    //------------------------------------------------------------------------------------------------------------------
    //STRING INFO FORMAT: 'Series:Issue:Title:Description:Publisher:ReleaseDate:Format:AddDate:Creator(s)'
    public AddFromInput(CommandType type, String info, ComixDatabase db, User uc) {
        super(type, info, db, uc);
        init(type, info, db, uc);
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void init(CommandType commandType, String info, ComixDatabase db, User uc) {
        id = ""+uc.getCurrentNextID();
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public Collection<Comic> run() {
        addResult = new ArrayList<>();

        ArrayList<String> creators = new ArrayList<>();

        String[] comicInfo = info.split(":");
        String currComicSeries = comicInfo[0];
        String currComicIssue = comicInfo[1];
        String currComicTitle = comicInfo[2];
        String currComicDescription = comicInfo[3];
        String currComicPublisher = comicInfo[4];
        String currComicReleaseDate = comicInfo[5];
        String currComicFormat = comicInfo[6];
        String currComicAddDate = comicInfo[7];
        String currComicCreators = comicInfo[8];

        creators.add(currComicCreators);//TODO

        Comic comicToAdd = new Comic(id,currComicSeries,currComicIssue,currComicTitle,currComicDescription,currComicPublisher,currComicReleaseDate,currComicFormat,currComicAddDate,creators);

        uc.addCustomComicToUser(comicToAdd);

        addResult.add(comicToAdd);

        Result addResultVisitor = new AddResult();
        setResultString(getResult(addResultVisitor));
        return addResult;
    }
    //------------------------------------------------------------------------------------------------------------------
    //RESULT METHODS
    @Override
    public Collection<Comic> getCollection() {
        return addResult;
    }

    @Override
    public String getResult(Result result) {
        return result.visit(this);
    }

    @Override
    public String undo() {
        uc.removeComic(id);
        return "Undid the add command";
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
