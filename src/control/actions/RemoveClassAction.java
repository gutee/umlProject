package control.actions;

import control.ProjectHandler;
import gui.smallFrames.RemoveClassFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 13:34
 */
public class RemoveClassAction extends AbstractAction {
    private ProjectHandler projectHandler;

    public RemoveClassAction(ProjectHandler projectHandler) {
        this.projectHandler = projectHandler;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new RemoveClassFrame(projectHandler);
    }
}
