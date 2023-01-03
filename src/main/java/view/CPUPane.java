package view;

import javax.swing.JPanel;

import simulator.SimulatorSettings;

public class CPUPane extends JPanel {

    private final SettingPane addSubStationSizePane;
    private final SettingPane mulDivStationSizePane;
    private final SettingPane loadStationSizePane;
    private final SettingPane storeStationSizePane;
    private final SettingPane numberOfRegistersPane;

    public CPUPane(SimulatorSettings settings) {
        super();

        addSubStationSizePane = new SettingPane("ADD/SUB Station Size", "The number of entries in ADD/SUB station", settings.getAddSubStationSize(), 1, 10000);
        mulDivStationSizePane = new SettingPane("MUL/DIV Station Size", "The number of entries in MUL/DIV station", settings.getMulDivStationSize(), 1, 10000);
        loadStationSizePane = new SettingPane("LOAD Station Size", "The number of entries in Load station", settings.getLoadStationSize(), 1, 10000);
        storeStationSizePane = new SettingPane("STORE Station Size", "The number of entries in Store station", settings.getStoreStationSize(), 1, 10000);
        numberOfRegistersPane = new SettingPane("Number of Registers", "The number of registers in the CPU", settings.getNumberOfRegisters(), 1, 10000);

        add(addSubStationSizePane);
        add(mulDivStationSizePane);
        add(loadStationSizePane);
        add(storeStationSizePane);
        add(numberOfRegistersPane);
    }

    public int getAddSubStationSize() throws SettingsException {
        return addSubStationSizePane.getValue();
    }

    public int getMulDivStationSize() throws SettingsException {
        return mulDivStationSizePane.getValue();
    }

    public int getLoadStationSize() throws SettingsException {
        return loadStationSizePane.getValue();
    }

    public int getStoreStationSize() throws SettingsException {
        return storeStationSizePane.getValue();
    }

    public int getNumberOfRegisters() throws SettingsException {
        return numberOfRegistersPane.getValue();
    }
}
