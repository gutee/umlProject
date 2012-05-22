package java;

import de.gulden.util.javasource.Class;
import de.gulden.util.javasource.NamedIterator;
import de.gulden.util.javasource.Package;
import de.gulden.util.javasource.jjt.ParseException;
import model.UMLClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class JavaParserTest
{
    @Test
    public void textSimpleClass()
    {
        try {
            JavaParser parser = new JavaParser();
            Package pack = parser.read(new File("test/prog2/uml/resources/SimpleJava.java"));

            NamedIterator classes = pack.getAllClasses();
            Class theClass = (Class) classes.find("prog2.uml.resources.SimpleJava");

            assertEquals("Name", theClass.getName(), "prog2.uml.resources.SimpleJava");


        } catch (ParseException e) {
            fail("Error reading the JAVA simple class");
        } catch (IOException e) {
            fail("Error reading the JAVA simple class");
        }
    }

    @Test
    public void textSimpleClassModel()
    {
        try {
            JavaParser parser = new JavaParser();
            Package pack = parser.read(new File("test/prog2/uml/resources/SimpleJava.java"));

            NamedIterator classes = pack.getAllClasses();
            Class theClass = (Class) classes.find("prog2.uml.resources.SimpleJava");

            //UMLClass simpleClass = new UMLClass();
            //simpleClass.parseJavaClass(theClass);

            //assertEquals("Name", simpleClass.getName(), theClass.getUnqualifiedName());


        } catch (ParseException e) {
            fail("Error reading the JAVA simple class");
        } catch (IOException e) {
            fail("Error reading the JAVA simple class");
        }
    }


}
