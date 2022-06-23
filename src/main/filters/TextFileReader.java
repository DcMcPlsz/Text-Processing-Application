import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

public class TextFileReader {

    @Override
    public ArrayList<String> reading(File fileObject) throws Throwable {
        log.info("The file is received");
        ArrayList<String> lines = new ArrayList<String>();
        try {
            // File fileObject = new File("FileToBeRead.txt");
            Scanner fileReader = new Scanner(fileObject);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                lines.add(data);
                System.out.println(data);
            }
            fileReader.close();
            return lines;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}
