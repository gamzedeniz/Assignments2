import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile { //Reading the file and stroing every line in an arrayList
    public static ArrayList<String> readF (String file) throws FileNotFoundException //to read the files and store it into the arraylist
    {
        File myfile = new File(file);
        Scanner filereader = new Scanner(myfile);
        ArrayList<String> mylist = new ArrayList<String>();
        while (filereader.hasNextLine())
            mylist.add(filereader.nextLine());
        filereader.close();
        return mylist;
    }
}
