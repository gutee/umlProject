package model;

import de.gulden.util.javasource.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 12:03
 */
public class UMLClass implements Serializable {
    private String umlClassTitle;
    private List<UMLAttribute> umlAttributes;
    private List<UMLMethod> umlMethods;
    private String visibility;
    private String superClass;
    private String classNameInPackage;

    UMLClass(String umlClassTitle, String visibility, String superClass, String classNameInPackage) {
        this.umlClassTitle = umlClassTitle;
        umlAttributes = new ArrayList<UMLAttribute>();
        umlMethods = new ArrayList<UMLMethod>();
        this.visibility = visibility;
        this.superClass = superClass;
        this.classNameInPackage = classNameInPackage;
    }

    public void addAttribute(UMLAttribute attribute) {
        umlAttributes.add(attribute);
    }

    public void addMethod(UMLMethod method) {
        umlMethods.add(method);
    }

    public UMLMethod getMethodAt(int i) {
        return umlMethods.get(i);
    }

    public UMLAttribute getAttributeAt(int i) {
        return umlAttributes.get(i);
    }

    public int getMethodsSize() {
        return umlMethods.size();
    }

    public int getAttributesSize() {
        return umlAttributes.size();
    }

    public String getUmlClassTitle() {
        return umlClassTitle;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getSuperClass() {
        return superClass;
    }

    public String getClassNameInPackage() {
        return classNameInPackage;
    }

    public static UMLClass extractBeautyJClass(de.gulden.util.javasource.Class beautyJClass) {
        String visibility = "";
        if (beautyJClass.getModifierString().toUpperCase().contains(Visibility.PUBLIC.toString())) {
            visibility = Visibility.PUBLIC.toString();
        } else if (beautyJClass.getModifierString().toUpperCase().contains(Visibility.PRIVATE.toString())) {
            visibility = Visibility.PRIVATE.toString();
        } else if (beautyJClass.getModifierString().toUpperCase().contains(Visibility.PROTECTED.toString())) {
            visibility = Visibility.PROTECTED.toString();
        } else {
            visibility = Visibility.PACKAGE.toString();
        }
        UMLClass umlClass = new UMLClass(beautyJClass.getUnqualifiedName(),
                visibility, beautyJClass.getSuperclassName(), beautyJClass.getName());
        NamedIterator members = beautyJClass.getAllMembers();
        for (int i = 0; i < members.size(); i++) {
            Member member = (Member) members.next();
            if (member.toString().substring(0, 6).equalsIgnoreCase("field ")) {
                umlClass.addAttribute(UMLAttribute.extractBeautyJField((Field) member));
            } else if (member.toString().substring(0, 6).equalsIgnoreCase("method")) {
                umlClass.addMethod(UMLMethod.extractBeautyJMethod((Method) member));
            }
        }
        return umlClass;
    }

    public boolean isClassInAttributes(UMLClass umlClass) {
        boolean result = false;
        for (int i = 0; i < getAttributesSize(); i++) {
            if (umlClass.getUmlClassTitle().equalsIgnoreCase(getAttributeAt(i).getType())) {
                result = true;
            }
        }
        return result;
    }

    public boolean isClassInParameters(UMLClass umlClass) {
        boolean result = false;
        for (int meth = 0; meth < getMethodsSize(); meth++) {
            for (int par = 0; par < getMethodAt(meth).getParametersSize(); par++) {
                if (umlClass.getUmlClassTitle().equalsIgnoreCase(getMethodAt(meth).getParameterAt(par).getType())) {
                    result = true;
                }
            }
        }
        return result;
    }

    public boolean isClassInReturnType(UMLClass umlClass) {
        boolean result = false;
        for (int meth = 0; meth < getMethodsSize(); meth++) {
            if (umlClass.getUmlClassTitle().equalsIgnoreCase(getMethodAt(meth).getReturnType())) {
                result = true;
            }
        }
        return result;
    }

    public boolean isThisClassThisExtension(String extension) {
        boolean result = false;
        if (classNameInPackage.equalsIgnoreCase(extension)) {
            result = true;
        }
        return result;
    }

    public String toString() {
        return "\t\t" + classNameInPackage;
    }
}
