package control.actions;

import control.ProjectHandler;
import gui.smallFrames.EditNameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: Martin Gutierrez
 * Date: 04/07/12
 * Time: 23:55
 */
public class EditNameAction extends AbstractAction {
    private ProjectHandler projectHandler;

    public EditNameAction(ProjectHandler projectHandler){
        this.projectHandler = projectHandler;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new EditNameFrame(projectHandler);
    }
}
