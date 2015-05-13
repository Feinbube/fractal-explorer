package luv.programs.fractalexplorer.panels;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ToolBox extends RecursivelyRepaintingPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    MainPanel mainPanel;

    Label xLabel;
    Label yLabel;
    Label zLabel;

    JButton saveButton;
    JButton animationButton;
    JButton resetButton;
    JButton recordingButton;
    JButton openExampleButton;
    JButton loadExampleButton;
    JButton saveExampleButton;

    public ToolBox(MainPanel mainPanel) {

        this.mainPanel = mainPanel;

        this.setLayout(new GridLayout(1, 8, 10, 0));

        xLabel = new Label("" + mainPanel.getFractalX());
        this.add(xLabel);
        yLabel = new Label("" + mainPanel.getFractalY());
        this.add(yLabel);
        zLabel = new Label("" + mainPanel.getFractalZ());
        this.add(zLabel);

        resetButton = addButton("Reset View");
        saveButton = addButton("Save Picture");
        animationButton = addButton("Animation: OFF");
        recordingButton = addButton("Record Video");
        
        openExampleButton = addButton("Open Example");
        loadExampleButton = addButton("Load Example");
        saveExampleButton = addButton("Save Example");

        this.addKeyListener(mainPanel);

        this.setBorder(BorderFactory.createTitledBorder("Toolbox"));
    }

	private JButton addButton(final String label) {
		JButton b = new JButton(label);
        b.setFocusable(false);
        b.addActionListener(this);
        this.add(b);
        return b;
	}

    @Override
    protected void paintComponent(Graphics g) {
        xLabel.setText("X: " + mainPanel.getFractalX());
        yLabel.setText("Y: " + mainPanel.getFractalY());
        zLabel.setText("Z: " + mainPanel.getFractalZ());

        animationButton.setText(mainPanel.animationActive() ? "Animation: ON" : "Animation: OFF");
        recordingButton.setText(mainPanel.recordingActive() ? "Finish Recording" : "Record Video");

        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)) {
            mainPanel.saveImage();
        }

        else if (e.getSource().equals(animationButton)) {
            mainPanel.toogleAnimation();
        }

        else if (e.getSource().equals(resetButton)) {
            mainPanel.resetPosition();
        }

        else if (e.getSource().equals(recordingButton)) {
            mainPanel.toogleRecording();
        }

        else if (e.getSource().equals(openExampleButton)) {
            mainPanel.loadDefaultExamples();
        }
        
        else if (e.getSource().equals(loadExampleButton)) {
            mainPanel.loadExampleFromFile();
        }
        
        else if (e.getSource().equals(saveExampleButton)) {
            mainPanel.saveExampleToFile();
        }

        mainPanel.forceRecursiveRepaint();
    }
}
