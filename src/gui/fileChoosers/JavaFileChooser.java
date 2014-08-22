package gui.fileChoosers;

import control.ProjectHandler;

import javax.swing.*;
import java.io.File;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 12:03
 */
public class JavaFileChooser {
    private JFileChooser fileChooser;

    public JavaFileChooser(ProjectHandler projectHandler) {
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                String fileType = Extensions.getExtension(f);
                if (f.isDirectory()) return true;
                if (fileType.equals(".java")) return true;
                else return false;
            }

            @Override
            public String getDescription() {
                return "*.java";
            }
        });
        fileChooser.setVisible(true);
        int choice = fileChooser.showOpenDialog(new JPanel());
        if (choice == JFileChooser.APPROVE_OPTION) {
            projectHandler.addClass(fileChooser.getSelectedFile());
        }
    }
}
