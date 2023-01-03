import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;
import java.awt.Desktop;

import exceptions.SimulatorRuntimeException;
import exceptions.SimulatorSyntaxException;
import simulator.CodeParser;
import simulator.Computer;
import simulator.Instruction;
import simulator.SimulatorSettings;
import view.DisplayProgram;
import view.SettingsWindow;

public class App {

    public static void main(String[] args) throws SimulatorSyntaxException, IOException, SimulatorRuntimeException {
        SettingsWindow window = new SettingsWindow(getDefaultSettings());
        window.addDialogDoneListener((settings) -> {
            try {
                Computer computer = new Computer(settings);
                Queue<Instruction> instructions = CodeParser.parseCode(args[0]);
                computer.getCpu().setInstructionQueue(instructions);
                computer.getCpu().runProgram();

                File file = new File("output/printings.html");

                BufferedWriter br = new BufferedWriter(new FileWriter(file));
                try {
                    br.write(
                            "<html><head><title>CPU Simulator</title><link href='printings.css' rel='stylesheet'/></head><body>");
                    for (String html : DisplayProgram.getCycleHtmls()) {
                        br.write(html);
                    }
                    br.write("</body></html>");

                } finally {
                    br.close();
                }
                Desktop.getDesktop().browse(file.toURI());
            } catch (IOException e) {

            } catch (SimulatorSyntaxException e) {
                e.printStackTrace();
            } catch (SimulatorRuntimeException e) {
                e.printStackTrace();
            }
        });
        // SimulatorSettings settings = readSimulatorSettings();

    }

    private static SimulatorSettings getDefaultSettings() {
        return new SimulatorSettings(4, 2, 6, 40, 2, 1, 2, 3, 2, 3, 4, 32);
    }
}
