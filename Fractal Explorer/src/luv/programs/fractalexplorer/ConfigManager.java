package luv.programs.fractalexplorer;

import luv.programs.fractalexplorer.panels.MainPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager extends WindowAdapter {

    MainPanel panel;
    Properties properties;

    public ConfigManager() {
        File f = new File("fractals.config");
        properties = new Properties();
        if (f.exists()) {
            try {
                properties.load(new FileInputStream("fractals.config"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                properties = new Properties();
            } catch (IOException e) {
                e.printStackTrace();
                properties = new Properties();
            }
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        saveProperties(e);
        System.exit(0);
    }

    public void setMainPanel(MainPanel panel) {
        this.panel = panel;
    }

    public int getX() {
        return new Integer(properties.getProperty("x", "0"));
    }

    public int getY() {
        return new Integer(properties.getProperty("y", "0"));
    }

    public int getWidth() {
        return new Integer(properties.getProperty("width", "800"));
    }

    public int getHeight() {
        return new Integer(properties.getProperty("height", "480"));
    }

    public int getSelectedColorizer() {
        return new Integer(properties.getProperty("colorizer", "0"));
    }

    public int getSelectedFractal() {
        int result = new Integer(properties.getProperty("fractal", "0"));
        return result != -1 ? result : 0;
    }

    public double getFractalX() {
        return new Double(properties.getProperty("fractalx", "0.0"));
    }

    public double getFractalY() {
        return new Double(properties.getProperty("fractaly", "0.0"));
    }

    public double getFractalZ() {
        return new Double(properties.getProperty("fractalz", "0.01"));
    }

    protected void saveProperties(WindowEvent e) {
        Properties properties = new Properties();

        properties.setProperty("x", "" + e.getWindow().getX());
        properties.setProperty("y", "" + e.getWindow().getY());
        properties.setProperty("width", "" + e.getWindow().getSize().width);
        properties.setProperty("height", "" + e.getWindow().getSize().height);
        properties.setProperty("colorizer", "" + panel.getColorizerId());
        properties.setProperty("fractal", "" + panel.getFractalId());
        properties.setProperty("fractalx", "" + panel.getFractalX());
        properties.setProperty("fractaly", "" + panel.getFractalY());
        properties.setProperty("fractalz", "" + panel.getFractalZ());

        try {
            properties.store(new FileOutputStream("fractals.config"), "fractals last session config");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
