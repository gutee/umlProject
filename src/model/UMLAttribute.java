package model;

import java.lang.String;import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Martin
 * Date: 17/11/11
 * Time: 14:55
 * To change this template use File | Settings | File Templates.
 */
public class UMLAttribute {
    String attributeName;
    String type;            //si es int, String, etc
    List<Modifier> modifier;          //si es privado, publico, etc
    Visibility visibility;

    UMLAttribute(String attributeName, String type, List<Modifier> modifier, Visibility visibility) {
        this.attributeName = attributeName;
        this.type = type;
        this.modifier = modifier;
        this.visibility = visibility;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getType() {
        return type;
    }
}
