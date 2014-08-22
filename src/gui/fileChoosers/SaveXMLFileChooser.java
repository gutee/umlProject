package gui.fileChoosers;

import control.ProjectHandler;

import javax.swing.*;

/**
 * User: Martin Gutierrez
 * Date: 01/07/12
 * Time: 00:20
 */
public class SaveXMLFileChooser {
    private JFileChooser fileChooser;

    public SaveXMLFileChooser(ProjectHandler projectHandler) {
        fileChooser = new JFileChooser();
        fileChooser.setVisible(true);
        int choice = fileChooser.showSaveDialog(new JPanel());
        if (choice == JFileChooser.APPROVE_OPTION) {
            projectHandler.writeXML(fileChooser.getSelectedFile());
        }
    }
}