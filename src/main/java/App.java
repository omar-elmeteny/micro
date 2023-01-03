import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Queue;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

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
                DisplayProgram.getInstance().setProgramName(args[0]);
                DisplayProgram.getInstance().setProgramListing(new String(Files.readAllBytes(Paths.get(args[0])), StandardCharsets.UTF_8));
                Queue<Instruction> instructions = CodeParser.parseCode(args[0]);
                computer.getCpu().setInstructionQueue(instructions);
                computer.getCpu().runProgram();

                File file = new File("output/printings.html");

                BufferedWriter br = new BufferedWriter(new FileWriter(file));
                try {
                    MustacheFactory mf = new DefaultMustacheFactory();
                    Mustache m = mf.compile("templates/output.mustache");
                    m.execute(br, DisplayProgram.getInstance()).flush();

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
