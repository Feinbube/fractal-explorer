package luv.programs.fractalexplorer.panels.configurable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import luv.programs.fractalexplorer.panels.MainPanel;
import luv.programs.fractalexplorer.panels.RecursivelyRepaintingPanel;
import luv.values.Configurable;
import luv.values.Property;
import luv.values.generators.ValueGenerator;
import luv.values.mappers.ValueMapper;

public abstract class ConfigurablePanel extends RecursivelyRepaintingPanel implements ActionListener {

    Configurable instance;
    ConfigurablePanel parent;
    MainPanel mainPanel;

    JButton switchButton;
    List<PropertyRow> propertyRows;

    List<ConfigurablePanel> childPanels;

    public ConfigurablePanel(MainPanel mainPanel, ConfigurablePanel parent, Configurable instance) {
        this.mainPanel = mainPanel;
        this.parent = parent;

        propertyRows = new ArrayList<>();
        childPanels = new ArrayList<>();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.setInstance(instance);
    }

    public void setInstance(Configurable instance) {
        if(instance == null) {
            return;
        }
        this.instance = instance;
        this.propertyRows.clear();
        this.removeAll();
        this.childPanels.clear();

        List<JButton> buttons = getSafeList(getButtons());
        if (this.isSwitchable()) {
            switchButton = createButton("Switch");
            buttons.add(0, switchButton);
        }

        if (buttons.size() > 0) {
            Panel header = new Panel(new GridLayout(2, (buttons.size() + 1) / 2 + 1));
            header.add(new Label(instance.getCategoryName()));
            for (int i = 0; i < (buttons.size() + 1) / 2; i++) {
                header.add(buttons.get(i));
            }
            header.add(new Label(instance.getName()));
            for (int i = (buttons.size() + 1) / 2; i < buttons.size(); i++) {
                header.add(buttons.get(i));
            }
            this.add(header);
        }

        Property[] properties = instance.getProperties();
        if (properties.length > 0) {
            Panel propertyPanel = new Panel(new GridBagLayout());
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridy = 0;
            for (Property property : properties) {
                propertyRows.add(new PropertyRow(mainPanel, instance, property, propertyPanel, gridBagConstraints));
            }
            this.add(propertyPanel);
        }

        for (Configurable childConfigurable : getSafeList(getConfigurables())) {
            this.add(new Label(""));
            Panel childPanel = new Panel();
            childPanel.setLayout(new BoxLayout(childPanel, BoxLayout.X_AXIS));
            childPanel.add(new Label(" "));
            ConfigurablePanel childConfigurablePanel = newChildPanel(childConfigurable);
            childPanel.add(childConfigurablePanel);
            childPanels.add(childConfigurablePanel);

            this.add(childPanel);
        }
    }

    public Configurable getInstance() {
        return instance;
    }

    protected abstract boolean isSwitchable();

    protected abstract JButton[] getButtons();

    protected abstract Configurable[] getConfigurables();

    protected abstract void performSwitch();

    private <T> List<T> getSafeList(T[] objects) {
        List<T> result = new ArrayList<>();
        if (objects != null) {
            result.addAll(Arrays.asList(objects));
        }
        return result;
    }

    protected ConfigurablePanel newChildPanel(Configurable configurable) {
        if (configurable instanceof ValueMapper) {
            return new ValueMapperPanel(mainPanel, this, (ValueMapper) configurable);
        } else if (configurable instanceof ValueGenerator) {
            return new ValueGeneratorPanel(mainPanel, this, (ValueGenerator) configurable);
        }

        throw new RuntimeException("No suitable panel found for " + configurable);
    }

    public JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.addActionListener(this);
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (PropertyRow propertyRow : propertyRows) {
            propertyRow.update();
        }

        super.paintComponent(g);
    }

    @Override
    public void forceRecursiveRepaint() {
        super.forceRecursiveRepaint();

        for (ConfigurablePanel child : childPanels) {
            child.forceRecursiveRepaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(switchButton)) {
            performSwitch();
            mainPanel.forceRecursiveRepaint();
        }
    }

    protected void switchTo(ConfigurablePanel child, Configurable newInstance) {
        if (newInstance == null) {
            return;
        }
        
        ((ValueMapper) instance).exchange(childPanels.indexOf(child), ((ValueGenerator) newInstance));
        setInstance(instance);
    }

    protected boolean hasMultipleChildren() {
        return instance instanceof ValueMapper && ((ValueMapper) instance).size() > 1;
    }

    protected void removeChild(ConfigurablePanel child) {
        ((ValueMapper) instance).remove(childPanels.indexOf(child));
        setInstance(instance);
    }

    protected void addChild(Configurable newInstance) {
        if (newInstance == null) {
            return;
        }
        ((ValueMapper) instance).add((ValueGenerator) newInstance);
        setInstance(instance);
    }

    protected void encapsulate(ConfigurablePanel child, Configurable newInstance) {
        if (newInstance == null) {
            return;
        }
        ValueGenerator oldValueGenerator = ((ValueMapper) instance).get(childPanels.indexOf(child));
        ((ValueMapper) newInstance).add(oldValueGenerator);
        ((ValueMapper) instance).replace(childPanels.indexOf(child), (ValueGenerator) newInstance);
        setInstance(instance);
    }
}
