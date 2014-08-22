package gui;

import control.ProjectHandler;
import control.actions.*;
import gui.graphPanel.GraphPanel;
import model.UMLModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import com.apple.eawt.Application;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 12:03
 */
public class MainFrame {
    private JFrame mainFrame;
    private JButton addClassButton, graphButton, removeClassButton, generateJavaButton;
    private JMenuBar menuBar;
    private JList list;
    private JMenu fileMenu, projectMenu;
    private JMenuItem newProjectItem, saveProjectItem, loadProjectItem, saveXMLItem,
            loadXMLItem, addClassItem, graphItem, removeClassItem, generateJavaItem, editNameItem;
    private JScrollPane graphScrollPane;
    private JPanel buttonPanel, listPanel, referencePanel;
    private GraphPanel graphPanel;
    private JLabel projectName, extensionReference, attributeReference, publicReference,
            privateReference, packageReference, protectedReference;
    private ImageIcon newProjectIcon, saveIcon, loadIcon, xmlIcon,
            addClassIcon, removeClassIcon, graphIcon, generateJavaIcon, editNameIcon;
    private Image frameIcon;
    private ProjectHandler projectHandler;
    private final static Dimension DIMENSION = new Dimension(950, 700);
    private final static Dimension LIST_DIMENSION = new Dimension(225, 0);

    public static void main(String[] args) {
        new MainFrame();
    }

    public MainFrame() {
        projectHandler = new ProjectHandler(this);
        startMainFrame();
    }

    public void updateProjectLabel() {
        projectName.setText(projectHandler.getProjectName());
    }

    public void updateList() {
        list.setListData(projectHandler.getArrayClasses());
        list.updateUI();
    }

    public void updateNumberOfClasses(int numberOfClasses){
        graphPanel.setNumberofClasses(numberOfClasses);
    }

    public void updateGraphPanel(UMLModel umlModel){
        graphPanel.removeAll();
        graphPanel.replaceModel(umlModel.getUmlModel());
        graphPanel.setSize();
        graphPanel.repaint();
        graphPanel.updateUI();
    }

    public void setStartUpButtons() {
        saveProjectItem.setEnabled(false);
        saveXMLItem.setEnabled(false);
        addClassButton.setEnabled(false);
        addClassItem.setEnabled(false);
        removeClassButton.setEnabled(false);
        removeClassItem.setEnabled(false);
        graphButton.setEnabled(false);
        graphItem.setEnabled(false);
        generateJavaButton.setEnabled(false);
        generateJavaItem.setEnabled(false);
        editNameItem.setEnabled(false);
    }

    public void setEnableAllButtons() {
        saveProjectItem.setEnabled(true);
        saveXMLItem.setEnabled(true);
        addClassButton.setEnabled(true);
        addClassItem.setEnabled(true);
        removeClassButton.setEnabled(true);
        removeClassItem.setEnabled(true);
        graphButton.setEnabled(true);
        graphItem.setEnabled(true);
        generateJavaButton.setEnabled(true);
        generateJavaItem.setEnabled(true);
        editNameItem.setEnabled(true);
    }

    public void setNewProjectButtons() {
        saveProjectItem.setEnabled(true);
        saveXMLItem.setEnabled(true);
        addClassButton.setEnabled(true);
        addClassItem.setEnabled(true);
        editNameItem.setEnabled(true);
    }

    public void setEmptyListButtons() {
        removeClassButton.setEnabled(false);
        removeClassItem.setEnabled(false);
        graphButton.setEnabled(false);
        graphItem.setEnabled(false);
        generateJavaButton.setEnabled(false);
        generateJavaItem.setEnabled(false);
    }

    private void startIcons() {
        Image temp;
        newProjectIcon = new ImageIcon("src//gui//icons//NewProjectIcon.png");
        temp = newProjectIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        newProjectIcon = new ImageIcon(temp);
        saveIcon = new ImageIcon("src//gui//icons//SaveIcon.png");
        temp = saveIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        saveIcon = new ImageIcon(temp);
        loadIcon = new ImageIcon("src//gui//icons//LoadIcon.jpg");
        temp = loadIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        loadIcon = new ImageIcon(temp);
        xmlIcon = new ImageIcon("src//gui//icons//XMLIcon.png");
        temp = xmlIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        xmlIcon = new ImageIcon(temp);
        addClassIcon = new ImageIcon("src//gui//icons//AddClassIcon.png");
        temp = addClassIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        addClassIcon = new ImageIcon(temp);
        removeClassIcon = new ImageIcon("src//gui//icons//RemoveClassIcon.png");
        temp = removeClassIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        removeClassIcon = new ImageIcon(temp);
        graphIcon = new ImageIcon("src//gui//icons//GraphIcon.png");
        temp = graphIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        graphIcon = new ImageIcon(temp);
        generateJavaIcon = new ImageIcon("src//gui//icons//GenerateJavaIcon.png");
        temp = generateJavaIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        generateJavaIcon = new ImageIcon(temp);
        editNameIcon = new ImageIcon("src//gui//icons//EditNameIcon.png");
        temp = editNameIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        editNameIcon = new ImageIcon(temp);
        ImageIcon frameIcon2 = new ImageIcon("src//gui//icons//FrameIcon.png");
        frameIcon = frameIcon2.getImage();
    }

    private void startFileAccelerators() {
        newProjectItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        saveProjectItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        loadProjectItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        saveXMLItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        loadXMLItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
    }

    private void startProjectAccelerators() {
        addClassItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        removeClassItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        editNameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        graphItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        generateJavaItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, ActionEvent.CTRL_MASK));
    }

    private void startFileMenu() {
        startIcons();
        newProjectItem = new JMenuItem("New Project", newProjectIcon);
        newProjectItem.addActionListener(new NewProjectAction(projectHandler));
        saveProjectItem = new JMenuItem("Save Project", saveIcon);
        saveProjectItem.addActionListener(new SaveProjectAction(projectHandler));
        loadProjectItem = new JMenuItem("Load Project", loadIcon);
        loadProjectItem.addActionListener(new LoadProjectAction(projectHandler));
        saveXMLItem = new JMenuItem("Save Project as XML", xmlIcon);
        saveXMLItem.addActionListener(new SaveXMLAction(projectHandler));
        loadXMLItem = new JMenuItem("Load Project From XML", xmlIcon);
        loadXMLItem.addActionListener(new LoadXMLAction(projectHandler));
        fileMenu = new JMenu("File");
        startFileAccelerators();
        fileMenu.add(newProjectItem);
        fileMenu.add(saveProjectItem);
        fileMenu.add(loadProjectItem);
        fileMenu.addSeparator();
        fileMenu.add(saveXMLItem);
        fileMenu.add(loadXMLItem);
    }

    private void startProjectMenu() {
        addClassItem = new JMenuItem("Add Class", addClassIcon);
        addClassItem.addActionListener(new AddClassAction(projectHandler));
        removeClassItem = new JMenuItem("Remove Class", removeClassIcon);
        removeClassItem.addActionListener(new RemoveClassAction(projectHandler));
        editNameItem = new JMenuItem("Edit Project Name", editNameIcon);
        editNameItem.addActionListener(new EditNameAction(projectHandler));
        graphItem = new JMenuItem("Graph Project", graphIcon);
        graphItem.addActionListener(new GraphProjectAction(projectHandler));
        generateJavaItem = new JMenuItem("Generate Java Files", generateJavaIcon);
        generateJavaItem.addActionListener(new GenerateJavaAction(projectHandler));
        projectMenu = new JMenu("Project");
        startProjectAccelerators();
        projectMenu.add(addClassItem);
        projectMenu.add(removeClassItem);
        projectMenu.add(editNameItem);
        projectMenu.addSeparator();
        projectMenu.add(graphItem);
        projectMenu.add(generateJavaItem);
    }

    private JMenuBar startMenuBar() {
        menuBar = new JMenuBar();
        startFileMenu();
        startProjectMenu();
        menuBar.add(fileMenu);
        menuBar.add(projectMenu);
        return menuBar;
    }

    private JPanel startButtons() {
        buttonPanel = new JPanel(new GridLayout(1, 4));
        addClassButton = new JButton("Add Class", addClassIcon);
        addClassButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        addClassButton.setHorizontalTextPosition(AbstractButton.CENTER);
        addClassButton.setToolTipText("Add a new class to the Project (Ctrl + A)");
        addClassButton.addActionListener(new AddClassAction(projectHandler));
        removeClassButton = new JButton("Remove Class", removeClassIcon);
        removeClassButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        removeClassButton.setHorizontalTextPosition(AbstractButton.CENTER);
        removeClassButton.setToolTipText("Remove a selected class from the Project (Ctrl + R)");
        removeClassButton.addActionListener(new RemoveClassAction(projectHandler));
        graphButton = new JButton("Graph Project", graphIcon);
        graphButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        graphButton.setHorizontalTextPosition(AbstractButton.CENTER);
        graphButton.setToolTipText("Graph the classes in the Project (Ctrl + G)");
        graphButton.addActionListener(new GraphProjectAction(projectHandler));
        generateJavaButton = new JButton("Generate Java Files", generateJavaIcon);
        generateJavaButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        generateJavaButton.setHorizontalTextPosition(AbstractButton.CENTER);
        generateJavaButton.setToolTipText("Generate a .java file for each class (Ctrl + J)");
        generateJavaButton.addActionListener(new GenerateJavaAction(projectHandler));
        buttonPanel.add(addClassButton);
        buttonPanel.add(removeClassButton);
        buttonPanel.add(graphButton);
        buttonPanel.add(generateJavaButton);
        return buttonPanel;
    }

    private JPanel startReferencePanel(){
        extensionReference = new JLabel("\t\t\tRed Arrow: Extension Arrow");
        attributeReference = new JLabel("\t\t\tBlue Arrow: Attribute Arrow");
        publicReference = new JLabel("\t\t\t+: Public Visibility");
        privateReference = new JLabel("\t\t\t-: Private Visibility");
        packageReference = new JLabel("\t\t\t~: Package Visibility");
        protectedReference = new JLabel("\t\t\t#: Protected Visibility");
        extensionReference.setForeground(Color.RED);
        extensionReference.setFont(new Font("Serif", Font.ITALIC, 14));
        attributeReference.setForeground(Color.BLUE);
        attributeReference.setFont(new Font("Serif", Font.ITALIC, 14));
        publicReference.setFont(new Font("Serif", Font.ITALIC, 14));
        privateReference.setFont(new Font("Serif", Font.ITALIC, 14));
        packageReference.setFont(new Font("Serif", Font.ITALIC, 14));
        protectedReference.setFont(new Font("Serif", Font.ITALIC, 14));
        referencePanel = new JPanel(new GridLayout(6, 1));
        referencePanel.add(extensionReference);
        referencePanel.add(attributeReference);
        referencePanel.add(publicReference);
        referencePanel.add(privateReference);
        referencePanel.add(packageReference);
        referencePanel.add(protectedReference);
        return referencePanel;

    }

    private JPanel startListPanel() {
        listPanel = new JPanel(new BorderLayout());
        projectName = new JLabel(projectHandler.getProjectName());
        list = new JList();
        listPanel.setPreferredSize(LIST_DIMENSION);
        listPanel.add(projectName, BorderLayout.NORTH);
        listPanel.add(startReferencePanel(), BorderLayout.SOUTH);
        listPanel.add(list, BorderLayout.CENTER);
        listPanel.setBackground(new Color(9, 105, 96));
        list.setBackground(new Color(9, 105, 96));
        return listPanel;
    }

    private void startMainFrame() {
        mainFrame = new JFrame("UML Graphing Project");
        mainFrame.setPreferredSize(DIMENSION);
        mainFrame.setJMenuBar(startMenuBar());
        mainFrame.add(startButtons(), BorderLayout.NORTH);
        mainFrame.add(startListPanel(), BorderLayout.WEST);
        graphPanel = new GraphPanel();
        graphScrollPane = new JScrollPane(graphPanel);
        mainFrame.add(graphScrollPane);
        setStartUpButtons();
        Application.getApplication().setDockIconImage(frameIcon);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}