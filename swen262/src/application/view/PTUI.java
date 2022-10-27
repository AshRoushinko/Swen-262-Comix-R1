package view;

public class PTUI {


    //Constructor
    public PTUI() {
        init();
    }

    /**
     * This method handles any input from the user and sends the information to the App class. The result of the operation will be printed on the
     * screen for the user to see.
     */
    public void handleCommandSelection(String infoMessage){
        //--------------------------------------------------------------------------------------------------------------
        //Get command info messages
        if (infoMessage.equals("1")){
            System.out.println("1: Exact Search - Will only show comics with a title identical to search parameters.\n" +
                    "2: Partial Search - Will show all comics that include search parameters in title.");
        }
        else if(infoMessage.equals("11")){
            System.out.println("Exact Search: ");
        }
        else if(infoMessage.equals("12")){
            System.out.println("Partial Search: ");
        }
        else if(infoMessage.equals("2")){
            System.out.println("1: Add From Database - Search through the database and add a comic to your personal collection\n" +
                    "2: Add From Input - Add a comic by typing in all of its information into the corresponding fields");
        }
        else if(infoMessage.equals("21")){
            System.out.println("Search the database for a comic...\n" +
                    "1: Exact Search - Will only show comics with a title identical to search parameters.\n" +
                    "2: Partial Search - Will show all comics that include search parameters in title.");
        }
        else if (infoMessage.equals("211")){
            System.out.println("Exact Search: ");
        }
        else if (infoMessage.equals("212")){
            System.out.println("Partial Search: ");
        }
        else if(infoMessage.equals("22")){
            System.out.println("Adding comic manually...\nPlease fill out each field.\nSeries: ");
        }
        else if(infoMessage.equals("3")){

        }
        else if(infoMessage.equals("4")){

        }
        else if(infoMessage.equals("5")){

        }
        else if(infoMessage.equals("6")){

        }
        else if(infoMessage.equals("7")){

        }
        else if(infoMessage.equals("8")){

        }
        //--------------------------------------------------------------------------------------------------------------
        //Run command info messages
        else if(infoMessage.substring(0,1).equals("S")){
            System.out.println("\nSearch Results: \n" +
                    "-----------------------------------------------------------------------------------------------------------------------------------"+infoMessage.substring(1));
        }
        else{
            System.out.println("Invalid Entry\nPlease try again...\n");
        }
    }

    public void display(Result r){
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
