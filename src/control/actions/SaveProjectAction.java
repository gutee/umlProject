package control.actions;

import control.ProjectHandler;
import gui.fileChoosers.SaveSERFileChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 13:15
 */
public class SaveProjectAction extends AbstractAction {
    private ProjectHandler projectHandler;

    public SaveProjectAction(ProjectHandler projectHandler) {
        this.projectHandler = projectHandler;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new SaveSERFileChooser(projectHandler);
    }
}
