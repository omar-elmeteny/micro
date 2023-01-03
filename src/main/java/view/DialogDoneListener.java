package view;

import simulator.SimulatorSettings;

@FunctionalInterface
public interface DialogDoneListener {
    void dialogDone(SimulatorSettings settings);
}
