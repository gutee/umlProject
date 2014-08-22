package control.actions;

import control.ProjectHandler;
import gui.fileChoosers.SaveJavaFileChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 18:22
 */
public class GenerateJavaAction extends AbstractAction {
    private ProjectHandler projectHandler;

    public GenerateJavaAction(ProjectHandler projectHandler) {
        this.projectHandler = projectHandler;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new SaveJavaFileChooser(projectHandler);
    }
}
