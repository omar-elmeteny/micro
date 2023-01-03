package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import simulator.SimulatorSettings;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.event.*;

public class SettingsWindow extends JDialog {

        private final ArrayList<DialogDoneListener> listeners = new ArrayList<>();
        private final CyclesPane cyclesPane;
        private final CPUPane cpuPane;
        private final MemoryPane memoryPane;

        public SettingsWindow(SimulatorSettings settings) {
            super();
            this.setTitle("Please enter the settings for the simulation.");
            this.setSize(600, 400);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.setResizable(false);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            JTabbedPane tabbedPane = new JTabbedPane();
            cyclesPane = new CyclesPane(settings);
            memoryPane = new MemoryPane(settings);
            cpuPane = new CPUPane(settings);

            tabbedPane.addTab("Clock Cycles", new ImageIcon("images/cpu_clock.png", "Clock cycles"), cyclesPane, "Set the clock cycles of various instructions.");
            tabbedPane.addTab("RAM", new ImageIcon("images/ram.png", "RAM"), memoryPane, "Set the size of the computer's RAM.");
            tabbedPane.addTab("CPU", new ImageIcon("images/cpu.png", "CPU"), cpuPane, "Set the number of registers and the sizes of different stations.");

            add(tabbedPane, BorderLayout.CENTER);

            JButton startButton = new JButton("Start Simulation ");
            startButton.addActionListener((ev) -> {
                try {
                    SimulatorSettings newSettings = new SimulatorSettings(

                            cyclesPane.getAddCycles(),
                            cyclesPane.getSubCycles(),
                            cyclesPane.getMulCycles(),
                            cyclesPane.getDivCycles(),
                            cyclesPane.getLoadCycles(),
                            cyclesPane.getStoreCycles(),

                            memoryPane.getMemorySize(),
                            cpuPane.getAddSubStationSize(),
                            cpuPane.getMulDivStationSize(),
                            cpuPane.getLoadStationSize(),
                            cpuPane.getStoreStationSize(),

                            cpuPane.getNumberOfRegisters()


                    );
                    fireDialogDone(newSettings);
                    this.dispose();
                } catch (SettingsException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            this.add(startButton, BorderLayout.SOUTH);
            this.setVisible(true);

            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    System.exit(0);
                }
            });
        }

        public void addDialogDoneListener(DialogDoneListener listener) {
            listeners.add(listener);
        }

        public void removeDialogDoneListener(DialogDoneListener listener) {
            listeners.remove(listener);
        }

        private void fireDialogDone(SimulatorSettings settings) {
            for (DialogDoneListener listener : listeners) {
                listener.dialogDone(settings);
            }
        }
}
