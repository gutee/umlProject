package model;

import de.gulden.util.javasource.Field;

import java.io.Serializable;
import java.lang.String;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 12:03
 */
public class UMLAttribute implements Serializable {
    private String attributeName;
    private String type;            //si es int, String, etc
    private String visibility;

    UMLAttribute(String attributeName, String type, String visibility) {
        this.attributeName = attributeName;
        this.type = type;
        this.visibility = visibility;
    }

    UMLAttribute(String attributeName, String type) {             //parameters
        this.attributeName = attributeName;
        this.type = type;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getType() {
        return type;
    }

    public String getVisibility() {
        return visibility;
    }

    public static UMLAttribute extractBeautyJField(Field beautyJField) {
        String visibility = "";
        if (beautyJField.getModifierString().toUpperCase().contains(Visibility.PUBLIC.toString())) {
            visibility = Visibility.PUBLIC.toString();
        } else if (beautyJField.getModifierString().toUpperCase().contains(Visibility.PRIVATE.toString())) {
            visibility = Visibility.PRIVATE.toString();
        } else if (beautyJField.getModifierString().toUpperCase().contains(Visibility.PROTECTED.toString())) {
            visibility = Visibility.PROTECTED.toString();
        } else {
            visibility = Visibility.PACKAGE.toString();
        }
        return new UMLAttribute(beautyJField.getUnqualifiedName(),
                beautyJField.getType().getUnqualifiedTypeName(), visibility);
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
        s += attributeName + ": " + type;
        return s;
    }
}
