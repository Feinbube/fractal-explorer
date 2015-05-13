package luv.programs.fractalexplorer.panels;

import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import luv.values.Configurable;

public class ConfigurableSelector extends JDialog implements ActionListener {

    Map<JButton, Configurable> buttonConfigurableMap = new HashMap<>();
    Configurable result = null;

    public ConfigurableSelector(List<Configurable> configurables) {
        this.setModal(true);

        Panel contentPanel = new Panel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        for (String category : Configurable.getCategories(configurables)) {
            contentPanel.add(new Label(category + ":"));
            List<Configurable> currentConfigurables = Configurable.getWithCategory(configurables, category);

            Panel buttons = new Panel();
            buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
            for (int i = 0; i < currentConfigurables.size(); i++) {
                JButton button = new JButton(getButtonText(currentConfigurables, i));
                button.addActionListener(this);
                buttons.add(button);
                button.setFocusable(false);
                buttonConfigurableMap.put(button, currentConfigurables.get(i));
                if (newButtonRowRequired(i)) {
                    contentPanel.add(buttons);
                    buttons = new Panel();
                    buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
                }
            }
            contentPanel.add(buttons);
        }

        this.add(contentPanel);
        this.pack();
    }

    protected boolean newButtonRowRequired(int i) {
        return i % 5 == 4;
    }

    protected String getButtonText(List<Configurable> configurables, int i) {
        return configurables.get(i).getName();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        result = this.buttonConfigurableMap.get(e.getSource());
        this.setVisible(false);
    }

    public Configurable getResult() {
        this.setVisible(true);
        return result;
    }
}
