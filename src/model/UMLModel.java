package model;

import de.gulden.util.javasource.NamedIterator;
import de.gulden.util.javasource.jjt.ParseException;
import gui.smallFrames.ErrorFrame;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.Exception;
import java.lang.String;
import java.util.ArrayList;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 12:03
 */
public class UMLModel implements Serializable {
    private String name;
    private ArrayList<UMLClass> umlModel;

    public UMLModel(String name) {
        this.name = name;
        umlModel = new ArrayList<UMLClass>();
    }

    public void addClass(UMLClass umlClass) {
        if (!isThisClassRepeated(umlClass)) {
            umlModel.add(umlClass);
        } else {
            new ErrorFrame("Error", "The class already exists");
        }
    }

    private boolean isThisClassRepeated(UMLClass umlClass) {
        boolean result = false;
        for (int classNumber = 0; classNumber < getModelSize(); classNumber++) {
            if (getClassAt(classNumber).getClassNameInPackage().equals(umlClass.getClassNameInPackage())) {
                result = true;
            }
        }
        return result;
    }

    public void removeClass(String classToRemove) {
        for (int classNumber = 0; classNumber < getModelSize(); classNumber++) {
            if (getClassAt(classNumber).getClassNameInPackage().equalsIgnoreCase(classToRemove)) {
                umlModel.remove(getClassAt(classNumber));
            }
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<UMLClass> getUmlModel() {
        return umlModel;
    }

    public boolean isEmpty() {
        return umlModel.isEmpty();
    }

    public Object[] getArrayClasses() {
        return umlModel.toArray();
    }

    public UMLClass getClassAt(int i){
        return umlModel.get(i);
    }

    public int getModelSize(){
        return umlModel.size();
    }

    public void setName(String name){
        this.name = name;
    }

    public void writeXML(File directory) {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Project");
            doc.appendChild(rootElement);

            Element projectName = doc.createElement("name");
            projectName.appendChild(doc.createTextNode(getName()));
            rootElement.appendChild(projectName);

            Element projectVisibility = doc.createElement("visibility");
            projectVisibility.setAttribute("value", "public");
            rootElement.appendChild(projectVisibility);

            Element ownedElement = doc.createElement("ownedElement");
            rootElement.appendChild(ownedElement);

            for (int i = 0; i < getModelSize(); i++) {            //for each class

                // class number element
                Element classNumber = doc.createElement("Class");
                classNumber.setAttribute("id", "a" + i);
                ownedElement.appendChild(classNumber);

                // class name element
                Element className = doc.createElement("name");
                className.appendChild(doc.createTextNode(getClassAt(i).getClassNameInPackage()));
                classNumber.appendChild(className);

                // class visibility value
                Element classVisibility = doc.createElement("visibility");
                classVisibility.setAttribute("value", getClassAt(i).getVisibility());
                classNumber.appendChild(classVisibility);

                //super class name
                Element extension = doc.createElement("extends");
                extension.appendChild(doc.createTextNode(getClassAt(i).getSuperClass()));
                classNumber.appendChild(extension);

                // feature element appended to class number
                Element feature = doc.createElement("feature");
                classNumber.appendChild(feature);

                // attribute element appended to feature, each attribute has its elements
                for (int j = 0; j < getClassAt(i).getAttributesSize(); j++) { //for each attribute within the class
                    Element attribute = doc.createElement("Attribute");
                    feature.appendChild(attribute);

                    Element attributeName = doc.createElement("name");
                    attributeName.appendChild(doc.createTextNode(getClassAt(i).
                            getAttributeAt(j).getAttributeName()));
                    attribute.appendChild(attributeName);

                    Element attributeVisibility = doc.createElement("visibility");
                    attributeVisibility.setAttribute("value", getClassAt(i).
                            getAttributeAt(j).getVisibility().toLowerCase());
                    attribute.appendChild(attributeVisibility);

                    Element attributeType = doc.createElement("type");
                    attributeType.appendChild(doc.createTextNode(getClassAt(i).getAttributeAt(j).getType()));
                    attribute.appendChild(attributeType);
                }

                // method element appended to feature, each method has its elements
                for (int k = 0; k < getClassAt(i).getMethodsSize(); k++) {        //for each class method
                    Element method = doc.createElement("Operation");
                    feature.appendChild(method);

                    // methods elements
                    Element methodName = doc.createElement("name");
                    methodName.appendChild(doc.createTextNode(getClassAt(i).getMethodAt(k).getMethodName()));
                    method.appendChild(methodName);

                    Element methodVisibility = doc.createElement("visibility");
                    methodVisibility.setAttribute("value", getClassAt(i).
                            getMethodAt(k).getVisibility().toLowerCase());
                    method.appendChild(methodVisibility);

                    Element returnType = doc.createElement("returnType");
                    returnType.appendChild(doc.createTextNode(getClassAt(i).getMethodAt(k).getReturnType()));
                    method.appendChild(returnType);

                    Element args = doc.createElement("args");
                    method.appendChild(args);

                    for (int g = 0; g < getClassAt(i).getMethodAt(k).getParametersSize(); g++) {   //each parameter
                        Element arg = doc.createElement("arg");
                        args.appendChild(arg);

                        Element argName = doc.createElement("name");
                        argName.appendChild(doc.createTextNode(getClassAt(i).
                                getMethodAt(k).getParameterAt(g).getAttributeName()));
                        arg.appendChild(argName);

                        Element argType = doc.createElement("type");
                        argType.appendChild(doc.createTextNode(getClassAt(i).
                                getMethodAt(k).getParameterAt(g).getType()));
                        arg.appendChild(argType);
                    }
                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(directory.getAbsolutePath() + ".xml");
            transformer.transform(source, result);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public static UMLModel readXML(File directory) {
        UMLModel model = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(directory);
            doc.getDocumentElement().normalize();
            String projectName = doc.getDocumentElement().getElementsByTagName("name").item(0).getTextContent();
            model = new UMLModel(projectName);
            UMLClass classs = null;

            NodeList classes = doc.getElementsByTagName("Class");

            for (int i = 0; i < classes.getLength(); i++) {

                Node oneClass = classes.item(i);
                if (oneClass.getNodeType() == Node.ELEMENT_NODE) {

                    Element oneClassElement = (Element) oneClass;
                    String classFullName = getTagValue("name", oneClassElement);
                    String className = classFullName.substring(classFullName.
                            lastIndexOf(".") + 1, classFullName.length());
                    String classVisibility = getTagValueAttr("visibility", oneClassElement).toUpperCase();
                    String extension = getTagValue("extends", oneClassElement);
                    NodeList attributes = oneClassElement.getElementsByTagName("Attribute");
                    classs = new UMLClass(className, classVisibility, extension, classFullName);
                    for (int attr = 0; attr < attributes.getLength(); attr++) {
                        Node oneAttr = attributes.item(attr);
                        if (oneClass.getNodeType() == Node.ELEMENT_NODE) {
                            Element oneAttrElement = (Element) oneAttr;
                            String attributeName = getTagValue("name", oneAttrElement);
                            String attributeVisibility = getTagValueAttr("visibility", oneAttrElement).toUpperCase();
                            String attributeType = getTagValue("type", oneAttrElement);
                            classs.addAttribute(new UMLAttribute(attributeName, attributeType, attributeVisibility));
                        }
                    }
                    NodeList methods = oneClassElement.getElementsByTagName("Operation");
                    for (int meth = 0; meth < methods.getLength(); meth++) {
                        Node oneMethod = methods.item(meth);
                        if (oneMethod.getNodeType() == Node.ELEMENT_NODE) {
                            Element oneMethodElement = (Element) oneMethod;
                            String methodName = getTagValue("name", oneMethodElement);
                            String methodVisibility = getTagValueAttr("visibility", oneMethodElement).toUpperCase();
                            String methodReturnType = getTagValue("returnType", oneMethodElement);
                            UMLMethod method = new UMLMethod(methodName, methodReturnType, methodVisibility);
                            NodeList args = oneMethodElement.getElementsByTagName("arg");
                            for (int arguments = 0; arguments < args.getLength(); arguments++) {
                                Node oneArg = args.item(arguments);
                                if (oneArg.getNodeType() == Node.ELEMENT_NODE) {
                                    Element oneArgElement = (Element) oneArg;
                                    String argName = getTagValue("name", oneArgElement);
                                    String argType = getTagValue("type", oneArgElement);
                                    method.addParameter(new UMLAttribute(argName, argType));
                                }
                            }
                            classs.addMethod(method);
                        }
                    }
                }
                model.addClass(classs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    private static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

        Node nValue = (Node) nlList.item(0);

        return nValue.getNodeValue();
    }

    private static String getTagValueAttr(String sTag, Element eElement){
        return ((Element) eElement.getElementsByTagName(sTag).item(0)).getAttribute("value");
    }

    public void generateJavaFiles(File directory) {
        FileWriter fw;
        BufferedWriter bw;
        UMLClass umlClass;
        UMLMethod umlMethod;
        UMLAttribute umlAttribute;
        for (int i = 0; i < getModelSize(); i++) {
            umlClass = getClassAt(i);
            String superClassName = umlClass.getSuperClass();

            String superClassUnqName = superClassName.substring(superClassName.lastIndexOf(".")
                    + 1, superClassName.length());
            if(superClassUnqName.equalsIgnoreCase("object")){
                superClassUnqName = "";
            } else {
                superClassUnqName = " extends " + superClassUnqName;
            }
            try {
                fw = new FileWriter(directory + "/" + umlClass.getUmlClassTitle() + ".java");
                bw = new BufferedWriter(fw);
                bw.write("package " + umlClass.getClassNameInPackage() + ";\n\n");
                //bw.write("import " + superClassName + "\n\n");
                bw.write(umlClass.getVisibility().toLowerCase() + " class " +
                        umlClass.getUmlClassTitle() + superClassUnqName + " { \n");
                for (int attr = 0; attr < umlClass.getAttributesSize(); attr++) {               //attrs
                    umlAttribute = umlClass.getAttributeAt(attr);
                    if(!Visibility.valueOf(umlAttribute.getVisibility()).equals(Visibility.PACKAGE)){
                        bw.write("\t" + umlAttribute.getVisibility().toLowerCase() + " " +
                            umlAttribute.getType() + " " + umlAttribute.getAttributeName() + ";\n");
                    } else {
                        bw.write("\t" + umlAttribute.getType() + " " + umlAttribute.getAttributeName() + ";\n");
                    }
                }
                bw.write("\n");
                for (int meth = 0; meth < umlClass.getMethodsSize(); meth++) {             //methods
                    umlMethod = umlClass.getMethodAt(meth);
                    bw.write("\t" + umlMethod.getVisibility().toLowerCase() + " " + umlMethod.getReturnType() + " " +
                            umlMethod.getMethodName() + " (");
                    if (umlMethod.getParametersSize() == 0) {
                        bw.write(" ) {}\n\n");
                    }
                    for (int par = 0; par < umlMethod.getParametersSize(); par++) {                 //parameters
                        if (par != umlMethod.getParametersSize() - 1) {
                            bw.write(umlMethod.getParameterAt(par).getType() + " " +
                                    umlMethod.getParameterAt(par).getAttributeName() + ", ");
                        } else {
                            bw.write(umlMethod.getParameterAt(par).getType() + " " +
                                    umlMethod.getParameterAt(par).getAttributeName() + ") {}\n\n");
                        }
                    }
                }
                bw.write("}");
                bw.flush();
                bw.close();
                fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveModelAsSer(File directory) {
        try {
            FileOutputStream fos = new FileOutputStream(directory + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static UMLModel loadModelFromSer(File directory) {
        UMLModel model = null;
        try {
            FileInputStream fis = new FileInputStream(directory);
            ObjectInputStream ois = new ObjectInputStream(fis);
            model = (UMLModel) ois.readObject();
            ois.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return model;
    }

    public void addClassFromFile(File directory) {
        JavaParser javaParser = new JavaParser();
        UMLClass umlClass;
        try {
            de.gulden.util.javasource.Package pack = javaParser.read(directory);
            NamedIterator classes = pack.getAllClasses();
            de.gulden.util.javasource.Class oneClass = (de.gulden.util.javasource.Class) classes.next();
            umlClass = UMLClass.extractBeautyJClass(oneClass);
            addClass(umlClass);
        } catch (ParseException pe) {
            new ErrorFrame("Error", "Generics (< >) not supported");
        } catch (IOException ex) {

        } catch (NoClassDefFoundError ex) {
            new ErrorFrame("Error", "Can't add class: \n" + ex.getMessage());
        }
    }
}