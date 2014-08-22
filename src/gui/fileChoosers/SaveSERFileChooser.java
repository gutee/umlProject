package gui.fileChoosers;

import control.ProjectHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 12:03
 */
public class SaveSERFileChooser {
    private JFileChooser fileChooser;

    public SaveSERFileChooser(ProjectHandler projectHandler) {
        fileChooser = new JFileChooser();
        fileChooser.setVisible(true);
        int choice = fileChooser.showSaveDialog(new JPanel());
        if (choice == JFileChooser.APPROVE_OPTION) {
            projectHandler.saveProject(fileChooser.getSelectedFile());
        }
    }
}
