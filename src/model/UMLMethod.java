package model;

import de.gulden.util.javasource.Method;
import de.gulden.util.javasource.NamedIterator;
import de.gulden.util.javasource.Parameter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 12:03
 */
public class UMLMethod implements Serializable {
    private String methodName;
    private List<UMLAttribute> parameters;
    private String returnType;
    private String visibility;

    UMLMethod(String methodName, String returnType, String visibility) {
        this.methodName = methodName;
        this.returnType = returnType;
        parameters = new ArrayList<UMLAttribute>();
        this.visibility = visibility;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getVisibility() {
        return visibility;
    }

    public UMLAttribute getParameterAt(int i) {
        return parameters.get(i);
    }

    public int getParametersSize() {
        return parameters.size();
    }

    public void addParameter(UMLAttribute parameter) {
        parameters.add(parameter);
    }

    public static UMLMethod extractBeautyJMethod(Method beautyJMethod) {
        String visibility = "";
        if (beautyJMethod.getModifierString().toUpperCase().contains(Visibility.PUBLIC.toString())) {
            visibility = Visibility.PUBLIC.toString();
        } else if (beautyJMethod.getModifierString().toUpperCase().contains(Visibility.PRIVATE.toString())) {
            visibility = Visibility.PRIVATE.toString();
        } else if (beautyJMethod.getModifierString().toUpperCase().contains(Visibility.PROTECTED.toString())) {
            visibility = Visibility.PROTECTED.toString();
        } else {
            visibility = Visibility.PACKAGE.toString();
        }
        UMLMethod umlMethod = new UMLMethod(beautyJMethod.getUnqualifiedName(),
                beautyJMethod.getType().getUnqualifiedTypeName(), visibility);
        NamedIterator parameters = beautyJMethod.getParameters();
        for (int i = 0; i < parameters.size(); i++) {
            Parameter parameter = (Parameter) parameters.next();
            umlMethod.addParameter(new UMLAttribute(parameter.getUnqualifiedName(),
                    parameter.getType().getUnqualifiedTypeName()));
        }
        return umlMethod;
    }

    public String toString() {
        String s = "";
        if (visibility != null) {
            if (visibility.equalsIgnoreCase(Visibility.PUBLIC.toString())) {
                s += "+ ";
            } else if (visibility.equalsIgnoreCase(Visibility.PRIVATE.toString())) {
                s += "- ";
            } else if (visibility.equalsIgnoreCase(Visibility.PROTECTED.toString())) {
                s += "# ";
            } else if (visibility.equalsIgnoreCase(Visibility.PACKAGE.toString())) {
                s += "~ ";
            }
        }
        s += methodName + "(";
        for (int par = 0; par < getParametersSize(); par++) {
            if (par == getParametersSize() - 1) {
                s += getParameterAt(par).toString();
            } else {
                s += getParameterAt(par).toString() + ",";
            }
        }
        s += "): " + returnType;
        return s;
    }
}