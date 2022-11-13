import controller.App;

public class Comix {

    public static void main(String[] args) {
        System.out.println("");
        System.out.println("Creating App");
        App comixApp = new App();
        System.out.println("Initializing Comix Application");
        comixApp.init();
        System.out.println("Running Comix Application");
        comixApp.run();
    }
}
