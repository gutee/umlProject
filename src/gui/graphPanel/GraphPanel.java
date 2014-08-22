package gui.graphPanel;

import model.UMLClass;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.*;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 12:03
 */
public class GraphPanel extends JPanel{
    private ArrayList<UMLClass> model;
    private final int CHAR_HEIGHT = 20;
    private int firstLineY, attributesSize, secondLineY, methodSize, numberOfClasses, classX, classY;
    private final int TITLE_X = 15;
    private final int TITLE_Y = 25;
    private final int DISTANCE_BETWEEN_BOXES = 40;
    private final int BOX_WIDTH = 325;
    private final int ARR_SIZE = 4;

    public GraphPanel() {
        model = new ArrayList<UMLClass>();
        numberOfClasses = model.size();
    }

    public void replaceModel(ArrayList<UMLClass> model) {
        this.model = model;
        numberOfClasses = model.size();
    }

    public void setSize() {
        setPreferredSize(new Dimension(totalHorizontalDimension(), totalVerticalDimension()));
    }

    public void paintComponent(Graphics g) throws IndexOutOfBoundsException{
        super.paintComponent(g);
        for (int classNumber = 0; classNumber < numberOfClasses; classNumber++) {
            UMLClass actualClass = model.get(classNumber);
            attributesSize = calculateAttributeSize(model.get(classNumber));
            methodSize = calculateMethodSize(model.get(classNumber));
            classX = getClassX(classNumber);
            classY = getClassY(classNumber);

            firstLineY = classY + TITLE_Y + 15;
            int secondLineY = getSecondLineY(firstLineY, attributesSize);

            g.drawRect(classX, classY, BOX_WIDTH, calculateBoxHeight(actualClass));
            g.drawString(actualClass.getUmlClassTitle(), classX + TITLE_X, classY + TITLE_Y);
            g.drawLine(classX, firstLineY, classX + BOX_WIDTH, firstLineY);
            for (int attributeNumber = 0; attributeNumber < attributesSize; attributeNumber++) {
                g.drawString(actualClass.getAttributeAt(attributeNumber).toString(), getClassX(classNumber) + 15,
                        firstLineY + CHAR_HEIGHT * attributeNumber + 15);
            }
            g.drawLine(classX, secondLineY, classX + BOX_WIDTH, secondLineY);
            for (int methodNumber = 0; methodNumber < methodSize; methodNumber++) {
                g.drawString(actualClass.getMethodAt(methodNumber).toString(), classX + 15,
                        secondLineY + CHAR_HEIGHT * methodNumber + 15);
            }
        }
        for (int classNumber = 0; classNumber < numberOfClasses; classNumber++) {
            UMLClass actualClass = model.get(classNumber);
            secondLineY = getSecondLineY(getClassY(classNumber) + TITLE_Y + 15,
                    calculateAttributeSize(model.get(classNumber)));
            int columnNumber = classNumber % 2;
            for (int previousClassNumber = 0; previousClassNumber < numberOfClasses; previousClassNumber++) {
                UMLClass previousClass = model.get(previousClassNumber);
                if (!previousClass.getClassNameInPackage().equalsIgnoreCase(actualClass.getClassNameInPackage())) {
                    if (actualClass.isClassInAttributes(previousClass) || actualClass.isClassInParameters(previousClass)
                            || actualClass.isClassInReturnType(previousClass)) {
                        int xFrom, xTo;
                        if (columnNumber == 0) xFrom = 10 + BOX_WIDTH;
                        else xFrom = getClassX(classNumber);
                        if (previousClassNumber % 2 == 0) xTo = 10 + BOX_WIDTH;
                        else xTo = getClassX(previousClassNumber);
                        ((Graphics2D)g).setStroke(new BasicStroke(1.0f));
                        g.setColor(Color.BLUE);
                        drawArrow(g, xFrom, secondLineY, xTo, getSecondLineY(getClassY(previousClassNumber)
                                + TITLE_Y + 15, calculateAttributeSize(previousClass)));
                    } else if(actualClass.isThisClassThisExtension(previousClass.getSuperClass())){
                        int xFrom, xTo;
                        if (columnNumber == 0) xFrom = 10 + BOX_WIDTH;
                        else xFrom = getClassX(classNumber);
                        if (previousClassNumber % 2 == 0) xTo = 10 + BOX_WIDTH;
                        else xTo = getClassX(previousClassNumber);
                        float dash1[] = {7.0f};
                        BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
                                BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
                        ((Graphics2D)g).setStroke(dashed);
                        g.setColor(Color.RED);
                        drawArrow(g, xFrom, secondLineY, xTo, getSecondLineY(getClassY(previousClassNumber)
                                + TITLE_Y + 15, calculateAttributeSize(previousClass)));
                    }
                }
            }
        }
    }

    public void setNumberofClasses(int numberOfClasses){
        this.numberOfClasses = numberOfClasses;
    }

    private int totalHorizontalDimension() {
        if(numberOfClasses < 2){
            return BOX_WIDTH + DISTANCE_BETWEEN_BOXES / 2;
        } else{
            return BOX_WIDTH + DISTANCE_BETWEEN_BOXES + BOX_WIDTH + DISTANCE_BETWEEN_BOXES / 2;
        }
    }

    private int totalVerticalDimension() {
        int firstColumn = 0;
        int secondColumn = 0;
        for(int pairs = 0; pairs < numberOfClasses; pairs += 2){
            firstColumn += calculateBoxHeight(model.get(pairs));
            firstColumn += DISTANCE_BETWEEN_BOXES;
        }
        for (int impairs = 1; impairs < numberOfClasses; impairs += 2){
            secondColumn += calculateBoxHeight(model.get(impairs));
            secondColumn += DISTANCE_BETWEEN_BOXES;
        }
        return Math.max(firstColumn, secondColumn) - DISTANCE_BETWEEN_BOXES / 2;
    }

    private int getSecondLineY(int firstLine, int attributesSize) {
        return firstLine + CHAR_HEIGHT * attributesSize + 10;
    }

    private int getClassY(int classNumber) {
        int lineNumber = classNumber / 2;
        int y = 10;
        if (lineNumber != 0) {
            y = getClassY(classNumber - 2) + calculateBoxHeight(model.get(classNumber - 2))
                    + DISTANCE_BETWEEN_BOXES;
        }
        return y;
    }

    private int getClassX(int classNumber) {
        int columnNumber = classNumber % 2;
        return (BOX_WIDTH + DISTANCE_BETWEEN_BOXES) * columnNumber + 10;
    }

    private int calculateBoxHeight(UMLClass umlClass) {
        int attributes = calculateAttributeSize(umlClass);
        int methods = calculateMethodSize(umlClass);
        int box_height = CHAR_HEIGHT * attributes + CHAR_HEIGHT * methods + 60;
        return box_height;
    }

    private int calculateAttributeSize(UMLClass umlClass) {
        return umlClass.getAttributesSize();
    }

    private int calculateMethodSize(UMLClass umlClass) {
        return umlClass.getMethodsSize();
    }

    private void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) g1.create();

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        g.drawLine(0, 0, len, 0);
        g.fillPolygon(new int[]{len, len - ARR_SIZE, len - ARR_SIZE, len},
                new int[]{0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }

}