/*
    Mackenzie Hamlett
    Evan Smith
    Jacob Cox
    Zachary Kay

    comments to group: Main will only be used to call the ScannerClass and take in the relevant files, please do not put Scanner functions here!

    IMPORTANT: 
    - the only issue is the output puts an extra comma before the closing paren
    - the file is taken in through the user entering the file path from the computer and not assuming the .txt file is added to the program file, I will text yall later and see which yall think is better, not hard to switch to the other.
*/
import java.util.Scanner;
public class App {
    // Change this to true to enable command line args, and not prompt keyboard input
    static boolean useCommandLineArgs = false; 
    public static void main(String[] args) throws Exception {
        String filePath;
        ScannerClass scanFile = new ScannerClass();
        if (!useCommandLineArgs) {
            Scanner keyboard = new Scanner(System.in);

            // scan in the file using users FILE PATH FROM COMPUTER
            System.out.println("Input file path: ");
            filePath = keyboard.nextLine();
            
            keyboard.close();
        } else {
            filePath = args[0]; // Get the cmd line arg 
            System.out.print(filePath);
        }
        if (filePath.contains(".txt")) {
            // Pass it to the actual scanner
            scanFile.scan_ScannerClass(filePath);
        } else {
            System.out.println("error.");
            System.exit(0);
        }
    
    }
}
