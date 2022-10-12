package controller;

import model.Database;
import model.User;
import view.PTUI;

import java.util.Scanner;

public class App {

    private final String filePath = "swen262/comics.csv";

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
            else{
                //Command currCommand = new Command(commandType, input.next(), db.getComiccollection());
                //view.handle(currCommand.run());
            }
        }
    }

    private String getCommandType(){
        Boolean firstE = true;
        String s1 = input.next();
        System.out.println(s1);
        while (firstE){
            if (s1.equals("1")){
                firstE = false;
                view.handle("1");
                return "1";
            }
            else if(s1.equals("2")){
                firstE = false;
                return "2";
            }
            else if(s1.equals("3")){
                firstE = false;
                return "3";
            }
            else if(s1.equals("4")){
                firstE = false;
                return "4";
            }
            else if(s1.equals("5")){
                firstE = false;
                return "5";
            }
            else if(s1.equals("6")){
                firstE = false;
                return "6";
            }
            else if(s1.equals("7")){
                firstE = false;
                return "7";
            }
            else if(s1.equals("8")){
                firstE = false;
                return "";
            }
            else{
                view.handle("I");
            }
        }
        return "";
    }

    public void init(){
        db = new Database(filePath);
        view = new PTUI();
        user = new User();
    }

    public void process(){
        //TODO this method will process the commands sent from the PTUI. Allows for all other methods to be private
    }

}
