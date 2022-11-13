package controller;

import model.Comic;
import model.ComixDatabase;
import model.Series;
import model.User;
import view.Result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Edit extends Command{
    public Edit(CommandType type, String info, ComixDatabase db, User uc) {
        super(type, info, db, uc);
    }

    @Override
    public void init(CommandType commandType, String info, ComixDatabase db, User uc) {
        //STRING INFO EXAMPLE: "312:Batman"
        //String id = 312
        //String editvalue = "Batman"
        //comic.setSeries(editvalue);
        //comic.setTitle(editvalue);
    }

    /**
     * Purpose - runs the edit command
     * @return - A collection of comics (for this command there will only be one comic in the returned collection (it will be the edited comic)
     */
    @Override
    public Collection<Comic> run() {
        Collection<Comic> result = new ArrayList<>();
        //split the info string into two variables: ID and VALUE
        //Remove the comic and set it as a variable (Comic = uc.remove(id))
        //Depending on the command type change the value of one of the comics attributes using Comic.setAttribute(VALUE)
        //Then add it back to the user collection with uc.addComicToUser(Comic)
        //I made a test that will print whats in the users collection down below
        //Use the comic I hard coded in. It's ID is 1



        //test
        Iterator<Comic> test = result.iterator();
        while (test.hasNext()){
            System.out.println(test.next().toString());
        }

        return result;
    }

    @Override
    public Collection<Comic> getCollection() {
        return null;
    }

    @Override
    public String getResult(Result result) {
        return null;
    }

    @Override
    public String undo() {
        return null;
    }

    @Override
    public void setResultString(String s) {

    }

    @Override
    public String toString() {
        return null;
    }
}
