package controller;

import model.ComixDatabase;
import model.User;
import view.PTUI;

import java.util.Scanner;
import java.util.Stack;

public class App {

    private final String FILEPATH = "swen262/comics.csv";

    private Stack commandHistory;

    private ComixDatabase db;
    private User user;
    private PTUI view;
    private Scanner input;

    private Boolean running;

    public App(){
    }

    //TODO add a signin command (Store each profile in one csv file) SIGNOUT COMMAND WILL STORE THE PROFILE.

    public void run(){
        running = true;
        while (running){
            input = new Scanner(System.in);
            String commandType = getCommandType();
            String commandArgs = input.nextLine();
            if(commandType.equals("")){
                running = false;
            }
            else if (commandType.equals("11")||commandType.equals("12")){
                String searchCriteria = commandArgs;
                Command currCommand = new Search(commandType, searchCriteria, db.getComicCollection(), user.getComiccollection());
                currCommand.run();
                view.display(currCommand.getResult());
                commandHistory.add(currCommand);
            }
            else{

            }
        }
    }

    private String getCommandType(){
        Boolean firstE = true;
        String userInput = input.nextLine();
        String commandCode = "";
        while (firstE){
            if (userInput.equals("1")){
                view.handleCommandSelection("1");
                String searchTypeInput = input.nextLine();
                if (searchTypeInput.equals("1")){
                    firstE = false;
                    view.handleCommandSelection("11");
                    commandCode = "11";
                }
                else if (searchTypeInput.equals("2")){
                    firstE = false;
                    view.handleCommandSelection("12");
                    commandCode = "12";
                }
                else{
                    view.handleCommandSelection("I");
                    commandCode = "";
                }
            }
            else if(userInput.equals("2")){
                firstE = false;
                commandCode = "2";
            }
            else if(userInput.equals("3")){
                firstE = false;
                commandCode = "3";
            }
            else if(userInput.equals("4")){
                firstE = false;
                commandCode = "4";
            }
            else if(userInput.equals("5")){
                firstE = false;
                commandCode = "5";
            }
            else if(userInput.equals("6")){
                firstE = false;
                commandCode = "6";
            }
            else if(userInput.equals("7")){
                firstE = false;
                commandCode = "7";
            }
            else if(userInput.equals("8")){
                firstE = false;
                commandCode = "";
            }
            else{
                view.handleCommandSelection("I");
            }
        }
        return commandCode;
    }

    public void init(){
        db = new ComixDatabase(FILEPATH);
        //db.display();
        view = new PTUI();
        user = new User();
        commandHistory = new Stack<Command>();
    }

    public void process(){
        //TODO this method will process the commands sent from the PTUI. Allows for all other methods to be private
    }

}
