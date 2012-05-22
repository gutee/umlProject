package model;

import org.w3c.dom.Attr;import org.w3c.dom.Document;import org.w3c.dom.Element;import org.w3c.dom.Node;import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.lang.Exception;import java.lang.String;import java.lang.System;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Martin
 * Date: 17/11/11
 * Time: 15:22
 * To change this template use File | Settings | File Templates.
 */
public class UMLModel {
    List<UMLClass> umlModel;

    UMLModel(List<UMLClass> umlModel) {
        umlModel = new ArrayList<UMLClass>();
    }
    
    public void addClass(UMLClass umlClass){
        umlModel.add(umlClass);
    }

    public void writeXML() {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Project");
            doc.appendChild(rootElement);

            for (int i = 0; i < umlModel.size(); i++) {            //for each class

                // class number element
                Element classNumber = doc.createElement("Class");
                rootElement.appendChild(classNumber);

                Attr attr = doc.createAttribute("id");
		        attr.setValue("" + i);
		        rootElement.setAttributeNode(attr);

                // class name element
                Element className = doc.createElement("name");
                className.appendChild(doc.createTextNode(umlModel.get(i).umlClassTitle.toString()));
                classNumber.appendChild(className);

                // class modifier element
                for(int m = 0; m < umlModel.get(i).modifier.size(); m++) {             //for each class modifier
                    Element classModifier = doc.createElement("modifier");
                    classModifier.appendChild(doc.createTextNode(umlModel.get(i).modifier.get(m).name()));
                    classNumber.appendChild(classModifier);
                }

                // feature element appended to class number
                Element feature = doc.createElement("feature");
                classNumber.appendChild(feature);

                // attribute element appended to feature, each attribute has its elements
                for (int j = 0; j < umlModel.get(i).umlAttributes.size(); j++) {      //for each attribute within the class
                    Element attribute = doc.createElement("attribute");
                    feature.appendChild(attribute);

                    // attributes elements
                    Element attributeName = doc.createElement("name");
                    attributeName.appendChild(doc.createTextNode(umlModel.get(i).umlAttributes.get(j).getAttributeName()));
                    attribute.appendChild(attributeName);

                    for(int n = 0; n < umlModel.get(i).umlAttributes.size(); n++) {           //for each attribute modifier
                        Element attributeModifier = doc.createElement("modifier");
                        attributeModifier.appendChild(doc.createTextNode(umlModel.get(i).umlAttributes.get(j).modifier.get(n).name()));
                        attribute.appendChild(attributeModifier);
                    }

                    Element methodType = doc.createElement("Type");
                    methodType.appendChild(doc.createTextNode(umlModel.get(i).umlAttributes.get(j).getType()));
                    attribute.appendChild(methodType);
                }

                // method element appended to feature, each method has its elements
                for (int k = 0; k < umlModel.get(i).umlMethods.size(); k++) {        //for each class method
                    Element method = doc.createElement("Operation");
                    feature.appendChild(method);

                    // methods elements
                    Element methodName = doc.createElement("name");
                    methodName.appendChild(doc.createTextNode(umlModel.get(i).umlMethods.get(k).getMethodName()));
                    method.appendChild(methodName);

                    for(int s = 0; s < umlModel.get(i).umlAttributes.size(); s++) {      //for each method modifier within the class
                        Element methodModifier = doc.createElement("modifier");
                        methodModifier.appendChild(doc.createTextNode(umlModel.get(i).umlMethods.get(k).modifier.get(s).name()));
                        method.appendChild(methodModifier);
                    }

                    Element returnType = doc.createElement("returnType");
                    returnType.appendChild(doc.createTextNode(umlModel.get(i).umlMethods.get(k).getReturnType()));
                    method.appendChild(returnType);

                    Element args = doc.createElement("args");
                    method.appendChild(args);

                    for(int g = 0; g < umlModel.get(i).umlMethods.get(k).attributes.size(); g++) {   //for each method argument
                        Element arg = doc.createElement("arg");
                        args.appendChild(arg);

                        Element argName = doc.createElement("name");
                        argName.appendChild(doc.createTextNode(umlModel.get(i).umlMethods.get(k).attributes.get(g).getAttributeName()));
                        arg.appendChild(argName);

                        Element argType = doc.createElement("type");
                        argType.appendChild(doc.createTextNode(umlModel.get(i).umlMethods.get(k).attributes.get(g).getType()));
                        arg.appendChild(argType);
                    }
                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\file.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public void readXML() {
        try {
            File fXmlFile = new File("c:\\file.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
            System.out.println("-----------------------");

            for (int j = 0; j < umlModel.size(); j++) {
                NodeList nList = doc.getElementsByTagName("ClassNumber" + (j + 1));

                for (int i = 0; i < nList.getLength(); i++) {

                    Node nNode = nList.item(i);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;

                        System.out.println("Class Name : " + getTagValue("className", eElement));
                        for (int k = 0; k < umlModel.get(i).umlAttributes.size(); k++) {
                            System.out.println("Attribute " + (k + 1) + ": " + getTagValue("attributeName" + (k + 1), eElement));
                        }
                        for (int k = 0; k < umlModel.get(i).umlMethods.size(); k++) {
                            System.out.println("Method " + (k + 1) + ": " + getTagValue("methodName" + (k + 1), eElement));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

        Node nValue = (Node) nlList.item(0);

        return nValue.getNodeValue();
    }
}