package controller;

import model.Comic;
import model.ComixDatabase;
import model.Series;
import model.User;
import view.AddResult;
import view.Result;

import java.util.ArrayList;
import java.util.Collection;

public class AddFromInput extends Command{

    private Collection<Comic> addResult;
    private String resultString;

    public AddFromInput(CommandType type, String info, ComixDatabase db, User uc) {
        super(type, info, db, uc);
    }

    @Override
    public void init(CommandType commandType, String info, ComixDatabase db, User uc) {

    }

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

        Comic comicToAdd = new Comic(""+uc.getCurrentNextID(),currComicSeries,currComicIssue,currComicTitle,currComicDescription,currComicPublisher,currComicReleaseDate,currComicFormat,currComicAddDate,creators);

        uc.addCustomComicToUser(comicToAdd);

        addResult.add(comicToAdd);

        Result addResultVisitor = new AddResult();
        setResultString(getResult(addResultVisitor));
        return addResult;
    }

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
