package controller;

import model.Database;
import model.User;
import view.PTUI;

import java.util.Scanner;
import java.util.Stack;

public class App {

    private final String FILEPATH = "swen262/comics.csv";

    private Stack commandHistory;

    private Database db;
    private User user;
    private PTUI view;
    private Scanner input;

    private Boolean running;

    public App(){
    }

    public void run(){
        running = true;
        while (running){
            input = new Scanner(System.in);
            String commandType = getCommandType();
            if(commandType.equals("")){
                running = false;
            }
            else if (commandType.equals("11")||commandType.equals("12")){
                String searchCriteria = input.next();
                Command currCommand = new Search(commandType, searchCriteria, db.getComiccollection(), user.getComiccollection());
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
        String userInput = input.next();
        while (firstE){
            if (userInput.equals("1")){
                view.handleCommandSelection("1");
                String searchTypeInput = input.next();
                if (searchTypeInput.equals("1")){
                    firstE = false;
                    view.handleCommandSelection("11");
                    return "11";
                }
                else if (searchTypeInput.equals("2")){
                    firstE = false;
                    view.handleCommandSelection("12");
                    return "12";
                }
                else{
                    view.handleCommandSelection("I");
                    return "";
                }
            }
            else if(userInput.equals("2")){
                firstE = false;
                return "2";
            }
            else if(userInput.equals("3")){
                firstE = false;
                return "3";
            }
            else if(userInput.equals("4")){
                firstE = false;
                return "4";
            }
            else if(userInput.equals("5")){
                firstE = false;
                return "5";
            }
            else if(userInput.equals("6")){
                firstE = false;
                return "6";
            }
            else if(userInput.equals("7")){
                firstE = false;
                return "7";
            }
            else if(userInput.equals("8")){
                firstE = false;
                return "";
            }
            else{
                view.handleCommandSelection("I");
            }
        }
        return "";
    }

    public void init(){
        db = new Database(FILEPATH);
        db.testPrint();
        view = new PTUI();
        user = new User();
        commandHistory = new Stack<Command>();
    }

    public void process(){
        //TODO this method will process the commands sent from the PTUI. Allows for all other methods to be private
    }

}
