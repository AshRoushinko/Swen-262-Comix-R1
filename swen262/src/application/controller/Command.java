package controller;

import model.Comic;
import model.ComixDatabase;
import model.Series;
import model.User;
import view.Result;

import java.util.Collection;

//Purpose - An abstract class that defines necessary functionality for commands. (Uses the Command Pattern)
public abstract class Command {

    public String info;
    public CommandType commandType;
    public ComixDatabase db;
    public User uc;

    //Constructor
    /**
     * @param commandType - An enum which represents subtypes of commands
     * @param info - A String that contains information required to process the command
     * @param db - A reference to the Database Object
     * @param uc - A reference to the User object
     */
    public Command(CommandType commandType, String info, ComixDatabase db, User uc){
        this.commandType = commandType;
        this.info = info;
        this.db = db;
        this.uc = uc;
    }

    /**
     * Purpose - This command is only used for certain scenarios where data must be initialized before the command is processed
     * @param commandType - See constructor
     * @param info - See constructor
     * @param db - See constructor
     * @param uc - See constructor
     */
    public abstract void init(CommandType commandType, String info, ComixDatabase db, User uc);

    /**
     * Purpose - This method runs the command and performs operations based on the constructor parameters and type of command
     * @return - Each command involves one or more comics; This returns a collection of all comics involved in the process
     */
    public abstract Collection<Comic> run();

    /**
     * Purpose - Provides the Collection found from running the command
     * @return
     */
    public abstract Collection<Comic> getCollection();

    /**
     * Purpose - This method interacts with result classes which follows the visitor pattern; It's main purpose is to generate
     *           a string that displays the results of running the command
     * @param result - Visitor
     * @return - Returns a string which represents the result of the command
     */
    public abstract String getResult(Result result);

    /**
     * Purpose - This method will undo the command
     * @return - Returns a string //TODO
     */
    public abstract String undo();

    /**
     * Purpose - to provide the subtype of the command; used outside of this class
     * @return - Enum CommandType
     */
    public CommandType getCommandType(){
        return this.commandType;
    }

    /**
     * Purpose - Stores a string that represents the commands result
     * @param s - The result string
     */
    public abstract void setResultString(String s);

    /**
     * This gets the result string
     * @return - Returns the result string
     */
    public abstract String toString();
}
