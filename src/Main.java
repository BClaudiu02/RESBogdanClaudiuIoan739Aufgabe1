import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Ereignis> ereignise = EventProcessor.readXML("C:\\Users\\Claudiu\\Documents\\GitHub\\RESBogdanClaudiuIoan739Aufgabe1\\ninja_events.xml");


        System.out.println("\nEreignisse der Jonin Stufe:");
        EventProcessor.printJoninEvents(ereignise);
    }
}
