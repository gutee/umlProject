package control.actions;

import control.ProjectHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 18:21
 */
public class GraphProjectAction extends AbstractAction {
    private ProjectHandler projectHandler;

    public GraphProjectAction(ProjectHandler projectHandler) {
        this.projectHandler = projectHandler;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        projectHandler.updateGraphPanel();
    }
}
