package view;

import controller.Command;
import controller.CommandType;

public class PTUI {


    //Constructor
    public PTUI() {
        init();
    }

    /**
     * This method handles any input from the user and sends the information to the App class. The result of the operation will be printed on the
     * screen for the user to see.
     */
    public void handleCommandSelection(CommandType infoMessage){
        //--------------------------------------------------------------------------------------------------------------
        //Get command info messages
        if (infoMessage==CommandType.SEARCH){
            System.out.println("Search the database for a comic...\n" +
                    "1: Exact Search - Will only show comics with content identical to search parameters.\n" +
                    "2: Partial Search - Will show all comics that include the search parameters.");
        }
        else if (infoMessage==CommandType.SEARCHSELECT){
            System.out.println("Select which field you would like to search for...\n" +
                    "1: Series\n" +
                    "2: Issue\n" +
                    "3: Title\n" +
                    "4: Publisher\n" +
                    "5: Add Date\n" +
                    "6: Creator(s)\n");
        }
        else if(infoMessage==CommandType.SEARCHEXACT){
            System.out.println("Exact Search: ");
        }
        else if(infoMessage==CommandType.SEARCHPARTIAL){
            System.out.println("Partial Search: ");
        }
        else if(infoMessage==CommandType.SEARCHCREATORS){
            System.out.println("You can search for a single creator or a collaboration of two or more creators...\n" +
                    "If you wish to search for collaborations, use this format: 'Creator1|Creator2|Creator3|...'");
        }
        else if(infoMessage==CommandType.ADD){
            System.out.println("1: Add From Database - Search through the database and add a comic to your personal collection\n" +
                    "2: Add From Input - Add a comic by typing in all of its information into the corresponding fields");
        }
        else if(infoMessage==CommandType.ADDSELECT){
            System.out.println("1: Select 1 if you already know the comics ID\n" +
                    "2: Select 2 if you wish to browse the Database");
        }
        else if(infoMessage==CommandType.ADDFROMDB){
            System.out.println("Enter the Comic ID of the comic you would like to add to your personal collection\nID:");
        }
        else if(infoMessage==CommandType.ADDFROMINPUT){
            System.out.println("Adding comic manually...\nPlease fill out each field.\nSeries: ");
        }
        else if(infoMessage==CommandType.REMOVE){
            System.out.println("You have selected to remove a comic from your personal collection...");
        }
        else if(infoMessage==CommandType.REMOVESELECT){
            System.out.println("1: Select 1 if you already know the ID of the comic you would like to remove\n" +
                    "2: Select 2 if you wish to browse your Collection");
        }
        else if(infoMessage==CommandType.REMOVECOMPLETE){
            System.out.println("Enter the Comic ID of the comic you would like to remove to your personal collection\nID:");
        }
        else if(infoMessage==CommandType.EDIT){
            System.out.println("Select which field you would like to edit. You will select the comic after...\n" +
                    "1: Series\n" +
                    "2: Issue\n" +
                    "3: Title\n" +
                    "4: Description\n" +
                    "5: Publisher\n" +
                    "6: Release Date\n" +
                    "7: Format\n" +
                    "8: Add Date\n" +
                    "9: Creators");
        }
        else if(infoMessage==CommandType.EDITSELECT){
            System.out.println("1: Select 1 if you already know the comics ID\n" +
                    "2: Select 2 if you wish to browse your collection");
        }
        else if(infoMessage==CommandType.EDITCOMPLETE){
            System.out.println("Enter the ID of the comic you want to edit and the new value for the attribute you want to change in the following format...\n" +
                    "(ID:NEWVALUE)");
        }
        else if(infoMessage==CommandType.MARK){
            System.out.println("1: Grade a comic\n" +
                    "2: Slab a comic");
        }
        else if(infoMessage==CommandType.MARKGRADED){
            System.out.println("Enter the ID of the comic you would like to grade along with the grade value...\n" +
                    "Your entry should match this format: 'ID:GRADEVALUE'");
        }
        else if(infoMessage==CommandType.MARKSLABBED){
            System.out.println("Enter the ID of the comic you would like to slab...");
        }

        else if(infoMessage==CommandType.BROWSE){
            System.out.println("Search through your personal collection...\n" +
                    "1: Entire Collection\n" +
                    "2: Publishers\n" +
                    "3: Series\n" +
                    "4: Volumes\n" +
                    "5: Issues");
        }
        else if(infoMessage==CommandType.BROWSECOLLECTION){
            System.out.println("Displaying your entire collection...");
        }
        else if(infoMessage==CommandType.BROWSEPUBLISHERS){
            System.out.println("Enter the name of the Publisher you would like to search for\n" +
                    "Publisher:");
        }
        else if(infoMessage==CommandType.BROWSESERIES){
            System.out.println("Enter the name of the Series you would like to search for\n" +
                    "Series:");
        }
        else if(infoMessage==CommandType.BROWSEVOLUMES){
            System.out.println("Enter the name of the Volume you would like to search for\n" +
                    "Series:");
        }
        else if(infoMessage==CommandType.BROWSEISSUES){
            System.out.println("Enter the name of the Issue you would like to search for\n" +
                    "Series:");
        }
        else if(infoMessage.equals("7")){

        }
        else if(infoMessage.equals("8")){

        }
        //--------------------------------------------------------------------------------------------------------------
        //Run command info messages
        //else if(infoMessage.substring(0,1).equals("S")){
        //    System.out.println("\nSearch Results: \n" +
        //            "-----------------------------------------------------------------------------------------------------------------------------------"+infoMessage.substring(1));
        //}
        else{
            System.out.println("Invalid Entry\nPlease try again...\n");
        }
    }

    public void display(Command r){
        System.out.println(r.toString());
    }

    private void init(){
        System.out.println("--WELCOME TO COMIX!!!--\n" +
                "A comic book database...\n\n" +
                "Enter the number of the desired command.\nHere are a list of commands:\n" +
                "1: Search Database for Comics\n" +
                "2: Add to Collection\n" +
                "3: Remove from Collection\n" +
                "4: Edit Comic in Collection\n" +
                "5: Mark Comic in Collection\n" +
                "6: Browse Personal Collection\n" +
                "7: Store Profile\n" +
                "8: Close Program\n\n" +
                "INPUT: ");

        //TODO print a list of all the commands the user can use and implement. Command info will be sent back to the App class

    }

}
