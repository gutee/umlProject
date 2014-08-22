package control.actions;

import control.ProjectHandler;
import gui.fileChoosers.JavaFileChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 13:31
 */
public class AddClassAction extends AbstractAction {
    private ProjectHandler projectHandler;

    public AddClassAction(ProjectHandler projectHandler) {
        this.projectHandler = projectHandler;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new JavaFileChooser(projectHandler);
    }
}