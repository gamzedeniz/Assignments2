import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException
    {
        Personnel [] people = {new Officer(),new Security(),new ResearchAssistant(),new FacultyMember(),new PartTime(),new Worker(),new Chief()};
        for(int k=0;k<7;k++)
            people[k].create(ReadFile.readF(args[0]),ReadFile.readF(args[0]).size(),ReadFile.readF(args[1]));
    }
}
