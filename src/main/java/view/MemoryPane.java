package view;

import javax.swing.JPanel;

import simulator.SimulatorSettings;

public class MemoryPane extends JPanel {

    private final SettingPane memorySizePane;

    public MemoryPane(SimulatorSettings settings) {

        super();
        memorySizePane = new SettingPane("Memory Size (KB)", "The size of the memory in Kibibytes", settings.getMemSizeInKB(), 1, 100);

        add(memorySizePane);
    }

    public int getMemorySize() throws SettingsException {
        return memorySizePane.getValue();
    }
}
