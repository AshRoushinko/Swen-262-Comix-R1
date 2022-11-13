package controller;

import model.Comic;
import model.Series;
import view.Result;

import java.util.ArrayList;
import java.util.Collection;

public class Edit extends Command{
    public Edit(CommandType type, String info, Collection<Series> db, Collection<Series> uc) {
        super(type, info, db, uc);
    }

    @Override
    public void init(CommandType commandType, String info, Collection<Series> db, Collection<Series> uc) {
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
        //Variables you need, "info" and "uc"
        //You dont need to worry about the type variable or the db variable
        //The string info variable will contain the id of the comic to edit and all of the information about what the user wants to edit.
        //It will have the following format: ("comicID,newissue,newtitle,newdescription,newpublisher,newreleasedate,newformat,newadddate,newcreator(s))" (you should split the with the commas)
        //Every variable from the string is a string besides creators which is an arraylist of strings just in case there are multiple creators, if there are it will look like: ...,newaddate,creator1|creator2|creator3"
        //Split the creator variable by this thing -> | <- I think its called "the pipe"
        //Then you will iterate through the User collection which is the variable uc, until you find the comic with the matching id (you can look at the search command for help)
        //once you find the comic you will use the comics setter methods to edit the comic. EX: comic.setTitle()
        //before you edit a variable in the comic you need to see if its blank meaning they didnt want to change said variable. EX: ("13,4,Batman,Batman does things,"blank","blank","blank","blank","blank")
        //In the example above the user only wants to change the issue, title, and description for the comic with the id 13
        //So you set the issue, title, and description of the comic you got from the user collection, add it to the list I made above called result and you should be done.
        //Tests are in progress so you wont really be able to see if it works or not unless you hard code it in or try in another
        //Editing the series is a lot more complicated because of how the collections are setup. I will ask him if we need to be able to do this; for now you dont need to worry about it
        return result;
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
