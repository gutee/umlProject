package gui.fileChoosers;

import control.ProjectHandler;

import javax.swing.*;

/**
 * User: Martin Gutierrez
 * Date: 01/07/12
 * Time: 00:09
 */
public class SaveJavaFileChooser {
    private JFileChooser fileChooser;

    public SaveJavaFileChooser(ProjectHandler projectHandler) {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setVisible(true);
        int choice = fileChooser.showSaveDialog(new JPanel());
        if (choice == JFileChooser.APPROVE_OPTION) {
            projectHandler.generateJavaFiles(fileChooser.getCurrentDirectory());
        }
    }
}