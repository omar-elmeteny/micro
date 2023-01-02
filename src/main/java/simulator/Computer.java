package simulator;

public class Computer {

    private final CPU cpu;

    public CPU getCpu() {
        return cpu;
    }

    private final Memory memory;

    public Memory getMemory() {
        return memory;
    }

    public Computer(SimulatorSettings settings) {
        super();
        memory = new Memory(settings);
        cpu = new CPU(settings, this);
    }

}
