package luv.programs.fractalexplorer.panels.configurable;

import java.awt.GridBagConstraints;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import luv.programs.fractalexplorer.panels.MainPanel;
import luv.values.Configurable;
import luv.values.Property;

public class PropertyRow implements ChangeListener, ActionListener {

    MainPanel mainPanel;

    Configurable instance;
    String propertyName;

    Label valueDisplay;
    JSlider valueSetter;
    JCheckBox animateCheckBox;

    public PropertyRow(MainPanel mainPanel, Configurable instance, Property property, Panel propertyPanel, GridBagConstraints gridBagConstraints) {
        this.mainPanel = mainPanel;
        this.instance = instance;
        this.propertyName = property.name;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        propertyPanel.add(new Label(property.name + ":"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        valueDisplay = new Label("" + property.value);
        propertyPanel.add(valueDisplay, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        animateCheckBox = new JCheckBox("animate", false);
        animateCheckBox.setFocusable(false);
        animateCheckBox.addActionListener(this);
        propertyPanel.add(animateCheckBox, gridBagConstraints);

        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        valueSetter = new JSlider(JSlider.HORIZONTAL, (int) (property.minValue * 1000), (int) (property.maxValue * 1000), (int) (property.value * 1000));
        valueSetter.setFocusable(false);
        valueSetter.addChangeListener(this);
        propertyPanel.add(valueSetter, gridBagConstraints);

        gridBagConstraints.gridy++;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource().equals(valueSetter)) {
            instance.setProperty(propertyName, ((JSlider) e.getSource()).getValue() / (float) 1000);
            mainPanel.forceRecursiveRepaint();
        }
    }

    void update() {
        valueDisplay.setText("" + instance.getPropertyValue(propertyName));
        valueSetter.setValue((int) (1000 * instance.getPropertyValue(propertyName)));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(animateCheckBox)) {
            mainPanel.toogleAnimator(instance, propertyName);
        }
    }
}
