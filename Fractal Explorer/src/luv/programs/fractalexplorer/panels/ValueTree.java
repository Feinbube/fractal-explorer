package luv.programs.fractalexplorer.panels;

import luv.programs.fractalexplorer.panels.configurable.ConfigurablePanel;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import luv.programs.fractalexplorer.panels.configurable.ColorizerPanel;
import luv.programs.fractalexplorer.panels.configurable.ValueBagPanel;
import luv.values.Configurable;
import luv.values.colorizers.Colorizer;
import luv.values.mappers.ValueBag;

public class ValueTree extends RecursivelyRepaintingPanel implements ActionListener {

    MainPanel mainPanel;

    final ConfigurablePanel colorizerPanel;
    final ConfigurablePanel rootPanel;

    public ValueTree(MainPanel mainPanel, Colorizer colorizer, ValueBag root) {
        this.mainPanel = mainPanel;

        Panel contentPanel = new Panel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        contentPanel.add(new Label("Colorizer:"));
        colorizerPanel = new ColorizerPanel(this.mainPanel, null, colorizer);
        contentPanel.add(colorizerPanel);

        contentPanel.add(new Label(""));

        contentPanel.add(new Label("Value Generation:"));
        rootPanel = new ValueBagPanel(this.mainPanel, null, root);
        contentPanel.add(rootPanel);

        this.add(contentPanel);
    }

    void setColorizer(Colorizer colorizer) {
        if (colorizer != null) {
            colorizerPanel.setInstance(colorizer);
        }
    }

    void setRoot(Configurable root) {
        rootPanel.setInstance(root);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void forceRecursiveRepaint() {
        super.forceRecursiveRepaint();

        colorizerPanel.forceRecursiveRepaint();
        rootPanel.forceRecursiveRepaint();
    }

    Colorizer getColorizer() {
        return (Colorizer) colorizerPanel.getInstance();
    }
}
