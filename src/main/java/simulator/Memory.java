package simulator;

import exceptions.SimulatorRuntimeException;

public class Memory {

    private final double[] dataMemory;

    public double[] getDataMemory() {
        return dataMemory;
    }

    private final int[] instructionMemory;

    public int[] getInstructionMemory() {
        return instructionMemory;
    }

    public Memory(SimulatorSettings settings) {
        super();
        dataMemory = new double[settings.getMemSizeInKB() * 1024];
        instructionMemory = new int[settings.getMemSizeInKB() * 1024];
    }

    public void storeMem(double value, int address) throws SimulatorRuntimeException {
        if(address < 0 || address > dataMemory.length - 1) {
            throw new SimulatorRuntimeException("Wrong address, address must be between 0 and " + (dataMemory.length - 1));
        }
        dataMemory[address] = value;
    }

    public double loadMem(int address) throws SimulatorRuntimeException {
        if(address < 0 || address > dataMemory.length - 1) {
            throw new SimulatorRuntimeException("Wrong address, address must be between 0 and " + (dataMemory.length - 1));
        }
        return dataMemory[address];
    }
}
