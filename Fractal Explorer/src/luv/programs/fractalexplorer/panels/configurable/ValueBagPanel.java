package luv.programs.fractalexplorer.panels.configurable;

import javax.swing.JButton;
import luv.programs.fractalexplorer.panels.MainPanel;
import luv.values.Configurable;
import luv.values.mappers.ValueBag;

public class ValueBagPanel extends ConfigurablePanel {

    public ValueBagPanel(MainPanel mainPanel, ConfigurablePanel parent, ValueBag instance) {
        super(mainPanel, parent, (Configurable) instance);
    }

    @Override
    protected boolean isSwitchable() {
        return false;
    }

    @Override
    protected JButton[] getButtons() {
        return null;
    }

    @Override
    protected Configurable[] getConfigurables() {
        return ((ValueBag) instance).get().toArray(new Configurable[]{});
    }

    @Override
    protected void performSwitch() {
    }
}
