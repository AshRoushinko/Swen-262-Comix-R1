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


    private Stack commandHistory;

    private ComixDatabase db;
    private User user;
    private PTUI view;
    private Scanner input;

    private Boolean running;

    public App(){
        System.out.println("APP STARTED");
    }

    //TODO add a signin command (Store each profile in one csv file) SIGNOUT COMMAND WILL STORE THE PROFILE.

    //TODO add functionality for undoing a command

    public void run(){
        running = true;
        while (running){
            input = new Scanner(System.in);
            CommandType commandType = getCommandType(ForceCommand.NONE);
            String commandArgs = input.nextLine();//Search criteria
            if(commandType==null){
                System.out.println("CommandType is Null");
                running = false;
            }
            //----------------------------------------------------------------------------------------------------------
            //SEARCH
            else if (commandType==CommandType.SEARCHEXACT||commandType==CommandType.SEARCHPARTIAL){
                String searchCriteria = commandArgs;
                Command currCommand = new Search(commandType, searchCriteria, db, user);
                runCommand(currCommand);
            }
            //----------------------------------------------------------------------------------------------------------
            //ADD
            else if (commandType==CommandType.ADDFROMDBEXACT||commandType==CommandType.ADDFROMDBPARTIAL){
                //FIRST RUN A SEARCH TO SHOW ALL OF THE POSSIBLE COMICS TO ADD
                String searchCriteria = commandArgs;
                Command currCommand = new Search(commandType, searchCriteria, db, user);
                runCommand(currCommand);
                //THEN HANDLE ADD COMMAND
                view.handleCommandSelection(CommandType.ADDFROMDB);
                Scanner addSelectionScanner = new Scanner(System.in);
                String addSelection = addSelectionScanner.nextLine();
                currCommand = new AddFromDB(commandType, addSelection, db, user);
                Collection<Comic> comicToAdd = currCommand.run();// This is equal to the comic selected by the user

                //commandHistory.add(currCommand);
            }
            else if (commandType==CommandType.ADDFROMINPUT){
                String searchCriteria = commandArgs;
                Command currCommand = new AddFromInput(commandType, searchCriteria, db, user);
                currCommand.run();
                //view.display(currCommand.getResult());
                //commandHistory.add(currCommand);
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
                Collection<Comic> browseresult = currCommand.run();
            }

            //----------------------------------------------------------------------------------------------------------
            // EDIT
            else if(commandType==CommandType.EDITSERIES||commandType==CommandType.EDITISSUE||commandType==CommandType.EDITTITLE||commandType==CommandType.EDITDESCRIPTION||commandType==CommandType.EDITRELEASEDATE||commandType==CommandType.EDITPUBLISHER||commandType==CommandType.EDITFORMAT||commandType==CommandType.EDITADDDATE||commandType==CommandType.EDITCREATORS){
                if (commandArgs.equals("1")){
                }
                else{
                    CommandType browseInstance = getCommandType(ForceCommand.BROWSE);
                    String searchCriteria;
                    if (browseInstance==CommandType.BROWSECOLLECTION){
                        searchCriteria = "";
                    }
                    else{
                        searchCriteria = commandArgs;
                    }
                    Command currCommand = new Browse(commandType, searchCriteria, db, user);
                    Collection<Comic> browseresult = currCommand.run();
                }
                view.handleCommandSelection(CommandType.EDITCOMPLETE);
                Scanner editScanner = new Scanner(System.in);
                String editCriteria = editScanner.nextLine();
                Command currCommand = new Edit(commandType,editCriteria,db,user);
                Collection<Comic> editResult = currCommand.run();

            }

            //----------------------------------------------------------------------------------------------------------
            //
            else{

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
                view.handleCommandSelection(CommandType.SEARCH);
                String searchTypeInput = input.nextLine();
                if (searchTypeInput.equals("1")){
                    firstE = false;
                    commandCode = CommandType.SEARCHEXACT;
                }
                else if (searchTypeInput.equals("2")){
                    firstE = false;
                    commandCode = CommandType.SEARCHPARTIAL;
                }
                else{
                    commandCode = CommandType.ERROR;
                    view.handleCommandSelection(commandCode);
                }
                view.handleCommandSelection(commandCode);
            }
            //----------------------------------------------------------------------------------------------------------
            //ADD
            else if(userInput.equals("2")){
                view.handleCommandSelection(CommandType.ADD);
                String addTypeInput = input.nextLine();
                firstE = false;
                if (addTypeInput.equals("1")){
                    view.handleCommandSelection(CommandType.SEARCH);
                    addTypeInput = input.nextLine();
                    if (addTypeInput.equals("1")){
                        view.handleCommandSelection(CommandType.SEARCHEXACT);
                        commandCode = CommandType.ADDFROMDBEXACT;
                    }
                    else if (addTypeInput.equals("2")){
                        view.handleCommandSelection(CommandType.SEARCHPARTIAL);
                        commandCode = CommandType.ADDFROMDBPARTIAL;
                    }
                    else{
                        commandCode = CommandType.ERROR;
                    }
                }
                else if (addTypeInput.equals("2")){
                    commandCode = CommandType.ADDFROMINPUT;
                    view.handleCommandSelection(commandCode);
                }
                else{
                    commandCode = CommandType.ERROR;
                    view.handleCommandSelection(commandCode);
                }
            }
            //----------------------------------------------------------------------------------------------------------
            //REMOVE
            else if(userInput.equals("3")){
                firstE = false;
                commandCode = CommandType.REMOVE;
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
                    view.handleCommandSelection(CommandType.GRADE);
                }
                else if (markTypeInput.equals("2")){

                }
                else{
                    commandCode = CommandType.ERROR;
                    view.handleCommandSelection(commandCode);
                }
                //commandCode = CommandType.MARK;
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
            }
            //----------------------------------------------------------------------------------------------------------
            //ERROR
            else{
                firstE = false;
                commandCode = CommandType.ERROR;
                view.handleCommandSelection(commandCode);
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
        //System.out.println("Creating command history stack");
        //commandHistory = new Stack<Command>();
        //System.out.println("Successfully created command history stack");
    }

    public void process(){
        //TODO this method will process the commands sent from the PTUI. Allows for all other methods to be private
    }

}
