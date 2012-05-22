package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Martin
 * Date: 17/11/11
 * Time: 15:09
 * To change this template use File | Settings | File Templates.
 */
public class UMLClass {
    UMLClassTitle umlClassTitle;
    List<UMLAttribute> umlAttributes;
    List<UMLMethod> umlMethods;
    List<Modifier> modifier;
    Visibility visibility;
    String elGutee;

    UMLClass(UMLClassTitle umlClassTitle, List<Modifier> modifier, Visibility visibility) {
        this.umlClassTitle = umlClassTitle;
        umlAttributes = new ArrayList<UMLAttribute>();
        umlMethods = new ArrayList<UMLMethod>();
        this.modifier = modifier;
        this.visibility = visibility;
    }
    
    public void addAttribute(UMLAttribute attribute){
        umlAttributes.add(attribute);
    }
    
    public void addMethod(UMLMethod method){
        umlMethods.add(method);
    }
}
