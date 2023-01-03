package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import simulator.SimulatorSettings;

public class CyclesPane extends JPanel {

    private final SettingPane addCyclesPane;
    private final SettingPane subCyclesPane;
    private final SettingPane mulCylesPane;
    private final SettingPane divCylesPane;
    private final SettingPane loadCylesPane;
    private final SettingPane storeCylesPane;


    public CyclesPane(SimulatorSettings settings) {
        super();

        addCyclesPane = new SettingPane("ADD.D Instructions Cycles", "The number of CPU cycles to execute the ADD.D instruction", settings.getAddCycles(), 1, 10000);
        subCyclesPane = new SettingPane("SUB.D Instructions Cycles", "The number of CPU cycles to execute the SUB.D instruction", settings.getSubCycles(), 1, 10000);
        mulCylesPane = new SettingPane("MUL.D Instructions Cycles", "The number of CPU cycles to execute the MUL.D instruction", settings.getMulCycles(), 1, 10000);
        divCylesPane = new SettingPane("DIV.D Instructions Cycles", "The number of CPU cycles to execute the DIV.D instruction", settings.getDivCycles(), 1, 10000);
        loadCylesPane = new SettingPane("L.D Instructions Cycles", "The number of CPU cycles to execute the L.D instruction", settings.getLoadCycles(), 1, 10000);
        storeCylesPane = new SettingPane("S.D Instructions Cycles", "The number of CPU cycles to execute the S.D instruction", settings.getStoreCycles(), 1, 10000);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(addCyclesPane);
        add(subCyclesPane);
        add(mulCylesPane);
        add(divCylesPane);
        add(loadCylesPane);
        add(storeCylesPane);
    }


    public int getAddCycles() throws SettingsException {
        return addCyclesPane.getValue();
    }

    public int getSubCycles() throws SettingsException {
        return subCyclesPane.getValue();
    }


    public int getMulCycles() throws SettingsException {
        return mulCylesPane.getValue();
    }

    public int getDivCycles() throws SettingsException {
        return divCylesPane.getValue();
    }

    public int getLoadCycles() throws SettingsException {
        return loadCylesPane.getValue();
    }

    public int getStoreCycles() throws SettingsException {
        return storeCylesPane.getValue();
    }
}
