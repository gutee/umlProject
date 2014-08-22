package control.actions;

import control.ProjectHandler;
import gui.smallFrames.NewProjectFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 13:15
 */
public class NewProjectAction extends AbstractAction {
    private ProjectHandler projectHandler;

    public NewProjectAction(ProjectHandler projectHandler) {
        this.projectHandler = projectHandler;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new NewProjectFrame(projectHandler);
    }
}
