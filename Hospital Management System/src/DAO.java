import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface DAO {
    public Patient getByID(int ID) throws FileNotFoundException;// read a single entry from the file
    public void deleteByID(int ID) throws IOException; // delete a single entry from file
    public void add(String object) throws IOException; // add or update an entry
    public ArrayList<Patient> getALL() throws FileNotFoundException; // get all entries

}
