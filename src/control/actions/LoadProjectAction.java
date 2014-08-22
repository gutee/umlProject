package control.actions;

import control.ProjectHandler;
import gui.fileChoosers.SERFileChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 13:16
 */
public class LoadProjectAction extends AbstractAction {
    private ProjectHandler projectHandler;

    public LoadProjectAction(ProjectHandler projectHandler) {
        this.projectHandler = projectHandler;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new SERFileChooser(projectHandler);
    }
}
