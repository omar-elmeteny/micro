package simulator;

import exceptions.SimulatorRuntimeException;

public class Memory {

    private final Double[] dataMemory;

    public Double[] getDataMemory() {
        return dataMemory;
    }

    private final int[] instructionMemory;

    public int[] getInstructionMemory() {
        return instructionMemory;
    }

    public Memory(SimulatorSettings settings) {
        super();
        dataMemory = new Double[settings.getMemSizeInKB() * 1024];
        for(int i = 0;i < dataMemory.length;i++){
            dataMemory[i] = 100 * Math.random() + 1; 
        }
        instructionMemory = new int[settings.getMemSizeInKB() * 1024];
    }

    public void storeMem(Double value, int address) throws SimulatorRuntimeException {
        if(address < 0 || address > dataMemory.length - 1) {
            throw new SimulatorRuntimeException("Wrong address, address must be between 0 and " + (dataMemory.length - 1));
        }
        dataMemory[address] = value;
    }

    public Double loadMem(int address) throws SimulatorRuntimeException {
        if(address < 0 || address > dataMemory.length - 1) {
            throw new SimulatorRuntimeException("Wrong address, address must be between 0 and " + (dataMemory.length - 1));
        }
        return dataMemory[address];
    }
}
