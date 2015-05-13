package luv.programs.fractalexplorer;

import luv.programs.fractalexplorer.panels.MainPanel;
import java.awt.BorderLayout;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;

public class MainWindow {

    public static void main(String[] args) {
        JFrame f = new JFrame("Fractal Explorer");

        ConfigManager configManager = new ConfigManager();
        f.addWindowListener(configManager);

        MainPanel panel = new MainPanel(f, Runtime.getRuntime().availableProcessors() - 1, configManager);
        configManager.setMainPanel(panel);

        f.getContentPane().add(panel, BorderLayout.CENTER);

        f.addKeyListener((KeyListener) panel);
        f.setFocusTraversalKeysEnabled(false);
        panel.setFocusTraversalKeysEnabled(false);

        f.addMouseListener((MouseListener) panel);
        f.addMouseMotionListener((MouseMotionListener) panel);
        f.addMouseWheelListener((MouseWheelListener) panel);
        f.addComponentListener((ComponentListener) panel);

        f.setBounds(configManager.getX(), configManager.getY(), configManager.getWidth(), configManager.getHeight());

        f.setVisible(true);
    }
}
