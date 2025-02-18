import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EventProcessor {
    public static List<Ereignis> readXML(String filepath) throws Exception {
        File xmlFile = new File(filepath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("log");
        List<Ereignis> events = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                int id = Integer.parseInt(element.getElementsByTagName("Id").item(0).getTextContent());
                String charaktername = element.getElementsByTagName("Charaktername").item(0).getTextContent();
                Stufe stufe = Stufe.valueOf(element.getElementsByTagName("Stufe").item(0).getTextContent());
                String beschreibung = element.getElementsByTagName("Beschreibung").item(0).getTextContent();
                LocalDate datum = LocalDate.parse(element.getElementsByTagName("Datum").item(0).getTextContent());
                Double kraftpunkte = Double.valueOf(element.getElementsByTagName("Kraftpunkte").item(0).getTextContent());

                Ereignis ereignis = new Ereignis(id, charaktername, stufe, beschreibung, datum, kraftpunkte);
                events.add(ereignis);
            }
        }
        return events;
    }

    public static void printJoninEvents(List<Ereignis> ereignise) {
        ereignise.stream()
                .filter(e -> e.getStufe() == Stufe.Jonin)
                .sorted(Comparator.comparing(e -> e.datum, Comparator.reverseOrder()))
                .forEach(System.out::println);
    }

    public static void saveStufePunkte(List<Ereignis> events, String ausgabeDatei) throws IOException {
        Map<Object, Long> stufeCount = events.stream()
                .collect(Collectors.groupingBy(e -> e.stufe, TreeMap::new, Collectors.counting()));

        List<Map.Entry<Object, Long>> sortierteEintraege = stufeCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ausgabeDatei))) {
            for (Map.Entry<Object, Long> entry : sortierteEintraege) {
                writer.write(entry.getKey() + "%" + entry.getValue());
                writer.newLine();
            }
        }
    }



}
