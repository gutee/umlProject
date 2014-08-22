package gui.smallFrames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 12:03
 */
public class ErrorFrame {
    private JFrame frame;
    private JPanel labelPanel, OKButtonPanel;
    private JLabel label;
    private JButton OKButton;
    private final static Dimension FRAME_DIMENSION = new Dimension(600, 400);

    public ErrorFrame(String titleBar, String message) {
        Image temp;
        ImageIcon icon = new ImageIcon("src//gui//icons//ErrorFrameIcon.png");
        temp = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        icon = new ImageIcon(temp);
        OKButton = new JButton("Ok");
        OKButtonPanel = new JPanel();
        OKButtonPanel.add(OKButton);
        OKButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });
        label = new JLabel(message, icon, SwingConstants.CENTER);
        labelPanel = new JPanel();
        labelPanel.add(label);
        frame = new JFrame(titleBar);
        frame.setLayout(new BorderLayout());
        frame.setSize(FRAME_DIMENSION);
        frame.add(labelPanel, BorderLayout.CENTER);
        frame.add(OKButtonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
