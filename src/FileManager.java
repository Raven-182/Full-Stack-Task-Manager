import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileManager {

    private final File FILE = new File("src/Tasks.txt");

    public String readFile() throws FileNotFoundException {
        String returnValue = "";

        try (Scanner scan = new Scanner(FILE)) {
            while (scan.hasNextLine()) {
                returnValue += scan.nextLine() + "\n";
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }
        return returnValue;
    }

    public void writeFile(String task) throws IOException {
        FileWriter write = new FileWriter(FILE, true);
        BufferedWriter bw = new BufferedWriter(write);
        if (!task.trim().isEmpty()) {
            bw.write(task);
            bw.newLine();
        }

        bw.close();
    }

}
