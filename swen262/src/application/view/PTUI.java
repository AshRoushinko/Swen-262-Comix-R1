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
        else if(infoMessage==CommandType.ADDFROMINPUTISSUE){
            System.out.println("ISSUE:");
        }
        else if(infoMessage==CommandType.ADDFROMINPUTTITLE){
            System.out.println("TITLE:");
        }
        else if(infoMessage==CommandType.ADDFROMINPUTDESCRIPTION){
            System.out.println("DESCRIPTION:");
        }
        else if(infoMessage==CommandType.ADDFROMINPUTPUBLISHER){
            System.out.println("PUBLISHER: ");
        }
        else if(infoMessage==CommandType.ADDFROMINPUTRELEASEDATE){
            System.out.println("RELEASE DATE: ");
        }
        else if(infoMessage==CommandType.ADDFROMINPUTFORMAT){
            System.out.println("FORMAT: ");
        }
        else if(infoMessage==CommandType.ADDFROMINPUTADDDATE){
            System.out.println("ADD DATE: ");
        }
        else if(infoMessage==CommandType.ADDFROMINPUTCREATOR){
            System.out.println("CREATOR(s): ");
        }
        else if (infoMessage==CommandType.ADDAGAIN){
            System.out.println("1: Add another\n" +
                    "2: Continue");
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
                    "2: Slab a comic\n" +
                    "3: Sign a comic\n" +
                    "4: Authenticate a comic");
        }
        else if(infoMessage==CommandType.MARKGRADEDVALUE){
            System.out.println("ENTER A GRADE VALUE (1-10):");
        }
        else if(infoMessage==CommandType.MARKGRADEDVALUEINVALID){
            System.out.println("INVALID ENTRY...\nMust be a number 1-10");
        }
        else if(infoMessage==CommandType.MARKGRADED){
            System.out.println("Enter the ID of the comic you would like to grade:");
        }
        else if(infoMessage==CommandType.MARKSLABBED){
            System.out.println("Enter the ID of the comic you would like to slab:");
        }
        else if(infoMessage==CommandType.MARKSIGN){
            System.out.println("Enter the ID of the comic you would like to sign:");
        }
        else if(infoMessage==CommandType.MARKAUTHENTICATE){
            System.out.println("Enter the ID of the comic you would like to authenticate:");
        }
        else if(infoMessage==CommandType.BROWSE){
            System.out.println("""
                    Search through your personal collection...
                    1: Entire Collection
                    2: Publishers
                    3: Series
                    4: Volumes
                    5: Issues
                    6: Graded Comics
                    7: Slabbed Comics
                    8: Signed Comics
                    9: Authenticated Comics
                    10: Runs
                    11: Gaps""");
        }
        else if(infoMessage==CommandType.BROWSECOLLECTION){
            System.out.println("Displaying your entire collection...");
        }
        else if(infoMessage==CommandType.BROWSEGRADED){
            System.out.println("Displaying all graded comics in your collection...");
        }
        else if(infoMessage==CommandType.BROWSESLABBED){
            System.out.println("Displaying all slabbed comics in your collection...");
        }
        else if(infoMessage==CommandType.BROWSESIGNED){
            System.out.println("Displaying all signed comics in your collection...");
        }
        else if(infoMessage==CommandType.BROWSEAUTHENTICATED){
            System.out.println("Displaying all authenticated comics in your collection...");
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
            System.out.println("Enter the number of the Volume you would like to search for\n" +
                    "Volume:");
        }
        else if(infoMessage==CommandType.BROWSEISSUES){
            System.out.println("Enter the name of the Issue you would like to search for\n" +
                    "Issue:");
        }
        else if(infoMessage==CommandType.BROWSERUNS){
            System.out.println("Displaying all Runs in your collection...");
        }
        else if(infoMessage==CommandType.BROWSEGAPS){
            System.out.println("Displaying all Gaps in your collection...");
        }
        else if(infoMessage.equals("7")){

        }
        else if(infoMessage==CommandType.CLOSEPROGRAM){
            System.out.println("STOPPING PROGRAM..");
        }
        else if (infoMessage==CommandType.UNDOEMPTY){
            System.out.println("No commands to undo");
        }
        else if (infoMessage==CommandType.UNDO){
            System.out.println("Loading last command...");
        }
        else if (infoMessage==CommandType.UNDOADD){
            System.out.println("Last undo-able command was an ADD command...\n" +
                    "Are you sure you want to undo?");
        }
        else if (infoMessage==CommandType.UNDOREMOVE){
            System.out.println("Last undo-able command was a REMOVE command...\n" +
                    "Are you sure you want to undo?");
        }
        else if (infoMessage==CommandType.UNDOEDIT){
            System.out.println("Last undo-able command was a EDIT command...\n" +
                    "Are you sure you want to undo?");
        }
        else if (infoMessage==CommandType.UNDOMARK){
            System.out.println("Last undo-able command was a MARK command...\n" +
                    "Are you sure you want to undo?");
        }
        else if (infoMessage==CommandType.UNDOCONFIRM){
            System.out.println("1: Confirm\n" +
                    "2: CANCEL");
        }
        else if (infoMessage==CommandType.UNDOCOMPLETE){
            System.out.println("Command successfully undone");
        }
        else if (infoMessage==CommandType.REDOEMPTY){
            System.out.println("No commands to redo");
        }
        else if (infoMessage==CommandType.REDO){
            System.out.println("Loading last command...");
        }
        else if (infoMessage==CommandType.REDOADD){
            System.out.println("Last redo-able command was an ADD command...\n" +
                    "Are you sure you want to undo?");
        }
        else if (infoMessage==CommandType.REDOREMOVE){
            System.out.println("Last redo-able command was a REMOVE command...\n" +
                    "Are you sure you want to undo?");
        }
        else if (infoMessage==CommandType.REDOEDIT){
            System.out.println("Last redo-able command was a EDIT command...\n" +
                    "Are you sure you want to undo?");
        }
        else if (infoMessage==CommandType.REDOMARK){
            System.out.println("Last redo-able command was a MARK command...\n" +
                    "Are you sure you want to undo?");
        }
        else if (infoMessage==CommandType.REDOCONFIRM){
            System.out.println("1: Confirm\n" +
                    "2: CANCEL");
        }
        else if (infoMessage==CommandType.REDOCOMPLETE){
            System.out.println("Command successfully redone");
        }
        else if(infoMessage==CommandType.CONTINUEAUTHENTICATED){
            System.out.println("""
                    1: Search Database for Comics
                    2: Add to Collection
                    3: Remove from Collection
                    4: Edit Comic in Collection
                    5: Mark Comic in Collection
                    6: Browse Personal Collection
                    7: Store Profile
                    8: Close Program
                    9: Undo Last Command
                    10: Redo Last Command

                    INPUT:\s""");
        }
        else if(infoMessage==CommandType.CONTINUEGUEST){
            System.out.println("""
                    1: Search Database for Comics
                    2: Browse Personal Collection
                    3: Close Program
                    4: Sign in

                    INPUT:\s""");
        }
        else if(infoMessage==CommandType.CONTINUESTART){
            System.out.println("""
                    Enter the number of the desired command.
                    Here are a list of commands:
                    1: Sign in
                    2: Continue as Guest
                    3: Close Program
                    
                    INPUT:\s""");
        }
        else if (infoMessage==CommandType.NOTSIGNIN){
            System.out.println("You must be signed in to use this command...");
        }
        else if (infoMessage==CommandType.SIGNIN){
            System.out.println("PASSWORD:");
        }
        else if (infoMessage==CommandType.SIGNINSUCCESS){
            System.out.println("WELCOME!!!");
        }
        else if (infoMessage==CommandType.SIGNINFAIL){
            System.out.println("Password incorrect...");
        }
        else if (infoMessage==CommandType.GUEST){
            System.out.println("Signing in as guest...");
        }
        //--------------------------------------------------------------------------------------------------------------
        //Run command info messages
        //else if(infoMessage.substring(0,1).equals("S")){
        //    System.out.println("\nSearch Results: \n" +
        //            "-----------------------------------------------------------------------------------------------------------------------------------"+infoMessage.substring(1));
        //}
        else if (infoMessage==CommandType.START){
            System.out.println("""
                    APP STARTED...
                    --WELCOME TO COMIX!!!--
                    A comic book database...
                    
                    Enter the number of the desired command.
                    Here are a list of commands:
                    1: Sign in
                    2: Continue as Guest
                    3: Close Program
                    
                    INPUT:\s""");
        }
        else{
            System.out.println("Invalid Entry\nPlease try again...\n");
        }
    }

    public void display(Command r){
        System.out.println(r.toString());
    }

    private void init(){
        handleCommandSelection(CommandType.START);

        //TODO print a list of all the commands the user can use and implement. Command info will be sent back to the App class

    }

}
