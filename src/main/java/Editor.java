import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Editor extends JFrame implements ActionListener {

    JTextArea textArea;

    //Constructor for GUI
    Editor() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Text Editor");
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());this.setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(450, 450));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 22));

        this.add(textArea); //adding to scroll pane later
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}