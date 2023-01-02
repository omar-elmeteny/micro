import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Queue;
import java.util.Scanner;

import exceptions.SimulatorSyntaxException;
import simulator.CodeParser;
import simulator.Computer;
import simulator.Instruction;
import simulator.SimulatorSettings;

public class App {

    public static void main(String[] args) throws SimulatorSyntaxException, IOException {
        //SimulatorSettings settings = readSimulatorSettings();
        SimulatorSettings settings = getDefaultSettings();
        Computer computer = new Computer(settings);
        Queue<Instruction> instructions = CodeParser.parseCode(args[0]);
        computer.getCpu().setInstructionQueue(instructions);
    }

    private static SimulatorSettings getDefaultSettings() {
        return new SimulatorSettings(1, 2, 3, 5, 1, 1, 2, 3, 2, 4, 4);
    }

    private static SimulatorSettings readSimulatorSettings() {
        Scanner sc = new Scanner(new FilterInputStream(System.in) {
            @Override
            public void close() throws IOException {

            }
        });
        try {
            System.out.println("Please enter the latency of addition: ");
            int addCycles = sc.nextInt();
            System.out.println("Please enter the latency of substraction: ");
            int subCycles = sc.nextInt();
            System.out.println("Please enter the latency of multiplication: ");
            int mulCycles = sc.nextInt();
            System.out.println("Please enter the latency of division: ");
            int divCycles = sc.nextInt();
            System.out.println("Please enter the latency of load: ");
            int loadCycles = sc.nextInt();
            System.out.println("Please enter the latency of store: ");
            int storeCycles = sc.nextInt();
            System.out.println("Please enter the sizey of memory in KB: ");
            int memSizeInKB = sc.nextInt();
            System.out.println("Please enter the size of add/sub station: ");
            int addSubStationSize = sc.nextInt();
            System.out.println("Please enter the size of mul/div station: ");
            int mulDivStationSize = sc.nextInt();
            System.out.println("Please enter the size of load station: ");
            int loadStationSize = sc.nextInt();
            System.out.println("Please enter the size of store station: ");
            int storeStationSize = sc.nextInt();
            SimulatorSettings settings = new SimulatorSettings(addCycles, subCycles, mulCycles, divCycles, loadCycles,
                    storeCycles, memSizeInKB, addSubStationSize, mulDivStationSize, loadStationSize, storeStationSize);
            return settings;

        } finally {
            sc.close();
        }
    }
}
