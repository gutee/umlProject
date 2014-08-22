package gui.smallFrames;

import control.ProjectHandler;
import model.UMLClass;
import model.UMLModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 12:03
 */
public class RemoveClassFrame extends AbstractAction {
    private JFrame frame;
    private JPanel comboBoxPanel, buttonPanel;
    private JComboBox comboBox;
    private JLabel label;
    private ProjectHandler projectHandler;
    private JButton removeButton, cancelButton;
    private final static Dimension FRAME_DIMENSION = new Dimension(430, 130);
    private final static Dimension BOXPANEL_DIMENSION = new Dimension(270, 70);

    public RemoveClassFrame(ProjectHandler projectHandler) {
        this.projectHandler = projectHandler;
        startFrame();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void startFrame() {
        frame = new JFrame("Remove Class");
        frame.setPreferredSize(FRAME_DIMENSION);
        label = new JLabel("\n\tSelect class to remove");
        frame.setLayout(new BorderLayout());
        frame.add(label, BorderLayout.NORTH);
        frame.add(startComboBoxPanel(), BorderLayout.CENTER);
        frame.add(startButtonPanel(), BorderLayout.SOUTH);
        frame.pack();
    }

    private JPanel startComboBoxPanel() {
        comboBoxPanel = new JPanel();
        comboBoxPanel.setPreferredSize(BOXPANEL_DIMENSION);
        comboBox = new JComboBox();
        comboBox.setPreferredSize(new Dimension(265, 70));
        ArrayList<UMLClass> classes = (ArrayList<UMLClass>) projectHandler.getClasses();
        for (int i = 0; i < classes.size(); i++) {
            comboBox.addItem(classes.get(i).getClassNameInPackage());
        }
        comboBoxPanel.add(comboBox);
        return comboBoxPanel;
    }

    private void closeFrame() {
        frame.setVisible(false);
        frame.dispose();
    }

    private JPanel startButtonPanel() {
        buttonPanel = new JPanel();
        removeButton = new JButton("Remove");
        removeButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeFrame();
            }
        });
        buttonPanel.add(removeButton);
        buttonPanel.add(cancelButton);
        return buttonPanel;
    }

    public void actionPerformed(ActionEvent e) {
        projectHandler.removeClass((String) comboBox.getSelectedItem());
        closeFrame();
    }
}