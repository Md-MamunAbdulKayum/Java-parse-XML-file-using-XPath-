/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sourav.xpathex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Kayum-603
 */
public class XPathEX {

    public void readFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        File fileDir = new File(classLoader.getResource("resources/customer.xml").getFile());
        String nodeValue;
        String attributeValue;
        String firstName;
        if (fileDir.exists()) {
            System.out.println("file found");

            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document document = db.parse(new FileInputStream(fileDir));// same xml comments as above.

                XPathFactory xpf = XPathFactory.newInstance();
                XPath xpath = xpf.newXPath();
                String nodeLavel = "/company/customers/customer";
                // reading attribute value
                Element userElement = (Element) xpath.evaluate(nodeLavel, document,
                        XPathConstants.NODE);

                attributeValue = userElement.getAttribute("deskNo"); // will return first child node's 'deskNo' attribute value

                System.out.println("Value of attribute 'Desk No:' " + attributeValue);

                attributeValue = userElement.getAttribute("cusId");
                System.out.println("Value of attribute 'Customer no:' " + attributeValue);

                nodeValue = userElement.getElementsByTagName("firstname").item(0).getTextContent();
                System.out.println("value of clild node 'firstname:' " + nodeValue);

                // read all child node 'firstname' values
                NodeList nodeList = (NodeList) xpath.compile(nodeLavel).evaluate(document, XPathConstants.NODESET);
                for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                Element userElementForNodes = (Element) nNode;
                nodeValue = userElementForNodes.getElementsByTagName("firstname").item(0).getTextContent();
                System.out.println("value of clild node 'firstname:' " + nodeValue);
                }


            } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            }

        } else {
            System.out.println("file not found");
        }
    }

    public static void main(String[] args) {
        XPathEX readObj = new XPathEX();
        readObj.readFile();
    }

}
