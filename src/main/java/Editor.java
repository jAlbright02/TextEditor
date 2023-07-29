import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Editor extends JFrame implements ActionListener {

    JTextArea textArea;
    JScrollPane scrollPane;
    JSpinner fontSizeSpinner;
    JLabel fontLabel;
    JComboBox fontBox;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem openItem;
    JMenuItem saveItem;
    JMenuItem exitItem;

    //Constructor for GUI
    Editor() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Text Editor");
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());this.setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 22));

        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(450, 450));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        fontLabel = new JLabel("Font size: ");

        fontSizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(new Dimension(50, 25));
        fontSizeSpinner.setValue(22);
        fontSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, (int) fontSizeSpinner.getValue()));

            }
        });

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontBox = new JComboBox(fonts);
        fontBox.addActionListener(this);
        fontBox.setSelectedItem("SansSerif");

        //Menu Bar -------------------

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        //------------------------ Menu

        this.setJMenuBar(menuBar);
        this.add(fontBox);
        this.add(fontLabel);
        this.add(fontSizeSpinner);
        this.add(scrollPane);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fontBox) {
            textArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));
        }

        if (e.getSource() == openItem) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("C:\\Users\\user\\Documents\\TextEditor\\"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            fileChooser.setFileFilter(filter);

            int usrResponse = fileChooser.showOpenDialog(null);
            if (usrResponse == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                Scanner fileIn = null;

                try {
                    fileIn = new Scanner(file);
                    if (file.isFile()) {
                        while (fileIn.hasNextLine()) {
                            String line = fileIn.nextLine() + "\n";
                            textArea.append(line);
                        }
                    }

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } finally {
                    fileIn.close();
                }
            }

        }

        if (e.getSource() == saveItem) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("C:\\Users\\user\\Documents\\TextEditor\\"));

            int usrResponse = fileChooser.showSaveDialog(null);
            if (usrResponse == JFileChooser.APPROVE_OPTION) {
                File file;
                PrintWriter fileOut = null;

                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    fileOut = new PrintWriter(file);
                    fileOut.println(textArea.getText());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } finally {
                    fileOut.close();
                }
            }
        }

        if (e.getSource() == exitItem) {
            System.exit(0);
        }

    }
}