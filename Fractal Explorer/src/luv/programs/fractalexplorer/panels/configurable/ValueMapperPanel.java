package luv.programs.fractalexplorer.panels.configurable;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import luv.programs.fractalexplorer.panels.MainPanel;
import luv.programs.fractalexplorer.panels.ConfigurableSelector;
import luv.values.Configurable;
import luv.values.mappers.ValueMapper;

public class ValueMapperPanel extends ConfigurablePanel {

    JButton addParentButton;
    JButton removeButton;
    JButton addSiblingButton;

    public ValueMapperPanel(MainPanel mainPanel, ConfigurablePanel parent, ValueMapper instance) {
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

        return new JButton[]{addParentButton, removeButton, addSiblingButton};
    }

    @Override
    protected Configurable[] getConfigurables() {
        return ((ValueMapper) instance).get().toArray(new Configurable[]{});
    }

    @Override
    protected void performSwitch() {
        parent.switchTo(this, new ConfigurableSelector(mainPanel.getValueMappers()).getResult());
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
