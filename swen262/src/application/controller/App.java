package controller;

import model.ComixDatabase;
import model.User;
import view.PTUI;

import java.util.*;

public class App {

    private final String FILEPATH = "swen262/comics.csv";

    private ComixDatabase db;
    private User user;
    private PTUI view;
    private Scanner input;

    private final String PASSWORD = "password";

    private Stack<Command> commandHistory;
    private Stack<Command> redoList;

    private Boolean running;

    private UserState userState;

    public App(){
        init();
    }

    //TODO add a signin command (Store each profile in one csv file) SIGNOUT COMMAND WILL STORE THE PROFILE.
    public void run(){
        running = true;
        while (running){
            input = new Scanner(System.in);
            CommandType commandType = getCommandType(ForceCommand.NONE);
            String commandArgs;
            if (commandType==CommandType.BROWSECOLLECTION||commandType==CommandType.BROWSEGRADED||commandType==CommandType.BROWSESLABBED||commandType==CommandType.BROWSESIGNED||commandType==CommandType.BROWSEAUTHENTICATED||commandType==CommandType.BROWSERUNS||commandType==CommandType.BROWSEGAPS){
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
            else if (commandType==CommandType.GUEST){
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
                while(true){
                    view.handleCommandSelection(CommandType.ADDFROMDB);
                    Scanner addSelectionScanner = new Scanner(System.in);
                    String addSelection = addSelectionScanner.nextLine();
                    Command currCommand = new AddFromDB(commandType, addSelection, db, user);
                    runCommand(currCommand);
                    AddFromDB commandAddFromDBCast = (AddFromDB) currCommand;
                    if (commandAddFromDBCast.isAdded()){
                        commandHistory.push(currCommand);
                    }
                    view.handleCommandSelection(CommandType.ADDAGAIN);
                    String againSelection = addSelectionScanner.nextLine();
                    System.out.println(againSelection);
                    if (againSelection.equals("1")){

                    }
                    else{
                        break;
                    }
                }
            }
            else if (commandType==CommandType.ADDFROMINPUT){
                //TODO
                String newComicstr = commandArgs;
                //TODO MOVE THESE PRINT STATEMENTS TO PTUI
                view.handleCommandSelection(CommandType.ADDFROMINPUTISSUE);
                newComicstr = newComicstr+":"+input.nextLine();
                view.handleCommandSelection(CommandType.ADDFROMINPUTTITLE);
                newComicstr = newComicstr+":"+input.nextLine();
                view.handleCommandSelection(CommandType.ADDFROMINPUTDESCRIPTION);
                newComicstr = newComicstr+":"+input.nextLine();
                view.handleCommandSelection(CommandType.ADDFROMINPUTPUBLISHER);
                newComicstr = newComicstr+":"+input.nextLine();
                view.handleCommandSelection(CommandType.ADDFROMINPUTRELEASEDATE);
                newComicstr = newComicstr+":"+input.nextLine();
                view.handleCommandSelection(CommandType.ADDFROMINPUTFORMAT);
                newComicstr = newComicstr+":"+input.nextLine();
                view.handleCommandSelection(CommandType.ADDFROMINPUTADDDATE);
                newComicstr = newComicstr+":"+input.nextLine();
                view.handleCommandSelection(CommandType.ADDFROMINPUTCREATOR);
                newComicstr = newComicstr+":"+input.nextLine();
                Command currCommand = new AddFromInput(commandType, newComicstr, db, user);
                runCommand(currCommand);
                commandHistory.push(currCommand);
            }
            //----------------------------------------------------------------------------------------------------------
            // BROWSE
            else if(commandType==CommandType.BROWSECOLLECTION||commandType==CommandType.BROWSEPUBLISHERS||commandType==CommandType.BROWSESERIES||commandType==CommandType.BROWSEISSUES||commandType==CommandType.BROWSEVOLUMES||commandType==CommandType.BROWSEGRADED||commandType==CommandType.BROWSESLABBED||commandType==CommandType.BROWSESIGNED||commandType==CommandType.BROWSEAUTHENTICATED||commandType==CommandType.BROWSEGAPS||commandType==CommandType.BROWSERUNS){
                String searchCriteria;
                if (commandType==CommandType.BROWSECOLLECTION||commandType==CommandType.BROWSEGRADED||commandType==CommandType.BROWSESLABBED||commandType==CommandType.BROWSESIGNED||commandType==CommandType.BROWSEAUTHENTICATED||commandType==CommandType.BROWSEGAPS||commandType==CommandType.BROWSERUNS){
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
                    String editBrowseCriteria;
                    if (browseInstance==CommandType.BROWSECOLLECTION){
                        editBrowseCriteria = "";
                    }
                    else{
                        editBrowseCriteria = editBrowseSelectionScanner.nextLine();
                    }
                    Command currCommand = new Browse(browseInstance, editBrowseCriteria, db, user);
                    runCommand(currCommand);
                }
                view.handleCommandSelection(CommandType.EDITCOMPLETE);
                Scanner editScanner = new Scanner(System.in);
                String editCriteria = editScanner.nextLine();
                System.out.println(editCriteria);
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
            else if(commandType==CommandType.MARKGRADED||commandType==CommandType.MARKSLABBED||commandType==CommandType.MARKSIGN||commandType==CommandType.MARKAUTHENTICATE){
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
                view.handleCommandSelection(commandType);
                Scanner markScanner = new Scanner(System.in);
                String markCriteria = markScanner.nextLine();
                if (commandType==CommandType.MARKGRADED){
                    while (true){
                        view.handleCommandSelection(CommandType.MARKGRADEDVALUE);
                        String markCriteria2 = markScanner.nextLine();
                        if (markCriteria2.equals("1")||markCriteria2.equals("2")||markCriteria2.equals("3")||markCriteria2.equals("4")||markCriteria2.equals("5")||markCriteria2.equals("6")||markCriteria2.equals("7")||markCriteria2.equals("8")||markCriteria2.equals("9")||markCriteria2.equals("10")){
                            markCriteria = markCriteria+":"+markCriteria2;
                            break;
                        }
                        else{
                            view.handleCommandSelection(CommandType.MARKGRADEDVALUEINVALID);
                        }
                    }
                }
                Command currCommand = new Mark(commandType, markCriteria, db, user);
                runCommand(currCommand);
                Mark commandMarkCast = (Mark) currCommand;
                if (commandMarkCast.isApplied()){
                    commandHistory.push(currCommand);
                }
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
                else if (currCommand.commandType==CommandType.MARKGRADED||currCommand.commandType==CommandType.MARKSLABBED||currCommand.commandType==CommandType.MARKSIGN||currCommand.commandType==CommandType.MARKAUTHENTICATE){
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
                    view.handleCommandSelection(CommandType.UNDOCOMPLETE);
                }
                else{
                    commandHistory.push(currCommand);
                }
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
                else if (currCommand.commandType==CommandType.MARKGRADED||currCommand.commandType==CommandType.MARKSLABBED||currCommand.commandType==CommandType.MARKSIGN||currCommand.commandType==CommandType.MARKAUTHENTICATE){
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
                    view.handleCommandSelection(CommandType.REDOCOMPLETE);
                }
                else{
                    redoList.push(currCommand);
                }
            }
            //----------------------------------------------------------------------------------------------------------
            //SIGNIN
            else if (commandType==CommandType.SIGNIN){
                String signInattempt = commandArgs;
                if (signInattempt.equals(PASSWORD)){
                    userState = UserState.AUTHENTICATED;
                    view.handleCommandSelection(CommandType.SIGNINSUCCESS);
                }
                else{
                    view.handleCommandSelection(CommandType.SIGNINFAIL);
                }
            }
            //----------------------------------------------------------------------------------------------------------
            //GUEST
            else if (commandType==CommandType.GUEST){
                //view.handleCommandSelection(CommandType.);
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
            //----------------------------------------------------------------------------------------------------------
            //CONTINUE
            if (userState==UserState.START){
                view.handleCommandSelection(CommandType.CONTINUESTART);
            }
            else if (userState==UserState.GUEST){
                view.handleCommandSelection(CommandType.CONTINUEGUEST);
            }
            else{
                view.handleCommandSelection(CommandType.CONTINUEAUTHENTICATED);
            }
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
                if (userState==UserState.START){
                    firstE = false;
                    commandCode = CommandType.SIGNIN;
                    view.handleCommandSelection(commandCode);
                }
                else{
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
            }
            //----------------------------------------------------------------------------------------------------------
            //ADD
            else if(userInput.equals("2")){
                firstE = false;
                if (userState==UserState.START){
                    firstE = false;
                    userState = UserState.GUEST;
                    commandCode = CommandType.GUEST;
                    view.handleCommandSelection(commandCode);
                }
                else if (userState==UserState.GUEST){
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
                    else if (browseTypeInput.equals("6")){
                        commandCode = CommandType.BROWSEGRADED;
                    }
                    else if (browseTypeInput.equals("7")){
                        commandCode = CommandType.BROWSESLABBED;
                    }
                    else if (browseTypeInput.equals("8")){
                        commandCode = CommandType.BROWSESIGNED;
                    }
                    else if (browseTypeInput.equals("9")){
                        commandCode = CommandType.BROWSEAUTHENTICATED;
                    }
                    else if (browseTypeInput.equals("10")){
                        commandCode = CommandType.BROWSERUNS;
                    }
                    else if (browseTypeInput.equals("11")){
                        commandCode = CommandType.BROWSEGAPS;
                    }
                    else{
                        commandCode = CommandType.ERROR;
                    }
                    view.handleCommandSelection(commandCode);
                }
                else{
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
            }
            //----------------------------------------------------------------------------------------------------------
            //REMOVE
            else if(userInput.equals("3")){
                if (userState==UserState.START||userState==UserState.GUEST){
                    firstE = false;
                    commandCode = CommandType.CLOSEPROGRAM;
                    view.handleCommandSelection(commandCode);
                }
                else{
                    firstE = false;
                    commandCode = CommandType.REMOVESELECT;
                    view.handleCommandSelection(CommandType.REMOVE);
                    view.handleCommandSelection(commandCode);
                }
            }
            //----------------------------------------------------------------------------------------------------------
            //EDIT
            else if(userInput.equals("4")){
                if (userState==UserState.GUEST){
                    firstE = false;
                    commandCode = CommandType.SIGNIN;
                    view.handleCommandSelection(commandCode);
                }
                else if (userState==UserState.AUTHENTICATED){
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
                else{
                    firstE = false;
                    commandCode = CommandType.ERROR;
                }
            }
            //----------------------------------------------------------------------------------------------------------
            //MARK
            else if(userInput.equals("5")){
                firstE = false;
                if(userState==UserState.START||userState==UserState.GUEST){
                    commandCode = CommandType.ERROR;
                }
                else{
                    view.handleCommandSelection(CommandType.MARK);
                    String markTypeInput = input.nextLine();
                    if(markTypeInput.equals("1")){
                        commandCode = CommandType.MARKGRADED;
                    }
                    else if (markTypeInput.equals("2")){
                        commandCode = CommandType.MARKSLABBED;
                    }
                    else if (markTypeInput.equals("3")){
                        commandCode = CommandType.MARKSIGN;
                    }
                    else if (markTypeInput.equals("4")){
                        commandCode = CommandType.MARKAUTHENTICATE;
                    }
                    else{
                        commandCode = CommandType.ERROR;
                        view.handleCommandSelection(commandCode);
                    }
                    view.handleCommandSelection(CommandType.EDITSELECT);
                }
            }
            //----------------------------------------------------------------------------------------------------------
            //BROWSE
            else if(userInput.equals("6")||forced==ForceCommand.BROWSE){
                if (userState==UserState.START||userState==UserState.GUEST){
                    firstE = false;
                    commandCode = CommandType.ERROR;
                }
                else{
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
                    else if (browseTypeInput.equals("6")){
                        commandCode = CommandType.BROWSEGRADED;
                    }
                    else if (browseTypeInput.equals("7")){
                        commandCode = CommandType.BROWSESLABBED;
                    }
                    else if (browseTypeInput.equals("8")){
                        commandCode = CommandType.BROWSESIGNED;
                    }
                    else if (browseTypeInput.equals("9")){
                        commandCode = CommandType.BROWSEAUTHENTICATED;
                    }
                    else if (browseTypeInput.equals("10")){
                        commandCode = CommandType.BROWSERUNS;
                    }
                    else if (browseTypeInput.equals("11")){
                        commandCode = CommandType.BROWSEGAPS;
                    }
                    else{
                        commandCode = CommandType.ERROR;
                    }
                    view.handleCommandSelection(commandCode);
                }
            }
            //----------------------------------------------------------------------------------------------------------
            //STOREPROFILE
            else if (userInput.equals("7")){
                if (userState==UserState.START||userState==UserState.GUEST){
                    firstE = false;
                    commandCode = CommandType.ERROR;
                }
                else{
                    firstE = false;
                    commandCode = CommandType.STOREPROFILE;
                }
            }
            //----------------------------------------------------------------------------------------------------------
            //CLOSEPROGRAM
            else if(userInput.equals("8")){
                if (userState==UserState.START||userState==UserState.GUEST){
                    firstE = false;
                    commandCode = CommandType.ERROR;
                }
                else{
                    firstE = false;
                    commandCode = CommandType.CLOSEPROGRAM;
                    view.handleCommandSelection(commandCode);
                }
            }
            //----------------------------------------------------------------------------------------------------------
            //UNDO
            else if (userInput.equals("9")){
                firstE = false;
                if (userState==UserState.START||userState==UserState.GUEST){
                    commandCode = CommandType.ERROR;
                }
                else{
                    if (commandHistory.isEmpty()){
                        commandCode= CommandType.ERROR;
                        view.handleCommandSelection(CommandType.UNDOEMPTY);
                    }
                    else{
                        commandCode=CommandType.UNDO;
                        view.handleCommandSelection(commandCode);
                    }
                }
            }
            //----------------------------------------------------------------------------------------------------------
            //REDO
            else if (userInput.equals("10")){
                firstE = false;
                if (userState==UserState.START||userState==UserState.GUEST){
                    commandCode = CommandType.ERROR;
                }
                else{
                    if (redoList.isEmpty()){
                        commandCode= CommandType.ERROR;
                        view.handleCommandSelection(CommandType.REDOEMPTY);
                    }
                    else{
                        commandCode=CommandType.REDO;
                        view.handleCommandSelection(commandCode);
                    }
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
        this.userState = UserState.START;
    }

}
