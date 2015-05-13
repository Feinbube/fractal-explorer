package luv.programs.fractalexplorer.panels.configurable;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import luv.programs.fractalexplorer.panels.MainPanel;
import luv.programs.fractalexplorer.panels.ConfigurableSelector;
import luv.values.Configurable;
import luv.values.generators.ValueGenerator;

public class ValueGeneratorPanel extends ConfigurablePanel {

    JButton addParentButton;
    JButton removeButton;
    JButton addSiblingButton;

    public ValueGeneratorPanel(MainPanel mainPanel, ConfigurablePanel parent, ValueGenerator instance) {
        super(mainPanel, parent, (Configurable) instance);
    }

    @Override
    protected boolean isSwitchable() {
        return true;
    }

    @Override
    protected JButton[] getButtons() {
        addParentButton = createButton("Add Parent");
        removeButton = createButton("Remove");
        addSiblingButton = createButton("Add Sibling");

        return parent.hasMultipleChildren()
                ? new JButton[]{addParentButton, removeButton, addSiblingButton}
                : new JButton[]{addParentButton, addSiblingButton};
    }

    @Override
    protected Configurable[] getConfigurables() {
        return null;
    }

    @Override
    protected void performSwitch() {
        parent.switchTo(this, new ConfigurableSelector(mainPanel.getValueGenerators()).getResult());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getSource().equals(addParentButton)) {
            parent.encapsulate(this, new ConfigurableSelector(mainPanel.getValueMappers()).getResult());
            mainPanel.forceRecursiveRepaint();
        }
        if (e.getSource().equals(removeButton)) {
            parent.removeChild(this);
            mainPanel.forceRecursiveRepaint();
        }
        if (e.getSource().equals(addSiblingButton)) {
            parent.addChild(new ConfigurableSelector(mainPanel.getValueGenerators()).getResult());
            mainPanel.forceRecursiveRepaint();
        }
    }
}
