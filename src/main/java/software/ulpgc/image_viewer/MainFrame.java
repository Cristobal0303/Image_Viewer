package software.ulpgc.image_viewer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {

    private ImageDisplay imageDisplay;
    private final Map<String, Command> commands;

    public MainFrame() {

        this.commands = new HashMap<>();

        setTitle("Image Viewer");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(createImageDisplay());
        add(createToolbar(), BorderLayout.SOUTH);

    }

    private Component createToolbar() {

        JPanel panel = new JPanel();
        panel.add(createButton("<"));
        panel.add(createButton(">"));

        return panel;

    }

    private Component createButton(String label) {

        JButton button = new JButton(label);
        button.addActionListener(e -> {

            try {

                commands.get(label).execute();

            } catch (IOException ex) {

                throw new RuntimeException(ex);

            }
        };

        return button;

    }

    private Component createImageDisplay() {

        SwingImageDisplay display = new SwingImageDisplay();
        this.imageDisplay = display;

        return display;

    }

    public void add(String name, Command command) {

        commands.put(name, command);

    }

    public ImageDisplay imageDisplay() {

        return imageDisplay;

    }
}
