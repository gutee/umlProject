package gui.smallFrames;

import control.ProjectHandler;
import control.actions.NewProjectAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 12:03
 */
public class NewProjectFrame extends AbstractAction {
    private JFrame frame;
    private ProjectHandler projectHandler;
    private JPanel textPanel, buttonPanel;
    private JLabel label;
    private JTextField textField;
    private JButton createButton, cancelButton;
    private final static Dimension DIMENSION = new Dimension(300, 120);
    private final static Dimension TEXTFIELD_DIMENSION = new Dimension(200, 25);

    public NewProjectFrame(ProjectHandler projectHandler) {
        this.projectHandler = projectHandler;
        createButton = new JButton("Create");
        createButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });
        buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(cancelButton);
        label = new JLabel("Name The New Project: ");
        textField = new JTextField();
        textField.setPreferredSize(TEXTFIELD_DIMENSION);
        textPanel = new JPanel();
        textPanel.add(label);
        textPanel.add(textField);
        frame = new JFrame("New Project");
        frame.setPreferredSize(DIMENSION);
        frame.setLayout(new BorderLayout());
        frame.add(textPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.PAGE_END);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if(textField.getText().equals("")){
            projectHandler.newProject("No Name Project");
        } else {
            projectHandler.newProject(textField.getText());
        }
        frame.setVisible(false);
        frame.dispose();
    }
}
