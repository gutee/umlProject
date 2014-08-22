package control.actions;

import control.ProjectHandler;
import gui.fileChoosers.XMLFileChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 13:16
 */
public class LoadXMLAction extends AbstractAction {
    private ProjectHandler projectHandler;

    public LoadXMLAction(ProjectHandler projectHandler) {
        this.projectHandler = projectHandler;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new XMLFileChooser(projectHandler);
    }
}
