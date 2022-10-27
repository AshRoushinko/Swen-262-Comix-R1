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
            else if (commandType.equals("211")||commandType.equals("212")){
                //Run a search command so the user can add a comic from the search
                String searchCriteria = commandArgs;
                Command currCommand = new Search(commandType.substring(1), searchCriteria, db.getComicCollection(), user.getComiccollection());
                currCommand.run();
                view.display(currCommand.getResult());
                commandHistory.add(currCommand);

                Scanner addSelectionScanner = new Scanner(System.in);
                String addSelection = addSelectionScanner.nextLine();
                currCommand = new AddFromDB(commandType, addSelection, db.getComicCollection(), user.getComiccollection());
                currCommand.run();
                Result addresult = currCommand.getResult();
                Collection<Comic> comicToAddCF = addresult.getResultCollection();
                Comic comictoAdd = comicToAddCF.iterator().next();
                user.addComicToUser(comictoAdd);
                view.display(addresult);
                commandHistory.add(currCommand);
            }
            else if (commandType.equals("22")){
                String searchCriteria = commandArgs;
                Command currCommand = new AddFromInput(commandType, searchCriteria, db.getComicCollection(), user.getComiccollection());
                currCommand.run();
                view.display(currCommand.getResult());
                commandHistory.add(currCommand);
            }
            else{

            }
        }
    }


    //TODO CAN POSSIBLY MAKE THIS CLEANER
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
                view.handleCommandSelection("2");
                String addTypeInput = input.nextLine();
                firstE = false;
                if (addTypeInput.equals("1")){
                    firstE = false;
                    view.handleCommandSelection("21");
                    addTypeInput = input.nextLine();
                    if (addTypeInput.equals("1")){
                        view.handleCommandSelection("211");
                        commandCode = "211";
                    }
                    else if (addTypeInput.equals("2")){
                        view.handleCommandSelection("212");
                        commandCode = "212";
                    }
                    else{
                        view.handleCommandSelection("I");
                        commandCode = "";
                    }
                }
                else if (addTypeInput.equals("2")){
                    firstE = false;
                    view.handleCommandSelection("22");
                    commandCode = "22";
                }
                else{
                    view.handleCommandSelection("I");
                    commandCode = "";
                }
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
