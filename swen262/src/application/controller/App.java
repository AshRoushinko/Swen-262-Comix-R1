package controller;

import model.Comic;
import model.ComixDatabase;
import model.User;
import view.PTUI;
import view.Result;
import view.SearchResult;

import java.util.*;

public class App {

    private final String FILEPATH = "swen262/comics.csv";

    private ComixDatabase db;
    private User user;
    private PTUI view;
    private Scanner input;

    private Stack<Command> commandHistory;
    private Stack<Command> redoList;

    private Boolean running;

    public App(){
        init();
    }

    //TODO add a signin command (Store each profile in one csv file) SIGNOUT COMMAND WILL STORE THE PROFILE.

    //TODO add functionality for undoing a command

    public void run(){
        running = true;
        while (running){
            input = new Scanner(System.in);
            CommandType commandType = getCommandType(ForceCommand.NONE);
            String commandArgs;
            if (commandType==CommandType.BROWSECOLLECTION){
                commandArgs = "";
            }
            else if (commandType==CommandType.CLOSEPROGRAM){
                running = false;
                break;
            }
            else if (commandType==CommandType.ERROR){
                commandArgs = "ERROR";
            }
            else if (commandType==CommandType.UNDO||commandType==CommandType.REDO){
                commandArgs = "";
            }
            else{
                commandArgs = input.nextLine();//Search criteria
            }
            if(commandType==null){
                System.out.println("CommandType is Null");
                running = false;
            }
            //----------------------------------------------------------------------------------------------------------
            //SEARCH
            else if (commandType==CommandType.SEARCHSERIES||commandType==CommandType.SEARCHISSUE||commandType==CommandType.SEARCHTITLE||commandType==CommandType.SEARCHPUBLISHER||commandType==CommandType.SEARCHADDDATE||commandType==CommandType.SEARCHCREATORS){
                String commandInfo = "";
                String searchCriteria = commandArgs;
                while(true) {
                    while (true) {
                        commandInfo = "";
                        if (searchCriteria.equals("1")) {
                            commandInfo = commandInfo + "EXACT";
                            if (commandType == CommandType.SEARCHCREATORS) {
                                view.handleCommandSelection(CommandType.SEARCHCREATORS);
                            }
                            view.handleCommandSelection(CommandType.SEARCHEXACT);
                            break;
                        } else if (searchCriteria.equals("2")) {
                            commandInfo = commandInfo + "PARTIAL";
                            if (commandType == CommandType.SEARCHCREATORS) {
                                view.handleCommandSelection(CommandType.SEARCHCREATORS);
                            }
                            view.handleCommandSelection(CommandType.SEARCHPARTIAL);
                            break;
                        } else {
                            view.handleCommandSelection(CommandType.ERROR);
                            view.handleCommandSelection(CommandType.SEARCH);
                            searchCriteria = input.nextLine();
                        }
                    }
                    Scanner searchCriteriaSelectionScanner = new Scanner(System.in);
                    String searchCriteriaSelection = searchCriteriaSelectionScanner.nextLine();
                    commandInfo = commandInfo+":"+searchCriteriaSelection;
                    if (commandInfo.split(":").length>2){
                        view.handleCommandSelection(CommandType.ERROR);
                        view.handleCommandSelection(CommandType.SEARCH);
                        searchCriteria = input.nextLine();
                    }
                    else{
                        break;
                    }
                }
                Command currCommand = new Search(commandType, commandInfo, db, user);
                runCommand(currCommand);
            }
            //----------------------------------------------------------------------------------------------------------
            //ADD
            else if (commandType==CommandType.ADDFROMDB){
                if (commandArgs.equals("1")){
                }
                else{
                    //RUN A SEARCH TO SHOW ALL OF THE POSSIBLE COMICS TO ADD
                    String commandInfo = "";
                    CommandType searchInstance = getCommandType(ForceCommand.SEARCH);
                    Scanner addSearchSelectionScanner = new Scanner(System.in);
                    String addSearchSelection = addSearchSelectionScanner.nextLine();
                    String searchCriteria = addSearchSelection;
                    if (searchCriteria.equals("1")){
                        commandInfo = commandInfo+"EXACT";
                        if (searchInstance==CommandType.SEARCHCREATORS){
                            view.handleCommandSelection(CommandType.SEARCHCREATORS);
                        }
                        view.handleCommandSelection(CommandType.SEARCHEXACT);
                    }
                    else if (searchCriteria.equals("2")){
                        commandInfo = commandInfo+"PARTIAL";
                        if (searchInstance==CommandType.SEARCHCREATORS){
                            view.handleCommandSelection(CommandType.SEARCHCREATORS);
                        }
                        view.handleCommandSelection(CommandType.SEARCHPARTIAL);
                    }
                    else{
                        System.out.print("Invalid entry"); //TODO
                    }
                    Scanner searchCriteriaSelectionScanner = new Scanner(System.in);
                    String searchCriteriaSelection = searchCriteriaSelectionScanner.nextLine();
                    commandInfo = commandInfo+":"+searchCriteriaSelection;
                    System.out.println("CommandInfo: "+commandInfo);
                    System.out.println("Command Type: "+searchInstance);
                    Command currCommand = new Search(searchInstance, commandInfo, db, user);
                    runCommand(currCommand);
                }
                //HANDLE ADD COMMAND
                view.handleCommandSelection(CommandType.ADDFROMDB);
                Scanner addSelectionScanner = new Scanner(System.in);
                String addSelection = addSelectionScanner.nextLine();
                System.out.println("");
                Command currCommand = new AddFromDB(commandType, addSelection, db, user);
                runCommand(currCommand);
                commandHistory.push(currCommand);
            }
            else if (commandType==CommandType.ADDFROMINPUT){
                //TODO
                String newComicstr = commandArgs;
                //TODO MOVE THESE PRINT STATEMENTS TO PTUI
                System.out.println("Issue: ");
                newComicstr = newComicstr+":"+input.nextLine();
                System.out.println("Title: ");
                newComicstr = newComicstr+":"+input.nextLine();
                System.out.println("Description: ");
                newComicstr = newComicstr+":"+input.nextLine();
                System.out.println("Publisher: ");
                newComicstr = newComicstr+":"+input.nextLine();
                System.out.println("Release Date: ");
                newComicstr = newComicstr+":"+input.nextLine();
                System.out.println("Format: ");
                newComicstr = newComicstr+":"+input.nextLine();
                System.out.println("Add Date: ");
                newComicstr = newComicstr+":"+input.nextLine();
                System.out.println("Creator: ");
                newComicstr = newComicstr+":"+input.nextLine();
                Command currCommand = new AddFromInput(commandType, newComicstr, db, user);
                runCommand(currCommand);
                commandHistory.push(currCommand);
            }
            //----------------------------------------------------------------------------------------------------------
            // BROWSE
            else if(commandType==CommandType.BROWSECOLLECTION||commandType==CommandType.BROWSEPUBLISHERS||commandType==CommandType.BROWSESERIES||commandType==CommandType.BROWSEISSUES||commandType==CommandType.BROWSEVOLUMES){
                String searchCriteria;
                if (commandType==CommandType.BROWSECOLLECTION){
                    searchCriteria = "";
                }
                else{
                    searchCriteria = commandArgs;
                }
                Command currCommand = new Browse(commandType, searchCriteria, db, user);
                runCommand(currCommand);
            }

            //----------------------------------------------------------------------------------------------------------
            // EDIT
            else if(commandType==CommandType.EDITSERIES||commandType==CommandType.EDITISSUE||commandType==CommandType.EDITTITLE||commandType==CommandType.EDITDESCRIPTION||commandType==CommandType.EDITRELEASEDATE||commandType==CommandType.EDITPUBLISHER||commandType==CommandType.EDITFORMAT||commandType==CommandType.EDITADDDATE||commandType==CommandType.EDITCREATORS){
                if (commandArgs.equals("1")){
                }
                else{
                    CommandType browseInstance = getCommandType(ForceCommand.BROWSE);
                    Scanner editBrowseSelectionScanner = new Scanner(System.in);
                    String editBrowseSelection = editBrowseSelectionScanner.nextLine();
                    String browseCriteria;
                    if (browseInstance==CommandType.BROWSECOLLECTION){
                        browseCriteria = "";
                    }
                    else{
                        browseCriteria = editBrowseSelection;
                    }
                    Command currCommand = new Browse(browseInstance, browseCriteria, db, user);
                    runCommand(currCommand);
                }
                view.handleCommandSelection(CommandType.EDITCOMPLETE);
                Scanner editScanner = new Scanner(System.in);
                String editCriteria = editScanner.nextLine();
                Command currCommand = new Edit(commandType,editCriteria,db,user);
                runCommand(currCommand);
                commandHistory.push(currCommand);
            }

            //----------------------------------------------------------------------------------------------------------
            //REMOVE
            else if (commandType==CommandType.REMOVESELECT){
                if (commandArgs.equals("1")){
                }
                else{
                    CommandType browseInstance = getCommandType(ForceCommand.BROWSE);
                    Scanner removeBrowseSelectionScanner = new Scanner(System.in);
                    String removeBrowseCriteria;
                    if (browseInstance==CommandType.BROWSECOLLECTION){
                        removeBrowseCriteria = "";

                    }
                    else{
                        removeBrowseCriteria = removeBrowseSelectionScanner.nextLine();
                    }
                    Command currCommand = new Browse(browseInstance, removeBrowseCriteria, db, user);
                    runCommand(currCommand);
                }
                view.handleCommandSelection(CommandType.REMOVECOMPLETE);
                Scanner removeScanner = new Scanner(System.in);
                String removeCriteria = removeScanner.nextLine();
                Command currCommand = new Remove(commandType, removeCriteria, db, user);
                runCommand(currCommand);
                commandHistory.push(currCommand);
            }

            //----------------------------------------------------------------------------------------------------------
            //MARK
            else if(commandType==CommandType.MARKGRADED||commandType==CommandType.MARKSLABBED){
                if (commandArgs.equals("1")){
                }
                else{
                    CommandType browseInstance = getCommandType(ForceCommand.BROWSE);
                    Scanner markBrowseSelectionScanner = new Scanner(System.in);
                    String markBrowseCriteria;
                    if (browseInstance==CommandType.BROWSECOLLECTION){
                        markBrowseCriteria = "";
                    }
                    else{
                        markBrowseCriteria = markBrowseSelectionScanner.nextLine();
                    }
                    Command currCommand = new Browse(browseInstance, markBrowseCriteria, db, user);
                    runCommand(currCommand);
                }
                if (commandType==CommandType.MARKGRADED){
                    view.handleCommandSelection(CommandType.MARKGRADED);
                }
                else{
                    view.handleCommandSelection(CommandType.MARKSLABBED);
                }
                Scanner markScanner = new Scanner(System.in);
                String markCriteria = markScanner.nextLine();
                Command currCommand = new Mark(commandType, markCriteria, db, user);
                runCommand(currCommand);
                commandHistory.push(currCommand);
            }

            //----------------------------------------------------------------------------------------------------------
            //STORE PROFILE


            //----------------------------------------------------------------------------------------------------------
            //UNDO
            else if (commandType==CommandType.UNDO){
                Command currCommand = commandHistory.pop();
                if (currCommand.commandType==CommandType.ADDFROMINPUT||currCommand.commandType==CommandType.ADDFROMDB){
                    view.handleCommandSelection(CommandType.UNDOADD);
                }
                else if (currCommand.commandType==CommandType.EDITSERIES||currCommand.commandType==CommandType.EDITISSUE||currCommand.commandType==CommandType.EDITTITLE||currCommand.commandType==CommandType.EDITDESCRIPTION||currCommand.commandType==CommandType.EDITRELEASEDATE||currCommand.commandType==CommandType.EDITFORMAT||currCommand.commandType==CommandType.EDITPUBLISHER||currCommand.commandType==CommandType.EDITADDDATE||currCommand.commandType==CommandType.EDITCREATORS){
                    view.handleCommandSelection(CommandType.UNDOEDIT);
                }
                else if (currCommand.commandType==CommandType.REMOVESELECT){
                    view.handleCommandSelection(CommandType.UNDOREMOVE);
                }
                else if (currCommand.commandType==CommandType.MARKGRADED||currCommand.commandType==CommandType.MARKSLABBED){
                    view.handleCommandSelection(CommandType.UNDOMARK);
                }
                else{

                }
                view.handleCommandSelection(CommandType.UNDOCONFIRM);
                Scanner undoSelectScanner = new Scanner(System.in);
                String undoInput = undoSelectScanner.nextLine();
                if (undoInput.equals("1")){
                    currCommand.undo();
                    redoList.push(currCommand);
                }
                else{
                    commandHistory.push(currCommand);
                }
                view.handleCommandSelection(CommandType.UNDOCOMPLETE);
            }
            //----------------------------------------------------------------------------------------------------------
            //REDO
            else if (commandType==CommandType.REDO){
                Command currCommand = redoList.pop();
                if (currCommand.commandType==CommandType.ADDFROMINPUT||currCommand.commandType==CommandType.ADDFROMDB){
                    view.handleCommandSelection(CommandType.REDOADD);
                }
                else if (currCommand.commandType==CommandType.EDITSERIES||currCommand.commandType==CommandType.EDITISSUE||currCommand.commandType==CommandType.EDITTITLE||currCommand.commandType==CommandType.EDITDESCRIPTION||currCommand.commandType==CommandType.EDITRELEASEDATE||currCommand.commandType==CommandType.EDITFORMAT||currCommand.commandType==CommandType.EDITPUBLISHER||currCommand.commandType==CommandType.EDITADDDATE||currCommand.commandType==CommandType.EDITCREATORS){
                    view.handleCommandSelection(CommandType.REDOEDIT);
                }
                else if (currCommand.commandType==CommandType.REMOVESELECT){
                    view.handleCommandSelection(CommandType.REDOREMOVE);
                }
                else if (currCommand.commandType==CommandType.MARKGRADED||currCommand.commandType==CommandType.MARKSLABBED){
                    view.handleCommandSelection(CommandType.REDOMARK);
                }
                else{

                }
                view.handleCommandSelection(CommandType.REDOCONFIRM);
                Scanner redoSelectScanner = new Scanner(System.in);
                String redoInput = redoSelectScanner.nextLine();
                if (redoInput.equals("1")){
                    currCommand.run();
                    commandHistory.push(currCommand);
                }
                else{
                    redoList.push(currCommand);
                }
                view.handleCommandSelection(CommandType.REDOCOMPLETE);
            }

            //----------------------------------------------------------------------------------------------------------
            //ERROR
            else if (commandType==CommandType.ERROR){
                view.handleCommandSelection(CommandType.ERROR);
            }


            else{
                System.out.println("Command type not recognized\n" +
                        "COMMAND TYPE: "+commandType);
            }
            view.handleCommandSelection(CommandType.CONTINUE);
        }
    }

    //TODO Handle Error command code
    private CommandType getCommandType(ForceCommand forced){
        Boolean firstE = true;
        String userInput;
        if (forced==ForceCommand.NONE){
             userInput = input.nextLine();
        }
        else{
            userInput = "forced";
        }
        CommandType commandCode = null;
        while (firstE){
            //----------------------------------------------------------------------------------------------------------
            //SEARCH
            if (userInput.equals("1")||forced==ForceCommand.SEARCH){
                view.handleCommandSelection(CommandType.SEARCHSELECT);
                String searchTypeInput = input.nextLine();
                if (searchTypeInput.equals("1")){
                    firstE = false;
                    commandCode = CommandType.SEARCHSERIES;
                }
                else if (searchTypeInput.equals("2")){
                    firstE = false;
                    commandCode = CommandType.SEARCHISSUE;
                }
                else if (searchTypeInput.equals("3")){
                    firstE = false;
                    commandCode = CommandType.SEARCHTITLE;
                }
                else if (searchTypeInput.equals("4")){
                    firstE = false;
                    commandCode = CommandType.SEARCHPUBLISHER;
                }
                else if (searchTypeInput.equals("5")){
                    firstE = false;
                    commandCode = CommandType.SEARCHADDDATE;
                }
                else if (searchTypeInput.equals("6")){
                    firstE = false;
                    commandCode = CommandType.SEARCHCREATORS;
                }
                else{
                    commandCode = CommandType.ERROR;
                    view.handleCommandSelection(commandCode);
                }
                if (!(commandCode==CommandType.ERROR)){
                    view.handleCommandSelection(CommandType.SEARCH);
                }
            }
            //----------------------------------------------------------------------------------------------------------
            //ADD
            else if(userInput.equals("2")){
                firstE = false;
                while(true){
                    view.handleCommandSelection(CommandType.ADD);
                    String addTypeInput = input.nextLine();
                    if (addTypeInput.equals("1")){
                        commandCode = CommandType.ADDFROMDB;
                        view.handleCommandSelection(CommandType.ADDSELECT);
                        break;
                    }
                    else if (addTypeInput.equals("2")){
                        commandCode = CommandType.ADDFROMINPUT;
                        view.handleCommandSelection(commandCode);
                        break;
                    }
                    else{
                        commandCode = CommandType.ERROR;
                        view.handleCommandSelection(commandCode);
                    }
                }
            }
            //----------------------------------------------------------------------------------------------------------
            //REMOVE
            else if(userInput.equals("3")){
                firstE = false;
                commandCode = CommandType.REMOVESELECT;
                view.handleCommandSelection(CommandType.REMOVE);
                view.handleCommandSelection(commandCode);
            }
            //----------------------------------------------------------------------------------------------------------
            //EDIT
            else if(userInput.equals("4")){
                firstE = false;
                view.handleCommandSelection(CommandType.EDIT);
                String editTypeInput = input.nextLine();
                if (editTypeInput.equals("1")){
                    commandCode = CommandType.EDITSERIES;
                }
                else if (editTypeInput.equals("2")){
                    commandCode = CommandType.EDITISSUE;
                }
                else if (editTypeInput.equals("3")){
                    commandCode = CommandType.EDITTITLE;
                }
                else if (editTypeInput.equals("4")){
                    commandCode = CommandType.EDITDESCRIPTION;
                }
                else if (editTypeInput.equals("5")){
                    commandCode = CommandType.EDITPUBLISHER;
                }
                else if (editTypeInput.equals("6")){
                    commandCode = CommandType.EDITRELEASEDATE;
                }
                else if (editTypeInput.equals("7")){
                    commandCode = CommandType.EDITFORMAT;
                }
                else if (editTypeInput.equals("8")){
                    commandCode = CommandType.EDITADDDATE;
                }
                else if (editTypeInput.equals("9")){
                    commandCode = CommandType.EDITCREATORS;
                }
                else{
                    commandCode = CommandType.ERROR;
                    view.handleCommandSelection(commandCode);
                }
                view.handleCommandSelection(CommandType.EDITSELECT);

            }
            //----------------------------------------------------------------------------------------------------------
            //MARK
            else if(userInput.equals("5")){
                firstE = false;
                view.handleCommandSelection(CommandType.MARK);
                String markTypeInput = input.nextLine();
                if(markTypeInput.equals("1")){
                    commandCode = CommandType.MARKGRADED;
                }
                else if (markTypeInput.equals("2")){
                    commandCode = CommandType.MARKSLABBED;
                }
                else{
                    commandCode = CommandType.ERROR;
                    view.handleCommandSelection(commandCode);
                }
                view.handleCommandSelection(CommandType.EDITSELECT);
            }
            //----------------------------------------------------------------------------------------------------------
            //BROWSE
            else if(userInput.equals("6")||forced==ForceCommand.BROWSE){
                firstE = false;
                view.handleCommandSelection(CommandType.BROWSE);
                String browseTypeInput = input.nextLine();
                if (browseTypeInput.equals("1")){
                    commandCode = CommandType.BROWSECOLLECTION;
                }
                else if (browseTypeInput.equals("2")) {
                    commandCode = CommandType.BROWSEPUBLISHERS;
                }
                else if (browseTypeInput.equals("3")){
                    commandCode = CommandType.BROWSESERIES;
                }
                else if (browseTypeInput.equals("4")){
                    commandCode = CommandType.BROWSEVOLUMES;
                }
                else if (browseTypeInput.equals("5")){
                    commandCode = CommandType.BROWSEISSUES;
                }
                else{
                    commandCode = CommandType.ERROR;
                }
                view.handleCommandSelection(commandCode);
            }
            //----------------------------------------------------------------------------------------------------------
            //STOREPROFILE
            else if(userInput.equals("7")){
                firstE = false;
                commandCode = CommandType.STOREPROFILE;
            }
            //----------------------------------------------------------------------------------------------------------
            //CLOSEPROGRAM
            else if(userInput.equals("8")){
                firstE = false;
                commandCode = CommandType.CLOSEPROGRAM;
                view.handleCommandSelection(commandCode);
            }
            //----------------------------------------------------------------------------------------------------------
            //UNDO
            else if (userInput.equals("9")){
                firstE = false;
                if (commandHistory.isEmpty()){
                    commandCode= CommandType.ERROR;
                    view.handleCommandSelection(CommandType.UNDOEMPTY);
                }
                else{
                    commandCode=CommandType.UNDO;
                    view.handleCommandSelection(commandCode);
                }
            }
            //----------------------------------------------------------------------------------------------------------
            //REDO
            else if (userInput.equals("10")){
                firstE = false;
                if (redoList.isEmpty()){
                    commandCode= CommandType.ERROR;
                    view.handleCommandSelection(CommandType.REDOEMPTY);
                }
                else{
                    commandCode=CommandType.REDO;
                    view.handleCommandSelection(commandCode);
                }
            }
            //----------------------------------------------------------------------------------------------------------
            //ERROR
            else{
                firstE = false;
                commandCode = CommandType.ERROR;
            }
        }
        if (commandCode==null){
            System.out.println("COMMAND CODE IS NULL");
        }
        return commandCode;
    }

    private void runCommand(Command command){
        command.run();
        view.display(command);
    }

    public void init(){
        db = new ComixDatabase(FILEPATH);
        view = new PTUI();
        user = new User();
        commandHistory = new Stack<Command>();
        redoList = new Stack<Command>();
    }

}
