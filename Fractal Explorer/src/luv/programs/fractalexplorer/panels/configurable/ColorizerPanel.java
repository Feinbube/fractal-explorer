package luv.programs.fractalexplorer.panels.configurable;

import javax.swing.JButton;
import luv.programs.fractalexplorer.panels.MainPanel;
import luv.programs.fractalexplorer.panels.ConfigurableSelector;
import luv.values.Configurable;
import luv.values.colorizers.Colorizer;

public class ColorizerPanel extends ConfigurablePanel {

    public ColorizerPanel(MainPanel mainPanel, ConfigurablePanel parent, Colorizer instance) {
        super(mainPanel, parent, (Configurable) instance);
    }

    @Override
    protected boolean isSwitchable() {
        return true;
    }

    @Override
    protected JButton[] getButtons() {
        return null;
    }

    @Override
    protected Configurable[] getConfigurables() {
        return null;
    }

    @Override
    protected void performSwitch() {
        this.setInstance(new ConfigurableSelector(mainPanel.getColorizers()).getResult());
    }
}
