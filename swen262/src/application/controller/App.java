package controller;

import model.Comic;
import model.ComixDatabase;
import model.User;
import view.PTUI;
import view.Result;

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
        System.out.println("App Created");
    }

    //TODO add a signin command (Store each profile in one csv file) SIGNOUT COMMAND WILL STORE THE PROFILE.

    public void run(){
        System.out.println("Application succesfully Started");
        System.out.println("Setting running value to true");
        running = true;
        System.out.println("Beginning Operations");
        while (running){
            System.out.println("Creating Scanner for user input");
            input = new Scanner(System.in);
            System.out.println("Input variable succesfully created");
            System.out.println("Getting command type");
            CommandType commandType = getCommandType(ForceCommand.NONE);
            System.out.println("Succesfully retrieved Command type");
            System.out.println("Asking user for command arguments");
            String commandArgs = input.nextLine();//Search criteria
            System.out.println("Command args: "+commandArgs);
            if(commandType==null){
                System.out.println("CommandType is Null");
                running = false;
            }
            //----------------------------------------------------------------------------------------------------------
            //SEARCH
            else if (commandType==CommandType.SEARCHEXACT||commandType==CommandType.SEARCHPARTIAL){
                String searchCriteria = commandArgs;
                Command currCommand = new Search(commandType, searchCriteria, db, user);
                currCommand.run();
                view.display(currCommand.getResult());
                //commandHistory.add(currCommand);
            }
            //----------------------------------------------------------------------------------------------------------
            //ADD
            else if (commandType==CommandType.ADDFROMDBEXACT||commandType==CommandType.ADDFROMDBPARTIAL){
                //Run a search command so the user can add a comic from the search
                String searchCriteria = commandArgs;
                Command currCommand = new Search(commandType, searchCriteria, db, user);
                currCommand.run();
                view.display(currCommand.getResult());
                //commandHistory.add(currCommand);
                System.out.println("Succesfully ran a search with the given criteria");
                System.out.println("Asking the user for the id of the comic they want to add");
                view.handleCommandSelection(CommandType.ADDFROMDB);
                System.out.println("Succesfully asked the user for the comic id");
                System.out.println("Creating scanner to retrieve the comic ID");
                //IF THIS DOESNT WORK JUST TRY USING THE SCANNER THATS ALREADY INITIALIZED
                Scanner addSelectionScanner = new Scanner(System.in);
                System.out.println("SCANNER SUCCESFULLY CREATED");
                System.out.println("CREATING STRING THAT WILL BE ASSIGNED TO THE USERS INPUT FOT THE COMIC ID");
                String addSelection = addSelectionScanner.nextLine();
                System.out.println("USER INPUT/COMIC ID: "+addSelection);
                System.out.println("SETTING CURRENT COMMAND TO A NEW ADDFROMDB COMMAND");
                currCommand = new AddFromDB(commandType, addSelection, db, user);
                System.out.println("SUCCESSFULLY CREATED ADDFROMDB COMMAND");
                System.out.println("RUNNING COMMAND");
                Collection<Comic> comicToAdd = currCommand.run();// This is equal to the comic selected by the user
                System.out.println("COMMAND WAS SUCCESSFULL");

                //Result addresult = currCommand.getResult();
                //Collection<Comic> comicToAddCF = addresult.getResultCollection();
                //Comic comictoAdd = comicToAddCF.iterator().next();
                //user.addComicToUser(comictoAdd);
                //view.display(addresult);
                //commandHistory.add(currCommand);
            }
            else if (commandType==CommandType.ADDFROMINPUT){
                String searchCriteria = commandArgs;
                Command currCommand = new AddFromInput(commandType, searchCriteria, db, user);
                currCommand.run();
                view.display(currCommand.getResult());
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
        System.out.println("Create boolean to check if command type has been decided");
        Boolean firstE = true;
        System.out.println("Boolean Created");
        System.out.println("Creating String based off users next input");
        String userInput;
        if (forced==ForceCommand.NONE){
             userInput = input.nextLine();
        }
        else{
            userInput = "forced";
        }
        System.out.println("Declare a commandtype enum to return once initialized");
        CommandType commandCode = null;
        System.out.println("enum declared");
        while (firstE){
            System.out.println("Check the user input to determine the next prompt");
            //----------------------------------------------------------------------------------------------------------
            //SEARCH
            if (userInput.equals("1")){
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
                }
                view.handleCommandSelection(commandCode);
            }
            //----------------------------------------------------------------------------------------------------------
            //ADD
            else if(userInput.equals("2")){
                System.out.println("User Selected Add command");
                view.handleCommandSelection(CommandType.ADD);
                System.out.println("Printed Add Command prompt");
                System.out.println("Getting users input for the type of add command");
                String addTypeInput = input.nextLine();
                System.out.println("User input: "+ addTypeInput);
                firstE = false;
                if (addTypeInput.equals("1")){
                    System.out.println("User selected add from database");
                    System.out.println("Sending search prompt to user");
                    view.handleCommandSelection(CommandType.SEARCH);
                    addTypeInput = input.nextLine();
                    System.out.println("User input: "+ addTypeInput);
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
            else if(userInput.equals("3")){
                firstE = false;
                commandCode = CommandType.REMOVE;
            }
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
            else if(userInput.equals("7")){
                firstE = false;
                commandCode = CommandType.STOREPROFILE;
            }
            else if(userInput.equals("8")){
                firstE = false;
                commandCode = CommandType.CLOSEPROGRAM;
            }
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

    public void init(){
        System.out.println("Creating Database Object");
        db = new ComixDatabase(FILEPATH);
        System.out.println("Database Succesfully Created");
        System.out.println("Creating PTUI");
        view = new PTUI();
        System.out.println("Successfully created PTUI");
        System.out.println("Creating User Object");
        user = new User();
        System.out.println("Successfully Created User Object");
        //System.out.println("Creating command history stack");
        //commandHistory = new Stack<Command>();
        //System.out.println("Successfully created command history stack");
    }

    public void process(){
        //TODO this method will process the commands sent from the PTUI. Allows for all other methods to be private
    }

}
