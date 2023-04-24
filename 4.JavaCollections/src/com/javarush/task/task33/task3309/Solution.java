package com.javarush.task.task33.task3309;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/* 
Комментарий внутри xml
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(toXmlWithComment(new XmlObject(), "second", "it's a comment"));
    }

    public static String toXmlWithComment(Object obj, String tagName, String comment) {
        try {
            //marshalling object using JAXB
            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(obj, writer);

            //creating DOM and comment to insert
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(new ByteArrayInputStream(writer.toString().getBytes()));

            //recursively iterating through DOM and inserting comment
            iterateThrowNodesAndAddComment(document, tagName, comment);

            //unmarshalling DOM to XML
            DOMSource domSource = new DOMSource(document);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter stringWriter = new StringWriter();
            StreamResult streamResult = new StreamResult(stringWriter);
            transformer.transform(domSource, streamResult);

            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //method for iteration through parsed DOM and adding the comment on condition
    //(node is the root element)
    public static void iterateThrowNodesAndAddComment (Document document, String tagName, String comment) {
        NodeList listOfChildren = document.getElementsByTagName(tagName);
        for (int i = 0; i < listOfChildren.getLength(); i++) {
            Comment commentForInsertion = document.createComment(comment);
            listOfChildren.item(i).getParentNode().insertBefore(commentForInsertion, listOfChildren.item(i));
        }
    }

    //object for marshalling to XML
    @XmlType(name = "first")
    @XmlRootElement
    public static class XmlObject {
        @XmlElement
        public static List<String> second = new ArrayList<>();


        public XmlObject() {
            second.add("Text-1");
            second.add("Text-2");
            second.add("Text-3");
            second.add("Text-4");
        }
    }
}
