import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Queue;
import java.util.Scanner;

import exceptions.SimulatorRuntimeException;
import exceptions.SimulatorSyntaxException;
import simulator.CodeParser;
import simulator.Computer;
import simulator.Instruction;
import simulator.SimulatorSettings;

public class App {

    public static void main(String[] args) throws SimulatorSyntaxException, IOException, SimulatorRuntimeException {
        // SimulatorSettings settings = readSimulatorSettings();
        SimulatorSettings settings = getDefaultSettings();
        Computer computer = new Computer(settings);
        Queue<Instruction> instructions = CodeParser.parseCode(args[0]);
        computer.getCpu().setInstructionQueue(instructions);
        computer.getCpu().runProgram();
    }


    private static SimulatorSettings getDefaultSettings() {
        return new SimulatorSettings(2, 2, 10, 40, 2, 1, 2, 3, 2, 3, 4);
    }

    private static SimulatorSettings readSimulatorSettings() {
        Scanner sc = new Scanner(new FilterInputStream(System.in) {
            @Override
            public void close() throws IOException {

            }
        });
        try {
            System.out.println("Please enter the latency of addition: ");
            Integer addCycles = sc.nextInt();
            System.out.println("Please enter the latency of substraction: ");
            Integer subCycles = sc.nextInt();
            System.out.println("Please enter the latency of multiplication: ");
            Integer mulCycles = sc.nextInt();
            System.out.println("Please enter the latency of division: ");
            Integer divCycles = sc.nextInt();
            System.out.println("Please enter the latency of load: ");
            Integer loadCycles = sc.nextInt();
            System.out.println("Please enter the latency of store: ");
            Integer storeCycles = sc.nextInt();
            System.out.println("Please enter the sizey of memory in KB: ");
            Integer memSizeInKB = sc.nextInt();
            System.out.println("Please enter the size of add/sub station: ");
            Integer addSubStationSize = sc.nextInt();
            System.out.println("Please enter the size of mul/div station: ");
            Integer mulDivStationSize = sc.nextInt();
            System.out.println("Please enter the size of load station: ");
            Integer loadStationSize = sc.nextInt();
            System.out.println("Please enter the size of store station: ");
            Integer storeStationSize = sc.nextInt();
            SimulatorSettings settings = new SimulatorSettings(addCycles, subCycles, mulCycles, divCycles, loadCycles,
                    storeCycles, memSizeInKB, addSubStationSize, mulDivStationSize, loadStationSize, storeStationSize);
            return settings;

        } finally {
            sc.close();
        }
    }
}
