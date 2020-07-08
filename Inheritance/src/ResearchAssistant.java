import java.io.IOException;
import java.util.ArrayList;

public class ResearchAssistant extends Academician{
    @Override
    public void create(ArrayList<String> personnel, int size,ArrayList<String> weeks) throws IOException {
        int new_size;
        ResearchAssistant [] assistants = new ResearchAssistant [size];
        for (int i=0;i<size;i++)
            assistants[i]= new ResearchAssistant();  // create each actual Person
        new_size=super.fillInfo(personnel,assistants,"R"); //filling information about the person
        if (new_size >0) { //if such person exist
            super.totalPay(assistants,105); //for base salary and ssbenefits
            super.writeInfo(assistants, new_size); //writing info out into a file
        }

    }
}

