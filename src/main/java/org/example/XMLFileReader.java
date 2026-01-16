package org.example;

/**
 * @author Anastasis Drougas
 * @author Angjelo Hoxhaj
 */

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class XMLFileReader {

    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

    public XMLFileReader() {}

    public Activities fileReader(String filename){
        Activities list = null;
          try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new FileInputStream(filename));
            return new Activities(doc.getElementsByTagName("Activity"));
        } catch (Exception e) {
            System.err.println("Invalid or empty XML file skipped: " + filename);
            return new Activities();
        }
    }

    public void processXMLFiles(File[] fileList, ArrayList<Activity> activities){
        XMLFileReader xml = new XMLFileReader();
        for (File f : fileList) {
            Activities activityList = xml.fileReader(f.getAbsolutePath());
            activities.addAll(activityList.getActivities());
        }
    }

    public static String getNodeValue(NodeList n) {
        return n.item(0).getChildNodes().item(0).getNodeValue();
    }


}
