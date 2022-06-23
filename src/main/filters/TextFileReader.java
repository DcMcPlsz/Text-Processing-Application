
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

import akka.actor.UntypedActor;

public class TextFileReader extends UntypedActor {

    public void onReceive(File fileObject) {
        if (fileObject instanceof File) {
            try {
                // File fileObject = new File("FileToBeRead.txt");
                Scanner fileReader = new Scanner(fileObject);
                while (fileReader.hasNextLine()) {
                    String data = fileReader.nextLine();
                    System.out.println(data);
                }
                fileReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else
            throw new IllegalArgumentException();

    }

    @Override
    public void onReceive(Object arg0) throws Throwable {
        // TODO Auto-generated method stub

    }

    public static void main(String[] args) {
        TextFileReader actor = new TextFileReader();
        File fileObject = new File("FileToBeRead.txt");
        actor.onReceive(fileObject);
        // // try {
        // // Scanner fileReader = new Scanner(fileObject);
        // // while (fileReader.hasNextLine()) {
        // // String data = fileReader.nextLine();
        // // System.out.println(data);
        // // }
        // // fileReader.close();
        // // } catch (FileNotFoundException e) {
        // // System.out.println("An error occurred.");
        // // e.printStackTrace();
        // // }
    }

}
