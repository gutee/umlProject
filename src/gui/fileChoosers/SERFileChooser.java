package gui.fileChoosers;

import control.ProjectHandler;

import javax.swing.*;
import java.io.File;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 12:03
 */
public class SERFileChooser {
    private JFileChooser fileChooser;

    public SERFileChooser(ProjectHandler projectHandler) {
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                String fileType = Extensions.getExtension(f);
                if (f.isDirectory()) return true;
                if (fileType.equals(".ser")) return true;
                else return false;
            }

            @Override
            public String getDescription() {
                return "*.ser";
            }
        });
        fileChooser.setVisible(true);
        int choice = fileChooser.showOpenDialog(new JPanel());
        if (choice == JFileChooser.APPROVE_OPTION) {
            projectHandler.loadProject(fileChooser.getSelectedFile());
        }
    }
}

