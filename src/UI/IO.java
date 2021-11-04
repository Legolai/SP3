package UI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IO {
    public void readFromFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner data = new Scanner(file);
    }

}
