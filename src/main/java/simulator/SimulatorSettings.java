package simulator;

public class SimulatorSettings {

    private final int addCycles;

    public int getAddCycles() {
        return addCycles;
    }

    private final int subCycles;

    public int getSubCycles() {
        return subCycles;
    }

    private final int mulCycles;

    public int getMulCycles() {
        return mulCycles;
    }

    private final int divCycles;

    public int getDivCycles() {
        return divCycles;
    }

    private final int loadCycles;

    public int getLoadCycles() {
        return loadCycles;
    }

    private final int storeCycles;

    public int getStoreCycles() {
        return storeCycles;
    }

    private final int memSizeInKB;

    public int getMemSizeInKB() {
        return memSizeInKB;
    }

    private final int addSubStationSize;

    public int getAddSubStationSize() {
        return addSubStationSize;
    }

    private final int mulDivStationSize;

    public int getMulDivStationSize() {
        return mulDivStationSize;
    }

    private final int loadStationSize;

    public int getLoadStationSize() {
        return loadStationSize;
    }

    private final int storeStationSize;

    public int getStoreStationSize() {
        return storeStationSize;
    }

    private final int numberOfRegisters;

    public int getNumberOfRegisters() {
        return numberOfRegisters;
    }

    public SimulatorSettings(int addCycles, int subCycles, int mulCycles, int divCycles, int loadCycles,
            int storeCycles, int memSizeInKB, int addSubStationSize, int mulDivStationSize, int loadStationSize,
            int storeStationSize, int numberOfRegisters) {
        super();
        this.addCycles = addCycles;
        this.subCycles = subCycles;
        this.mulCycles = mulCycles;
        this.divCycles = divCycles;
        this.loadCycles = loadCycles;
        this.storeCycles = storeCycles;
        this.memSizeInKB = memSizeInKB;
        this.addSubStationSize = addSubStationSize;
        this.mulDivStationSize = mulDivStationSize;
        this.loadStationSize = loadStationSize;
        this.storeStationSize = storeStationSize;
        this.numberOfRegisters = numberOfRegisters;
    }

}
