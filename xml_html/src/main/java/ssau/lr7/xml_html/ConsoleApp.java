package ssau.lr7.xml_html;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

// cd C:\Users\sirar\IdeaProjects\labs\xml_html\src\main\java\ssau\lr7\xml_html
// java ConsoleApp.java input.xml output.xml
public class ConsoleApp {
    public static void main(String[] args) {
    /*
    Разработать на Java консольное приложение,
    имеющее два входных параметра: имена входного и выходного файла.
    Задача приложения заключается в проверке значения средней оценки
    и его коррекции, если в исходном документе оно не соответствует
    действительности. Использовать DOM - анализатор.
     */
        String prefixFile = "C:\\Users\\sirar\\IdeaProjects\\labs\\xml_html\\src\\main\\resources\\ssau\\lr7\\xml_html\\xml\\";
        System.out.println("Hello and welcome!");

        if (args.length < 2) throw new RuntimeException("Not enough arguments");

        try {
            checkAverageMarkAndCorrect(prefixFile + args[0], prefixFile + args[1]);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private static void checkAverageMarkAndCorrect(String inputFile, String outputFile) throws TransformerException {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document inputDoc = builder.parse(inputFile);
            printElementAttributes(inputDoc);

            double correctAvg = getCorrectAvg(inputDoc), currentAvg = getAvgFromXml(inputDoc);
            if (correctAvg != currentAvg) {
                System.out.println("Correct average value = " + correctAvg);
                rewriteAvg(inputDoc, correctAvg, outputFile);
                printElementAttributes(builder.parse(outputFile));
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    static void rewriteAvg(Document sourceDoc, double newAvg, String path) throws TransformerException {
        NodeList averageList = sourceDoc.getElementsByTagName("average");
        if (averageList.getLength() > 0) {
            Element averageElement = (Element) averageList.item(0);
            averageElement.setTextContent(String.valueOf(newAvg));
        } else {
            Element averageElement = sourceDoc.createElement("average");
            averageElement.setTextContent(String.valueOf(newAvg));
            sourceDoc.getDocumentElement().appendChild(averageElement);
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamResult result = new StreamResult(new File(path));
        transformer.transform(new DOMSource(sourceDoc), result);
    }

    private static double getAvgFromXml(Document doc) {
        Element average = (Element) doc.getElementsByTagName("average").item(0);
        return Double.parseDouble(average.getTextContent());
    }

    private static double getCorrectAvg(Document doc) {
        NodeList subjects = doc.getElementsByTagName("subject");
        int sum = 0;
        for (int j = 0; j < subjects.getLength(); j++) {
            Element subject = (Element) subjects.item(j);
            sum += Integer.parseInt(subject.getAttribute("mark"));
        }
        return (double) sum / subjects.getLength();
    }

    static void printElementAttributes(Document doc) {
        System.out.println("The attributes of each element are: ");
        NodeList nl = doc.getElementsByTagName("*");
        for (int j = 0; j < nl.getLength(); j++) {
            Element e = (Element) nl.item(j);
            System.out.println(e.getTagName() + ":");
            NamedNodeMap nnm = e.getAttributes();
            if (nnm != null) {
                for (int i = 0; i < nnm.getLength(); i++) {
                    Node n = nnm.item(i);
                    System.out.print(" " + n.getNodeName() + " = " + n.getNodeValue());
                }
            }
            System.out.println();
        }
    }
}