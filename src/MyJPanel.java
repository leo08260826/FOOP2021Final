import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;

public class MyJPanel extends JPanel {
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static final long serialVersionUID = 1L;
    private Image image = toolkit.getImage("./src/images/bg001.png");

    public MyJPanel(Image image, int width, int height) {
        this.image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }
}