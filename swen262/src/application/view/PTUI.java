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
    public void handle(String infoMessage){
        if (infoMessage.equals("1")){
            System.out.println("Search: ");
        }
        else if(infoMessage.equals("2")){

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
        else if(infoMessage.substring(0,1).equals("S")){
            System.out.println("\nSearch Results: \n" +
                    "-----------------------------------------------------------------------------------------------------------------------------------"+infoMessage.substring(1));
        }
        else{
            System.out.println("Invalid Entry\nPlease try again...\n");
        }
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
