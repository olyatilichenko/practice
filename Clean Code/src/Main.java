
import java.io.FileNotFoundException;


public class Main {

    public static void main(String[] args) {
        try {
            Interface.choice();
        } catch (FileNotFoundException ex) {
            System.out.println("Error! File not found.");
        }
    }
}
