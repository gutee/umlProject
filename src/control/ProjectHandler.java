package control;

import gui.MainFrame;
import model.UMLClass;
import model.UMLModel;

import java.io.File;
import java.util.List;

/**
 * User: Martin Gutierrez
 * Date: 30/06/12
 * Time: 22:23
 */
public class ProjectHandler {
    private MainFrame mainFrame;
    private UMLModel umlModel;

    public ProjectHandler(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void newProject(String name) {
        umlModel = new UMLModel(name);
        mainFrame.setNewProjectButtons();
        mainFrame.updateProjectLabel();
        mainFrame.updateList();
        mainFrame.updateGraphPanel(umlModel);
    }

    public void editProjectName(String name){
        umlModel.setName(name);
        mainFrame.updateProjectLabel();
    }

    public void addClass(File fileDirectory) {
        umlModel.addClassFromFile(fileDirectory);
        mainFrame.updateList();
        if (umlModel.isEmpty()) {
            mainFrame.setEmptyListButtons();
        } else {
            mainFrame.setEnableAllButtons();
        }
    }

    public void removeClass(String name) {
        umlModel.removeClass(name);
        mainFrame.updateList();
        if (umlModel.isEmpty()) {
            mainFrame.setEmptyListButtons();
            mainFrame.updateGraphPanel(umlModel);
        } else {
            mainFrame.setEnableAllButtons();
        }
        mainFrame.updateNumberOfClasses(umlModel.getModelSize());
    }

    public void writeXML(File directory) {
        umlModel.writeXML(directory);
    }

    public void readXML(File directory) {
        umlModel = UMLModel.readXML(directory);
        mainFrame.updateList();
        if (umlModel.isEmpty()) {
            mainFrame.setEmptyListButtons();
        } else {
            mainFrame.setEnableAllButtons();
        }
        mainFrame.updateProjectLabel();
        mainFrame.updateGraphPanel(umlModel);
    }

    public void saveProject(File directory) {
        umlModel.saveModelAsSer(directory);
    }

    public void loadProject(File directory) {
        umlModel = UMLModel.loadModelFromSer(directory);
        mainFrame.updateList();
        if (umlModel.isEmpty()) {
            mainFrame.setEmptyListButtons();
        } else {
            mainFrame.setEnableAllButtons();
        }
        mainFrame.updateProjectLabel();
        mainFrame.updateGraphPanel(umlModel);
    }

    public void generateJavaFiles(File directory) {
        umlModel.generateJavaFiles(directory);
    }

    public List<UMLClass> getClasses() {
        return umlModel.getUmlModel();
    }

    public Object[] getArrayClasses() {
        return umlModel.getArrayClasses();
    }

    public String getProjectName() {
        if (umlModel == null) {
            return " ";
        } else {
            return umlModel.getName();
        }
    }

    public void updateGraphPanel(){
        mainFrame.updateGraphPanel(umlModel);
    }
}