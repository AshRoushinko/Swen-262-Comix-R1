package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

public class Database {

    //Instance variables
    //A collection of series which is a collection of comics; represents the database
    private Collection<Series> comiccollection;

    //String array for checking if a character is a letter, number, or a specific char
    //TODO figure out how to use regex instead of looping through this array
    public static final String[] LETTERSANDOTHERS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "#", "[", "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h",
            "j", "k", "l", "z", "x", "c", "v", "b", "n", "m", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S",
            "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M", ".","Q"};

    //Constructor
    public Database(String path){
        init(path);
    }

    /**
     * Purpose - initialize the database; sets the collection equal to an new array list and parses the data from the csv file
     * @param path - A string representing the file path
     */
    private void init(String path) {

        this.comiccollection = new ArrayList<>();//set the collection to an arraylist

        Parse(path);//Parse the data from csv file

    }

    /**
     * Purpose - Parses the data from the csv file
     * @param path - A string representing the file path
     */
    public void Parse(String path){
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

                //SERIES
                //------------------------------------------------------------------------------------------------------
                ParseData seriesInfo = parseSeries(currLine,currLineIDX);//Creates a ParseInfo object and sets it to the return value of the parseSeries Method

                String currComicSeries = seriesInfo.getText();//Create a string representing the Comic series

                currLineIDX = seriesInfo.getIdx();//Set the current idx to the idx one past the end position of the series title

                currLine = currLine.substring(currLineIDX+1);//Set the current line to a substring of itself which excludes the series (Plus one for comma)
                currLineIDX = 0;//Set the current line index to zero because the line is now a substring of the previous
                //SERIESTESTS
                //System.out.println(currLine);
                //System.out.println(currComicSeries);
                //------------------------------------------------------------------------------------------------------
                //ISSUE
                ParseData issueInfo = parseIssue(currLine,currLineIDX);//Creates a ParseInfo object and sets it to the return value of the parseIssue Method

                String currComicIssue = issueInfo.getText();//Create a string representing the Comic issue

                currLineIDX = issueInfo.getIdx();//Set the current idx to the idx one past the end position of the issue title

                currLine = currLine.substring(currLineIDX);//Set the current line to a substring of itself which excludes the issue (Plus one for comma)
                currLineIDX = 0;//Set the current line index to zero because the line is now a substring of the previous
                //ISSUETESTS
                //System.out.println("After: "+currLine);
                //System.out.println("After: "+currComicIssue);
                //------------------------------------------------------------------------------------------------------
                //TITLE - currLine title cases (None = "," ||| NOQUOTE = "Letter" ||| Quote = "\"")
                ParseData titleInfo = parseTitle(currLine,currLineIDX);//Creates a ParseInfo object and sets it to the return value of the parseTitle Method

                String currComicTitle = titleInfo.getText();//Create a string representing the Comic title

                currLineIDX = titleInfo.getIdx();//Set the current idx to the idx one past the end position of the title
                currLine = currLine.substring(currLineIDX);//Set the current line to a substring of itself which excludes the title
                //Line will start with , meaning theres no description, or a quotation mark meaning there is a description; can also have embedded :(
                currLineIDX = 0;//Set the current line index to zero because the line is now a substring of the previous
                //TITLETESTS
                //System.out.println("After: "+currLine);
                //System.out.println("After: "+currComicTitle);
                //------------------------------------------------------------------------------------------------------
                //DESCRIPTION
                ParseData descInfo = parseDescription(currLine,currLineIDX);//Creates a ParseInfo object and sets it to the return value of the parseDescription Method
                String currComicDesc = descInfo.getText();//Create a string representing the Comic description

                currLineIDX = descInfo.getIdx();//Set the current idx to the idx one past the end position of the description
                currLine = currLine.substring(currLineIDX);//Set the current line to a substring of itself which excludes the description

                currLineIDX = 0;//Set the current line index to zero because the line is now a substring of the previous
                //DESCRIPTIONTESTS
                //System.out.println("After: "+currLine);
                //System.out.println("Description: "+currComicDesc);
                //------------------------------------------------------------------------------------------------------
                //PUBLISHER - CurrLine starts as: (\") || (LETTER) || (",") = NOTHING
                ParseData publisherInfo = parsePublisher(currLine,currLineIDX);//Creates a ParseInfo object and sets it to the return value of the parsePublisher Method
                String currComicPublisher = publisherInfo.getText();//Create a string representing the Comic publisher

                currLineIDX = publisherInfo.getIdx();//Set the current idx to the idx one past the end position of the publisher
                currLine = currLine.substring(currLineIDX);//Set the current line to a substring of itself which excludes the publisher
                //Line will start with TODO
                currLineIDX = 0;//Set the current line index to zero because the line is now a substring of the previous
                //PUBLISHERTESTS
                //System.out.println("After: "+currLine);
                //System.out.println("Publisher: "+currComicPublisher);
                //------------------------------------------------------------------------------------------------------
                //RELEASEDATE
                ParseData releaseDateInfo = parseReleaseDate(currLine,currLineIDX);//Creates a ParseInfo object and sets it to the return value of the parseReleaseDate Method
                String currComicReleaseDate = releaseDateInfo.getText();//Create a string representing the Comic release date

                currLineIDX = releaseDateInfo.getIdx();//Set the current idx to the idx one past the end position of the release date
                currLine = currLine.substring(currLineIDX);//Set the current line to a substring of itself which excludes the release date
                //Line will start with TODO
                currLineIDX = 0;//Set the current line index to zero because the line is now a substring of the previous
                //RELEASEDATETESTS
                //System.out.println("After: "+currLine);
                //System.out.println("Release Date: "+currComicReleaseDate);
                //------------------------------------------------------------------------------------------------------
                //FORMAT
                ParseData formatInfo = parseFormat(currLine,currLineIDX);//Creates a ParseInfo object and sets it to the return value of the parseFormat Method
                String currComicFormat = formatInfo.getText();//Create a string representing the Comic format

                currLineIDX = formatInfo.getIdx();//Set the current idx to the idx one past the end position of the format
                currLine = currLine.substring(currLineIDX);//Set the current line to a substring of itself which excludes the format
                //Line will start with TODO
                currLineIDX = 0;//Set the current line index to zero because the line is now a substring of the previous
                //FORMATTESTS
                //System.out.println("After: "+currLine);
                //System.out.println("Format: "+currComicFormat);
                //------------------------------------------------------------------------------------------------------
                //ADDDATE
                ParseData addDateInfo = parseAddDate(currLine,currLineIDX);//Creates a ParseInfo object and sets it to the return value of the parseAddDate Method
                String currComicAddDate = addDateInfo.getText();//Create a string representing the Comic add Date

                currLineIDX = addDateInfo.getIdx();//Set the current idx to the idx one past the end position of the add Date
                currLine = currLine.substring(currLineIDX);//Set the current line to a substring of itself which excludes the add Date
                //Line will start with TODO
                currLineIDX = 0;//Set the current line index to zero because the line is now a substring of the previous
                //ADDDATETESTS
                //System.out.println("After: "+currLine);
                //System.out.println("ADDDATE: "+currComicAddDate);
                //------------------------------------------------------------------------------------------------------
                //CREATORS
                ParseData creatorsInfo = parseCreators(currLine,currLineIDX);//Creates a ParseInfo object and sets it to the return value of the parseCreators Method
                creatorsInfo.buildCreatorArray();
                ArrayList<String> currComicCreators = creatorsInfo.getCreators();//Create a string representing the Comic creators
                //CREATORSTESTS
                //System.out.println("After: "+currLine);
                //System.out.println(currComicCreators);
                //------------------------------------------------------------------------------------------------------
                //Create the comic book object
                Comic currComic = new Comic(currComicSeries, currComicIssue, currComicTitle, currComicDesc,
                        currComicPublisher, currComicReleaseDate, currComicFormat, currComicAddDate,currComicCreators);

                //Create the series object
                if (first){//Check to see if the current iteration is the first
                    currSeries = new Series(currComicSeries, currComicList);//Create a new series with the series name and an empty array list for comics
                    currSeries.addComic(currComic);//Add the comic to the series
                    first = false;//Toggle boolean to show the current iteration is not the first
                }
                else if (!currSeries.getName().equals(currComicSeries)){//Check to see if comic is part of new series
                    comiccollection.add(currSeries);//Add the current series to the collection
                    currSeries = new Series(currComicSeries, currComicList);//Create a new series with the series name and an empty array list for comics
                    currSeries.addComic(currComic);//Add the comic to the series
                }
                else if (currSeries.getName().equals(currComicSeries)){//Comic is part of current Series
                    currSeries.addComic(currComic);//Add the comic to the series
                }
                else{
                    System.out.println("ERROR");
                }
            }
        }catch(FileNotFoundException exception){
            exception.printStackTrace();
        }
    }
    //PARSE METHODS
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Purpose - To get the Series name from the line
     * @param line - The current line
     * @param idx - the current idx
     * @return - returns a ParseInfo object containing the series and the updated line index
     */
    private ParseData parseSeries(String line, int idx){
        String seriesGet;//Creates an empty string that will be initialized with the series title and returned
        if(line.startsWith("\"")){//Check to see if the series has quotations
            //Find the index of the next quotation mark (This should be the end of the series title)
            int seriesEnd = line.substring(1).indexOf("\"")+1;
            //Create a new string that contains the series title (The text in between the first set of parenthesis)
            seriesGet = line.substring(1,seriesEnd);
            //Add the length of the series to the idx
            idx+=seriesGet.length()+2;//The plus 2 is for the parenthesis
        }
        else{
            //Find the index of the first comma which should mark the end of the series title
            int seriesEnd = line.indexOf(",");
            //Create a new string that contains the series title (The text before the first comma)
            seriesGet = line.substring(0,seriesEnd);
            //Add the length of the series to the idx
            idx+=seriesGet.length();
        }
        return new ParseData(idx, seriesGet);//Return a parse info object containing the parse information for the series
    }
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Purpose - to get the issue from the line
     * @param line - The current line
     * @param idx - the current idx
     * @return - returns a ParseInfo object containing the issue and the updated line index
     */
    private ParseData parseIssue(String line, int idx){
        String issueGet;//Creates an empty string that will be initialized with the issue of the comic and returned
        if(line.startsWith(",")){
            issueGet = "";
        }
        else{
            int issueEnd = line.indexOf(",");
            issueGet = line.substring(0,issueEnd);
            idx+=issueEnd;
        }
        idx+=1;
        return new ParseData(idx, issueGet);//Return a parse info object containing the parse information for the issue
    }
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Purpose - to get the title from the line
     * @param line - The current line
     * @param idx - the current idx
     * @return - returns a ParseInfo object containing the title and the updated line index
     */
    private ParseData parseTitle(String line, int idx){
        String titleGet;//Creates an empty string that will be initialized with the title and returned
        int titleEnd;//Declares the integer that will represent the index following the end of the title.
        //Check for no title
        if (line.startsWith(",")){
            titleGet = "";//Set title to an empty string
            idx+=1;//Offset the idx by one for the comma
        }
        else if (line.startsWith("\"")){//Check for quotations
            if(line.contains("\"\"")){//Check for embedded quotations (Checks whole line not just the title)
                if(line.startsWith("\"\"\"")){//Check for entire title in embedded quotations
                    if(line.substring(3).contains("\"\"\",")){//Check to see if it ends with triple quotations
                        titleEnd = line.indexOf("\"\"\",");//Get the index of the triple quotations followed by the comma (""",) which represents the end of the title
                        String titleGetTemp = line.substring(1,titleEnd+2);//Set the title (Still includes double quotes)(start index is one to remove quotations)
                        titleGet = titleGetTemp.replaceAll("\"\"", "\"");//Replace all double quotes with singe quotes
                        idx+=titleEnd+4;//Add the index of the end of the title to the current index (add four for quotations and comma)
                    }
                    else{//Doesn't end in triple quotations
                        //FIND INDEX OF "" and then search for ", after
                        titleEnd = line.indexOf(",\"");//Get the index of the comma followed by a single quote (,") which represents the end of the title
                        String titleGetTemp = line.substring(1,titleEnd-1);//Set the title (Still includes double quotes)(start index is one to remove quotations)
                        titleGet = titleGetTemp.replaceAll("\"\"", "\"");//Replace all double quotes with singe quotes
                        idx+=titleEnd+1;//Add the index of the end of the title to the current index (add one for comma)
                    }
                }
                else{//embedded quotes are included but not in first position (first position always has single quote)
                    if(line.substring(1).contains("\"\"\",")){//Check to see if it ends with triple quotations
                        titleEnd = line.indexOf("\"\"\",");//Get the index of the triple quotations followed by the comma (""",) which represents the end of the title
                        String titleGetTemp = line.substring(1,titleEnd+2);//Set the title (Still includes double quotes)(start index is one to remove quotations)
                        titleGet = titleGetTemp.replaceAll("\"\"", "\"");//Replace all double quotes with singe quotes
                        idx+=titleEnd+4;////Add the index of the end of the title to the current index (add four for quotes and comma)
                    }
                    else{//Doesn't end with triple quotations
                        if(line.indexOf("\",")<line.indexOf("\"\"")){//This checks to see if the double quotes are after the end of the title (No embedded quotes)
                            titleEnd = line.indexOf("\",");//Gets the index of the quotation followed by the comma (",) which represents the end of the title
                            titleGet = line.substring(1,titleEnd);//Set the title (start index is one to remove quotation)
                            idx+=titleEnd+2;//Add the index of the end of the title to the current index (add two for quotations and comma)
                        }
                        else{
                            if (line.substring(1).contains("\"\",")){//Need to look for (",) which is end of the title: ("",) will show up too so if the line has it
                                //the search for (",) will continue after (can be multiple ("",))
                                int lastDoubleQuoteC = line.lastIndexOf("\"\",")+3;//Gets the last index of the ("",) in the line (add by three to offset ("",))
                                int titleEndTemp = line.substring(lastDoubleQuoteC).indexOf("\",");//Gets the index of (",) from the last instance of ("",)
                                titleEnd = lastDoubleQuoteC+titleEndTemp;//Gets the real index of the (",) following the end of the title
                                String titleGetTemp = line.substring(1,titleEnd);//Set the title (start index is one to remove quotation)(Still includes double quotes)
                                titleGet = titleGetTemp.replaceAll("\"\"", "\"");//Replace all double quotes with singe quotes
                                idx+=titleEnd+2;//Add the index of the end of the title to the current index (add two for quotations and comma)
                            }
                            else{//Ends with (",) and has any embedded quotes
                                titleEnd = line.indexOf("\",");//Get the index of the quotation followed by the comma (",) which represents the end of the title
                                String titleGetTemp = line.substring(1,titleEnd);//Set the title (start index is one to remove quotation)(Still includes double quotes)
                                titleGet = titleGetTemp.replaceAll("\"\"", "\"");//Replace all double quotes with singe quotes
                                idx+=titleEnd+2;//Add the index of the end of the title to the current index (add two for quotations and comma)
                            }
                        }
                    }
                }
            }
            else{//If there is no embedded quotations
                titleEnd = line.indexOf("\",");//Get the index of the quotation followed by the comma (",) which represents the end of the title
                titleGet = line.substring(1,titleEnd);//Set the title (start index is one to remove quotation)
                idx+=titleEnd+2;//Add the index of the end of the title to the current index (add two for quotations and comma)
            }
        }
        //Handles any Title without parenthesis
        else{
            titleEnd = line.indexOf(",");//Gets the index of the comma following the title
            titleGet = line.substring(0,titleEnd);//Generates the title
            idx+=titleEnd+1;//Add the index of the end of the title to the current index (add one for comma)
        }
        return new ParseData(idx, titleGet);//Return a parse info object containing the parse information for the title
    }
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Purpose - to get the Description from the line
     * @param line - The current line
     * @param idx - the current idx
     * @return - returns a ParseInfo object containing the Description and the updated line index
     */
    private ParseData parseDescription(String line, int idx){
        String descGet;//Creates an empty string that will be initialized with the description and returned
        int descEnd;//Declares the integer that will represent the index following the end of the description.

        //Check for no description
        if (line.startsWith(",")){
            descGet = "";//Set description to an empty string
            idx+=1;//Offset the idx by one for the comma
        }
        else if (line.startsWith("\"")){//Checks for Quotes in front
            descEnd = line.indexOf("\",");//Get the index of the quotation followed by the comma (",) which represents the end of the description
            String descGetTemp = line.substring(1,descEnd);//Get the description (Still has embedded quotes) (Start is one to remove first quote)
            descGet = descGetTemp.replaceAll("\"\"","\"");//Replace all double quotes with singe quotes
            idx += descEnd+2;//Update the index to the next value (Add 2 for quote and comma)
        }
        else{//No quotes in description
            descEnd = line.indexOf(",");//Gets the index of the comma following the description
            descGet = line.substring(0,descEnd);//Generates the description
            idx+=descEnd+1;//Add the index of the end of the description to the current index (add one for comma)
        }
        return new ParseData(idx, descGet);//Return a parse info object containing the parse information for the Description
    }
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Purpose - to get the Publisher from the line
     * @param line - The current line
     * @param idx - the current idx
     * @return - returns a ParseInfo object containing the Publisher and the updated line index
     */
    private ParseData parsePublisher(String line, int idx){
        String pubGet;//Creates an empty string that will be initialized with the publisher name and returned
        int pubEnd;//Declares the integer that will represent the index following the end of the publisher name.

        //Check for no publisher
        if (line.startsWith(",")){
            pubGet = "";//Set publisher to an empty string
            idx+=1;//Offset the idx by one for the comma
        }
        else if(line.startsWith("\"")){//Check for parenthesis
            pubEnd = line.indexOf("\",");//Get the index of the quotation followed by the comma (",) which represents the end of the publisher name
            pubGet = line.substring(1,pubEnd);//Set the publishers name (initial idx is one to offset the parenthesis)
            idx+=pubEnd+2;//Update idx to the index after the end of the publisher name(idx is added by two to remove (",))
        }
        else{//The publisher value starts with a letter
            pubEnd = line.indexOf(",");//Getting the index of the comma after the publisher name
            pubGet = line.substring(0,pubEnd);//Creating the substring
            idx+=pubEnd+1;//updating the idx to the index after the end of the publisher name(idx is added by one for comma)
        }
        return new ParseData(idx, pubGet);//Return a parse info object containing the parse information for the Publisher
    }
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Purpose - to get the ReleaseDate from the line
     * @param line - The current line
     * @param idx - the current idx
     * @return - returns a ParseInfo object containing the ReleaseDate and the updated line index
     */
    private ParseData parseReleaseDate(String line, int idx){
        //Can either be inside quotation marks, a year without quotation marks, or nothing

        String releaseGet;//Creates an empty string that will be initialized with the release date and returned
        int releaseEnd;//Declares the integer that will represent the index following the end of the release date.
        if (line.startsWith("\"")){//Check to see if it starts with a quote
            releaseEnd = line.indexOf("\",");//Get the index of the quotation followed by the comma (",) which represents the end of the release date value
            releaseGet = line.substring(1,releaseEnd);//Set the release date string (Starting value is one to remove the quoteation mark)
            idx+=releaseEnd+2;//Get the index after the release date value (add two to remove the closing quotation mark and the comma)
        }
        else if (line.startsWith(",")){//Check to see if there is no release date value
            releaseGet = "";
            idx+=1;//Add one to remove the comma
        }
        else{//The date has no quotations and contains 4 numbers EX: 1988
            releaseEnd = line.indexOf(",");//Getting the index of the comma after the release date
            releaseGet = line.substring(0,releaseEnd);//Creating the substring
            idx+=releaseEnd+1;//updating the idx to the index after the end of the release date(idx is added by one for comma)
        }
        return new ParseData(idx, releaseGet);//Return a parse info object containing the parse information for the ReleaseDate
    }
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Purpose - to get the Format from the line
     * @param line - The current line
     * @param idx - the current idx
     * @return - returns a ParseInfo object containing the Format and the updated line index
     */
    private ParseData parseFormat(String line, int idx){
        //Can start with: A letter, A quote, or a comma which means there is no format

        String formatGet;//Creates an empty string that will be initialized with the format and returned
        int formatEnd;//Declares the integer that will represent the index following the end of the format.
        if (line.startsWith("\"")){//Check to see if it starts with a quote
            formatEnd = line.indexOf("\",");//Get the index of the quotation followed by the comma (",) which represents the end of the format value
            formatGet = line.substring(1,formatEnd);//Set the format string (Starting value is one to remove the quotation mark)
            idx+=formatEnd+2;//Get the index after the format value (add two to remove the closing quotation mark and the comma)
        }
        else if (line.startsWith(",")){//Check to see if there is no format value
            formatGet = "";
            idx+=1;//Add one to remove the comma
        }
        else{//The format has no quotations (Starts with a letter
            formatEnd = line.indexOf(",");//Getting the index of the comma after the format
            formatGet = line.substring(0,formatEnd);//Creating the substring
            idx+=formatEnd+1;//updating the idx to the index after the end of the format(idx is added by one for comma)
        }
        return new ParseData(idx, formatGet);//Return a parse info object containing the parse information for the Format
    }
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Purpose - to get the AddDate from the line
     * @param line - The current line
     * @param idx - the current idx
     * @return - returns a ParseInfo object containing the AddDate and the updated line index
     */
    private ParseData parseAddDate(String line, int idx){//Line will always start with a quotation and end with one

        int addEnd = line.indexOf("\",");//Get the index of the quotation followed by the comma (",) which represents the end of the add date value
        String addGet = line.substring(1,addEnd);//Set the add date string (Starting value is one to remove the quoteation mark)
        idx+=addEnd+2;//Get the index after the add date value (add two to remove the closing quotation mark and the comma)

        return new ParseData(idx, addGet);//Return a parse info object containing the parse information for the AddDate
    }
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Purpose - to get the Creators from the line
     * @param line - The current line
     * @param idx - the current idx
     * @return - returns a ParseInfo object containing the Creators and the updated line index
     */
    private ParseData parseCreators(String line, int idx){

        String createGet;//Creates an empty string that will be initialized with the creators and returned

        if (line.equals("")){//Check to see if there are no creators
            createGet = "";//set the string
        }
        else{//There are creators
            createGet = line.substring(1,line.length()-1);//add the rest of the line excluding the parenthesis
        }
        return new ParseData(idx, createGet);//Return a parse info object containing the parse information for the creators
    }

    public Collection<Series> getComiccollection() {
        return comiccollection;
    }

    //------------------------------------------------------------------------------------------------------------------
    public void testPrint(){
        Iterator sI = comiccollection.iterator();
        while(sI.hasNext()){
            System.out.println(sI.next().toString());
        }
    }


}



