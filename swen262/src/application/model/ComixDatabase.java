package model;

import model.parsefiles.ParseData;
import model.parsefiles.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

public class ComixDatabase implements Database{

    //Instance variables
    //A collection of series which is a collection of comics; represents the database
    private Collection<Series> comicCollection;

    //String array for checking if a character is a letter, number, or a specific char
    //TODO figure out how to use regex instead of looping through this array
    public static final String[] LETTERSANDOTHERS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "#", "[", "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h",
            "j", "k", "l", "z", "x", "c", "v", "b", "n", "m", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S",
            "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M", ".","Q"};

    //Constructor
    public ComixDatabase(String path){
        init(path);
    }

    /**
     * Purpose - initialize the database; sets the collection equal to an new array list and parses the data from the csv file
     * @param path - A string representing the file path
     */
    private void init(String path) {

        this.comicCollection = new ArrayList<>();//set the collection to an arraylist

        parse(path);//parse the data from csv file

    }

    //TODO ADD A COMIC ID REPRESENTING THE COMICS IN ORDER 1-14301, WHEN BROWSING, THE USER CAN USA A COMMAND WITH AN ID INSTEAD OF A NAME

    /**
     * Purpose - Parses the data from the csv file
     * @param path - A string representing the file path
     */
    public void parse(String path){
        try{//Create a try catch block for Exceptions
            File comicCVSDatabase = new File(path);//Creates file object for the scanner to read/iterate through

            Scanner fileScanner = new Scanner(comicCVSDatabase);//Creates a scanner object to iterate through the csv file

            //Iterate over the first three lines of the CSV file which do not contain Comic book information
            fileScanner.nextLine();
            fileScanner.nextLine();
            fileScanner.nextLine();

            Boolean first = true;//Boolean - true if first comic in file ||| false if not

            Series currSeries = new Series("first iteration (temp)", null);//Variable that represents the current series

            //TODO turn into a loop, Try to make all sections the same (NOT METHODS)

            while (fileScanner.hasNextLine()){//Create a while loop that runs as long as the fileScanner has another line

                ArrayList<Comic> currComicList = new ArrayList<>();//Variable that represents the current list of comics (All in the same series)

                int currLineIDX = 0;//Creates a new integer which represents the index of the current line

                String currLine = fileScanner.nextLine();//Creates a string that contains the current line of the CSV file

                int currVariableIDX = 1;//(1-9)

                //TODO GIVE EACH COMIC A BASE VALUE

                //DECLARE ALL CURRENT COMIC BOOK VALUES
                String currComicSeries, currComicIssue, currComicTitle, currComicDescription, currComicReleaseDate,
                        currComicFormat, currComicAddDate, currComicPublisher;
                currComicSeries = currComicIssue = currComicTitle = currComicDescription = currComicReleaseDate = currComicFormat = currComicAddDate = currComicPublisher = "";
                ArrayList<String> currComicCreators = new ArrayList<>();
                //CREATE PARSER
                Parser parser = new Parser();
                while(parser.running()){
                    ParseData variableInfo = parser.parseValue(currLine,currLineIDX);//Creates a ParseInfo object and sets it to the return value of the current variables parse method
                    currLineIDX = variableInfo.getIdx();//Set the current idx to the idx one past the end position of the current variable
                    if(currVariableIDX<9){
                        String currVariable = variableInfo.getText();//Create a string representing the current variables series
                        if (currVariableIDX==1){
                            currComicSeries = currVariable;
                            currLine = currLine.substring(currLineIDX+1);//Set the current line to a substring of itself which excludes the series (Plus one for comma)
                        }
                        else if (currVariableIDX==2){
                            currComicIssue = currVariable;
                            currLine = currLine.substring(currLineIDX);//Set the current line to a substring of itself which excludes the current variable
                        }
                        else if (currVariableIDX==3){
                            currComicTitle = currVariable;
                            currLine = currLine.substring(currLineIDX);//Set the current line to a substring of itself which excludes the current variable
                        }
                        else if (currVariableIDX==4){
                            currComicDescription = currVariable;
                            currLine = currLine.substring(currLineIDX);//Set the current line to a substring of itself which excludes the current variable
                        }
                        else if (currVariableIDX==5){
                            currComicPublisher = currVariable;
                            currLine = currLine.substring(currLineIDX);//Set the current line to a substring of itself which excludes the current variable
                        }
                        else if (currVariableIDX==6){
                            currComicReleaseDate = currVariable;
                            currLine = currLine.substring(currLineIDX);//Set the current line to a substring of itself which excludes the current variable
                        }
                        else if (currVariableIDX==7){
                            currComicFormat = currVariable;
                            currLine = currLine.substring(currLineIDX);//Set the current line to a substring of itself which excludes the current variable
                        }
                        else if (currVariableIDX==8){
                            currComicAddDate = currVariable;
                            currLine = currLine.substring(currLineIDX);//Set the current line to a substring of itself which excludes the current variable
                        }
                        else{
                            System.out.println("ERROR in COMIX DATABASE PARSE FUNCTION");
                            break;
                        }
                    }
                    else if (currVariableIDX==9){
                        variableInfo.buildCreatorArray();
                        currComicCreators = variableInfo.getCreators();//Create a string representing the Comic creators
                    }
                    else{
                        System.out.println("ERROR in COMIX DATABASE PARSE FUNCTION");
                    }
                    currVariableIDX++;
                    currLineIDX = 0;
                }
                //------------------------------------------------------------------------------------------------------
                //Create the comic book object
                Comic currComic = new Comic(currComicSeries, currComicIssue, currComicTitle, currComicDescription,
                        currComicPublisher, currComicReleaseDate, currComicFormat, currComicAddDate,currComicCreators);

                //Create the series object
                if (first){//Check to see if the current iteration is the first
                    currSeries = new Series(currComicSeries, currComicList);//Create a new series with the series name and an empty array list for comics
                    currSeries.addComic(currComic);//Add the comic to the series
                    first = false;//Toggle boolean to show the current iteration is not the first
                }
                else if (!currSeries.getName().equals(currComicSeries)){//Check to see if comic is part of new series
                    comicCollection.add(currSeries);//Add the current series to the collection
                    currSeries = new Series(currComicSeries, currComicList);//Create a new series with the series name and an empty array list for comics
                    currSeries.addComic(currComic);//Add the comic to the series
                }
                else if (currSeries.getName().equals(currComicSeries)){//Comic is part of current Series
                    currSeries.addComic(currComic);//Add the comic to the series

                    if(!fileScanner.hasNextLine()){//Check to see if current comic is the last comic
                        comicCollection.add(currSeries);//Add the current series to the collection
                    }
                    else{

                    }
                }
                else{
                    System.out.println("ERROR");
                }
            }
        }catch(FileNotFoundException exception){
            exception.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //GETTER
    public Collection<Series> getComicCollection() {
        return comicCollection;
    }
    //------------------------------------------------------------------------------------------------------------------
    //OTHER
    public void sort(String filter) {
        //TODO
    }
    public void display() {
        System.out.println("Test");
        Iterator sI = comicCollection.iterator();
        System.out.println(sI.hasNext());
        while(sI.hasNext()){
            System.out.println("Test");
            System.out.println(sI.next().toString());
        }
    }

    @Override
    public Series getSeries(String name) {
        return null;
    }

    @Override
    public Comic getComic(int id) {
        return null;
    }
}



