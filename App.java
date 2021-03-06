
/*
    Mackenzie Hamlett
    Evan Smith
    Jacob Cox

    comments to group: Main will only be used to call the ScannerClass and take in the relevant files, please do not put Scanner functions here!

    IMPORTANT: 
    - the only issue is the output puts an extra comma before the closing paren
    - the file is taken in through the user entering the file path from the computer and not assuming the .txt file is added to the program file, I will text yall later and see which yall think is better, not hard to switch to the other.
*/
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        String filePath;
        ScannerClass scanFile = new ScannerClass();

        // Check command line arguments
        try {
            filePath = args[0]; // Get the cmd line arg
            System.out.println(filePath);
        }
        // If no command line arguments, we ask the user to type in the file path
        catch (IndexOutOfBoundsException eBoundsException) {
            Scanner keyboard = new Scanner(System.in);

            // scan in the file using users FILE PATH FROM COMPUTER
            System.out.print("Input file path:");
            filePath = keyboard.nextLine();

            keyboard.close();
        }

        if (filePath.contains(".txt")) {
            // Pass it to the actual scanner
            IDHashMap gHashMap = new IDHashMap();
            String tokenString = scanFile.scan_ScannerClass(filePath, gHashMap);
            System.out.println(tokenString);
            gHashMap.print();
            // Parser parser = new Parser(tokenString, gHashMap);
            Parser parser = new Parser("id-0, assign, expr-1, div, div, write, id-0", gHashMap);
            parser.parse();
    
        } else {
            System.out.println("error.");
            System.exit(0);
        }

    }
}
