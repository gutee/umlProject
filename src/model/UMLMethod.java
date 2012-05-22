package model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Martin
 * Date: 17/11/11
 * Time: 14:47
 * To change this template use File | Settings | File Templates.
 */
public class UMLMethod {
    String methodName;
    List<Modifier> modifier;
    List<UMLAttribute> attributes;
    String returnType;
    Visibility visibility;

    UMLMethod(String methodName, List<Modifier> modifier, String returnType, List<UMLAttribute> attributes, Visibility visibility) {
        this.methodName = methodName;
        this.modifier = modifier;
        this.returnType = returnType;
        this.attributes = attributes;
        this.visibility = visibility;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getReturnType() {
        return returnType;
    }
}